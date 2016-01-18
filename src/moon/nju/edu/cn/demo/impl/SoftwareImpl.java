/**
 */
package moon.nju.edu.cn.demo.impl;

import java.util.Collection;

import moon.nju.edu.cn.demo.DemoPackage;
import moon.nju.edu.cn.demo.Server;
import moon.nju.edu.cn.demo.Software;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Software</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.demo.impl.SoftwareImpl#getName <em>Name</em>}</li>
 *   <li>{@link moon.nju.edu.cn.demo.impl.SoftwareImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link moon.nju.edu.cn.demo.impl.SoftwareImpl#getServers <em>Servers</em>}</li>
 *   <li>{@link moon.nju.edu.cn.demo.impl.SoftwareImpl#getDependOn <em>Depend On</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SoftwareImpl extends MinimalEObjectImpl.Container implements Software {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getServers() <em>Servers</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServers()
	 * @generated
	 * @ordered
	 */
	protected Server servers;

	/**
	 * The cached value of the '{@link #getDependOn() <em>Depend On</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependOn()
	 * @generated
	 * @ordered
	 */
	protected EList<Software> dependOn;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SoftwareImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DemoPackage.Literals.SOFTWARE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DemoPackage.SOFTWARE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DemoPackage.SOFTWARE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Server getServers() {
		if (servers != null && servers.eIsProxy()) {
			InternalEObject oldServers = (InternalEObject)servers;
			servers = (Server)eResolveProxy(oldServers);
			if (servers != oldServers) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DemoPackage.SOFTWARE__SERVERS, oldServers, servers));
			}
		}
		return servers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Server basicGetServers() {
		return servers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServers(Server newServers) {
		Server oldServers = servers;
		servers = newServers;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DemoPackage.SOFTWARE__SERVERS, oldServers, servers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Software> getDependOn() {
		if (dependOn == null) {
			dependOn = new EObjectResolvingEList<Software>(Software.class, this, DemoPackage.SOFTWARE__DEPEND_ON);
		}
		return dependOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DemoPackage.SOFTWARE__NAME:
				return getName();
			case DemoPackage.SOFTWARE__VERSION:
				return getVersion();
			case DemoPackage.SOFTWARE__SERVERS:
				if (resolve) return getServers();
				return basicGetServers();
			case DemoPackage.SOFTWARE__DEPEND_ON:
				return getDependOn();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DemoPackage.SOFTWARE__NAME:
				setName((String)newValue);
				return;
			case DemoPackage.SOFTWARE__VERSION:
				setVersion((String)newValue);
				return;
			case DemoPackage.SOFTWARE__SERVERS:
				setServers((Server)newValue);
				return;
			case DemoPackage.SOFTWARE__DEPEND_ON:
				getDependOn().clear();
				getDependOn().addAll((Collection<? extends Software>)newValue);
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
			case DemoPackage.SOFTWARE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DemoPackage.SOFTWARE__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case DemoPackage.SOFTWARE__SERVERS:
				setServers((Server)null);
				return;
			case DemoPackage.SOFTWARE__DEPEND_ON:
				getDependOn().clear();
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
			case DemoPackage.SOFTWARE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DemoPackage.SOFTWARE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case DemoPackage.SOFTWARE__SERVERS:
				return servers != null;
			case DemoPackage.SOFTWARE__DEPEND_ON:
				return dependOn != null && !dependOn.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //SoftwareImpl
