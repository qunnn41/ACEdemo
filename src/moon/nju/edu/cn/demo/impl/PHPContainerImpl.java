/**
 */
package moon.nju.edu.cn.demo.impl;

import moon.nju.edu.cn.demo.ApacheContainer;
import moon.nju.edu.cn.demo.DemoPackage;
import moon.nju.edu.cn.demo.PHPContainer;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PHP Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.demo.impl.PHPContainerImpl#getDependOn <em>Depend On</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PHPContainerImpl extends SoftwareImpl implements PHPContainer {
	/**
	 * The cached value of the '{@link #getDependOn() <em>Depend On</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependOn()
	 * @generated
	 * @ordered
	 */
	protected ApacheContainer dependOn;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PHPContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DemoPackage.Literals.PHP_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApacheContainer getDependOn() {
		if (dependOn != null && dependOn.eIsProxy()) {
			InternalEObject oldDependOn = (InternalEObject)dependOn;
			dependOn = (ApacheContainer)eResolveProxy(oldDependOn);
			if (dependOn != oldDependOn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DemoPackage.PHP_CONTAINER__DEPEND_ON, oldDependOn, dependOn));
			}
		}
		return dependOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApacheContainer basicGetDependOn() {
		return dependOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDependOn(ApacheContainer newDependOn) {
		ApacheContainer oldDependOn = dependOn;
		dependOn = newDependOn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DemoPackage.PHP_CONTAINER__DEPEND_ON, oldDependOn, dependOn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DemoPackage.PHP_CONTAINER__DEPEND_ON:
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
			case DemoPackage.PHP_CONTAINER__DEPEND_ON:
				setDependOn((ApacheContainer)newValue);
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
			case DemoPackage.PHP_CONTAINER__DEPEND_ON:
				setDependOn((ApacheContainer)null);
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
			case DemoPackage.PHP_CONTAINER__DEPEND_ON:
				return dependOn != null;
		}
		return super.eIsSet(featureID);
	}

} //PHPContainerImpl
