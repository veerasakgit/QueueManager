package th.co.msat.motor.queuemanager.database;

import com.ibm.ejs.container.*;
import java.rmi.RemoteException;

/**
 * EJSLocalCMPQueuetbHome_6b8e8e23
 */
public class EJSLocalCMPQueuetbHome_6b8e8e23 extends EJSLocalWrapper implements th.co.msat.motor.queuemanager.database.QueuetbLocalHome, th.co.msat.motor.queuemanager.database.websphere_deploy.QueuetbBeanInternalLocalHome_6b8e8e23 {
	/**
	 * EJSLocalCMPQueuetbHome_6b8e8e23
	 */
	public EJSLocalCMPQueuetbHome_6b8e8e23() {
		super();	}
	/**
	 * findAllQueue
	 */
	public java.util.Collection findAllQueue() throws javax.ejb.FinderException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[0];
			}
	th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23 _EJS_beanRef = (th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = _EJS_beanRef.findAllQueue_Local();
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
	 * create
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal create(java.math.BigDecimal queueid) throws javax.ejb.CreateException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		th.co.msat.motor.queuemanager.database.QueuetbLocal _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = queueid;
			}
	th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23 _EJS_beanRef = (th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23)container.preInvoke(this, 1, _EJS_s, _jacc_parms);
			_EJS_result = _EJS_beanRef.create_Local(queueid);
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
	 * findByPrimaryKey
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal findByPrimaryKey(th.co.msat.motor.queuemanager.database.QueuetbKey primaryKey) throws javax.ejb.FinderException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		th.co.msat.motor.queuemanager.database.QueuetbLocal _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = primaryKey;
			}
	th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23 _EJS_beanRef = (th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23)container.preInvoke(this, 2, _EJS_s, _jacc_parms);
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
				container.postInvoke(this, 2, _EJS_s);
			} catch ( RemoteException ex ) {
				_EJS_s.setUncheckedLocalException(ex);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * findByQueueName
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal findByQueueName(java.lang.String queueName) throws javax.ejb.FinderException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		th.co.msat.motor.queuemanager.database.QueuetbLocal _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = queueName;
			}
	th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23 _EJS_beanRef = (th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23)container.preInvoke(this, 3, _EJS_s, _jacc_parms);
			_EJS_result = _EJS_beanRef.findByQueueName_Local(queueName);
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
				container.postInvoke(this, 3, _EJS_s);
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
	th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23 _EJS_beanRef = (th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23)container.preInvoke(this, 4, _EJS_s, _jacc_parms);
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
				container.postInvoke(this, 4, _EJS_s);
			} catch ( RemoteException ex ) {
				_EJS_s.setUncheckedLocalException(ex);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * findQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal findQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local(th.co.msat.motor.queuemanager.database.JobtbKey key) throws javax.ejb.FinderException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		th.co.msat.motor.queuemanager.database.QueuetbLocal _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = key;
			}
	th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23 _EJS_beanRef = (th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23)container.preInvoke(this, -2, _EJS_s, _jacc_parms);
			_EJS_result = _EJS_beanRef.findQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local(key);
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
	/**
	 * findByPrimaryKeyForCMR
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal findByPrimaryKeyForCMR(th.co.msat.motor.queuemanager.database.QueuetbKey key) throws javax.ejb.FinderException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		th.co.msat.motor.queuemanager.database.QueuetbLocal _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = key;
			}
	th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23 _EJS_beanRef = (th.co.msat.motor.queuemanager.database.EJSCMPQueuetbHomeBean_6b8e8e23)container.preInvoke(this, -2, _EJS_s, _jacc_parms);
			_EJS_result = _EJS_beanRef.findByPrimaryKeyForCMR_Local(key);
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
