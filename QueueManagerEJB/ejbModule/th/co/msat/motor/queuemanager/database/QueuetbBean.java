package th.co.msat.motor.queuemanager.database;
/**
 * Bean implementation class for Enterprise Bean: Queuetb
 */
public abstract class QueuetbBean implements javax.ejb.EntityBean {
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
	public th.co.msat.motor.queuemanager.database.QueuetbKey ejbCreate(
		java.math.BigDecimal queueid) throws javax.ejb.CreateException {
		setQueueid(queueid);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.math.BigDecimal queueid)
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
	 * Get accessor for persistent attribute: queueid
	 */
	public abstract java.math.BigDecimal getQueueid();
	/**
	 * Set accessor for persistent attribute: queueid
	 */
	public abstract void setQueueid(java.math.BigDecimal newQueueid);
	/**
	 * Get accessor for persistent attribute: queuename
	 */
	public abstract java.lang.String getQueuename();
	/**
	 * Set accessor for persistent attribute: queuename
	 */
	public abstract void setQueuename(java.lang.String newQueuename);
	/**
	 * Get accessor for persistent attribute: size
	 */
	public abstract java.math.BigDecimal getSize();
	/**
	 * Set accessor for persistent attribute: size
	 */
	public abstract void setSize(java.math.BigDecimal newSize);
	/**
	 * Get accessor for persistent attribute: available
	 */
	public abstract java.math.BigDecimal getAvailable();
	/**
	 * Set accessor for persistent attribute: available
	 */
	public abstract void setAvailable(java.math.BigDecimal newAvailable);
	/**
	 * Get accessor for persistent attribute: isterminate
	 */
	public abstract java.lang.String getIsterminate();
	/**
	 * Set accessor for persistent attribute: isterminate
	 */
	public abstract void setIsterminate(java.lang.String newIsterminate);
	/**
	 * Get accessor for persistent attribute: workingtime
	 */
	public abstract java.math.BigDecimal getWorkingtime();
	/**
	 * Set accessor for persistent attribute: workingtime
	 */
	public abstract void setWorkingtime(java.math.BigDecimal newWorkingtime);
	/**
	 * Get accessor for persistent attribute: messageclass
	 */
	public abstract java.lang.String getMessageclass();
	/**
	 * Set accessor for persistent attribute: messageclass
	 */
	public abstract void setMessageclass(java.lang.String newMessageclass);
	/**
	 * Get accessor for persistent attribute: activateclass
	 */
	public abstract java.lang.String getActivateclass();
	/**
	 * Set accessor for persistent attribute: activateclass
	 */
	public abstract void setActivateclass(java.lang.String newActivateclass);
	/**
	 * Get accessor for persistent attribute: createdate
	 */
	public abstract java.math.BigDecimal getCreatedate();
	/**
	 * Set accessor for persistent attribute: createdate
	 */
	public abstract void setCreatedate(java.math.BigDecimal newCreatedate);
	/**
	 * Get accessor for persistent attribute: createtime
	 */
	public abstract java.math.BigDecimal getCreatetime();
	/**
	 * Set accessor for persistent attribute: createtime
	 */
	public abstract void setCreatetime(java.math.BigDecimal newCreatetime);
	/**
	 * Get accessor for persistent attribute: createuser
	 */
	public abstract java.lang.String getCreateuser();
	/**
	 * Set accessor for persistent attribute: createuser
	 */
	public abstract void setCreateuser(java.lang.String newCreateuser);
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
	 * This method was generated for supporting the relationship role named jobtb.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getJobtb();
	/**
	 * This method was generated for supporting the relationship role named jobtb.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setJobtb(java.util.Collection aJobtb);
}
