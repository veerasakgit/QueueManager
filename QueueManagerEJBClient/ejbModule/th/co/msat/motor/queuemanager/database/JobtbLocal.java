package th.co.msat.motor.queuemanager.database;
/**
 * Local interface for Enterprise Bean: Jobtb
 */
public interface JobtbLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: jobid
	 */
	public java.math.BigDecimal getJobid();
	/**
	 * Set accessor for persistent attribute: jobid
	 */
	public void setJobid(java.math.BigDecimal newJobid);
	/**
	 * Get accessor for persistent attribute: message
	 */
	public byte[] getMessage();
	/**
	 * Set accessor for persistent attribute: message
	 */
	public void setMessage(byte[] newMessage);
	/**
	 * Get accessor for persistent attribute: status
	 */
	public java.lang.String getStatus();
	/**
	 * Set accessor for persistent attribute: status
	 */
	public void setStatus(java.lang.String newStatus);
	/**
	 * Get accessor for persistent attribute: piority
	 */
	public java.math.BigDecimal getPiority();
	/**
	 * Set accessor for persistent attribute: piority
	 */
	public void setPiority(java.math.BigDecimal newPiority);
	/**
	 * Get accessor for persistent attribute: submitdate
	 */
	public java.math.BigDecimal getSubmitdate();
	/**
	 * Set accessor for persistent attribute: submitdate
	 */
	public void setSubmitdate(java.math.BigDecimal newSubmitdate);
	/**
	 * Get accessor for persistent attribute: submittime
	 */
	public java.math.BigDecimal getSubmittime();
	/**
	 * Set accessor for persistent attribute: submittime
	 */
	public void setSubmittime(java.math.BigDecimal newSubmittime);
	/**
	 * Get accessor for persistent attribute: submituser
	 */
	public java.lang.String getSubmituser();
	/**
	 * Set accessor for persistent attribute: submituser
	 */
	public void setSubmituser(java.lang.String newSubmituser);
	/**
	 * Get accessor for persistent attribute: updatedate
	 */
	public java.math.BigDecimal getUpdatedate();
	/**
	 * Set accessor for persistent attribute: updatedate
	 */
	public void setUpdatedate(java.math.BigDecimal newUpdatedate);
	/**
	 * Get accessor for persistent attribute: updatetime
	 */
	public java.math.BigDecimal getUpdatetime();
	/**
	 * Set accessor for persistent attribute: updatetime
	 */
	public void setUpdatetime(java.math.BigDecimal newUpdatetime);
	/**
	 * Get accessor for persistent attribute: updateuser
	 */
	public java.lang.String getUpdateuser();
	/**
	 * Set accessor for persistent attribute: updateuser
	 */
	public void setUpdateuser(java.lang.String newUpdateuser);
	/**
	 * Get accessor for persistent attribute: updateprogram
	 */
	public java.lang.String getUpdateprogram();
	/**
	 * Set accessor for persistent attribute: updateprogram
	 */
	public void setUpdateprogram(java.lang.String newUpdateprogram);
	/**
	 * Get accessor for persistent attribute: recordstatus
	 */
	public java.lang.String getRecordstatus();
	/**
	 * Set accessor for persistent attribute: recordstatus
	 */
	public void setRecordstatus(java.lang.String newRecordstatus);
	/**
	 * This method was generated for supporting the relationship role named q_msatlib_jobtb_queueid_00001.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal getQ_msatlib_jobtb_queueid_00001();
	/**
	 * This method was generated for supporting the relationship role named q_msatlib_jobtb_queueid_00001.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setQ_msatlib_jobtb_queueid_00001(
		th.co.msat.motor.queuemanager.database.QueuetbLocal aQ_msatlib_jobtb_queueid_00001);
	/**
	 * Get accessor for persistent attribute: referenceno
	 */
	public java.lang.String getReferenceno();
	/**
	 * Set accessor for persistent attribute: referenceno
	 */
	public void setReferenceno(java.lang.String newReferenceno);
	/**
	 * Get accessor for persistent attribute: jobgroup
	 */
	public java.math.BigDecimal getJobgroup();
	/**
	 * Set accessor for persistent attribute: jobgroup
	 */
	public void setJobgroup(java.math.BigDecimal newJobgroup);
}
