package th.co.msat.motor.database;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessNotificationFacadeHomeBean_1bcd05ff
 */
public class EJSStatelessNotificationFacadeHomeBean_1bcd05ff extends EJSHome {
	/**
	 * EJSStatelessNotificationFacadeHomeBean_1bcd05ff
	 */
	public EJSStatelessNotificationFacadeHomeBean_1bcd05ff() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public th.co.msat.motor.database.NotificationFacade create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
th.co.msat.motor.database.NotificationFacade result = null;
boolean createFailed = false;
try {
	result = (th.co.msat.motor.database.NotificationFacade) super.createWrapper(null);
}
catch (javax.ejb.CreateException ex) {
	createFailed = true;
	throw ex;
} catch (java.rmi.RemoteException ex) {
	createFailed = true;
	throw ex;
} catch (Throwable ex) {
	createFailed = true;
	throw new CreateFailureException(ex);
} finally {
	if (createFailed) {
		super.createFailure(beanO);
	}
}
return result;	}
	/**
	 * create_Local
	 */
	public th.co.msat.motor.database.NotificationFacadeLocal create_Local() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
th.co.msat.motor.database.NotificationFacadeLocal result = null;
boolean createFailed = false;
boolean preCreateFlag = false;
try {
	result = (th.co.msat.motor.database.NotificationFacadeLocal) super.createWrapper_Local(null);
}
catch (javax.ejb.CreateException ex) {
	createFailed = true;
	throw ex;
} catch (java.rmi.RemoteException ex) {
	createFailed = true;
	throw ex;
} catch (Throwable ex) {
	createFailed = true;
	throw new CreateFailureException(ex);
} finally {
	if (createFailed) {
		super.createFailure(beanO);
	}
}
return result;	}
}
