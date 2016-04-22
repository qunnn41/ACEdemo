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
import kodkod.ast.IntConstant;
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

public class Model {
	//signatures
	private Relation modelTime, modelSoftware, modelServer;
	
	//fields
	private Relation tfirst, tlast, tick;
	private Relation installOn, dependOn, connectTo;
	private Relation soft_CPU, soft_MEM, ser_CPU, ser_MEM, used_CPU, used_MEM;
	
	/**
	 * store server list, software->server
	 */
	Map<Software, Server> serverOfSoft;
	/**
	 * store server information, server->demo.Server
	 */
	Map<Server, Relation> serverRelation;
	/**
	 * map software to relation
	 */
	Map<Software, Relation> softRelation;
	
	/**
	 * initialize signature and fields
	 */
	List<Software> softwareList;
	List<Server> serverList;
	public Model(ArrayList<Server> servList, ArrayList<Software> softList) {
		modelTime = Relation.unary("Time");
		tfirst = Relation.unary("first");
		tlast = Relation.unary("last");
		tick = Relation.binary("tick");
		modelServer = Relation.unary("Server");
		modelSoftware = Relation.unary("Software");
		
		installOn = Relation.ternary("installOn");
		dependOn = Relation.binary("dependOn");
		connectTo = Relation.binary("connectTo");
		soft_CPU = Relation.binary("soft_CPU");
		soft_MEM = Relation.binary("soft_MEM");
		ser_CPU = Relation.binary("ser_CPU");
		ser_MEM = Relation.binary("ser_MEM");
		used_CPU = Relation.ternary("used_CPU");
		used_MEM = Relation.ternary("used_MEM");
		
		softRelation = new HashMap<Software, Relation>(softList.size());
		for (int i = 0; i < softList.size(); ++i) {
			softRelation.put(softList.get(i), Relation.unary(softList.get(i).getName()));
		}
		
		serverRelation = new HashMap<Server, Relation>();
		for (int i = 0; i < servList.size(); ++i) {
			serverRelation.put(servList.get(i), Relation.unary(servList.get(i).getName()));
		}
		
		softwareList = new ArrayList<Software>(softList);
		serverList = new ArrayList<Server>(servList);
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
	 * sig Server {
	 *   cpu: Int,
	 *   mem: Int,
	 *   used_cpu: Tick -> Int,
	 *   used_mem: Tick -> Int
	 * }
	 * abstract sig Software {
	 *   cpu: Int,
	 *   mem: Int,
	 *   installOn: Tick -> lone Server,
	 *   dependOn: set Software,
	 *   connectTo: set Software
	 * }
	 * one sig DB, Apache, PHP, Web extends Software {}
	 * @return
	 */
	private Formula declarations() {
		Formula SoftOneSigConstraints = Formula.TRUE;
		Expression SoftUnionExpression = null;
		Expression SoftIntersectionExpression = null;
		for (Relation r : softRelation.values()) {
			SoftOneSigConstraints = SoftOneSigConstraints.and(r.one());
			SoftUnionExpression = SoftUnionExpression == null ? r : SoftUnionExpression.union(r);
			SoftIntersectionExpression = SoftIntersectionExpression == null ? r : SoftIntersectionExpression.intersection(r);
		}
		final Formula SoftUnionConstraints = SoftUnionExpression.eq(modelSoftware);
		final Formula SoftIntersectionConstraints = SoftIntersectionExpression.no();
		final Formula softConstraints = Formula.and(SoftOneSigConstraints, SoftUnionConstraints, SoftIntersectionConstraints);
		
		Formula ServerOneSigConstraints = Formula.TRUE;
		Expression ServerUnionExpression = null;
		Expression ServerIntersectionExpression = null;
		for (Relation r : serverRelation.values()) {
			ServerOneSigConstraints = ServerOneSigConstraints.and(r.one());
			ServerUnionExpression = ServerUnionExpression == null ? r : ServerUnionExpression.union(r);
			ServerIntersectionExpression = ServerIntersectionExpression == null ? r : ServerIntersectionExpression.intersection(r);
		}
		
		final Formula ServerUnionConstraints = ServerUnionExpression.eq(modelServer);
		final Formula ServerIntersectionConstraints = ServerIntersectionExpression.no();
		final Formula serverConstraints = Formula.and(ServerOneSigConstraints, ServerUnionConstraints, ServerIntersectionConstraints);

		//fields about installOn, dependOn, connectTo, installing
		final Formula softInstallServer = installOn.in(modelSoftware.product(modelTime).product(modelServer));
		final Formula softDepSoft = dependOn.in(modelSoftware.product(modelSoftware));
		final Formula softConnSoft = connectTo.in(modelSoftware.product(modelSoftware));
		final Formula usedCPU = used_CPU.in(modelServer.product(modelTime).product(Expression.INTS));
		final Formula usedMEM = used_MEM.in(modelServer.product(modelTime).product(Expression.INTS));
		
		final Formula CMconstraints = (soft_CPU.union(soft_MEM)).in(modelSoftware.product(Expression.INTS));
		
		//tick is a total ordering on time
		final Formula totalOrder = tick.totalOrder(modelTime, tfirst, tlast);
		
		return Formula.and(SoftOneSigConstraints, SoftUnionConstraints, SoftIntersectionConstraints, totalOrder,
				softConnSoft, softDepSoft, softInstallServer, serverConstraints, usedCPU, usedMEM, CMconstraints, softConstraints);
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
	 *   
	 *   all s : Server | all t : Tick | one s.cpu[t]
	 *   all s : Server | all t : Tick | one s.mem[t]
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
			
			Formula cpu = softRelation.get(soft).join(soft_CPU).eq(IntConstant.constant(soft.getCPU()).toExpression());
			Formula mem = softRelation.get(soft).join(soft_MEM).eq(IntConstant.constant(soft.getMEM()).toExpression());
			result = result.and(cpu).and(mem);
		}
		
		for (Server server : serverRelation.keySet()) {
			Formula cpu = serverRelation.get(server).join(ser_CPU).eq(IntConstant.constant(server.getCPU()).toExpression());
			Formula mem = serverRelation.get(server).join(ser_MEM).eq(IntConstant.constant(server.getMEM()).toExpression());
			result = result.and(cpu).and(mem);
		}
		
		final Variable s = Variable.unary("s");
		final Variable t = Variable.unary("t");
		final Formula cpu = (t.join(s.join(used_CPU)).one()).forAll(s.oneOf(modelServer)).forAll(t.oneOf(modelTime));
		final Formula mem = (t.join(s.join(used_MEM)).one()).forAll(s.oneOf(modelServer)).forAll(t.oneOf(modelTime));
		
		return Formula.and(result, cpu, mem);
	}
	
