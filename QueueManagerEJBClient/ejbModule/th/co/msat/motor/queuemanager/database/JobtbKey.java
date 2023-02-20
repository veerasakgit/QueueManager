package th.co.msat.motor.queuemanager.database;
/**
 * Key class for Entity Bean: Jobtb
 */
public class JobtbKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: jobid
	 */
	public java.math.BigDecimal jobid;
	/**
	 * Creates an empty key for Entity Bean: Jobtb
	 */
	public JobtbKey() {
	}
	/**
	 * Creates a key for Entity Bean: Jobtb
	 */
	public JobtbKey(java.math.BigDecimal jobid) {
		this.jobid = jobid;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof th.co.msat.motor.queuemanager.database.JobtbKey) {
			th.co.msat.motor.queuemanager.database.JobtbKey o = (th.co.msat.motor.queuemanager.database.JobtbKey) otherKey;
			return ((this.jobid.equals(o.jobid)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (jobid.hashCode());
	}
	/**
	 * Get accessor for persistent attribute: jobid
	 */
	public java.math.BigDecimal getJobid() {
		return jobid;
	}
	/**
	 * Set accessor for persistent attribute: jobid
	 */
	public void setJobid(java.math.BigDecimal newJobid) {
		jobid = newJobid;
	}
}
