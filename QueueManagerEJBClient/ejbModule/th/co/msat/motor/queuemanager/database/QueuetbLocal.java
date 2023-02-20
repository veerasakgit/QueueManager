package th.co.msat.motor.queuemanager.database;
/**
 * Local interface for Enterprise Bean: Queuetb
 */
public interface QueuetbLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: queueid
	 */
	public java.math.BigDecimal getQueueid();
	/**
	 * Set accessor for persistent attribute: queueid
	 */
	public void setQueueid(java.math.BigDecimal newQueueid);
	/**
	 * Get accessor for persistent attribute: queuename
	 */
	public java.lang.String getQueuename();
	/**
	 * Set accessor for persistent attribute: queuename
	 */
	public void setQueuename(java.lang.String newQueuename);
	/**
	 * Get accessor for persistent attribute: size
	 */
	public java.math.BigDecimal getSize();
	/**
	 * Set accessor for persistent attribute: size
	 */
	public void setSize(java.math.BigDecimal newSize);
	/**
	 * Get accessor for persistent attribute: available
	 */
	public java.math.BigDecimal getAvailable();
	/**
	 * Set accessor for persistent attribute: available
	 */
	public void setAvailable(java.math.BigDecimal newAvailable);
	/**
	 * Get accessor for persistent attribute: isterminate
	 */
	public java.lang.String getIsterminate();
	/**
	 * Set accessor for persistent attribute: isterminate
	 */
	public void setIsterminate(java.lang.String newIsterminate);
	/**
	 * Get accessor for persistent attribute: workingtime
	 */
	public java.math.BigDecimal getWorkingtime();
	/**
	 * Set accessor for persistent attribute: workingtime
	 */
	public void setWorkingtime(java.math.BigDecimal newWorkingtime);
	/**
	 * Get accessor for persistent attribute: messageclass
	 */
	public java.lang.String getMessageclass();
	/**
	 * Set accessor for persistent attribute: messageclass
	 */
	public void setMessageclass(java.lang.String newMessageclass);
	/**
	 * Get accessor for persistent attribute: activateclass
	 */
	public java.lang.String getActivateclass();
	/**
	 * Set accessor for persistent attribute: activateclass
	 */
	public void setActivateclass(java.lang.String newActivateclass);
	/**
	 * Get accessor for persistent attribute: createdate
	 */
	public java.math.BigDecimal getCreatedate();
	/**
	 * Set accessor for persistent attribute: createdate
	 */
	public void setCreatedate(java.math.BigDecimal newCreatedate);
	/**
	 * Get accessor for persistent attribute: createtime
	 */
	public java.math.BigDecimal getCreatetime();
	/**
	 * Set accessor for persistent attribute: createtime
	 */
	public void setCreatetime(java.math.BigDecimal newCreatetime);
	/**
	 * Get accessor for persistent attribute: createuser
	 */
	public java.lang.String getCreateuser();
	/**
	 * Set accessor for persistent attribute: createuser
	 */
	public void setCreateuser(java.lang.String newCreateuser);
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
	 * This method was generated for supporting the relationship role named jobtb.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getJobtb();
	/**
	 * This method was generated for supporting the relationship role named jobtb.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setJobtb(java.util.Collection aJobtb);
}