	private Formula specification() {
		return Formula.and(declarations(), ecoreConstraints(), running());
	}
	
	/**
	 * pred init {
	 *   no Software.installOn[first]
	 *   one server.used_cpu[tick]
	 *   one server.used_mem[tick]
	 * }
	 * @return
	 */
	private Formula init() {
		final Formula noInstallFirst = (tfirst.join(modelSoftware.join(installOn))).no();
		
		final Variable s1 = Variable.unary("s1");
		final Formula oneUsedCPU = (tfirst.join(s1.join(used_CPU)).sum().eq(s1.join(ser_CPU).sum()))
				.forAll(s1.oneOf(modelServer));
		
		final Variable s2 = Variable.unary("s2");
		final Formula oneUsedMEM = (tfirst.join(s2.join(used_MEM)).sum().eq(s2.join(ser_MEM).sum()))
				.forAll(s2.oneOf(modelServer));
		return Formula.and(noInstallFirst, oneUsedCPU, oneUsedMEM);
	}
	
	/**
	 * pred process(software : Software, server : Server, tick : Tick) {
	 *   no software.installOn[tick.prev]
	 *   software.installOn[tick] = server
	 *   
	 *   all s1 : software.dependOn | one s1.installOn[tick.prev]
	 *   all s2 : software.connectTo | one s2.installOn[tick.prev]
	 *   all s3 : Software - software | 
	 *     s3.installOn[tick] = s3.installOn[tick.prev]
	 *     
	 *   all s4 : Server - server | s4.used_cpu[tick] = s4.used_cpu[tick.prev] 
	 *   							and s4.used_mem[tick] = s4.used_mem[tick.prev]
	 *   
	 *   server.used_cpu[tick] = server.used_cpu[tick.prev] + software.cpu
	 *   server.used_mem[tick] = server.used_cpu[tick.prev] + software.mem
	 * }
	 * @return
	 */
	
