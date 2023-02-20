package th.co.msat.motor.database;
/**
 * Local interface for Enterprise Bean: FacingSheetSB
 */
public interface FacingSheetSBLocal extends javax.ejb.EJBLocalObject {
	public void putJob(
		String no,
		String username,
		String status,
		int piority,
		long jobgroup) throws Exception;
	//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
	public void callQueueRunningHost(String queueName) throws Exception;
}
