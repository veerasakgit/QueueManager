package th.co.msat.motor.database;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessFacingSheetSBHomeBean_0e4f5f3b
 */
public class EJSStatelessFacingSheetSBHomeBean_0e4f5f3b extends EJSHome {
	/**
	 * EJSStatelessFacingSheetSBHomeBean_0e4f5f3b
	 */
	public EJSStatelessFacingSheetSBHomeBean_0e4f5f3b() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public th.co.msat.motor.database.FacingSheetSB create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
th.co.msat.motor.database.FacingSheetSB result = null;
boolean createFailed = false;
try {
	result = (th.co.msat.motor.database.FacingSheetSB) super.createWrapper(null);
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
	public th.co.msat.motor.database.FacingSheetSBLocal create_Local() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
th.co.msat.motor.database.FacingSheetSBLocal result = null;
boolean createFailed = false;
boolean preCreateFlag = false;
try {
	result = (th.co.msat.motor.database.FacingSheetSBLocal) super.createWrapper_Local(null);
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
