package th.co.msat.motor.queuemanager.database;
/**
 * Key class for Entity Bean: Queuetb
 */
public class QueuetbKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: queueid
	 */
	public java.math.BigDecimal queueid;
	/**
	 * Creates an empty key for Entity Bean: Queuetb
	 */
	public QueuetbKey() {
	}
	/**
	 * Creates a key for Entity Bean: Queuetb
	 */
	public QueuetbKey(java.math.BigDecimal queueid) {
		this.queueid = queueid;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof th.co.msat.motor.queuemanager.database.QueuetbKey) {
			th.co.msat.motor.queuemanager.database.QueuetbKey o = (th.co.msat.motor.queuemanager.database.QueuetbKey) otherKey;
			return ((this.queueid.equals(o.queueid)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (queueid.hashCode());
	}
	/**
	 * Get accessor for persistent attribute: queueid
	 */
	public java.math.BigDecimal getQueueid() {
		return queueid;
	}
	/**
	 * Set accessor for persistent attribute: queueid
	 */
	public void setQueueid(java.math.BigDecimal newQueueid) {
		queueid = newQueueid;
	}
}
