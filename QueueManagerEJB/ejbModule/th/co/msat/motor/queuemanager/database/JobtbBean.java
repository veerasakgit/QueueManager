package th.co.msat.motor.queuemanager.database;
/**
 * Bean implementation class for Enterprise Bean: Jobtb
 */
public abstract class JobtbBean implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	public void setEntityContext(javax.ejb.EntityContext ctx) {
		myEntityCtx = ctx;
	}
	/**
	 * getEntityContext
	 */
	public javax.ejb.EntityContext getEntityContext() {
		return myEntityCtx;
	}
	/**
	 * unsetEntityContext
	 */
	public void unsetEntityContext() {
		myEntityCtx = null;
	}
	/**
	 * ejbCreate
	 */
	public th.co.msat.motor.queuemanager.database.JobtbKey ejbCreate(
		java.math.BigDecimal jobid) throws javax.ejb.CreateException {
		setJobid(jobid);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.math.BigDecimal jobid)
		throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbLoad
	 */
	public void ejbLoad() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() throws javax.ejb.RemoveException {
	}
	/**
	 * ejbStore
	 */
	public void ejbStore() {
	}
	/**
	 * Get accessor for persistent attribute: jobid
	 */
	public abstract java.math.BigDecimal getJobid();
	/**
	 * Set accessor for persistent attribute: jobid
	 */
	public abstract void setJobid(java.math.BigDecimal newJobid);
	/**
	 * Get accessor for persistent attribute: message
	 */
	public abstract byte[] getMessage();
	/**
	 * Set accessor for persistent attribute: message
	 */
	public abstract void setMessage(byte[] newMessage);
	/**
	 * Get accessor for persistent attribute: status
	 */
	public abstract java.lang.String getStatus();
	/**
	 * Set accessor for persistent attribute: status
	 */
	public abstract void setStatus(java.lang.String newStatus);
	/**
	 * Get accessor for persistent attribute: piority
	 */
	public abstract java.math.BigDecimal getPiority();
	/**
	 * Set accessor for persistent attribute: piority
	 */
	public abstract void setPiority(java.math.BigDecimal newPiority);
	/**
	 * Get accessor for persistent attribute: submitdate
	 */
	public abstract java.math.BigDecimal getSubmitdate();
	/**
	 * Set accessor for persistent attribute: submitdate
	 */
	public abstract void setSubmitdate(java.math.BigDecimal newSubmitdate);
	/**
	 * Get accessor for persistent attribute: submittime
	 */
	public abstract java.math.BigDecimal getSubmittime();
	/**
	 * Set accessor for persistent attribute: submittime
	 */
	public abstract void setSubmittime(java.math.BigDecimal newSubmittime);
	/**
	 * Get accessor for persistent attribute: submituser
	 */
	public abstract java.lang.String getSubmituser();
	/**
	 * Set accessor for persistent attribute: submituser
	 */
	public abstract void setSubmituser(java.lang.String newSubmituser);
	/**
	 * Get accessor for persistent attribute: updatedate
	 */
	public abstract java.math.BigDecimal getUpdatedate();
	/**
	 * Set accessor for persistent attribute: updatedate
	 */
	public abstract void setUpdatedate(java.math.BigDecimal newUpdatedate);
	/**
	 * Get accessor for persistent attribute: updatetime
	 */
	public abstract java.math.BigDecimal getUpdatetime();
	/**
	 * Set accessor for persistent attribute: updatetime
	 */
	public abstract void setUpdatetime(java.math.BigDecimal newUpdatetime);
	/**
	 * Get accessor for persistent attribute: updateuser
	 */
	public abstract java.lang.String getUpdateuser();
	/**
	 * Set accessor for persistent attribute: updateuser
	 */
	public abstract void setUpdateuser(java.lang.String newUpdateuser);
	/**
	 * Get accessor for persistent attribute: updateprogram
	 */
	public abstract java.lang.String getUpdateprogram();
	/**
	 * Set accessor for persistent attribute: updateprogram
	 */
	public abstract void setUpdateprogram(java.lang.String newUpdateprogram);
	/**
	 * Get accessor for persistent attribute: recordstatus
	 */
	public abstract java.lang.String getRecordstatus();
	/**
	 * Set accessor for persistent attribute: recordstatus
	 */
	public abstract void setRecordstatus(java.lang.String newRecordstatus);
	/**
	 * This method was generated for supporting the relationship role named q_msatlib_jobtb_queueid_00001.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract th.co.msat.motor.queuemanager.database.QueuetbLocal getQ_msatlib_jobtb_queueid_00001();
	/**
	 * This method was generated for supporting the relationship role named q_msatlib_jobtb_queueid_00001.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setQ_msatlib_jobtb_queueid_00001(
		th.co.msat.motor.queuemanager.database.QueuetbLocal aQ_msatlib_jobtb_queueid_00001);
	/**
	 * Get accessor for persistent attribute: referenceno
	 */
	public abstract java.lang.String getReferenceno();
	/**
	 * Set accessor for persistent attribute: referenceno
	 */
	public abstract void setReferenceno(java.lang.String newReferenceno);
	/**
	 * Get accessor for persistent attribute: jobgroup
	 */
	public abstract java.math.BigDecimal getJobgroup();
	/**
	 * Set accessor for persistent attribute: jobgroup
	 */
	public abstract void setJobgroup(java.math.BigDecimal newJobgroup);
}
