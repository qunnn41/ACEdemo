package moon.nju.edu.cn.main;

import java.util.ArrayList;

import moon.nju.edu.cn.chef.ChefTool;
import moon.nju.edu.cn.demo.ApacheContainer;
import moon.nju.edu.cn.demo.DemoFactory;
import moon.nju.edu.cn.demo.MySQL;
import moon.nju.edu.cn.demo.PHPContainer;
import moon.nju.edu.cn.demo.Server;
import moon.nju.edu.cn.demo.Software;
import moon.nju.edu.cn.demo.WebApp;
import moon.nju.edu.cn.kodkod.Model;

public class Main {
	/**
	 * parameters for server_1
	 */
	private String serverIp_1 = "192.168.1.19";
	private String serverType_1 = "ubuntu";
	private String serverUser_1 = "vagrant";
	private String serverPass_1 = "vagrant";
	
	/**
	 * parameters for server_2
	 */
	private String serverIp_2 = "192.168.1.14";
	private String serverType_2 = "centos";
	private String serverUser_2 = "vagrant";
	private String serverPass_2 = "vagrant";
	
	/**
	 * parameters for Apache
	 */
	private String apacheName = "Apache2";
	private String apacheVersion = "1.0";
	private int apachePort = 22;
	
	/**
	 * parameters for PHP
	 */
	private String phpName = "PHP";
	private String phpVersion = "1.0";
	
	/**
	 * parameters for MySQL
	 */
	private String mysqlName = "MySQL";
	private String mysqlVersion = "1.0";
	private String mysqlUser = "db_admin";
	private String mysqlPass = "database_password";
	
	/**
	 * parameters for WebApp
	 */
	private String webName = "WebApp";
	private String webVersion = "1.0";
	
	
	/**
	 * instance of ecore
	 */
	DemoFactory factory = DemoFactory.eINSTANCE;
	Server server_1 = factory.createServer();
	Server server_2 = factory.createServer();
	ApacheContainer apacheApp = factory.createApacheContainer();
	PHPContainer phpApp = factory.createPHPContainer();
	MySQL mysqlApp = factory.createMySQL();
	WebApp webApp = factory.createWebApp();
	
	
	
	/**
	 * fields
	 */
	ArrayList<Server> serverList = new ArrayList<Server>();
	ArrayList<Software> softList = new ArrayList<Software>();
	Model model;
	/**
	 * Initiation
	 */
	private void init() {
		server_1.setIP(serverIp_1);
		server_1.setType(serverType_1);
		server_1.setUsername(serverUser_1);
		server_1.setPassword(serverPass_1);
		server_1.setCPU(1);
		server_1.setMEM(1);
		server_1.setName("Ubuntu");
		serverList.add(server_1);
		
		server_2.setIP(serverIp_2);
		server_2.setType(serverType_2);
		server_2.setUsername(serverUser_2);
		server_2.setPassword(serverPass_2);
		server_2.setCPU(3);
		server_2.setMEM(4);
		server_2.setName("CentOS");
		serverList.add(server_2);
		
		apacheApp.setListenPort(apachePort);
		apacheApp.setName(apacheName);
		apacheApp.setVersion(apacheVersion);
		apacheApp.setCPU(1);
		apacheApp.setMEM(1);
		softList.add(apacheApp);
		
		phpApp.setName(phpName);
		phpApp.setVersion(phpVersion);
		phpApp.getDependOn().add(apacheApp);
		phpApp.setCPU(1);
		phpApp.setMEM(1);
		softList.add(phpApp);
		
		webApp.setName(webName);
		webApp.setVersion(webVersion);
		webApp.getDependOn().add(phpApp);
		webApp.setConnectTo(mysqlApp);
		webApp.setCPU(1);
		webApp.setMEM(1);
		softList.add(webApp);
		
		mysqlApp.setName(mysqlName);
		mysqlApp.setVersion(mysqlVersion);
		mysqlApp.setUsername(mysqlUser);
		mysqlApp.setPassword(mysqlPass);
		mysqlApp.setCPU(1);
		mysqlApp.setMEM(1);
		softList.add(mysqlApp);
	}
	
	/**
	 * validation and plan in Alloy
	 * @return 
	 */
	private String[][] check() {
		model = new Model(serverList, softList);
		return model.getSolution();
	}
	
	/**
	 * setup according to the result of Alloy
	 * @throws Exception 
	 */
	private void run(String[][] solution) throws Exception {
		ChefTool chefTool = ChefTool.getInstance();

		for (int i = 0; i < solution[0].length; ++i)
			System.out.println(solution[0][i] + "\t" + solution[1][i]);
//		for (String str : solution) {
//			switch (str) {
//				case "PHP":
//					chefTool.install(model.getServerFromSoftware(str), "web_php");
//					break;
//				case "WebApp":
//					chefTool.install(model.getServerFromSoftware(str), "web_app");
//					break;
//				case "Apache2":
//					chefTool.install(model.getServerFromSoftware(str), "web_apache");
//					break;
//				case "MySQL":
//					chefTool.install(model.getServerFromSoftware(str), "web_db");
//					break;
//				default: System.err.println("Wrong Software:" + str + "\n"); System.exit(-2);
//			}
//		}
		
		/*
		chefTool.install(server_2, "web_apache");
		chefTool.install(server_2, "web_php");
		chefTool.install(server_2, "web_app");
		
		System.out.println("apache done!!!\n\n");
		chefTool.install(server_1, "web_db");
		*/
	}
	
	public static void main(String[] args) throws Exception {
		Main m = new Main();
		m.init();
		m.run(m.check());
	}
}
