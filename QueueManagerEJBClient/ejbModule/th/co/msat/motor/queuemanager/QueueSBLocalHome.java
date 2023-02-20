package th.co.msat.motor.queuemanager;
/**
 * Local Home interface for Enterprise Bean: QueueSB
 */
public interface QueueSBLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: QueueSB
	 */
	public th.co.msat.motor.queuemanager.QueueSBLocal create()
		throws javax.ejb.CreateException;
}
