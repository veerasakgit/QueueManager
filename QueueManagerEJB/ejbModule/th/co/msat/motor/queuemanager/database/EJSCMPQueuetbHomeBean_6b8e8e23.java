package th.co.msat.motor.queuemanager.database;

import com.ibm.ejs.container.*;

/**
 * EJSCMPQueuetbHomeBean_6b8e8e23
 */
public class EJSCMPQueuetbHomeBean_6b8e8e23 extends EJSHome {
	/**
	 * EJSCMPQueuetbHomeBean_6b8e8e23
	 */
	public EJSCMPQueuetbHomeBean_6b8e8e23() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findByQueueName_Local
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal findByQueueName_Local(java.lang.String queueName) throws javax.ejb.FinderException, java.rmi.RemoteException {
		th.co.msat.motor.queuemanager.database.QueuetbLocal result = null;
		EntityBeanO beanO = null;

		th.co.msat.motor.queuemanager.database.QueuetbKey pKey = null;
		try {
			beanO = super.getFinderEntityBeanO();
			th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23 bean = (th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23) beanO.getEnterpriseBean();
			pKey = bean.ejbFindByQueueName_Local(queueName);
			result = (th.co.msat.motor.queuemanager.database.QueuetbLocal)activateBean_Local(pKey);
			super.releaseFinderEntityBeanO(beanO);
			beanO = null;
		}
		catch (javax.ejb.FinderException finderEx) {
			super.releaseFinderEntityBeanO(beanO);
			beanO = null;
			throw finderEx;
		}
		finally {
			if (beanO != null )
				super.discardFinderEntityBeanO(beanO);
		}
		return result;
	}
	/**
	 * create_Local
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal create_Local(java.math.BigDecimal queueid) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		th.co.msat.motor.queuemanager.database.QueuetbLocal result = null;
		boolean createFailed = false;
		boolean preCreateFlag = false;
		try {
			beanO = super.createBeanO();
			th.co.msat.motor.queuemanager.database.QueuetbBean bean = (th.co.msat.motor.queuemanager.database.QueuetbBean) beanO.getEnterpriseBean();
			preCreateFlag = super.preEjbCreate(beanO);
			bean.ejbCreate(queueid);
			Object ejsKey = keyFromBean(bean);
			result = (th.co.msat.motor.queuemanager.database.QueuetbLocal) super.postCreate_Local(beanO, ejsKey, true);
			bean.ejbPostCreate(queueid);
			super.afterPostCreate(beanO, ejsKey);
		}
		catch (javax.ejb.CreateException ex) {
			createFailed = true;
			throw ex;
		} catch (java.rmi.RemoteException ex) {
			createFailed = true;
			throw ex;
		} catch (Throwable ex) {
			createFailed = true;
			throw new CreateFailureException(ex);
		} finally {
			if(preCreateFlag && !createFailed)
				super.afterPostCreateCompletion(beanO);
			if (createFailed) {
				super.createFailure(beanO);
			}
		}
		return result;
	}
	/**
	 * findAllQueue_Local
	 */
	public java.util.Collection findAllQueue_Local() throws javax.ejb.FinderException, java.rmi.RemoteException {
		java.util.Collection result = null;
		EntityBeanO beanO = null;

		try {
			beanO = super.getFinderEntityBeanO();
			th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23 bean = (th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23) beanO.getEnterpriseBean();
			java.util.Collection pKeys = bean.ejbFindAllQueue_Local();
			result = super.getCMP20Collection_Local(pKeys);
			super.releaseFinderEntityBeanO(beanO);
			beanO = null;
		}
		catch (javax.ejb.FinderException finderEx) {
			super.releaseFinderEntityBeanO(beanO);
			beanO = null;
			throw finderEx;
		}
		finally {
			if (beanO != null )
				super.discardFinderEntityBeanO(beanO);
		}
		return result;
	}
	/**
	 * findByPrimaryKey_Local
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal findByPrimaryKey_Local(th.co.msat.motor.queuemanager.database.QueuetbKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return (th.co.msat.motor.queuemanager.database.QueuetbLocal)super.activateBean_Local(primaryKey);
	}
	/**
	 * findQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal findQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local(th.co.msat.motor.queuemanager.database.JobtbKey key) throws javax.ejb.FinderException, java.rmi.RemoteException {
		th.co.msat.motor.queuemanager.database.QueuetbLocal result = null;
		EntityBeanO beanO = null;

		th.co.msat.motor.queuemanager.database.QueuetbKey pKey = null;
		try {
			beanO = super.getFinderEntityBeanO();
			th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23 bean = (th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23) beanO.getEnterpriseBean();
			pKey = bean.ejbFindQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local(key);
			result = (th.co.msat.motor.queuemanager.database.QueuetbLocal)activateBean_Local(pKey);
			super.releaseFinderEntityBeanO(beanO);
			beanO = null;
		}
		catch (javax.ejb.FinderException finderEx) {
			super.releaseFinderEntityBeanO(beanO);
			beanO = null;
			throw finderEx;
		}
		finally {
			if (beanO != null )
				super.discardFinderEntityBeanO(beanO);
		}
		return result;
	}
	/**
	 * findByPrimaryKeyForCMR_Local
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal findByPrimaryKeyForCMR_Local(th.co.msat.motor.queuemanager.database.QueuetbKey key) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return (th.co.msat.motor.queuemanager.database.QueuetbLocal)super.activateBean_Local(key);
	}
	/**
	 * keyFromBean
	 */
	public Object keyFromBean(javax.ejb.EntityBean generalEJB) {
		th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23 tmpEJB = (th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23) generalEJB;
		th.co.msat.motor.queuemanager.database.QueuetbKey keyClass = new th.co.msat.motor.queuemanager.database.QueuetbKey();
		keyClass.queueid = tmpEJB.getQueueid();
		return keyClass;
	}
	/**
	 * keyFromFields
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbKey keyFromFields(java.math.BigDecimal f0) {
		th.co.msat.motor.queuemanager.database.QueuetbKey keyClass = new th.co.msat.motor.queuemanager.database.QueuetbKey();
		keyClass.queueid = f0;
		return keyClass;
	}
}
