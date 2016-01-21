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
import kodkod.instance.TupleSet;
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
	
	@SuppressWarnings("unused")
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
	 *   installOn: Tick -> lone Server,
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
		return Formula.and(oneSig, softSub, noDisj, totalOrder,
				softConnSoft, softDepSoft, softInstallServer);
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
		final Formula DepPHP = softApache.eq(softPHP.join(dependOn));
		final Formula DepWeb = softPHP.eq(softWeb.join(dependOn));
		
		final Formula Conn = (softDB.union(softApache).union(softPHP)).join(connectTo).no();
		final Formula ConnWeb = softDB.eq(softWeb.join(connectTo));
		
		return Formula.and(DepDBApa, DepPHP, DepWeb, Conn, ConnWeb);
	}
	
	private Formula specification() {
		return Formula.and(declarations(), constraintsFromEcoreInstance(), running());
	}
	
	/**
	 * pred init {
	 *   no Software.installOn[first]
	 * }
	 * @return
	 */
	private Formula init() {
		return ((tfirst.join(Software.join(installOn))).no());
	}
	
	/**
	 * pred install(software : Software, server : Server, tick : Tick) {
	 *   no software.installOn[tick.prev]
	 *   software.installOn[tick] = server
	 *   
	 *   all s1 : software.dependOn | one s1.installOn[tick.prev]
	 *   all s2 : software.connectTo | one s2.installOn[tick.prev]
	 *   all s3 : Software - software | 
	 *     s3.installOn[tick] = s3.installOn[tick.prev] 
	 * }
	 * @return
	 */
	
	private Formula installSoftware(Expression software, Expression server, Expression tick) {
		final Formula f1 = prev(tick).join(software.join(installOn)).no();
		final Formula f2 = server.eq(tick.join(software.join(installOn)));
		
		final Variable s1 = Variable.unary("s1");
		final Variable s2 = Variable.unary("s2");
		final Variable s3 = Variable.unary("s3");

		final Formula f3 = prev(tick).join(s1.join(installOn)).one().forAll(s1.oneOf(software.join(dependOn)));
		final Formula f4 = prev(tick).join(s2.join(installOn)).one().forAll(s2.oneOf(software.join(connectTo)));
		final Formula f51 = tick.join(s3.join(installOn)).eq(prev(tick).join(s3.join(installOn)));
		final Formula f5 = f51.forAll(s3.oneOf(Software.difference(software)));
		
		return Formula.and(f1, f2, f3, f4, f5);
	}
	
	/**
	 * pred goal {
	 *   all s : Software | one s.installOn[last]
	 *   
	 *   all disj s1, s2 : Software | s1 in s2.dependOn implies s1.installOn[last] = s2.installOn[last]
	 * }
	 * @return
	 */
	
	private Formula goal() {
		final Variable s = Variable.unary("s");
		final Formula f11 = tlast.join(s.join(installOn)).one();
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
	 *   all t : Tick - first | some soft : Software | some server : Server | 
	 *     install[soft, server, t]
	 *   goal
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
		
		final Formula f = f1.forSome(d2).forSome(d1).forAll(d3);
		return Formula.and(init(), goal(), f);
	}
	
	/**
	 * @param serverNum
	 * @param tickNum
	 * @return
	 */
	private Bounds bounds(int serverNum, int tickNum) {
		final List<String> atoms = new LinkedList<String>();
		for (int i = 0; i < serverNum; ++i) {
			atoms.add("Server" + i);
		}
		
		for (int i = 0; i < tickNum; ++i) {
			atoms.add("Tick" + i);
		}
		
		atoms.add("Apache");
		atoms.add("PHP");
		atoms.add("WebApp");
		atoms.add("DB");
		
		final Universe universe = new Universe(atoms);
		final TupleFactory factory = universe.factory();
		final Bounds bounds = new Bounds(universe);
		final String serverMax = "Server" + (serverNum - 1), 
				tickMax = "Tick" + (tickNum - 1);
		
		final TupleSet serverTuple = factory.range(factory.tuple("Server0"), factory.tuple(serverMax));
		final TupleSet tickTuple = factory.range(factory.tuple("Tick0"), factory.tuple(tickMax));
		final TupleSet softwareTuple = factory.range(factory.tuple("Apache"), factory.tuple("DB"));
		bounds.bound(Server, serverTuple);
		bounds.bound(Time, tickTuple);
		
		//one sig DB, Apache, PHP, Web extends Software {}
		bounds.bound(Software, factory.range(factory.tuple("Apache"), factory.tuple("DB")));
		bounds.bound(softApache, factory.range(factory.tuple("Apache"), factory.tuple("Apache")));
		bounds.bound(softDB, factory.range(factory.tuple("DB"), factory.tuple("DB")));
		bounds.bound(softPHP, factory.range(factory.tuple("PHP"), factory.tuple("PHP")));
		bounds.bound(softWeb, factory.range(factory.tuple("WebApp"), factory.tuple("WebApp")));
		
		bounds.bound(tick, tickTuple.product(tickTuple));
		bounds.bound(tfirst, tickTuple);
		bounds.bound(tlast, tickTuple);
		bounds.bound(installOn, softwareTuple.product(tickTuple).product(serverTuple));
		bounds.bound(dependOn, softwareTuple.product(softwareTuple));
		bounds.bound(connectTo, softwareTuple.product(softwareTuple));
		return bounds;
	}
	
	public static void main(String[] args) {
		int server = 3, tick = 5;
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
