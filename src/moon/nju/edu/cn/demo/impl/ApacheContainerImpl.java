/**
 */
package moon.nju.edu.cn.demo.impl;

import moon.nju.edu.cn.demo.ApacheContainer;
import moon.nju.edu.cn.demo.DemoPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Apache Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.demo.impl.ApacheContainerImpl#getListenPort <em>Listen Port</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ApacheContainerImpl extends SoftwareImpl implements ApacheContainer {
	/**
	 * The default value of the '{@link #getListenPort() <em>Listen Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getListenPort()
	 * @generated
	 * @ordered
	 */
	protected static final int LISTEN_PORT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getListenPort() <em>Listen Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getListenPort()
	 * @generated
	 * @ordered
	 */
	protected int listenPort = LISTEN_PORT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ApacheContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DemoPackage.Literals.APACHE_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getListenPort() {
		return listenPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListenPort(int newListenPort) {
		int oldListenPort = listenPort;
		listenPort = newListenPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DemoPackage.APACHE_CONTAINER__LISTEN_PORT, oldListenPort, listenPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DemoPackage.APACHE_CONTAINER__LISTEN_PORT:
				return getListenPort();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DemoPackage.APACHE_CONTAINER__LISTEN_PORT:
				setListenPort((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DemoPackage.APACHE_CONTAINER__LISTEN_PORT:
				setListenPort(LISTEN_PORT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DemoPackage.APACHE_CONTAINER__LISTEN_PORT:
				return listenPort != LISTEN_PORT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (listenPort: ");
		result.append(listenPort);
		result.append(')');
		return result.toString();
	}

} //ApacheContainerImpl