	private Formula process(Expression software, Expression server, Expression tick) {
		final Formula hasNotInstallBefore = prev(tick).join(software.join(installOn)).no();
		final Formula installNow = server.eq(tick.join(software.join(installOn)));
		
		final Variable s1 = Variable.unary("s1");
		final Formula dependOnInstalled = prev(tick).join(s1.join(installOn)).one().forAll(s1.oneOf(software.join(dependOn)));
		
		final Variable s2 = Variable.unary("s2");
		final Formula connectToInstalled = prev(tick).join(s2.join(installOn)).one().forAll(s2.oneOf(software.join(connectTo)));
		
		final Variable s3 = Variable.unary("s3");
		final Formula installRemain = tick.join(s3.join(installOn)).eq(prev(tick).join(s3.join(installOn)))
				.forAll(s3.oneOf(modelSoftware.difference(software)));
		
		final Formula usedCPUCal = tick.join(server.join(used_CPU)).sum()
				.eq(prev(tick).join(server.join(used_CPU)).sum().minus(software.join(soft_CPU).sum()));
		final Formula usedMEMCal = tick.join(server.join(used_MEM)).sum()
				.eq(prev(tick).join(server.join(used_MEM)).sum().minus(software.join(soft_MEM).sum()));
		
		final Variable s4 = Variable.unary("s4");
		final Formula usedCPURemain = tick.join(s4.join(used_CPU)).eq(prev(tick).join(s4.join(used_CPU)))
				.forAll(s4.oneOf(modelServer.difference(server)));
		
		final Variable s5 = Variable.unary("s5");
		final Formula usedMEMRemain = tick.join(s5.join(used_MEM)).eq(prev(tick).join(s5.join(used_MEM)))
				.forAll(s5.oneOf(modelServer.difference(server)));
		
		return Formula.and(hasNotInstallBefore, installNow, dependOnInstalled, connectToInstalled, 
				installRemain, usedCPUCal, usedCPURemain, usedMEMCal, usedMEMRemain);
	}
	
	/**
	 * pred goal {
	 *   all s : Software | one s.installOn[last]
	 *   all disj s1, s2 : Software | s1 in s2.dependOn implies s1.installOn[last] = s2.installOn[last]
	 *   
	 *   all s3 : Server | s3.used_mem[last] <= s3.mem
	 *   all s3 : Server | s3.used_cpu[last] <= s3.cpu
	 * }
	 * @return
	 */
	
