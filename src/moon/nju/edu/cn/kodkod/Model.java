package moon.nju.edu.cn.kodkod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import kodkod.ast.Decls;
import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.ast.Relation;
import kodkod.ast.Variable;
import kodkod.engine.Solution;
import kodkod.engine.Solver;
import kodkod.engine.satlab.SATFactory;
import kodkod.instance.Bounds;
import kodkod.instance.Instance;
import kodkod.instance.Tuple;
import kodkod.instance.TupleFactory;
import kodkod.instance.TupleSet;
import kodkod.instance.Universe;
import moon.nju.edu.cn.demo.Server;
import moon.nju.edu.cn.demo.Software;

@SuppressWarnings("unused")
public class Model {
	//signatures
	private Relation modelTime, modelSoftware, modelServer;
//	private Relation softApache, softWeb, softDB, softPHP;
	
	//fields
	private Relation tfirst, tlast, tick;
	private static Relation installOn, dependOn, connectTo;
	
	/**
	 * store server list, software->server
	 */
	Map<String, String> serverOfSoft;
	/**
	 * store server information, server->demo.Server
	 */
	Map<String, Server> serverMap;
	/**
	 * map software to relation
	 */
	Map<Software, Relation> softRelation;
	
	/**
	 * initialize signature and fields
	 */
	List<Software> softwareList;
	public Model(ArrayList<Server> serverList, ArrayList<Software> softList) {
		modelTime = Relation.unary("Time");
		tfirst = Relation.unary("first");
		tlast = Relation.unary("last");
		tick = Relation.binary("tick");
		modelServer = Relation.unary("Server");
		modelSoftware = Relation.unary("Software");
		
//		softApache = Relation.unary("Apache");
//		softWeb = Relation.unary("Web");
//		softDB = Relation.unary("DB");
//		softPHP = Relation.unary("PHP");
		
		/**
		 * get constraints from ecore model
		 */
		softRelation = new HashMap<Software, Relation>(softList.size());
		for (int i = 0; i < softList.size(); ++i) {
			softRelation.put(softList.get(i), Relation.unary(softList.get(i).getName()));
		}
		
		installOn = Relation.ternary("installOn");
		dependOn = Relation.binary("dependOn");
		connectTo = Relation.binary("connectTo");
		
		serverMap = new HashMap<String, Server>();
		for (int i = 0; i < serverList.size(); ++i) {
			serverMap.put("Server" + i, serverList.get(i));
		}
		
		softwareList = new ArrayList<Software>();
		softwareList.addAll(softList);
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
	 *   installOn: Tick -> lone Server,
	 *   dependOn: set Software,
	 *   connectTo: set Software
	 * }
	 * one sig DB, Apache, PHP, Web extends Software {}
	 * @return
	 */
	private Formula declarations() {
//		//one sig
//		final Formula oneSig = Formula.and(softApache.one(), softDB.one(), softPHP.one(), softWeb.one());
//		//DB + PHP + Apache + Web = Software
//		final Formula softSub = softApache.union(softDB).union(softPHP).union(softWeb).eq(modelSoftware);
//		//no DB & PHP & Apache & Web
//		final Formula noDisj = softApache.intersection(softDB).intersection(softPHP).intersection(softWeb).no();
		
		/**
		 * get constraints from ecore model
		 */
		Formula oneSigConstraints = null;
		Expression unionExpression = null;
		Expression intersectionExpression = null;
		for (Relation r : softRelation.values()) {
			oneSigConstraints = oneSigConstraints == null ? r.one() : oneSigConstraints.and(r.one());
			unionExpression = unionExpression == null ? r : unionExpression.union(r);
			intersectionExpression = intersectionExpression == null ? r : intersectionExpression.intersection(r);
		}
		
		final Formula unionConstraints = unionExpression.eq(modelSoftware);
		final Formula intersectionConstraints = intersectionExpression.no();
		final Formula softConstraints = Formula.and(oneSigConstraints, unionConstraints, intersectionConstraints);
		
		//fields about installOn, dependOn, connectTo
		final Formula softInstallServer = installOn.in(modelSoftware.product(modelTime).product(modelServer));
		final Formula softDepSoft = dependOn.in(modelSoftware.product(modelSoftware));
		final Formula softConnSoft = connectTo.in(modelSoftware.product(modelSoftware));
		//tick is a total ordering on time
		final Formula totalOrder = tick.totalOrder(modelTime, tfirst, tlast);
		return Formula.and(oneSigConstraints, unionConstraints, intersectionConstraints, totalOrder,
				softConnSoft, softDepSoft, softInstallServer);
//		return Formula.and(oneSig, softSub, noDisj, totalOrder,
//				softConnSoft, softDepSoft, softInstallServer);
	}
	
