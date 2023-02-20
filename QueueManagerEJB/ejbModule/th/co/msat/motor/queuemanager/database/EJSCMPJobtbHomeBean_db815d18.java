package th.co.msat.motor.queuemanager.database;

import com.ibm.ejs.container.*;

/**
 * EJSCMPJobtbHomeBean_db815d18
 */
public class EJSCMPJobtbHomeBean_db815d18 extends EJSHome {
	/**
	 * EJSCMPJobtbHomeBean_db815d18
	 */
	public EJSCMPJobtbHomeBean_db815d18() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create_Local
	 */
	public th.co.msat.motor.queuemanager.database.JobtbLocal create_Local(java.math.BigDecimal jobid) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		th.co.msat.motor.queuemanager.database.JobtbLocal result = null;
		boolean createFailed = false;
		boolean preCreateFlag = false;
		try {
			beanO = super.createBeanO();
			th.co.msat.motor.queuemanager.database.JobtbBean bean = (th.co.msat.motor.queuemanager.database.JobtbBean) beanO.getEnterpriseBean();
			preCreateFlag = super.preEjbCreate(beanO);
			bean.ejbCreate(jobid);
			Object ejsKey = keyFromBean(bean);
			result = (th.co.msat.motor.queuemanager.database.JobtbLocal) super.postCreate_Local(beanO, ejsKey, true);
			bean.ejbPostCreate(jobid);
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
	 * findByPrimaryKey_Local
	 */
	public th.co.msat.motor.queuemanager.database.JobtbLocal findByPrimaryKey_Local(th.co.msat.motor.queuemanager.database.JobtbKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return (th.co.msat.motor.queuemanager.database.JobtbLocal)super.activateBean_Local(primaryKey);
	}
	/**
	 * findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local
	 */
	public java.util.Collection findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local(th.co.msat.motor.queuemanager.database.QueuetbKey key) throws javax.ejb.FinderException, java.rmi.RemoteException {
		java.util.Collection result = null;
		EntityBeanO beanO = null;

		try {
			beanO = super.getFinderEntityBeanO();
			th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18 bean = (th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18) beanO.getEnterpriseBean();
			java.util.Collection pKeys = bean.ejbFindJobtbByQ_msatlib_jobtb_queueid_00001Key_Local(key);
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
	 * keyFromBean
	 */
	public Object keyFromBean(javax.ejb.EntityBean generalEJB) {
		th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18 tmpEJB = (th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18) generalEJB;
		th.co.msat.motor.queuemanager.database.JobtbKey keyClass = new th.co.msat.motor.queuemanager.database.JobtbKey();
		keyClass.jobid = tmpEJB.getJobid();
		return keyClass;
	}
	/**
	 * keyFromFields
	 */
	public th.co.msat.motor.queuemanager.database.JobtbKey keyFromFields(java.math.BigDecimal f0) {
		th.co.msat.motor.queuemanager.database.JobtbKey keyClass = new th.co.msat.motor.queuemanager.database.JobtbKey();
		keyClass.jobid = f0;
		return keyClass;
	}
}
