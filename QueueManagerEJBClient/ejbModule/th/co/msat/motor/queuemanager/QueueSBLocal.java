package th.co.msat.motor.queuemanager;
import th.co.msat.motor.queuemanager.vo.JobVO;
import th.co.msat.motor.queuemanager.vo.QueueVO;
import java.util.Collection;
import th.co.msat.motor.queuemanager.message.Message;
import java.math.BigDecimal;
import th.co.msat.motor.queuemanager.vo.QueueConfigurationVO;
import java.util.Date;
/**
 * Local interface for Enterprise Bean: QueueSB
 */
public interface QueueSBLocal extends javax.ejb.EJBLocalObject {
	public void put(String queueName, JobVO jobvo) throws Exception;
	public void createQueue(QueueVO q) throws Exception;
	public Collection getAllQueueName();
	public void stimulateQueue() throws Exception;
	public boolean isQueueTerminate(String queueName) throws Exception;
	public QueueConfigurationVO getQueueConfiguration(String qname)
		throws Exception;
	public Collection searchJob(
		String qname,
		String jobGroup,
		String submitUser,
		Date submitDateFrom,
		Date submitDateTo,
		String[] status,
		String refNo) throws Exception;
	public void deleteJob(BigDecimal jobid) throws Exception;
	public void put(
		String queueName,
		Message message,
		int piority,
		String username,
		String programId,
		String status,
		long jobgroup) throws Exception;
	public void putCollection(
		String queueName,
		Collection message,
		int piority,
		String username,
		String programId,
		String status,
		long jobgroup) throws Exception;
	public void updateQueueTerminate(String qname, String status)
		throws Exception;
	public void updateJobStatus(BigDecimal jobId, String jobstatus)
		throws Exception;
	public void updateJobStatus(Collection c, String status) throws Exception;
	/*protected JobtbLocal createJobtbLocal(BigDecimal jobid) {
	 JobtbLocalHome aJobtbLocalHome = (JobtbLocalHome) ServiceLocatorManager
	 .getLocalHome(STATIC_JobtbLocalHome_REF_NAME,
	 STATIC_JobtbLocalHome_CLASS);
	 try {
	 if (aJobtbLocalHome != null)
	 return aJobtbLocalHome.create(jobid);
	 } catch (javax.ejb.CreateException ce) {
	 // TODO Auto-generated catch block
	 ce.printStackTrace();
	 }
	 return null;
	 }*/

	/*protected QueuetbLocal find_QueuetbLocalHome_findByQueueName(
	 String queueName) {
	 QueuetbLocalHome aQueuetbLocalHome = (QueuetbLocalHome) ServiceLocatorManager
	 .getLocalHome(STATIC_QueuetbLocalHome_REF_NAME,
	 STATIC_QueuetbLocalHome_CLASS);
	 try {
	 if (aQueuetbLocalHome != null)
	 return aQueuetbLocalHome.findByQueueName(queueName);
	 } catch (javax.ejb.FinderException fe) {
	 // TODO Auto-generated catch block
	 fe.printStackTrace();
	 }
	 return null;
	 }
	 protected QueuetbLocal createQueuetbLocal(BigDecimal queueid) {
	 QueuetbLocalHome aQueuetbLocalHome = (QueuetbLocalHome) ServiceLocatorManager
	 .getLocalHome(STATIC_QueuetbLocalHome_REF_NAME,
	 STATIC_QueuetbLocalHome_CLASS);
	 try {
	 if (aQueuetbLocalHome != null)
	 return aQueuetbLocalHome.create(queueid);
	 } catch (javax.ejb.CreateException ce) {
	 // TODO Auto-generated catch block
	 ce.printStackTrace();
	 }
	 return null;
	 }
	 

	 protected Collection find_QueuetbLocalHome_findAllQueue() {
	 QueuetbLocalHome aQueuetbLocalHome = (QueuetbLocalHome) ServiceLocatorManager
	 .getLocalHome(STATIC_QueuetbLocalHome_REF_NAME,
	 STATIC_QueuetbLocalHome_CLASS);
	 try {
	 if (aQueuetbLocalHome != null)
	 return aQueuetbLocalHome.findAllQueue();
	 } catch (javax.ejb.FinderException fe) {
	 // TODO Auto-generated catch block
	 fe.printStackTrace();
	 }
	 return null;
	 }*/

	//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
	public void callQueueRunningHost(String queueName) throws Exception;
	public void callQueueRunningHost(String queueName, String process)
		throws Exception;
	/**
	 * Reset queue
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param qname
	 * @throws Exception
	 */
	public void resetQueue(String qname) throws Exception;
	//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]

	//Start Delete job by Ref.No. Veerasak Boonchern Aey 2010/03/23[yyyy/mm/dd]
	public int deleteJobByReferenceNo(String queueName, String refNo)
		throws Exception;
}
