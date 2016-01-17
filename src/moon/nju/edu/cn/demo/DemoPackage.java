/**
 */
package moon.nju.edu.cn.demo;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see moon.nju.edu.cn.demo.DemoFactory
 * @model kind="package"
 * @generated
 */
public interface DemoPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "demo";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.com/demo";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "demo";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DemoPackage eINSTANCE = moon.nju.edu.cn.demo.impl.DemoPackageImpl.init();

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.demo.impl.ServerImpl <em>Server</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.demo.impl.ServerImpl
	 * @see moon.nju.edu.cn.demo.impl.DemoPackageImpl#getServer()
	 * @generated
	 */
	int SERVER = 0;

	/**
	 * The feature id for the '<em><b>IP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVER__IP = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVER__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVER__USERNAME = 2;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVER__PASSWORD = 3;

	/**
	 * The number of structural features of the '<em>Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVER_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.demo.impl.SoftwareImpl <em>Software</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.demo.impl.SoftwareImpl
	 * @see moon.nju.edu.cn.demo.impl.DemoPackageImpl#getSoftware()
	 * @generated
	 */
	int SOFTWARE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE__VERSION = 1;

	/**
	 * The feature id for the '<em><b>Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE__SERVERS = 2;

	/**
	 * The number of structural features of the '<em>Software</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Software</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.demo.impl.ApacheContainerImpl <em>Apache Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.demo.impl.ApacheContainerImpl
	 * @see moon.nju.edu.cn.demo.impl.DemoPackageImpl#getApacheContainer()
	 * @generated
	 */
	int APACHE_CONTAINER = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APACHE_CONTAINER__NAME = SOFTWARE__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APACHE_CONTAINER__VERSION = SOFTWARE__VERSION;

	/**
	 * The feature id for the '<em><b>Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APACHE_CONTAINER__SERVERS = SOFTWARE__SERVERS;

	/**
	 * The feature id for the '<em><b>Listen Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APACHE_CONTAINER__LISTEN_PORT = SOFTWARE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Apache Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APACHE_CONTAINER_FEATURE_COUNT = SOFTWARE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Apache Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APACHE_CONTAINER_OPERATION_COUNT = SOFTWARE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.demo.impl.PHPContainerImpl <em>PHP Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.demo.impl.PHPContainerImpl
	 * @see moon.nju.edu.cn.demo.impl.DemoPackageImpl#getPHPContainer()
	 * @generated
	 */
	int PHP_CONTAINER = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHP_CONTAINER__NAME = SOFTWARE__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHP_CONTAINER__VERSION = SOFTWARE__VERSION;

	/**
	 * The feature id for the '<em><b>Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHP_CONTAINER__SERVERS = SOFTWARE__SERVERS;

	/**
	 * The feature id for the '<em><b>Depend On</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHP_CONTAINER__DEPEND_ON = SOFTWARE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>PHP Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHP_CONTAINER_FEATURE_COUNT = SOFTWARE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>PHP Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHP_CONTAINER_OPERATION_COUNT = SOFTWARE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.demo.impl.WebAppImpl <em>Web App</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.demo.impl.WebAppImpl
	 * @see moon.nju.edu.cn.demo.impl.DemoPackageImpl#getWebApp()
	 * @generated
	 */
	int WEB_APP = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__NAME = SOFTWARE__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__VERSION = SOFTWARE__VERSION;

	/**
	 * The feature id for the '<em><b>Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__SERVERS = SOFTWARE__SERVERS;

	/**
	 * The feature id for the '<em><b>Connect To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__CONNECT_TO = SOFTWARE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Depend On</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__DEPEND_ON = SOFTWARE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Web App</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP_FEATURE_COUNT = SOFTWARE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Web App</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP_OPERATION_COUNT = SOFTWARE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.demo.impl.MySQLImpl <em>My SQL</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.demo.impl.MySQLImpl
	 * @see moon.nju.edu.cn.demo.impl.DemoPackageImpl#getMySQL()
	 * @generated
	 */
	int MY_SQL = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_SQL__NAME = SOFTWARE__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_SQL__VERSION = SOFTWARE__VERSION;

	/**
	 * The feature id for the '<em><b>Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_SQL__SERVERS = SOFTWARE__SERVERS;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_SQL__PASSWORD = SOFTWARE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_SQL__USERNAME = SOFTWARE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>My SQL</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_SQL_FEATURE_COUNT = SOFTWARE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>My SQL</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_SQL_OPERATION_COUNT = SOFTWARE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.demo.Server <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Server</em>'.
	 * @see moon.nju.edu.cn.demo.Server
	 * @generated
	 */
	EClass getServer();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.demo.Server#getIP <em>IP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>IP</em>'.
	 * @see moon.nju.edu.cn.demo.Server#getIP()
	 * @see #getServer()
	 * @generated
	 */
	EAttribute getServer_IP();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.demo.Server#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see moon.nju.edu.cn.demo.Server#getType()
	 * @see #getServer()
	 * @generated
	 */
	EAttribute getServer_Type();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.demo.Server#getUsername <em>Username</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Username</em>'.
	 * @see moon.nju.edu.cn.demo.Server#getUsername()
	 * @see #getServer()
	 * @generated
	 */
	EAttribute getServer_Username();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.demo.Server#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see moon.nju.edu.cn.demo.Server#getPassword()
	 * @see #getServer()
	 * @generated
	 */
	EAttribute getServer_Password();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.demo.Software <em>Software</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Software</em>'.
	 * @see moon.nju.edu.cn.demo.Software
	 * @generated
	 */
	EClass getSoftware();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.demo.Software#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see moon.nju.edu.cn.demo.Software#getName()
	 * @see #getSoftware()
	 * @generated
	 */
	EAttribute getSoftware_Name();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.demo.Software#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see moon.nju.edu.cn.demo.Software#getVersion()
	 * @see #getSoftware()
	 * @generated
	 */
	EAttribute getSoftware_Version();

	/**
	 * Returns the meta object for the reference list '{@link moon.nju.edu.cn.demo.Software#getServers <em>Servers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Servers</em>'.
	 * @see moon.nju.edu.cn.demo.Software#getServers()
	 * @see #getSoftware()
	 * @generated
	 */
	EReference getSoftware_Servers();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.demo.ApacheContainer <em>Apache Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Apache Container</em>'.
	 * @see moon.nju.edu.cn.demo.ApacheContainer
	 * @generated
	 */
	EClass getApacheContainer();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.demo.ApacheContainer#getListenPort <em>Listen Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Listen Port</em>'.
	 * @see moon.nju.edu.cn.demo.ApacheContainer#getListenPort()
	 * @see #getApacheContainer()
	 * @generated
	 */
	EAttribute getApacheContainer_ListenPort();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.demo.PHPContainer <em>PHP Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PHP Container</em>'.
	 * @see moon.nju.edu.cn.demo.PHPContainer
	 * @generated
	 */
	EClass getPHPContainer();

	/**
	 * Returns the meta object for the reference '{@link moon.nju.edu.cn.demo.PHPContainer#getDependOn <em>Depend On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Depend On</em>'.
	 * @see moon.nju.edu.cn.demo.PHPContainer#getDependOn()
	 * @see #getPHPContainer()
	 * @generated
	 */
	EReference getPHPContainer_DependOn();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.demo.WebApp <em>Web App</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Web App</em>'.
	 * @see moon.nju.edu.cn.demo.WebApp
	 * @generated
	 */
	EClass getWebApp();

	/**
	 * Returns the meta object for the reference '{@link moon.nju.edu.cn.demo.WebApp#getConnectTo <em>Connect To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Connect To</em>'.
	 * @see moon.nju.edu.cn.demo.WebApp#getConnectTo()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_ConnectTo();

	/**
	 * Returns the meta object for the reference '{@link moon.nju.edu.cn.demo.WebApp#getDependOn <em>Depend On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Depend On</em>'.
	 * @see moon.nju.edu.cn.demo.WebApp#getDependOn()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_DependOn();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.demo.MySQL <em>My SQL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>My SQL</em>'.
	 * @see moon.nju.edu.cn.demo.MySQL
	 * @generated
	 */
	EClass getMySQL();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.demo.MySQL#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see moon.nju.edu.cn.demo.MySQL#getPassword()
	 * @see #getMySQL()
	 * @generated
	 */
	EAttribute getMySQL_Password();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.demo.MySQL#getUsername <em>Username</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Username</em>'.
	 * @see moon.nju.edu.cn.demo.MySQL#getUsername()
	 * @see #getMySQL()
	 * @generated
	 */
	EAttribute getMySQL_Username();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DemoFactory getDemoFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.demo.impl.ServerImpl <em>Server</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.demo.impl.ServerImpl
		 * @see moon.nju.edu.cn.demo.impl.DemoPackageImpl#getServer()
		 * @generated
		 */
		EClass SERVER = eINSTANCE.getServer();

		/**
		 * The meta object literal for the '<em><b>IP</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVER__IP = eINSTANCE.getServer_IP();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVER__TYPE = eINSTANCE.getServer_Type();

		/**
		 * The meta object literal for the '<em><b>Username</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVER__USERNAME = eINSTANCE.getServer_Username();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVER__PASSWORD = eINSTANCE.getServer_Password();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.demo.impl.SoftwareImpl <em>Software</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.demo.impl.SoftwareImpl
		 * @see moon.nju.edu.cn.demo.impl.DemoPackageImpl#getSoftware()
		 * @generated
		 */
		EClass SOFTWARE = eINSTANCE.getSoftware();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE__NAME = eINSTANCE.getSoftware_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE__VERSION = eINSTANCE.getSoftware_Version();

		/**
		 * The meta object literal for the '<em><b>Servers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE__SERVERS = eINSTANCE.getSoftware_Servers();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.demo.impl.ApacheContainerImpl <em>Apache Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.demo.impl.ApacheContainerImpl
		 * @see moon.nju.edu.cn.demo.impl.DemoPackageImpl#getApacheContainer()
		 * @generated
		 */
		EClass APACHE_CONTAINER = eINSTANCE.getApacheContainer();

		/**
		 * The meta object literal for the '<em><b>Listen Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APACHE_CONTAINER__LISTEN_PORT = eINSTANCE.getApacheContainer_ListenPort();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.demo.impl.PHPContainerImpl <em>PHP Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.demo.impl.PHPContainerImpl
		 * @see moon.nju.edu.cn.demo.impl.DemoPackageImpl#getPHPContainer()
		 * @generated
		 */
		EClass PHP_CONTAINER = eINSTANCE.getPHPContainer();

		/**
		 * The meta object literal for the '<em><b>Depend On</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHP_CONTAINER__DEPEND_ON = eINSTANCE.getPHPContainer_DependOn();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.demo.impl.WebAppImpl <em>Web App</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.demo.impl.WebAppImpl
		 * @see moon.nju.edu.cn.demo.impl.DemoPackageImpl#getWebApp()
		 * @generated
		 */
		EClass WEB_APP = eINSTANCE.getWebApp();

		/**
		 * The meta object literal for the '<em><b>Connect To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__CONNECT_TO = eINSTANCE.getWebApp_ConnectTo();

		/**
		 * The meta object literal for the '<em><b>Depend On</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__DEPEND_ON = eINSTANCE.getWebApp_DependOn();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.demo.impl.MySQLImpl <em>My SQL</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.demo.impl.MySQLImpl
		 * @see moon.nju.edu.cn.demo.impl.DemoPackageImpl#getMySQL()
		 * @generated
		 */
		EClass MY_SQL = eINSTANCE.getMySQL();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MY_SQL__PASSWORD = eINSTANCE.getMySQL_Password();

		/**
		 * The meta object literal for the '<em><b>Username</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MY_SQL__USERNAME = eINSTANCE.getMySQL_Username();

	}

} //DemoPackage