	/**
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
	private Formula ecoreConstraints() {
		Formula result = Formula.TRUE;
		for (Software soft : softRelation.keySet()) {
			Expression e = softRelation.get(soft).join(dependOn);
			Expression value = null;
			for (Software dependSoft : soft.getDependOn())
				value = value == null ? softRelation.get(dependSoft) : value.union(softRelation.get(dependSoft));
			final Formula dependConstraints = value == null ? e.no() : e.eq(value);
			result = result.and(dependConstraints);
		}
		return result;
//		final Formula DepDBApa = (softDB.union(softApache)).join(dependOn).no();
//		final Formula DepPHP = softApache.eq(softPHP.join(dependOn));
//		final Formula DepWeb = softPHP.eq(softWeb.join(dependOn));
//		
//		final Formula Conn = (softDB.union(softApache).union(softPHP)).join(connectTo).no();
//		final Formula ConnWeb = softDB.eq(softWeb.join(connectTo));
//		
//		return Formula.and(DepDBApa, DepPHP, DepWeb, Conn, ConnWeb);
	}
	
	private Formula specification() {
		return Formula.and(declarations(), ecoreConstraints(), running());
	}
	
	/**
	 * pred init {
	 *   no Software.installOn[first]
	 * }
	 * @return
	 */
	private Formula init() {
		return ((tfirst.join(modelSoftware.join(installOn))).no());
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
		final Formula f5 = f51.forAll(s3.oneOf(modelSoftware.difference(software)));
		
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
		final Formula f1 = f11.forAll(s.oneOf(modelSoftware));
		
		final Variable s1 = Variable.unary("s1");
		final Variable s2 = Variable.unary("s2");
		
		//all s1, s2 : Software | (s1 != s2 and s1 in s2.dependOn) => s1.installOn[last] = s2.installOn[last]
		final Formula f21 = s1.join(installOn).join(tlast).eq(s2.join(installOn).join(tlast));
		final Formula f22 = (s1.eq(s2).not().and(s1.in(s2.join(dependOn)))).implies(f21);
		final Formula f2 = f22.forAll(s1.oneOf(modelSoftware).and(s2.oneOf(modelSoftware)));
		
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
		
		final Decls d1 = soft.oneOf(modelSoftware);
		final Decls d2 = server.oneOf(modelServer);
		final Decls d3 = t.oneOf(modelTime.difference(tfirst));
		
		final Formula f = f1.forSome(d2).forSome(d1).forAll(d3);
		return Formula.and(init(), goal(), f);
	}
	
	
	private Bounds bounds(int serverNum, int tickNum) {
		final List<String> atoms = new LinkedList<String>();
		for (int i = 0; i < serverNum; ++i) {
			atoms.add("Server" + i);
		}
		
		for (int i = 0; i < tickNum; ++i) {
			atoms.add("Tick" + i);
		}

		for (Software soft : softwareList) {
			atoms.add(soft.getName());
		}
			
//		//scope is 1
//		atoms.add("Apache");
//		atoms.add("PHP");
//		atoms.add("WebApp");
//		atoms.add("DB");
		
		final Universe universe = new Universe(atoms);
		final TupleFactory factory = universe.factory();
		final Bounds bounds = new Bounds(universe);
		final String serverMax = "Server" + (serverNum - 1), 
				tickMax = "Tick" + (tickNum - 1);
		
		final String softFirst = softwareList.get(0).getName();
		final String softLast = softwareList.get(softwareList.size() - 1).getName();
		
		final TupleSet serverTuple = factory.range(factory.tuple("Server0"), factory.tuple(serverMax));
		final TupleSet tickTuple = factory.range(factory.tuple("Tick0"), factory.tuple(tickMax));
//		final TupleSet softwareTuple = factory.range(factory.tuple("Apache"), factory.tuple("DB"));
		final TupleSet softwareTuple = factory.range(factory.tuple(softFirst), factory.tuple(softLast));
		
		bounds.bound(modelServer, serverTuple);
		bounds.bound(modelTime, tickTuple);
		
		//one sig DB, Apache, PHP, Web extends Software {}
		bounds.bound(modelSoftware, factory.range(factory.tuple(softFirst), factory.tuple(softLast)));
		for (Software soft : softRelation.keySet())
			bounds.bound(softRelation.get(soft), factory.range(factory.tuple(soft.getName()), factory.tuple(soft.getName())));
//		bounds.bound(modelSoftware, factory.range(factory.tuple("Apache"), factory.tuple("DB")));
//		bounds.bound(softApache, factory.range(factory.tuple("Apache"), factory.tuple("Apache")));
//		bounds.bound(softDB, factory.range(factory.tuple("DB"), factory.tuple("DB")));
//		bounds.bound(softPHP, factory.range(factory.tuple("PHP"), factory.tuple("PHP")));
//		bounds.bound(softWeb, factory.range(factory.tuple("WebApp"), factory.tuple("WebApp")));
		
		bounds.bound(tick, tickTuple.product(tickTuple));
		bounds.bound(tfirst, tickTuple);
		bounds.bound(tlast, tickTuple);
		bounds.bound(installOn, softwareTuple.product(tickTuple).product(serverTuple));
		bounds.bound(dependOn, softwareTuple.product(softwareTuple));
		bounds.bound(connectTo, softwareTuple.product(softwareTuple));
		return bounds;
	}
	
