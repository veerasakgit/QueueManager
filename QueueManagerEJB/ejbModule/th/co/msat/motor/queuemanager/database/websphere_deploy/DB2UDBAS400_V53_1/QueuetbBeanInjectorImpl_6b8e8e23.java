package th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1;

/**
 * QueuetbBeanInjectorImpl_6b8e8e23
 */
public class QueuetbBeanInjectorImpl_6b8e8e23 implements th.co.msat.motor.queuemanager.database.websphere_deploy.QueuetbBeanInjector_6b8e8e23 {
	/**
	 * ejbCreate
	 */
	public void ejbCreate(com.ibm.ws.ejbpersistence.beanextensions.ConcreteBean cb, javax.resource.cci.IndexedRecord record) {
		th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23 concreteBean=(th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23)cb;
		record.set(0,concreteBean.getQueueid());
		record.set(1,concreteBean.getQueuename());
		record.set(2,concreteBean.getSize());
		record.set(3,concreteBean.getAvailable());
		record.set(4,concreteBean.getIsterminate());
		record.set(5,concreteBean.getWorkingtime());
		record.set(6,concreteBean.getMessageclass());
		record.set(7,concreteBean.getActivateclass());
		record.set(8,concreteBean.getCreatedate());
		record.set(9,concreteBean.getCreatetime());
		record.set(10,concreteBean.getCreateuser());
		record.set(11,concreteBean.getUpdatedate());
		record.set(12,concreteBean.getUpdatetime());
		record.set(13,concreteBean.getUpdateuser());
		record.set(14,concreteBean.getUpdateprogram());
		record.set(15,concreteBean.getRecordstatus());
	}
	/**
	 * ejbStore
	 */
	public void ejbStore(com.ibm.ws.ejbpersistence.beanextensions.ConcreteBean cb, javax.resource.cci.IndexedRecord record) {
		th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23 concreteBean=(th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23)cb;
		record.set(0,concreteBean.getQueueid());
		record.set(1,concreteBean.getQueuename());
		record.set(2,concreteBean.getSize());
		record.set(3,concreteBean.getAvailable());
		record.set(4,concreteBean.getIsterminate());
		record.set(5,concreteBean.getWorkingtime());
		record.set(6,concreteBean.getMessageclass());
		record.set(7,concreteBean.getActivateclass());
		record.set(8,concreteBean.getCreatedate());
		record.set(9,concreteBean.getCreatetime());
		record.set(10,concreteBean.getCreateuser());
		record.set(11,concreteBean.getUpdatedate());
		record.set(12,concreteBean.getUpdatetime());
		record.set(13,concreteBean.getUpdateuser());
		record.set(14,concreteBean.getUpdateprogram());
		record.set(15,concreteBean.getRecordstatus());
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove(com.ibm.ws.ejbpersistence.beanextensions.ConcreteBean cb, javax.resource.cci.IndexedRecord record) {
		th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23 concreteBean=(th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23)cb;
		record.set(0,concreteBean.getQueueid());
	}
	/**
	 * ejbFindByQueueName
	 */
	public void ejbFindByQueueName(java.lang.String queueName, javax.resource.cci.IndexedRecord record) {
		record.set(0,queueName);
	}
	/**
	 * ejbFindByPrimaryKey
	 */
	public void ejbFindByPrimaryKey(Object pkeyObject, javax.resource.cci.IndexedRecord record) {
		th.co.msat.motor.queuemanager.database.QueuetbKey pkey=(th.co.msat.motor.queuemanager.database.QueuetbKey)pkeyObject;
		record.set(0,pkey.queueid);
	}
	/**
	 * findQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local
	 */
	public void findQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local(th.co.msat.motor.queuemanager.database.JobtbKey fkey, javax.resource.cci.IndexedRecord record) {
		record.set(0,fkey.jobid);
	}
	/**
	 * readReadChecking
	 */
	public void readReadChecking(com.ibm.ws.ejbpersistence.beanextensions.ConcreteBean cb, javax.resource.cci.IndexedRecord record) {
		th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23 concreteBean=(th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23)cb;
		record.set(0,concreteBean.getQueueid());
	}
	/**
	 * ejbPartialStore
	 */
	public void ejbPartialStore(com.ibm.ws.ejbpersistence.beanextensions.ConcreteBean cb, javax.resource.cci.IndexedRecord record) {
		th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23 concreteBean=(th.co.msat.motor.queuemanager.database.ConcreteQueuetb_6b8e8e23)cb;
		com.ibm.ws.ejbpersistence.beanextensions.ConcreteBeanInstanceExtension  instanceExtension = (com.ibm.ws.ejbpersistence.beanextensions.ConcreteBeanInstanceExtension)concreteBean._WSCB_getInstanceInfo();
		 
		record.set(0,concreteBean.getQueueid());
		if(instanceExtension.isDirty(1))
		{
			record.set(1,concreteBean.getQueuename());
		}
		if(instanceExtension.isDirty(2))
		{
			record.set(2,concreteBean.getSize());
		}
		if(instanceExtension.isDirty(3))
		{
			record.set(3,concreteBean.getAvailable());
		}
		if(instanceExtension.isDirty(4))
		{
			record.set(4,concreteBean.getIsterminate());
		}
		if(instanceExtension.isDirty(5))
		{
			record.set(5,concreteBean.getWorkingtime());
		}
		if(instanceExtension.isDirty(6))
		{
			record.set(6,concreteBean.getMessageclass());
		}
		if(instanceExtension.isDirty(7))
		{
			record.set(7,concreteBean.getActivateclass());
		}
		if(instanceExtension.isDirty(8))
		{
			record.set(8,concreteBean.getCreatedate());
		}
		if(instanceExtension.isDirty(9))
		{
			record.set(9,concreteBean.getCreatetime());
		}
		if(instanceExtension.isDirty(10))
		{
			record.set(10,concreteBean.getCreateuser());
		}
		if(instanceExtension.isDirty(11))
		{
			record.set(11,concreteBean.getUpdatedate());
		}
		if(instanceExtension.isDirty(12))
		{
			record.set(12,concreteBean.getUpdatetime());
		}
		if(instanceExtension.isDirty(13))
		{
			record.set(13,concreteBean.getUpdateuser());
		}
		if(instanceExtension.isDirty(14))
		{
			record.set(14,concreteBean.getUpdateprogram());
		}
		if(instanceExtension.isDirty(15))
		{
			record.set(15,concreteBean.getRecordstatus());
		}
	}
	/**
	 * ejbStoreGetDirtyColumnFields
	 */
	public void ejbStoreGetDirtyColumnFields(com.ibm.ws.ejbpersistence.beanextensions.ConcreteBean cb, boolean[] dirtyBitmap) {
		
	}
}
