package th.co.msat.motor.database;

import com.ibm.ejs.container.*;
import java.rmi.RemoteException;

/**
 * EJSLocalStatelessNotificationFacade_1bcd05ff
 */
public class EJSLocalStatelessNotificationFacade_1bcd05ff extends EJSLocalWrapper implements th.co.msat.motor.database.NotificationFacadeLocal {
	/**
	 * EJSLocalStatelessNotificationFacade_1bcd05ff
	 */
	public EJSLocalStatelessNotificationFacade_1bcd05ff() {
		super();	}
	/**
	 * getRegisteredClaim
	 */
	public java.util.Collection getRegisteredClaim(long jobgroup) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(jobgroup);
			}
	th.co.msat.motor.database.NotificationFacadeBean beanRef = (th.co.msat.motor.database.NotificationFacadeBean)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getRegisteredClaim(jobgroup);
		}
		catch (java.lang.RuntimeException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (java.rmi.RemoteException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedLocalException(ex);
		}

		finally {
			try {
				container.postInvoke(this, 0, _EJS_s);
			} catch ( RemoteException ex ) {
				_EJS_s.setUncheckedLocalException(ex);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * searchUnAdjustClaim
	 */
	public java.util.Collection searchUnAdjustClaim(java.lang.String notiKeyword, boolean isold) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = notiKeyword;
				_jacc_parms[1] = new java.lang.Boolean(isold);
			}
	th.co.msat.motor.database.NotificationFacadeBean beanRef = (th.co.msat.motor.database.NotificationFacadeBean)container.preInvoke(this, 1, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.searchUnAdjustClaim(notiKeyword, isold);
		}
		catch (java.lang.RuntimeException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (java.rmi.RemoteException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedLocalException(ex);
		}

		finally {
			try {
				container.postInvoke(this, 1, _EJS_s);
			} catch ( RemoteException ex ) {
				_EJS_s.setUncheckedLocalException(ex);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
}
