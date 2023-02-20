package th.co.msat.motor.queuemanager.database;
/**
 * Local Home interface for Enterprise Bean: Queuetb
 */
public interface QueuetbLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Queuetb
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal create(
		java.math.BigDecimal queueid) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Queuetb
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal findByPrimaryKey(
		th.co.msat.motor.queuemanager.database.QueuetbKey primaryKey)
		throws javax.ejb.FinderException;
	public th.co.msat.motor.queuemanager.database.QueuetbLocal findByQueueName(java.lang.String queueName) throws javax.ejb.FinderException;
	public java.util.Collection findAllQueue() throws javax.ejb.FinderException;
}
