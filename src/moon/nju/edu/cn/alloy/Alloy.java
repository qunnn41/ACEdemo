package moon.nju.edu.cn.alloy;

import kodkod.ast.Formula;
import kodkod.ast.Relation;
import kodkod.ast.Variable;

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
		tfirst = Relation.unary("start");
		tlast = Relation.unary("end");
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
		//DB + PHP + Apache + Web in Software
		final Formula softSub = softApache.union(softDB).union(softPHP).union(softWeb).in(Software);
		//no DB & PHP & Apache & Web
		final Formula noDisj = softApache.intersection(softDB).intersection(softPHP).intersection(softWeb).no();
		//fields about installOn, dependOn, connectTo
		final Formula softInstallServer = installOn.in(Software.product(Time).product(Server));
		final Formula softDepSoft = dependOn.in(Software.product(Software));
		final Formula softConnSoft = connectTo.in(Software.product(Software));
		//tick is a total ordering on time
		final Formula totalOrder = tick.totalOrder(Time, tfirst, tlast);
		return Formula.and(softSub, noDisj, softConnSoft, softDepSoft, softInstallServer, totalOrder);
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
	
	public static void main(String[] args) {
		Alloy a = new Alloy();
		System.out.println(/*a.declarations().toString() + */a.init().toString() + 
		a.constraintsFromEcoreInstance().toString());
	}
}
