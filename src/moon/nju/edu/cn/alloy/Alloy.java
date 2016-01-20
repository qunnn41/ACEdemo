package moon.nju.edu.cn.alloy;

import java.util.LinkedList;
import java.util.List;

import kodkod.ast.Decls;
import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.ast.Relation;
import kodkod.ast.Variable;
import kodkod.engine.Solution;
import kodkod.engine.Solver;
import kodkod.engine.satlab.SATFactory;
import kodkod.instance.Bounds;
import kodkod.instance.TupleFactory;
import kodkod.instance.Universe;

public class Alloy {
	//sigs
	private Relation Time, Software, Server;
	private Relation softApache, softWeb, softDB, softPHP;
	
	//fields
	private Relation tfirst, tlast, tick;
	private Relation installOn, dependOn, connectTo;
	
	
	/**
	 * initialize signature and fields
	 */
	public Alloy() {
		Time = Relation.unary("Time");
		tfirst = Relation.unary("first");
		tlast = Relation.unary("last");
		tick = Relation.binary("tick");
		Server = Relation.unary("Server");
		Software = Relation.unary("Software");
		
		softApache = Relation.unary("Apache");
		softWeb = Relation.unary("Web");
		softDB = Relation.unary("DB");
		softPHP = Relation.unary("PHP");
		
		installOn = Relation.ternary("installOn");
		dependOn = Relation.binary("dependOn");
		connectTo = Relation.binary("connectTo");
	}
	
	private Expression next(Expression e) {
		return e.join(tick);
	}
	
	private Expression prev(Expression e) {
		return tick.join(e);
	}
	
	/**
	 * Returns the constraints implicit in signature and field declarations.
	 * open util/ordering[Tick]
	 * sig Tick {}
	 * sig Server {}
	 * abstract sig Software {
	 *   installOn: Tick -> Server,
	 *   dependOn: set Software,
	 *   connectTo: set Software
	 * }
	 * one sig DB, Apache, PHP, Web extends Software {}
	 * @return
	 */
	private Formula declarations() {
		final Formula oneSig = Formula.and(softApache.one(), softDB.one(), softPHP.one(), softWeb.one());
		//DB + PHP + Apache + Web in Software
		final Formula softSub = softApache.union(softDB).union(softPHP).union(softWeb).eq(Software);
		//no DB & PHP & Apache & Web
		final Formula noDisj = softApache.intersection(softDB).intersection(softPHP).intersection(softWeb).no();
		//fields about installOn, dependOn, connectTo
		final Formula softInstallServer = installOn.in(Software.product(Time).product(Server));
		final Formula softDepSoft = dependOn.in(Software.product(Software));
		final Formula softConnSoft = connectTo.in(Software.product(Software));
		//tick is a total ordering on time
		final Formula totalOrder = tick.totalOrder(Time, tfirst, tlast);
		return Formula.and(oneSig, softSub, noDisj, softConnSoft, softDepSoft, softInstallServer, totalOrder);
	}
	
	/**
	 * @TODO should combine the instance from parameter
	 * Returns the constraints implicit in ecore instance
	 * 
	 * pred {
	 *   no DB.dependOn
	 *   no Apache.dependOn
	 *   PHP.dependOn = Apache
	 *   Web.dependOn = PHP
	 *   
	 *   no DB.connectTo
	 *   no Apache.connectTo
	 *   no PHP.connectTo
	 *   Web.connectTo = DB
	 * }
	 * @return
	 */
	private Formula constraintsFromEcoreInstance() {
		final Formula DepDBApa = (softDB.union(softApache)).join(dependOn).no();
		final Formula DepPHP = softPHP.join(dependOn).eq(softApache);
		final Formula DepWeb = softWeb.join(dependOn).eq(softPHP);
		
		final Formula Conn = (softDB.union(softApache).union(softPHP)).join(connectTo).no();
		final Formula ConnWeb = softWeb.join(connectTo).eq(softDB);
		
		return Formula.and(DepDBApa, DepPHP, DepWeb, Conn, ConnWeb);
	}
	
	/**
	 * pred init {
	 *   no Software.installOn[first]
	 * }
	 * @return
	 */
	private Formula init() {
		return Software.join(installOn).join(tfirst).no();
	}
	
