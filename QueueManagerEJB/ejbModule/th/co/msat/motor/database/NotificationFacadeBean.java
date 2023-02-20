package th.co.msat.motor.database;

import java.util.Collection;

import th.co.msat.motor.queuemanager.database.NotificationDAO;
import th.co.msat.motor.queuemanager.database.NotificationDAOImpl;

/**
 * Bean implementation class for Enterprise Bean: NotificationFacade
 */
public class NotificationFacadeBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	public Collection getRegisteredClaim(long jobgroup) throws Exception{
		NotificationDAO ndao = new NotificationDAOImpl();
		Collection c =  ndao.getRegisteredClaim(jobgroup);
		return c;
		
	}
	
	public Collection getUnAdjustClaim()throws Exception{
		NotificationDAO ndao = new NotificationDAOImpl();
		Collection c = ndao.getUnAdjustClaim();
		return c;
	}
	public Collection searchUnAdjustClaim(String notiKeyword,boolean isold)throws Exception{
		NotificationDAO ndao = new NotificationDAOImpl();
		Collection c = ndao.searchUnAdjustClaim(notiKeyword,isold);
		return c;
	}
	
	
}
