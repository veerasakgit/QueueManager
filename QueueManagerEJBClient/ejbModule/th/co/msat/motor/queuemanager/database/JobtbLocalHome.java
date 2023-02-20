package th.co.msat.motor.queuemanager.database;
/**
 * Local Home interface for Enterprise Bean: Jobtb
 */
public interface JobtbLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Jobtb
	 */
	public th.co.msat.motor.queuemanager.database.JobtbLocal create(
		java.math.BigDecimal jobid) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Jobtb
	 */
	public th.co.msat.motor.queuemanager.database.JobtbLocal findByPrimaryKey(
		th.co.msat.motor.queuemanager.database.JobtbKey primaryKey)
		throws javax.ejb.FinderException;
}
