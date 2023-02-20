package th.co.msat.motor.queuemanager.database;

import com.ibm.ws.ejbpersistence.beanextensions.*;
import com.ibm.websphere.cpmi.*;
import th.co.msat.motor.queuemanager.database.websphere_deploy.QueuetbBeanCacheEntry_6b8e8e23;

/**
 * Bean implementation class for Enterprise Bean: Queuetb
 */
public class ConcreteQueuetb_6b8e8e23 extends th.co.msat.motor.queuemanager.database.QueuetbBean implements javax.ejb.EntityBean, ConcreteBeanWithLink {
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
	private QueuetbBeanCacheEntry_6b8e8e23 dataCacheEntry;
	/**
	 * _WSCB_getInstanceInfo
	 */
	public PMConcreteBeanInstanceInfo _WSCB_getInstanceInfo() {
			return instanceExtension;
	}
	/**
	 * ConcreteQueuetb_6b8e8e23
	 */
	public ConcreteQueuetb_6b8e8e23() {
		super();
		instanceExtension = ConcreteBeanInstanceExtensionFactory.getInstance(this);
	}
	/**
	 * getInjector
	 */
	private th.co.msat.motor.queuemanager.database.websphere_deploy.QueuetbBeanInjector_6b8e8e23 getInjector() {
		return (th.co.msat.motor.queuemanager.database.websphere_deploy.QueuetbBeanInjector_6b8e8e23)instanceExtension.getInjector();
	}
	/**
	 * hydrate
	 */
	public void hydrate(Object inRecord) {
		dataCacheEntry = (th.co.msat.motor.queuemanager.database.websphere_deploy.QueuetbBeanCacheEntry_6b8e8e23) inRecord;;
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
			jobtbLink = null;
	}
	/**
	 * ejbFindAllQueue_Local
	 */
	public java.util.Collection ejbFindAllQueue_Local() throws javax.ejb.FinderException {
		return (java.util.Collection) instanceExtension.executeFind("FindAllQueue", null);
	}
	/**
	 * ejbFindByPrimaryKey
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbKey ejbFindByPrimaryKey(th.co.msat.motor.queuemanager.database.QueuetbKey primaryKey) throws javax.ejb.FinderException {
		return (th.co.msat.motor.queuemanager.database.QueuetbKey)instanceExtension.ejbFindByPrimaryKey(primaryKey);
	}
	/**
	 * ejbFindByQueueName_Local
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbKey ejbFindByQueueName_Local(java.lang.String queueName) throws javax.ejb.FinderException {
		javax.resource.cci.IndexedRecord record = instanceExtension.getInputRecord("FindByQueueName");
		getInjector().ejbFindByQueueName(queueName, record);
		return (th.co.msat.motor.queuemanager.database.QueuetbKey) instanceExtension.executeFind("FindByQueueName", record);
	}
	/**
	 * ejbFindByPrimaryKey
	 */
	public Object ejbFindByPrimaryKey(java.lang.Object pk) throws javax.ejb.FinderException {
		return ejbFindByPrimaryKey((th.co.msat.motor.queuemanager.database.QueuetbKey)pk);
	}
	/**
	 * ejbFindByPrimaryKeyForCMR_Local
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbKey ejbFindByPrimaryKeyForCMR_Local(th.co.msat.motor.queuemanager.database.QueuetbKey pk) throws javax.ejb.FinderException {
		return (th.co.msat.motor.queuemanager.database.QueuetbKey)instanceExtension.ejbFindByPrimaryKey(pk);
	}
	/**
	 * ejbCreate
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbKey ejbCreate(java.math.BigDecimal queueid) throws javax.ejb.CreateException {
		dataCacheEntry = (QueuetbBeanCacheEntry_6b8e8e23) instanceExtension.createDataCacheEntry();
		 super.ejbCreate(queueid);
		return (th.co.msat.motor.queuemanager.database.QueuetbKey)instanceExtension.ejbCreate();
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.math.BigDecimal queueid) throws javax.ejb.CreateException {
		super.ejbPostCreate(queueid);
		instanceExtension.ejbPostCreate();
	}
	/**
	 * createPrimaryKey
	 */
	public Object createPrimaryKey() {
		th.co.msat.motor.queuemanager.database.QueuetbKey pk = new th.co.msat.motor.queuemanager.database.QueuetbKey();
		pk.queueid = getQueueid();
		return pk;
	}
	/**
	 * getNumberOfFields
	 */
	public int getNumberOfFields() {
		return 17;
	}
	/**
	 * getLink
	 */
	public com.ibm.ws.ejbpersistence.associations.AssociationLink getLink(java.lang.String name) {
		if (name.equals("jobtb")) return getJobtbLink();
		return null;
	}
	/**
	 * executeFinderForLink
	 */
	public java.lang.Object executeFinderForLink(java.lang.String name, java.lang.Object key) throws javax.ejb.FinderException {
		if (name.equals("jobtb")){
			th.co.msat.motor.queuemanager.database.websphere_deploy.JobtbBeanInternalLocalHome_db815d18 home = (th.co.msat.motor.queuemanager.database.websphere_deploy.JobtbBeanInternalLocalHome_db815d18)instanceExtension.getHomeForLink("jobtb");
		  return home.findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local((th.co.msat.motor.queuemanager.database.QueuetbKey)key);
		}
		return null;
	}
	/**
	 * getJobtb
	 */
	public java.util.Collection getJobtb() {
		return (java.util.Collection)getJobtbLink().getValue();
	}
	/**
	 * setJobtb
	 */
	public void setJobtb(java.util.Collection value) {
		getJobtbLink().setValue(value);
	}
	/**
	 * getJobtbLink
	 */
	private com.ibm.ws.ejbpersistence.associations.AssociationLink getJobtbLink() {
		if (jobtbLink == null) 
		jobtbLink = instanceExtension.createLink("jobtb",null,16);
		return jobtbLink;
	}
	private com.ibm.ws.ejbpersistence.associations.AssociationLink jobtbLink;
	/**
	 * ejbFindQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local
	 */
	public th.co.msat.motor.queuemanager.database.QueuetbKey ejbFindQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local(th.co.msat.motor.queuemanager.database.JobtbKey key) throws javax.ejb.FinderException {
		Object[] result = instanceExtension.getAssociatedKeys("jobtb",key);
		if (result!=null )  return (th.co.msat.motor.queuemanager.database.QueuetbKey) result[0];
		javax.resource.cci.IndexedRecord record = instanceExtension.getInputRecord("findQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local");
		getInjector().findQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local(key, record);
		return (th.co.msat.motor.queuemanager.database.QueuetbKey) instanceExtension.executeFind("findQ_msatlib_jobtb_queueid_00001ByJobtbKey_Local", record);
	}
	/**
	 * getOCCColumn
	 */
	public long getOCCColumn() {
		return dataCacheEntry.getOCCColumn();
	}
	/**
	 * Get accessor for persistent attribute: queueid
	 */
	public java.math.BigDecimal getQueueid() {
		return dataCacheEntry.getQueueid();
	}
	/**
	 * Set accessor for persistent attribute: queueid
	 */
	public void setQueueid(java.math.BigDecimal newQueueid) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(0,getQueueid(),newQueueid);
		else
			instanceExtension.markDirty(0);
		dataCacheEntry.setQueueid(newQueueid);
	}
	/**
	 * Get accessor for persistent attribute: queuename
	 */
	public java.lang.String getQueuename() {
		return dataCacheEntry.getQueuename();
	}
	/**
	 * Set accessor for persistent attribute: queuename
	 */
	public void setQueuename(java.lang.String newQueuename) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(1,getQueuename(),newQueuename);
		else
			instanceExtension.markDirty(1);
		dataCacheEntry.setQueuename(newQueuename);
	}
	/**
	 * Get accessor for persistent attribute: size
	 */
	public java.math.BigDecimal getSize() {
		return dataCacheEntry.getSize();
	}
	/**
	 * Set accessor for persistent attribute: size
	 */
	public void setSize(java.math.BigDecimal newSize) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(2,getSize(),newSize);
		else
			instanceExtension.markDirty(2);
		dataCacheEntry.setSize(newSize);
	}
	/**
	 * Get accessor for persistent attribute: available
	 */
	public java.math.BigDecimal getAvailable() {
		return dataCacheEntry.getAvailable();
	}
	/**
	 * Set accessor for persistent attribute: available
	 */
	public void setAvailable(java.math.BigDecimal newAvailable) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(3,getAvailable(),newAvailable);
		else
			instanceExtension.markDirty(3);
		dataCacheEntry.setAvailable(newAvailable);
	}
	/**
	 * Get accessor for persistent attribute: isterminate
	 */
	public java.lang.String getIsterminate() {
		return dataCacheEntry.getIsterminate();
	}
	/**
	 * Set accessor for persistent attribute: isterminate
	 */
	public void setIsterminate(java.lang.String newIsterminate) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(4,getIsterminate(),newIsterminate);
		else
			instanceExtension.markDirty(4);
		dataCacheEntry.setIsterminate(newIsterminate);
	}
	/**
	 * Get accessor for persistent attribute: workingtime
	 */
	public java.math.BigDecimal getWorkingtime() {
		return dataCacheEntry.getWorkingtime();
	}
	/**
	 * Set accessor for persistent attribute: workingtime
	 */
	public void setWorkingtime(java.math.BigDecimal newWorkingtime) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(5,getWorkingtime(),newWorkingtime);
		else
			instanceExtension.markDirty(5);
		dataCacheEntry.setWorkingtime(newWorkingtime);
	}
	/**
	 * Get accessor for persistent attribute: messageclass
	 */
	public java.lang.String getMessageclass() {
		return dataCacheEntry.getMessageclass();
	}
	/**
	 * Set accessor for persistent attribute: messageclass
	 */
	public void setMessageclass(java.lang.String newMessageclass) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(6,getMessageclass(),newMessageclass);
		else
			instanceExtension.markDirty(6);
		dataCacheEntry.setMessageclass(newMessageclass);
	}
	/**
	 * Get accessor for persistent attribute: activateclass
	 */
	public java.lang.String getActivateclass() {
		return dataCacheEntry.getActivateclass();
	}
	/**
	 * Set accessor for persistent attribute: activateclass
	 */
	public void setActivateclass(java.lang.String newActivateclass) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(7,getActivateclass(),newActivateclass);
		else
			instanceExtension.markDirty(7);
		dataCacheEntry.setActivateclass(newActivateclass);
	}
	/**
	 * Get accessor for persistent attribute: createdate
	 */
	public java.math.BigDecimal getCreatedate() {
		return dataCacheEntry.getCreatedate();
	}
	/**
	 * Set accessor for persistent attribute: createdate
	 */
	public void setCreatedate(java.math.BigDecimal newCreatedate) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(8,getCreatedate(),newCreatedate);
		else
			instanceExtension.markDirty(8);
		dataCacheEntry.setCreatedate(newCreatedate);
	}
	/**
	 * Get accessor for persistent attribute: createtime
	 */
	public java.math.BigDecimal getCreatetime() {
		return dataCacheEntry.getCreatetime();
	}
	/**
	 * Set accessor for persistent attribute: createtime
	 */
	public void setCreatetime(java.math.BigDecimal newCreatetime) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(9,getCreatetime(),newCreatetime);
		else
			instanceExtension.markDirty(9);
		dataCacheEntry.setCreatetime(newCreatetime);
	}
	/**
	 * Get accessor for persistent attribute: createuser
	 */
	public java.lang.String getCreateuser() {
		return dataCacheEntry.getCreateuser();
	}
	/**
	 * Set accessor for persistent attribute: createuser
	 */
	public void setCreateuser(java.lang.String newCreateuser) {
		if (instanceExtension.needValuesOnMarkDirty())
			instanceExtension.markDirty(10,getCreateuser(),newCreateuser);
		else
			instanceExtension.markDirty(10);
		dataCacheEntry.setCreateuser(newCreateuser);
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
			instanceExtension.markDirty(11,getUpdatedate(),newUpdatedate);
		else
			instanceExtension.markDirty(11);
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
			instanceExtension.markDirty(12,getUpdatetime(),newUpdatetime);
		else
			instanceExtension.markDirty(12);
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
			instanceExtension.markDirty(13,getUpdateuser(),newUpdateuser);
		else
			instanceExtension.markDirty(13);
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
			instanceExtension.markDirty(14,getUpdateprogram(),newUpdateprogram);
		else
			instanceExtension.markDirty(14);
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
			instanceExtension.markDirty(15,getRecordstatus(),newRecordstatus);
		else
			instanceExtension.markDirty(15);
		dataCacheEntry.setRecordstatus(newRecordstatus);
	}
}
