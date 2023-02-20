package th.co.msat.motor.queuemanager;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessQueueSBHomeBean_5cd20396
 */
public class EJSStatelessQueueSBHomeBean_5cd20396 extends EJSHome {
	/**
	 * EJSStatelessQueueSBHomeBean_5cd20396
	 */
	public EJSStatelessQueueSBHomeBean_5cd20396() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public th.co.msat.motor.queuemanager.QueueSB create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
th.co.msat.motor.queuemanager.QueueSB result = null;
boolean createFailed = false;
try {
	result = (th.co.msat.motor.queuemanager.QueueSB) super.createWrapper(null);
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
	public th.co.msat.motor.queuemanager.QueueSBLocal create_Local() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
th.co.msat.motor.queuemanager.QueueSBLocal result = null;
boolean createFailed = false;
boolean preCreateFlag = false;
try {
	result = (th.co.msat.motor.queuemanager.QueueSBLocal) super.createWrapper_Local(null);
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
