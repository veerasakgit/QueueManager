package th.co.msat.motor.queuemanager.database;

import com.ibm.ejs.container.*;
import java.rmi.RemoteException;

/**
 * EJSLocalCMPJobtbHome_db815d18
 */
public class EJSLocalCMPJobtbHome_db815d18 extends EJSLocalWrapper implements th.co.msat.motor.queuemanager.database.JobtbLocalHome, th.co.msat.motor.queuemanager.database.websphere_deploy.JobtbBeanInternalLocalHome_db815d18 {
	/**
	 * EJSLocalCMPJobtbHome_db815d18
	 */
	public EJSLocalCMPJobtbHome_db815d18() {
		super();	}
	/**
	 * create
	 */
	public th.co.msat.motor.queuemanager.database.JobtbLocal create(java.math.BigDecimal jobid) throws javax.ejb.CreateException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		th.co.msat.motor.queuemanager.database.JobtbLocal _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = jobid;
			}
	th.co.msat.motor.queuemanager.database.EJSCMPJobtbHomeBean_db815d18 _EJS_beanRef = (th.co.msat.motor.queuemanager.database.EJSCMPJobtbHomeBean_db815d18)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = _EJS_beanRef.create_Local(jobid);
		}
		catch (javax.ejb.CreateException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
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
	 * findByPrimaryKey
	 */
	public th.co.msat.motor.queuemanager.database.JobtbLocal findByPrimaryKey(th.co.msat.motor.queuemanager.database.JobtbKey primaryKey) throws javax.ejb.FinderException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		th.co.msat.motor.queuemanager.database.JobtbLocal _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = primaryKey;
			}
	th.co.msat.motor.queuemanager.database.EJSCMPJobtbHomeBean_db815d18 _EJS_beanRef = (th.co.msat.motor.queuemanager.database.EJSCMPJobtbHomeBean_db815d18)container.preInvoke(this, 1, _EJS_s, _jacc_parms);
			_EJS_result = _EJS_beanRef.findByPrimaryKey_Local(primaryKey);
		}
		catch (javax.ejb.FinderException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
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
	/**
	 * remove
	 */
	public void remove(java.lang.Object arg0) throws javax.ejb.RemoveException, javax.ejb.EJBException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = arg0;
			}
	th.co.msat.motor.queuemanager.database.EJSCMPJobtbHomeBean_db815d18 _EJS_beanRef = (th.co.msat.motor.queuemanager.database.EJSCMPJobtbHomeBean_db815d18)container.preInvoke(this, 2, _EJS_s, _jacc_parms);
			_EJS_beanRef.remove(arg0);
		}
		catch (javax.ejb.RemoveException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (javax.ejb.EJBException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (java.rmi.RemoteException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedLocalException(ex);
		}

		finally {
			try {
				container.postInvoke(this, 2, _EJS_s);
			} catch ( RemoteException ex ) {
				_EJS_s.setUncheckedLocalException(ex);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local
	 */
	public java.util.Collection findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local(th.co.msat.motor.queuemanager.database.QueuetbKey key) throws javax.ejb.FinderException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = key;
			}
	th.co.msat.motor.queuemanager.database.EJSCMPJobtbHomeBean_db815d18 _EJS_beanRef = (th.co.msat.motor.queuemanager.database.EJSCMPJobtbHomeBean_db815d18)container.preInvoke(this, -2, _EJS_s, _jacc_parms);
			_EJS_result = _EJS_beanRef.findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local(key);
		}
		catch (javax.ejb.FinderException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedLocalException(ex);
		}

		finally {
			try {
				container.postInvoke(this, -2, _EJS_s);
			} catch ( RemoteException ex ) {
				_EJS_s.setUncheckedLocalException(ex);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
}
