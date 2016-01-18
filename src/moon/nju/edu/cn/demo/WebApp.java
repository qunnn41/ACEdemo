/**
 */
package moon.nju.edu.cn.demo;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Web App</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.demo.WebApp#getConnectTo <em>Connect To</em>}</li>
 * </ul>
 *
 * @see moon.nju.edu.cn.demo.DemoPackage#getWebApp()
 * @model
 * @generated
 */
public interface WebApp extends Software {
	/**
	 * Returns the value of the '<em><b>Connect To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connect To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connect To</em>' reference.
	 * @see #setConnectTo(MySQL)
	 * @see moon.nju.edu.cn.demo.DemoPackage#getWebApp_ConnectTo()
	 * @model required="true"
	 * @generated
	 */
	MySQL getConnectTo();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.demo.WebApp#getConnectTo <em>Connect To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connect To</em>' reference.
	 * @see #getConnectTo()
	 * @generated
	 */
	void setConnectTo(MySQL value);

} // WebApp
