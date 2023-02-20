package th.co.msat.motor.queuemanager.database;

import com.ibm.ws.ejbpersistence.beanextensions.*;
import com.ibm.websphere.cpmi.*;
import th.co.msat.motor.queuemanager.database.websphere_deploy.JobtbBeanCacheEntry_db815d18;

/**
 * Bean implementation class for Enterprise Bean: Jobtb
 */
public class ConcreteJobtb_db815d18 extends th.co.msat.motor.queuemanager.database.JobtbBean implements javax.ejb.EntityBean, ConcreteBeanWithLink, ConcreteBeanWithForwardLink {
	/**
	 * setEntityContext
	 */
	public void setEntityContext(javax.ejb.EntityContext ctx) {
		super.setEntityContext(ctx);
		instanceExtension.setEntityContext(ctx);
	}
	/**
	 * unsetEntityContext
	 */
	public void unsetEntityContext() {
		super.unsetEntityContext();
		instanceExtension.unsetEntityContext();
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
			super.ejbActivate();
			instanceExtension.ejbActivate();
	}
	/**
	 * ejbLoad
	 */
	public void ejbLoad() {
		instanceExtension.ejbLoad();
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
		super.ejbPassivate();
		instanceExtension.ejbPassivate();
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() throws javax.ejb.RemoveException {
		super.ejbRemove();
		instanceExtension.ejbRemove();
	}
	/**
	 * ejbStore
	 */
	public void ejbStore() {
		super.ejbStore();
		instanceExtension.ejbStore();
	}
	private ConcreteBeanInstanceExtension instanceExtension;
	private JobtbBeanCacheEntry_db815d18 dataCacheEntry;
	/**
	 * _WSCB_getInstanceInfo
	 */
	public PMConcreteBeanInstanceInfo _WSCB_getInstanceInfo() {
			return instanceExtension;
	}
	/**
	 * ConcreteJobtb_db815d18
	 */
	public ConcreteJobtb_db815d18() {
		super();
		instanceExtension = ConcreteBeanInstanceExtensionFactory.getInstance(this);
	}
	/**
	 * getInjector
	 */
	private th.co.msat.motor.queuemanager.database.websphere_deploy.JobtbBeanInjector_db815d18 getInjector() {
		return (th.co.msat.motor.queuemanager.database.websphere_deploy.JobtbBeanInjector_db815d18)instanceExtension.getInjector();
	}
	/**
	 * hydrate
	 */
	public void hydrate(Object inRecord) {
		dataCacheEntry = (th.co.msat.motor.queuemanager.database.websphere_deploy.JobtbBeanCacheEntry_db815d18) inRecord;;
		super.ejbLoad();
	}
	/**
	 * resetCMP
	 */
	public void resetCMP() {
			dataCacheEntry = null;
	}
	/**
	 * resetCMR
	 */
	public void resetCMR() {
			q_msatlib_jobtb_queueid_00001Link = null;
	}
	/**
	 * ejbFindByPrimaryKey
	 */
	public th.co.msat.motor.queuemanager.database.JobtbKey ejbFindByPrimaryKey(th.co.msat.motor.queuemanager.database.JobtbKey primaryKey) throws javax.ejb.FinderException {
		return (th.co.msat.motor.queuemanager.database.JobtbKey)instanceExtension.ejbFindByPrimaryKey(primaryKey);
	}
	/**
	 * ejbFindByPrimaryKey
	 */
	public Object ejbFindByPrimaryKey(java.lang.Object pk) throws javax.ejb.FinderException {
		return ejbFindByPrimaryKey((th.co.msat.motor.queuemanager.database.JobtbKey)pk);
	}
	/**
	 * ejbFindByPrimaryKeyForCMR_Local
	 */
	public th.co.msat.motor.queuemanager.database.JobtbKey ejbFindByPrimaryKeyForCMR_Local(th.co.msat.motor.queuemanager.database.JobtbKey pk) throws javax.ejb.FinderException {
		return (th.co.msat.motor.queuemanager.database.JobtbKey)instanceExtension.ejbFindByPrimaryKey(pk);
	}
	/**
	 * ejbCreate
	 */
	public th.co.msat.motor.queuemanager.database.JobtbKey ejbCreate(java.math.BigDecimal jobid) throws javax.ejb.CreateException {
		dataCacheEntry = (JobtbBeanCacheEntry_db815d18) instanceExtension.createDataCacheEntry();
		 super.ejbCreate(jobid);
		return (th.co.msat.motor.queuemanager.database.JobtbKey)instanceExtension.ejbCreate();
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.math.BigDecimal jobid) throws javax.ejb.CreateException {
		super.ejbPostCreate(jobid);
		instanceExtension.ejbPostCreate();
	}
	/**
	 * createPrimaryKey
	 */
	public Object createPrimaryKey() {
		th.co.msat.motor.queuemanager.database.JobtbKey pk = new th.co.msat.motor.queuemanager.database.JobtbKey();
		pk.jobid = getJobid();
		return pk;
	}
	/**
	 * getNumberOfFields
	 */
	public int getNumberOfFields() {
		return 16;
	}
	/**
	 * getLink
	 */
	public com.ibm.ws.ejbpersistence.associations.AssociationLink getLink(java.lang.String name) {
		if (name.equals("q_msatlib_jobtb_queueid_00001")) return getQ_msatlib_jobtb_queueid_00001Link();
		return null;
	}
	/**
	 * executeFinderForLink
	 */
	public java.lang.Object executeFinderForLink(java.lang.String name, java.lang.Object key) throws javax.ejb.FinderException {
		if (name.equals("q_msatlib_jobtb_queueid_00001")){
			th.co.msat.motor.queuemanager.database.websphere_deploy.QueuetbBeanInternalLocalHome_6b8e8e23 home = (th.co.msat.motor.queuemanager.database.websphere_deploy.QueuetbBeanInternalLocalHome_6b8e8e23)instanceExtension.getHomeForLink("q_msatlib_jobtb_queueid_00001");
		  return home.findByPrimaryKeyForCMR((th.co.msat.motor.queuemanager.database.QueuetbKey)key);
		}
		return null;
	}
	/**
	 * getQ_msatlib_jobtb_queueid_00001
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbLocal getQ_msatlib_jobtb_queueid_00001() {
		return (th.co.msat.motor.queuemanager.database.QueuetbLocal)getQ_msatlib_jobtb_queueid_00001Link().getValue();
	}
	/**
	 * setQ_msatlib_jobtb_queueid_00001
	 */
	public void setQ_msatlib_jobtb_queueid_00001(th.co.msat.motor.queuemanager.database.QueuetbLocal value) {
		getQ_msatlib_jobtb_queueid_00001Link().setValue(value);
	}
	/**
	 * getQ_msatlib_jobtb_queueid_00001Key
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbKey getQ_msatlib_jobtb_queueid_00001Key() {
		if (q_msatlib_jobtb_queueid_00001Link == null) return dataCacheEntry.getQ_msatlib_jobtb_queueid_00001Key();
		return (th.co.msat.motor.queuemanager.database.QueuetbKey) q_msatlib_jobtb_queueid_00001Link.getKey();
	}
	/**
	 * getQ_msatlib_jobtb_queueid_00001Link
	 */
	private com.ibm.ws.ejbpersistence.associations.AssociationLink getQ_msatlib_jobtb_queueid_00001Link() {
		if (q_msatlib_jobtb_queueid_00001Link == null) 
		q_msatlib_jobtb_queueid_00001Link = instanceExtension.createLink("q_msatlib_jobtb_queueid_00001",dataCacheEntry.getQ_msatlib_jobtb_queueid_00001Key(),15);
		return q_msatlib_jobtb_queueid_00001Link;
	}
	private com.ibm.ws.ejbpersistence.associations.AssociationLink q_msatlib_jobtb_queueid_00001Link;
	/**
	 * ejbFindJobtbByQ_msatlib_jobtb_queueid_00001Key_Local
	 */
	public java.util.Collection ejbFindJobtbByQ_msatlib_jobtb_queueid_00001Key_Local(th.co.msat.motor.queuemanager.database.QueuetbKey key) throws javax.ejb.FinderException {
		Object[] result = instanceExtension.getAssociatedKeys("q_msatlib_jobtb_queueid_00001",key);
		if (result!=null )  return (java.util.Collection) result[0];
		javax.resource.cci.IndexedRecord record = instanceExtension.getInputRecord("findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local");
		getInjector().findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local(key, record);
		return (java.util.Collection) instanceExtension.executeFind("findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local", record);
	}
	/**
	 * refreshForeignKey
	 */
	public void refreshForeignKey() {
		if (q_msatlib_jobtb_queueid_00001Link != null) 
		dataCacheEntry.setQ_msatlib_jobtb_queueid_00001Key((th.co.msat.motor.queuemanager.database.QueuetbKey) q_msatlib_jobtb_queueid_00001Link.getKey());
	}
	/**
	 * getOCCColumn
	 */
	public long getOCCColumn() {
		return dataCacheEntry.getOCCColumn();
	}
	/**
	 * Get accessor for persistent attribute: jobid
	 */
	public java.math.BigDecimal getJobid() {
		return dataCacheEntry.getJobid();
	}
	/**
	 * Set accessor for persistent attribute: jobid
	 */
	public void setJobid(java.math.BigDecimal newJobid) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(0,getJobid(),newJobid);
		else
			instanceExtension.markDirty(0);
		dataCacheEntry.setJobid(newJobid);
	}
	/**
	 * Get accessor for persistent attribute: message
	 */
	public byte[] getMessage() {
		return dataCacheEntry.getMessage();
	}
	/**
	 * Set accessor for persistent attribute: message
	 */
	public void setMessage(byte[] newMessage) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(1,getMessage(),newMessage);
		else
			instanceExtension.markDirty(1);
		dataCacheEntry.setMessage(newMessage);
	}
	/**
	 * Get accessor for persistent attribute: status
	 */
	public java.lang.String getStatus() {
		return dataCacheEntry.getStatus();
	}
	/**
	 * Set accessor for persistent attribute: status
	 */
	public void setStatus(java.lang.String newStatus) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(2,getStatus(),newStatus);
		else
			instanceExtension.markDirty(2);
		dataCacheEntry.setStatus(newStatus);
	}
	/**
	 * Get accessor for persistent attribute: piority
	 */
	public java.math.BigDecimal getPiority() {
		return dataCacheEntry.getPiority();
	}
	/**
	 * Set accessor for persistent attribute: piority
	 */
	public void setPiority(java.math.BigDecimal newPiority) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(3,getPiority(),newPiority);
		else
			instanceExtension.markDirty(3);
		dataCacheEntry.setPiority(newPiority);
	}
	/**
	 * Get accessor for persistent attribute: submitdate
	 */
	public java.math.BigDecimal getSubmitdate() {
		return dataCacheEntry.getSubmitdate();
	}
	/**
	 * Set accessor for persistent attribute: submitdate
	 */
	public void setSubmitdate(java.math.BigDecimal newSubmitdate) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(4,getSubmitdate(),newSubmitdate);
		else
			instanceExtension.markDirty(4);
		dataCacheEntry.setSubmitdate(newSubmitdate);
	}
	/**
	 * Get accessor for persistent attribute: submittime
	 */
	public java.math.BigDecimal getSubmittime() {
		return dataCacheEntry.getSubmittime();
	}
	/**
	 * Set accessor for persistent attribute: submittime
	 */
	public void setSubmittime(java.math.BigDecimal newSubmittime) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(5,getSubmittime(),newSubmittime);
		else
			instanceExtension.markDirty(5);
		dataCacheEntry.setSubmittime(newSubmittime);
	}
	/**
	 * Get accessor for persistent attribute: submituser
	 */
	public java.lang.String getSubmituser() {
		return dataCacheEntry.getSubmituser();
	}
	/**
	 * Set accessor for persistent attribute: submituser
	 */
	public void setSubmituser(java.lang.String newSubmituser) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(6,getSubmituser(),newSubmituser);
		else
			instanceExtension.markDirty(6);
		dataCacheEntry.setSubmituser(newSubmituser);
	}
	/**
	 * Get accessor for persistent attribute: updatedate
	 */
	public java.math.BigDecimal getUpdatedate() {
		return dataCacheEntry.getUpdatedate();
	}
	/**
	 * Set accessor for persistent attribute: updatedate
	 */
	public void setUpdatedate(java.math.BigDecimal newUpdatedate) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(7,getUpdatedate(),newUpdatedate);
		else
			instanceExtension.markDirty(7);
		dataCacheEntry.setUpdatedate(newUpdatedate);
	}
	/**
	 * Get accessor for persistent attribute: updatetime
	 */
	public java.math.BigDecimal getUpdatetime() {
		return dataCacheEntry.getUpdatetime();
	}
	/**
	 * Set accessor for persistent attribute: updatetime
	 */
	public void setUpdatetime(java.math.BigDecimal newUpdatetime) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(8,getUpdatetime(),newUpdatetime);
		else
			instanceExtension.markDirty(8);
		dataCacheEntry.setUpdatetime(newUpdatetime);
	}
	/**
	 * Get accessor for persistent attribute: updateuser
	 */
	public java.lang.String getUpdateuser() {
		return dataCacheEntry.getUpdateuser();
	}
	/**
	 * Set accessor for persistent attribute: updateuser
	 */
	public void setUpdateuser(java.lang.String newUpdateuser) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(9,getUpdateuser(),newUpdateuser);
		else
			instanceExtension.markDirty(9);
		dataCacheEntry.setUpdateuser(newUpdateuser);
	}
	/**
	 * Get accessor for persistent attribute: updateprogram
	 */
	public java.lang.String getUpdateprogram() {
		return dataCacheEntry.getUpdateprogram();
	}
	/**
	 * Set accessor for persistent attribute: updateprogram
	 */
	public void setUpdateprogram(java.lang.String newUpdateprogram) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(10,getUpdateprogram(),newUpdateprogram);
		else
			instanceExtension.markDirty(10);
		dataCacheEntry.setUpdateprogram(newUpdateprogram);
	}
	/**
	 * Get accessor for persistent attribute: recordstatus
	 */
	public java.lang.String getRecordstatus() {
		return dataCacheEntry.getRecordstatus();
	}
	/**
	 * Set accessor for persistent attribute: recordstatus
	 */
	public void setRecordstatus(java.lang.String newRecordstatus) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(11,getRecordstatus(),newRecordstatus);
		else
			instanceExtension.markDirty(11);
		dataCacheEntry.setRecordstatus(newRecordstatus);
	}
	/**
	 * Get accessor for persistent attribute: referenceno
	 */
	public java.lang.String getReferenceno() {
		return dataCacheEntry.getReferenceno();
	}
	/**
	 * Set accessor for persistent attribute: referenceno
	 */
	public void setReferenceno(java.lang.String newReferenceno) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(12,getReferenceno(),newReferenceno);
		else
			instanceExtension.markDirty(12);
		dataCacheEntry.setReferenceno(newReferenceno);
	}
	/**
	 * Get accessor for persistent attribute: jobgroup
	 */
	public java.math.BigDecimal getJobgroup() {
		return dataCacheEntry.getJobgroup();
	}
	/**
	 * Set accessor for persistent attribute: jobgroup
	 */
	public void setJobgroup(java.math.BigDecimal newJobgroup) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(13,getJobgroup(),newJobgroup);
		else
			instanceExtension.markDirty(13);
		dataCacheEntry.setJobgroup(newJobgroup);
	}
	/**
	 * Get accessor for persistent attribute: q_msatlib_jobtb_queueid_00001_queueid
	 */
	public java.math.BigDecimal getQ_msatlib_jobtb_queueid_00001_queueid() {
		return dataCacheEntry.getQ_msatlib_jobtb_queueid_00001_queueid();
	}
	/**
	 * Set accessor for persistent attribute: q_msatlib_jobtb_queueid_00001_queueid
	 */
	public void setQ_msatlib_jobtb_queueid_00001_queueid(java.math.BigDecimal newQ_msatlib_jobtb_queueid_00001_queueid) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(14,getQ_msatlib_jobtb_queueid_00001_queueid(),newQ_msatlib_jobtb_queueid_00001_queueid);
		else
			instanceExtension.markDirty(14);
		dataCacheEntry.setQ_msatlib_jobtb_queueid_00001_queueid(newQ_msatlib_jobtb_queueid_00001_queueid);
	}
}
