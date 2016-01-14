package moon.nju.edu.cn.main;

import moon.nju.edu.cn.demo.ApacheContainer;
import moon.nju.edu.cn.demo.DemoFactory;
import moon.nju.edu.cn.demo.MySQL;
import moon.nju.edu.cn.demo.PHPContainer;
import moon.nju.edu.cn.demo.Server;
import moon.nju.edu.cn.demo.WebApp;

public class Main {
	/**
	 * parameters for server_1
	 */
	private String serverIp_1 = "10.0.0.1";
	private String serverType_1 = "ubuntu";
	private String serverUser_1 = "vagrant";
	private String serverPass_1 = "vagrant";
	
	/**
	 * parameters for server_2
	 */
	private String serverIp_2 = "10.0.0.2";
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
	private String webName = "MySQL";
	private String webVersion = "1.0";
	
	
	
	DemoFactory factory = DemoFactory.eINSTANCE;
	Server server_1;
	Server server_2;
	ApacheContainer apacheApp;
	PHPContainer phpApp;
	MySQL mysqlApp;
	WebApp webApp;
	
	/**
	 * Initiation
	 */
	private void init() {
		server_1 = factory.createServer();
		server_2 = factory.createServer();
		apacheApp = factory.createApacheContainer();
		phpApp = factory.createPHPContainer();
		mysqlApp = factory.createMySQL();
		webApp = factory.createWebApp();
		
		phpApp.setDependOn(apacheApp);
		webApp.setDependOn(phpApp);
		webApp.setConnectTo(mysqlApp);
		
		phpApp.getServers().add(server_1);
		webApp.getServers().add(server_1);
		apacheApp.getServers().add(server_2);
	}
	
	/**
	 * load parameters from configuration file
	 */
	private void createInstance() {
		server_1.setIP(serverIp_1);
		server_1.setType(serverType_1);
		server_2.setIP(serverIp_2);
		server_2.setType(serverType_2);
		
		apacheApp.setListenPort(apachePort);
		apacheApp.setName(apacheName);
		apacheApp.setVersion(apacheVersion);
		
		phpApp.setName(phpName);
		phpApp.setVersion(phpVersion);
		
		webApp.setName(webName);
		webApp.setVersion(webVersion);
	}
	
	/**
	 * validation and plan in Alloy
	 */
	private void check() {
		
	}
	
	/**
	 * setup according to the result of Alloy
	 */
	private void run() {
		
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		m.init();
		m.createInstance();
		
		/**
		 * throw model in alloy to do constraint solving
		 */
		m.check();
		m.run();
	}
}
