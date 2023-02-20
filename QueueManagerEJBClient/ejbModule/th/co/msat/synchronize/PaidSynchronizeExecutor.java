/*
 * Created on 3 ¸.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.synchronize;
 
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.coverpage.CoverpagePrinter;
import th.co.msat.motor.coverpage.CoverpagePrinterHome;
import th.co.msat.motor.queuemanager.executor.ExecutorImpl;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PaidSynchronizeExecutor extends ExecutorImpl{
	private boolean alive;
	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#execute()
	 */
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		alive = true;
		
		
		Connection conn = null;
		
		PaidSynchronizeMessage pmsg = (PaidSynchronizeMessage)getMessage();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update coverpagetb ");
		sql.append(" set premoum_paid = 'YES' ");
		sql.append(" where coverpage_id = ? ");
		PreparedStatement pstmt = null;
		try{
			conn= getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setBigDecimal(1,pmsg.getId());
			pstmt.executeQuery();
			
			
			CoverpagePrinter cp = createCoverpagePrinter();
			cp.print(pmsg.getNotificationno(),pmsg.getUsername());
		}finally{
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		
		
		alive = false;
	}

	public void onTimeout() {
		alive = false;
	}

	
	public boolean isAlive() {
		return alive;
	}
	
	
	public CoverpagePrinter createCoverpagePrinter() throws Exception{
		Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
        //env.put(Context.PROVIDER_URL,SystemEnvironment.getInstance().getBootStrapAddressServerUNIX());
        env.put(Context.PROVIDER_URL,SystemEnvironment.getInstance().getBootStrapAddress());
        try {
			InitialContext ctx = new InitialContext(env);
//			System.out.println(" ============================= hrere is an lookup for remote test ===================================");
			Object s = ctx.lookup("ejb/CoverpagePrinterHome");
//			System.out.println(s);
			CoverpagePrinterHome frHome = (CoverpagePrinterHome) PortableRemoteObject.narrow(s,CoverpagePrinterHome.class);
//			System.out.println(" ============================= hrere is an lookup for remote test ===================================");
			return frHome.create();
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

}