	private Formula goal() {
		//all s : Software | one s.installOn[last]
		final Variable s = Variable.unary("s");
		final Formula allSoftInstalled = tlast.join(s.join(installOn)).one().forAll(s.oneOf(modelSoftware));
		
		//all s1, s2 : Software | (s1 != s2 and s1 in s2.dependOn) => s1.installOn[last] = s2.installOn[last]
		final Variable s1 = Variable.unary("s1");
		final Variable s2 = Variable.unary("s2");
		final Formula f1 = tlast.join(s1.join(installOn)).eq(tlast.join(s2.join(installOn)));
		final Formula f2 = (s1.eq(s2).not().and(s1.in(s2.join(dependOn)))).implies(f1);
		final Formula sameSoftInstallSame = f2.forAll(s1.oneOf(modelSoftware).and(s2.oneOf(modelSoftware)));
		
		final Variable s3 = Variable.unary("s3");
		final Formula cpuLeft = (tlast.join(s3.join(used_CPU))).sum().gte(IntConstant.constant(0)).forAll(s3.oneOf(modelServer));
		
		final Variable s4 = Variable.unary("s4");
		final Formula memLeft = (tlast.join(s4.join(used_MEM))).sum().gte(IntConstant.constant(0)).forAll(s4.oneOf(modelServer));
		return Formula.and(allSoftInstalled, sameSoftInstallSame, cpuLeft, memLeft);
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
		
		final Formula f1 = process(soft, server, t);
		final Decls d1 = soft.oneOf(modelSoftware);
		final Decls d2 = server.oneOf(modelServer);
		final Decls d3 = t.oneOf(modelTime.difference(tfirst));
		final Formula f = f1.forSome(d2).forSome(d1).forAll(d3);
		
		return Formula.and(init(), goal(), f);
	}
	
	
	private Bounds bounds(int serverNum, int tickNum) {
		final List<Object> atoms = new LinkedList<Object>();
		final int maxSize = 12;
		final int minSize = 0;

		for (int i = 0; i < tickNum; ++i) {
			atoms.add("Tick" + i);
		}

		for (Server server : serverList) {
			atoms.add(server.getName());
		}
		for (Software soft : softwareList) {
			atoms.add(soft.getName());
		}
		
		for (int i = minSize; i <= maxSize; ++i) {
			atoms.add(Integer.valueOf(i));
		}
			
		final Universe universe = new Universe(atoms);
		final TupleFactory factory = universe.factory();
		final Bounds bounds = new Bounds(universe);
		final String tickMax = "Tick" + (tickNum - 1);
		
		final String softFirst = softwareList.get(0).getName();
		final String softLast = softwareList.get(softwareList.size() - 1).getName();
		final String serverFirst = serverList.get(0).getName();
		final String serverLast = serverList.get(serverList.size() - 1).getName();
		
		final TupleSet serverTuple = factory.range(factory.tuple(serverFirst), factory.tuple(serverLast));
		final TupleSet tickTuple = factory.range(factory.tuple("Tick0"), factory.tuple(tickMax));
		final TupleSet softwareTuple = factory.range(factory.tuple(softFirst), factory.tuple(softLast));
		final TupleSet intTuple = factory.range(factory.tuple(Integer.valueOf(minSize)), factory.tuple(Integer.valueOf(maxSize)));
		
		//bound tick
		bounds.bound(modelTime, tickTuple);
		
		//bound software
		bounds.bound(modelSoftware, softwareTuple);
		for (Software soft : softRelation.keySet())
			bounds.bound(softRelation.get(soft), 
					factory.range(factory.tuple(soft.getName()), factory.tuple(soft.getName())));
		
		//bound server
		bounds.bound(modelServer, serverTuple);
		for (Server server : serverRelation.keySet())
			bounds.bound(serverRelation.get(server), 
					factory.range(factory.tuple(server.getName()), factory.tuple(server.getName())));
		
		bounds.bound(tick, tickTuple.product(tickTuple));
		bounds.bound(tfirst, tickTuple);
		bounds.bound(tlast, tickTuple);
		bounds.bound(installOn, softwareTuple.product(tickTuple).product(serverTuple));
		bounds.bound(dependOn, softwareTuple.product(softwareTuple));
		bounds.bound(connectTo, softwareTuple.product(softwareTuple));
		bounds.bound(soft_CPU, softwareTuple.product(intTuple));
		bounds.bound(soft_MEM, softwareTuple.product(intTuple));
		bounds.bound(ser_CPU, serverTuple.product(intTuple));
		bounds.bound(ser_MEM, serverTuple.product(intTuple));
		bounds.bound(used_CPU, serverTuple.product(tickTuple).product(intTuple));
		bounds.bound(used_MEM, serverTuple.product(tickTuple).product(intTuple));
		
		//bounds for integer numbers
		for (int i = minSize; i <= maxSize; ++i) {
			bounds.boundExactly(i, factory.setOf(Integer.valueOf(i)));
		}
		return bounds;
	}
	
	@SuppressWarnings("unused")
	private Bounds bounds(int scope) {
		return bounds(scope, scope);
	}
	
	public String[][] getSolution() {
		final int serverNum = serverRelation.size();
		final int tickNum = softRelation.size() + 1;
		final Solver solver = new Solver();
		solver.options().setSolver(SATFactory.DefaultSAT4J);
		serverOfSoft = new HashMap<Software, Server>();
		String[][] process = new String[2][tickNum -1];
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
			String softName = null;
			while (it.hasNext()) {
				Tuple tuple = it.next();
//				System.out.println(tuple);
				//get new one
				if (softName == null || !softName.equals(tuple.atom(0))) {
					softName = (String) tuple.atom(0);
					int tick = Integer.parseInt(((String) tuple.atom(1)).substring(4));
					process[0][tick - 1] = softName;
					process[1][tick - 1] = (String) tuple.atom(2);
				}
			}
//			System.out.println("================CPU======================");
//			ts = map.get(used_CPU);
//			it = ts.iterator();
//			while (it.hasNext()) {
//				System.out.println(it.next());
//			}
//			System.out.println("================MEM======================");
//			ts = map.get(used_MEM);
//			it = ts.iterator();
//			while (it.hasNext()) {
//				System.out.println(it.next());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return process;
	}
}
