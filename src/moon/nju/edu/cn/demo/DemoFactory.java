/**
 */
package moon.nju.edu.cn.demo;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see moon.nju.edu.cn.demo.DemoPackage
 * @generated
 */
public interface DemoFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DemoFactory eINSTANCE = moon.nju.edu.cn.demo.impl.DemoFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Server</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Server</em>'.
	 * @generated
	 */
	Server createServer();

	/**
	 * Returns a new object of class '<em>Software</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Software</em>'.
	 * @generated
	 */
	Software createSoftware();

	/**
	 * Returns a new object of class '<em>Apache Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Apache Container</em>'.
	 * @generated
	 */
	ApacheContainer createApacheContainer();

	/**
	 * Returns a new object of class '<em>PHP Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>PHP Container</em>'.
	 * @generated
	 */
	PHPContainer createPHPContainer();

	/**
	 * Returns a new object of class '<em>Web App</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Web App</em>'.
	 * @generated
	 */
	WebApp createWebApp();

	/**
	 * Returns a new object of class '<em>My SQL</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>My SQL</em>'.
	 * @generated
	 */
	MySQL createMySQL();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DemoPackage getDemoPackage();

} //DemoFactory
