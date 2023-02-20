package th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1;

/**
 * JobtbBeanInjectorImpl_db815d18
 */
public class JobtbBeanInjectorImpl_db815d18 implements th.co.msat.motor.queuemanager.database.websphere_deploy.JobtbBeanInjector_db815d18 {
	/**
	 * ejbCreate
	 */
	public void ejbCreate(com.ibm.ws.ejbpersistence.beanextensions.ConcreteBean cb, javax.resource.cci.IndexedRecord record) {
		th.co.msat.motor.queuemanager.database.QueuetbKey tempQueuetbKey;

		th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18 concreteBean=(th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18)cb;
		record.set(0,concreteBean.getJobid());
		record.set(1,concreteBean.getMessage());
		record.set(2,concreteBean.getStatus());
		record.set(3,concreteBean.getPiority());
		record.set(4,concreteBean.getSubmitdate());
		record.set(5,concreteBean.getSubmittime());
		record.set(6,concreteBean.getSubmituser());
		record.set(7,concreteBean.getUpdatedate());
		record.set(8,concreteBean.getUpdatetime());
		record.set(9,concreteBean.getUpdateuser());
		record.set(10,concreteBean.getUpdateprogram());
		record.set(11,concreteBean.getRecordstatus());
		record.set(12,concreteBean.getReferenceno());
		record.set(13,concreteBean.getJobgroup());
		tempQueuetbKey= concreteBean.getQ_msatlib_jobtb_queueid_00001Key();
		if(tempQueuetbKey==null)
			record.set(14,null);
		else
			record.set(14,tempQueuetbKey.queueid);
	}
	/**
	 * ejbStore
	 */
	public void ejbStore(com.ibm.ws.ejbpersistence.beanextensions.ConcreteBean cb, javax.resource.cci.IndexedRecord record) {
		th.co.msat.motor.queuemanager.database.QueuetbKey tempQueuetbKey;

		th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18 concreteBean=(th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18)cb;
		record.set(0,concreteBean.getJobid());
		record.set(1,concreteBean.getMessage());
		record.set(2,concreteBean.getStatus());
		record.set(3,concreteBean.getPiority());
		record.set(4,concreteBean.getSubmitdate());
		record.set(5,concreteBean.getSubmittime());
		record.set(6,concreteBean.getSubmituser());
		record.set(7,concreteBean.getUpdatedate());
		record.set(8,concreteBean.getUpdatetime());
		record.set(9,concreteBean.getUpdateuser());
		record.set(10,concreteBean.getUpdateprogram());
		record.set(11,concreteBean.getRecordstatus());
		record.set(12,concreteBean.getReferenceno());
		record.set(13,concreteBean.getJobgroup());
		tempQueuetbKey= concreteBean.getQ_msatlib_jobtb_queueid_00001Key();
		if(tempQueuetbKey==null)
			record.set(14,null);
		else
			record.set(14,tempQueuetbKey.queueid);
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove(com.ibm.ws.ejbpersistence.beanextensions.ConcreteBean cb, javax.resource.cci.IndexedRecord record) {
		th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18 concreteBean=(th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18)cb;
		record.set(0,concreteBean.getJobid());
	}
	/**
	 * ejbFindByPrimaryKey
	 */
	public void ejbFindByPrimaryKey(Object pkeyObject, javax.resource.cci.IndexedRecord record) {
		th.co.msat.motor.queuemanager.database.JobtbKey pkey=(th.co.msat.motor.queuemanager.database.JobtbKey)pkeyObject;
		record.set(0,pkey.jobid);
	}
	/**
	 * findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local
	 */
	public void findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local(th.co.msat.motor.queuemanager.database.QueuetbKey fkey, javax.resource.cci.IndexedRecord record) {
		record.set(0,fkey.queueid);
	}
	/**
	 * readReadChecking
	 */
	public void readReadChecking(com.ibm.ws.ejbpersistence.beanextensions.ConcreteBean cb, javax.resource.cci.IndexedRecord record) {
		th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18 concreteBean=(th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18)cb;
		record.set(0,concreteBean.getJobid());
		record.set(1,concreteBean.getJobgroup());
	}
	/**
	 * ejbPartialStore
	 */
	public void ejbPartialStore(com.ibm.ws.ejbpersistence.beanextensions.ConcreteBean cb, javax.resource.cci.IndexedRecord record) {
		th.co.msat.motor.queuemanager.database.QueuetbKey tempQueuetbKey;

		th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18 concreteBean=(th.co.msat.motor.queuemanager.database.ConcreteJobtb_db815d18)cb;
		com.ibm.ws.ejbpersistence.beanextensions.ConcreteBeanInstanceExtension  instanceExtension = (com.ibm.ws.ejbpersistence.beanextensions.ConcreteBeanInstanceExtension)concreteBean._WSCB_getInstanceInfo();
		 
		record.set(0,concreteBean.getJobid());
		if(instanceExtension.isDirty(1))
		{
			record.set(1,concreteBean.getMessage());
		}
		if(instanceExtension.isDirty(2))
		{
			record.set(2,concreteBean.getStatus());
		}
		if(instanceExtension.isDirty(3))
		{
			record.set(3,concreteBean.getPiority());
		}
		if(instanceExtension.isDirty(4))
		{
			record.set(4,concreteBean.getSubmitdate());
		}
		if(instanceExtension.isDirty(5))
		{
			record.set(5,concreteBean.getSubmittime());
		}
		if(instanceExtension.isDirty(6))
		{
			record.set(6,concreteBean.getSubmituser());
		}
		if(instanceExtension.isDirty(7))
		{
			record.set(7,concreteBean.getUpdatedate());
		}
		if(instanceExtension.isDirty(8))
		{
			record.set(8,concreteBean.getUpdatetime());
		}
		if(instanceExtension.isDirty(9))
		{
			record.set(9,concreteBean.getUpdateuser());
		}
		if(instanceExtension.isDirty(10))
		{
			record.set(10,concreteBean.getUpdateprogram());
		}
		if(instanceExtension.isDirty(11))
		{
			record.set(11,concreteBean.getRecordstatus());
		}
		if(instanceExtension.isDirty(12))
		{
			record.set(12,concreteBean.getReferenceno());
		}
		if(instanceExtension.isDirty(13))
		{
			record.set(13,concreteBean.getJobgroup());
		}
		if(instanceExtension.isDirty(15))
		{
			tempQueuetbKey= concreteBean.getQ_msatlib_jobtb_queueid_00001Key();
		if(tempQueuetbKey==null)
			record.set(14,null);
		else
			record.set(14,tempQueuetbKey.queueid);
		}
	}
	/**
	 * ejbStoreGetDirtyColumnFields
	 */
	public void ejbStoreGetDirtyColumnFields(com.ibm.ws.ejbpersistence.beanextensions.ConcreteBean cb, boolean[] dirtyBitmap) {
		
	}
}
