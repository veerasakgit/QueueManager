package th.co.msat.motor.queuemanager;
/**
 * Home interface for Enterprise Bean: QueueSB
 */
public interface QueueSBHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: QueueSB
	 */
	public th.co.msat.motor.queuemanager.QueueSB create()
		throws javax.ejb.CreateException,
		java.rmi.RemoteException;
}