	/**
	 * pred install(software : Software, server : Server, tick : Tick) {
	 *   no software.installOn[tick.prev]
	 *   software.installOn[tick] = server
	 *   
	 *   all s1 : software.dependOn | some s1.installOn[tick]
	 *   all s2 : software.connectTo | some s2.installOn[tick]
	 *   all s3 : Software - software | 
	 *     s3.installOn[tick] = s3.installOn[tick.prev] 
	 * }
	 * @return
	 */
	
	private Formula installSoftware(Expression software, Expression server, Expression tick) {
		final Formula f1 = software.join(installOn).join(prev(tick)).no();
		final Formula f2 = software.join(installOn).join(tick).eq(server);
		
		final Variable s1 = Variable.unary("s1");
		final Variable s2 = Variable.unary("s2");
		final Variable s3 = Variable.unary("s3");

		final Formula f3 = s1.join(installOn).join(tick).some().forAll(s1.oneOf(software.join(dependOn)));
		final Formula f4 = s2.join(installOn).join(tick).some().forAll(s2.oneOf(software.join(connectTo)));
		final Formula f51 = s3.join(installOn).join(tick).eq(s3.join(installOn).join(prev(tick)));
		final Formula f5 = f51.forAll(s3.oneOf(Software.difference(software)));
		
		return Formula.and(f1, f2, f3, f4, f5);
	}
	
	/**
	 * pred end {
	 *   all s : Software | some s.installOn[last]
	 *   
	 *   all disj s1, s2 : Software | s1 in s2.dependOn implies s1.installOn[last] = s2.installOn[last]
	 * }
	 * @return
	 */
	
	private Formula end() {
		final Variable s = Variable.unary("s");
		final Formula f11 = s.join(installOn).join(tlast).some();
		final Formula f1 = f11.forAll(s.oneOf(Software));
		
		final Variable s1 = Variable.unary("s1");
		final Variable s2 = Variable.unary("s2");
		
		//all s1, s2 : Software | (s1 != s2 and s1 in s2.dependOn) => s1.installOn[last] = s2.installOn[last]
		final Formula f21 = s1.join(installOn).join(tlast).eq(s2.join(installOn).join(tlast));
		final Formula f22 = (s1.eq(s2).not().and(s1.in(s2.join(dependOn)))).implies(f21);
		final Formula f2 = f22.forAll(s1.oneOf(Software).and(s2.oneOf(Software)));
		
		return Formula.and(f1, f2);
	}
	
	/**
	 * pred running {
	 *   init
	 *   all t : Tick - first | one soft : Software | one server : Server | 
	 *     install[soft, server, t]
	 *   end
	 * }
	 * @return
	 */
	private Formula running() {
		final Variable soft = Variable.unary("soft");
		final Variable server = Variable.unary("server");
		final Variable t = Variable.unary("t");
		
		final Formula f1 = installSoftware(soft, server, t);
		
		final Decls d1 = soft.oneOf(Software);
		final Decls d2 = server.oneOf(Server);
		final Decls d3 = t.oneOf(Time.difference(tfirst));
		
		/**
		 * @TODO not right, struggle with one
		 */
		final Formula f = f1.forAll(d3.and(d2).and(d1));
		
		return Formula.and(init(), end(), f);
	}
	
	private Formula specification() {
		return declarations();
	}
	
	/**
	 * @TODO add bound of Apache, DB, PHP, Web
	 * @param server
	 * @param tick
	 * @return
	 */
	private Bounds bounds(int server, int tick) {
		final List<String> atoms = new LinkedList<String>();
		for (int i = 0; i < server; ++i) {
			atoms.add("Server" + i);
		}
		
		for (int i = 0; i < tick; ++i) {
			atoms.add("Tick" + i);
		}
		
		final Universe universe = new Universe(atoms);
		final TupleFactory factory = universe.factory();
		final Bounds bounds = new Bounds(universe);
		final String serverMax = "Server" + (server - 1), 
				tickMax = "Tick" + (tick - 1);
		
		bounds.bound(Server, factory.range(factory.tuple("Server0"), factory.tuple(serverMax)));
		bounds.bound(Time, factory.range(factory.tuple("Tick0"), factory.tuple(tickMax)));
		return bounds;
	}
	
	public static void main(String[] args) {
		int server = 2, tick = 5;
		final Alloy model = new Alloy();
		final Solver solver = new Solver();
		solver.options().setSolver(SATFactory.MiniSat);
		try {
			final Formula result = model.specification();
			System.out.println(result);
			final Solution solution = solver.solve(result, model.bounds(server, tick));
			System.out.println(solution);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
