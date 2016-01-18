/**
 */
package moon.nju.edu.cn.demo;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Software</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.demo.Software#getName <em>Name</em>}</li>
 *   <li>{@link moon.nju.edu.cn.demo.Software#getVersion <em>Version</em>}</li>
 *   <li>{@link moon.nju.edu.cn.demo.Software#getServers <em>Servers</em>}</li>
 *   <li>{@link moon.nju.edu.cn.demo.Software#getDependOn <em>Depend On</em>}</li>
 * </ul>
 *
 * @see moon.nju.edu.cn.demo.DemoPackage#getSoftware()
 * @model
 * @generated
 */
public interface Software extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see moon.nju.edu.cn.demo.DemoPackage#getSoftware_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.demo.Software#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see moon.nju.edu.cn.demo.DemoPackage#getSoftware_Version()
	 * @model
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.demo.Software#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Servers</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Servers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Servers</em>' reference.
	 * @see #setServers(Server)
	 * @see moon.nju.edu.cn.demo.DemoPackage#getSoftware_Servers()
	 * @model required="true"
	 * @generated
	 */
	Server getServers();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.demo.Software#getServers <em>Servers</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Servers</em>' reference.
	 * @see #getServers()
	 * @generated
	 */
	void setServers(Server value);

	/**
	 * Returns the value of the '<em><b>Depend On</b></em>' reference list.
	 * The list contents are of type {@link moon.nju.edu.cn.demo.Software}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Depend On</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depend On</em>' reference list.
	 * @see moon.nju.edu.cn.demo.DemoPackage#getSoftware_DependOn()
	 * @model
	 * @generated
	 */
	EList<Software> getDependOn();

} // Software