	private Bounds bounds(int scope) {
		return bounds(scope, scope);
	}
	
	public Server getServerFromSoftware(String str) {
		if (serverOfSoft == null || str == null || !serverOfSoft.containsKey(str)) {
			System.err.println("Something wrong in get server");
		}
		return serverMap.get(serverOfSoft.get(str));
	}
	
	public String[] getSolution() {
		final int serverNum = serverMap.size();
		final int tickNum = softRelation.size() + 1;
		final Solver solver = new Solver();
		solver.options().setSolver(SATFactory.MiniSat);
		serverOfSoft = new HashMap<String, String>();
		Map<String, Integer> tickMap = new HashMap<String, Integer>();
		try {
			final Formula result = specification();
			final Solution solution = solver.solve(result, bounds(serverNum, tickNum));
			if (solution.unsat()) {
				System.err.println("Model is unsatisfiable");
				System.exit(-1);
			} else {
				System.out.println(solution);
			}
			Instance instance = solution.instance();
			Map<Relation, TupleSet> map = instance.relationTuples();
			TupleSet ts = map.get(installOn);
			Iterator <Tuple> it = ts.iterator();
			while (it.hasNext()) {
				Tuple tuple = it.next();
				String s_name = (String) tuple.atom(0);
				//get i from Tick i
				int s_tick = Integer.parseInt(((String) tuple.atom(1)).substring(4));
				String s_server = (String) tuple.atom(2);
				if (serverOfSoft.containsKey(s_name)) {
					tickMap.put(s_name, Math.min(s_tick, tickMap.get(s_name)));
				} else {
					serverOfSoft.put(s_name, s_server);
					tickMap.put(s_name, s_tick);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//sequence of software, the index is the order to install
		String[] result = new String[tickNum - 1];
		for (String str : serverOfSoft.keySet()) {
//			System.out.println(str + "\t" + serverOfSoft.get(str));
			result[tickMap.get(str) - 1] = str;
		}
		return result;
	}
}
