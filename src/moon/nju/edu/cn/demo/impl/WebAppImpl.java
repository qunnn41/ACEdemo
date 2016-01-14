/**
 */
package moon.nju.edu.cn.demo.impl;

import moon.nju.edu.cn.demo.DemoPackage;
import moon.nju.edu.cn.demo.MySQL;
import moon.nju.edu.cn.demo.PHPContainer;
import moon.nju.edu.cn.demo.WebApp;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Web App</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.demo.impl.WebAppImpl#getConnectTo <em>Connect To</em>}</li>
 *   <li>{@link moon.nju.edu.cn.demo.impl.WebAppImpl#getDependOn <em>Depend On</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WebAppImpl extends SoftwareImpl implements WebApp {
	/**
	 * The cached value of the '{@link #getConnectTo() <em>Connect To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectTo()
	 * @generated
	 * @ordered
	 */
	protected MySQL connectTo;

	/**
	 * The cached value of the '{@link #getDependOn() <em>Depend On</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependOn()
	 * @generated
	 * @ordered
	 */
	protected PHPContainer dependOn;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WebAppImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DemoPackage.Literals.WEB_APP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MySQL getConnectTo() {
		if (connectTo != null && connectTo.eIsProxy()) {
			InternalEObject oldConnectTo = (InternalEObject)connectTo;
			connectTo = (MySQL)eResolveProxy(oldConnectTo);
			if (connectTo != oldConnectTo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DemoPackage.WEB_APP__CONNECT_TO, oldConnectTo, connectTo));
			}
		}
		return connectTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MySQL basicGetConnectTo() {
		return connectTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectTo(MySQL newConnectTo) {
		MySQL oldConnectTo = connectTo;
		connectTo = newConnectTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DemoPackage.WEB_APP__CONNECT_TO, oldConnectTo, connectTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PHPContainer getDependOn() {
		if (dependOn != null && dependOn.eIsProxy()) {
			InternalEObject oldDependOn = (InternalEObject)dependOn;
			dependOn = (PHPContainer)eResolveProxy(oldDependOn);
			if (dependOn != oldDependOn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DemoPackage.WEB_APP__DEPEND_ON, oldDependOn, dependOn));
			}
		}
		return dependOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PHPContainer basicGetDependOn() {
		return dependOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDependOn(PHPContainer newDependOn) {
		PHPContainer oldDependOn = dependOn;
		dependOn = newDependOn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DemoPackage.WEB_APP__DEPEND_ON, oldDependOn, dependOn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DemoPackage.WEB_APP__CONNECT_TO:
				if (resolve) return getConnectTo();
				return basicGetConnectTo();
			case DemoPackage.WEB_APP__DEPEND_ON:
				if (resolve) return getDependOn();
				return basicGetDependOn();
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
			case DemoPackage.WEB_APP__CONNECT_TO:
				setConnectTo((MySQL)newValue);
				return;
			case DemoPackage.WEB_APP__DEPEND_ON:
				setDependOn((PHPContainer)newValue);
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
			case DemoPackage.WEB_APP__CONNECT_TO:
				setConnectTo((MySQL)null);
				return;
			case DemoPackage.WEB_APP__DEPEND_ON:
				setDependOn((PHPContainer)null);
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
			case DemoPackage.WEB_APP__CONNECT_TO:
				return connectTo != null;
			case DemoPackage.WEB_APP__DEPEND_ON:
				return dependOn != null;
		}
		return super.eIsSet(featureID);
	}

} //WebAppImpl
