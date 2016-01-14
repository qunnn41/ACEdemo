/**
 */
package moon.nju.edu.cn.demo;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Apache Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.demo.ApacheContainer#getListenPort <em>Listen Port</em>}</li>
 * </ul>
 *
 * @see moon.nju.edu.cn.demo.DemoPackage#getApacheContainer()
 * @model
 * @generated
 */
public interface ApacheContainer extends Software {
	/**
	 * Returns the value of the '<em><b>Listen Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listen Port</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Listen Port</em>' attribute.
	 * @see #setListenPort(int)
	 * @see moon.nju.edu.cn.demo.DemoPackage#getApacheContainer_ListenPort()
	 * @model
	 * @generated
	 */
	int getListenPort();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.demo.ApacheContainer#getListenPort <em>Listen Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Listen Port</em>' attribute.
	 * @see #getListenPort()
	 * @generated
	 */
	void setListenPort(int value);

} // ApacheContainer
