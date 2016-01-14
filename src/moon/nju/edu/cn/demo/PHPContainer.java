/**
 */
package moon.nju.edu.cn.demo;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PHP Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.demo.PHPContainer#getDependOn <em>Depend On</em>}</li>
 * </ul>
 *
 * @see moon.nju.edu.cn.demo.DemoPackage#getPHPContainer()
 * @model
 * @generated
 */
public interface PHPContainer extends Software {
	/**
	 * Returns the value of the '<em><b>Depend On</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Depend On</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depend On</em>' reference.
	 * @see #setDependOn(ApacheContainer)
	 * @see moon.nju.edu.cn.demo.DemoPackage#getPHPContainer_DependOn()
	 * @model
	 * @generated
	 */
	ApacheContainer getDependOn();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.demo.PHPContainer#getDependOn <em>Depend On</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Depend On</em>' reference.
	 * @see #getDependOn()
	 * @generated
	 */
	void setDependOn(ApacheContainer value);

} // PHPContainer
