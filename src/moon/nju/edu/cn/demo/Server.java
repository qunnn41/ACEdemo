/**
 */
package moon.nju.edu.cn.demo;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Server</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.demo.Server#getIP <em>IP</em>}</li>
 *   <li>{@link moon.nju.edu.cn.demo.Server#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see moon.nju.edu.cn.demo.DemoPackage#getServer()
 * @model
 * @generated
 */
public interface Server extends EObject {
	/**
	 * Returns the value of the '<em><b>IP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IP</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IP</em>' attribute.
	 * @see #setIP(String)
	 * @see moon.nju.edu.cn.demo.DemoPackage#getServer_IP()
	 * @model
	 * @generated
	 */
	String getIP();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.demo.Server#getIP <em>IP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IP</em>' attribute.
	 * @see #getIP()
	 * @generated
	 */
	void setIP(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see moon.nju.edu.cn.demo.DemoPackage#getServer_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.demo.Server#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // Server
