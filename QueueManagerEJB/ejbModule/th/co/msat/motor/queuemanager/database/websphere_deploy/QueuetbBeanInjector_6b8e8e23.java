package th.co.msat.motor.queuemanager.database.websphere_deploy;

/**
 * Injector interface for Enterprise Bean: Queuetb
 */
public interface QueuetbBeanInjector_6b8e8e23 extends com.ibm.ws.ejbpersistence.beanextensions.EJBPartialInjector {
	/**
	 * ejbFindByQueueName
	 */
	public void ejbFindByQueueName(java.lang.String queueName, javax.resource.cci.IndexedRecord record);
	/**
	 * findQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local
	 */
	public void findQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local(th.co.msat.motor.queuemanager.database.JobtbKey key, javax.resource.cci.IndexedRecord record);
}
