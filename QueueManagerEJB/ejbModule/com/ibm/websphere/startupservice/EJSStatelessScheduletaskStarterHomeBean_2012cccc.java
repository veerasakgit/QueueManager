package com.ibm.websphere.startupservice;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessScheduletaskStarterHomeBean_2012cccc
 */
public class EJSStatelessScheduletaskStarterHomeBean_2012cccc extends EJSHome {
	/**
	 * EJSStatelessScheduletaskStarterHomeBean_2012cccc
	 */
	public EJSStatelessScheduletaskStarterHomeBean_2012cccc() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public com.ibm.websphere.startupservice.AppStartUp create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
com.ibm.websphere.startupservice.AppStartUp result = null;
boolean createFailed = false;
try {
	result = (com.ibm.websphere.startupservice.AppStartUp) super.createWrapper(null);
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
