package th.co.msat.motor.scheduletask;

import th.co.msat.schedulejobmanager.ScheduleTaskController;

/**
 * Bean implementation class for Enterprise Bean: ScheduletaskStarter
 */
public class ScheduletaskStarterBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
//		System.out.println(" get session context");
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
//		System.out.println(" set session context ");
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
//		System.out.println(" on create ");
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
//		System.out.println(" on activate ");
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
//		System.out.println(" on passivate ");
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
//		System.out.println(" on remove ");
	}
	
	public boolean start() {
//        System.out.println(" start...... ");
		new ScheduleTaskController();
        return true;
    }
 
    public void stop() {        //do cleanup here as needed
//    	System.out.println(" stop...... ");
    }

}
