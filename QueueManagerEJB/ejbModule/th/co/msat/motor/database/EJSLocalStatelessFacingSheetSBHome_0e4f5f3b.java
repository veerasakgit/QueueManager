package th.co.msat.motor.database;

import com.ibm.ejs.container.*;
import java.rmi.RemoteException;

/**
 * EJSLocalStatelessFacingSheetSBHome_0e4f5f3b
 */
public class EJSLocalStatelessFacingSheetSBHome_0e4f5f3b extends EJSLocalWrapper implements th.co.msat.motor.database.FacingSheetSBLocalHome {
	/**
	 * EJSLocalStatelessFacingSheetSBHome_0e4f5f3b
	 */
	public EJSLocalStatelessFacingSheetSBHome_0e4f5f3b() {
		super();	}
	/**
	 * create
	 */
	public th.co.msat.motor.database.FacingSheetSBLocal create() throws javax.ejb.CreateException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		th.co.msat.motor.database.FacingSheetSBLocal _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[0];
			}
	th.co.msat.motor.database.EJSStatelessFacingSheetSBHomeBean_0e4f5f3b _EJS_beanRef = (th.co.msat.motor.database.EJSStatelessFacingSheetSBHomeBean_0e4f5f3b)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = _EJS_beanRef.create_Local();
		}
		catch (javax.ejb.CreateException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedLocalException(ex);
		}

		finally {
			try {
				container.postInvoke(this, 0, _EJS_s);
			} catch ( RemoteException ex ) {
				_EJS_s.setUncheckedLocalException(ex);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * remove
	 */
	public void remove(java.lang.Object arg0) throws javax.ejb.RemoveException, javax.ejb.EJBException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = arg0;
			}
	th.co.msat.motor.database.EJSStatelessFacingSheetSBHomeBean_0e4f5f3b _EJS_beanRef = (th.co.msat.motor.database.EJSStatelessFacingSheetSBHomeBean_0e4f5f3b)container.preInvoke(this, 1, _EJS_s, _jacc_parms);
			_EJS_beanRef.remove(arg0);
		}
		catch (javax.ejb.RemoveException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (javax.ejb.EJBException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (java.rmi.RemoteException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedLocalException(ex);
		}

		finally {
			try {
				container.postInvoke(this, 1, _EJS_s);
			} catch ( RemoteException ex ) {
				_EJS_s.setUncheckedLocalException(ex);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
}
