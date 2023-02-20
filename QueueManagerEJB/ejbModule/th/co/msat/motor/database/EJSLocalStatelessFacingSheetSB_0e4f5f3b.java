package th.co.msat.motor.database;

import com.ibm.ejs.container.*;
import java.rmi.RemoteException;

/**
 * EJSLocalStatelessFacingSheetSB_0e4f5f3b
 */
public class EJSLocalStatelessFacingSheetSB_0e4f5f3b extends EJSLocalWrapper implements th.co.msat.motor.database.FacingSheetSBLocal {
	/**
	 * EJSLocalStatelessFacingSheetSB_0e4f5f3b
	 */
	public EJSLocalStatelessFacingSheetSB_0e4f5f3b() {
		super();	}
	/**
	 * callQueueRunningHost
	 */
	public void callQueueRunningHost(java.lang.String queueName) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = queueName;
			}
	th.co.msat.motor.database.FacingSheetSBBean beanRef = (th.co.msat.motor.database.FacingSheetSBBean)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			beanRef.callQueueRunningHost(queueName);
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
		return ;
	}
	/**
	 * putJob
	 */
	public void putJob(java.lang.String no, java.lang.String username, java.lang.String status, int piority, long jobgroup) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[5];
				_jacc_parms[0] = no;
				_jacc_parms[1] = username;
				_jacc_parms[2] = status;
				_jacc_parms[3] = new java.lang.Integer(piority);
				_jacc_parms[4] = new java.lang.Long(jobgroup);
			}
	th.co.msat.motor.database.FacingSheetSBBean beanRef = (th.co.msat.motor.database.FacingSheetSBBean)container.preInvoke(this, 1, _EJS_s, _jacc_parms);
			beanRef.putJob(no, username, status, piority, jobgroup);
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
		return ;
	}
}
