package th.co.msat.motor.database;
/**
 * Remote interface for Enterprise Bean: FacingSheetSB
 */
public interface FacingSheetSB extends javax.ejb.EJBObject {
	public void putJob(
		String no,
		String username,
		String status,
		int piority,
		long jobgroup) throws Exception, java.rmi.RemoteException;
	//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
	public void callQueueRunningHost(String queueName)
		throws Exception,
		java.rmi.RemoteException;
}
