/*
 * Created on 23 ¸.¤. 2551
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
public class NatureOfLossSynchronizeExecutor extends ExecutorImpl{
	boolean alive = false;
	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#execute()
	 */
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		alive = true;
		
		System.out.println("================== nat code exe ====================");
		
		NatureOfLossSynchronizeMessage message = (NatureOfLossSynchronizeMessage)getMessage();
		System.out.println(message.getClaimNo());
		updateNatureOfLoss(message.getClaimNo(),message.getNatureOfLoss());
		
		
		CoverpagePrinter cp = createCoverpagePrinter();
		cp.print(message.getNotificationno(),message.getUsername());
		
		alive = false;
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#onTimeout()
	 */
	public void onTimeout() {
		// TODO Auto-generated method stub
		alive = false;
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#isAlive()
	 */
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return alive;
	}
	
	private int updateNatureOfLoss(String claimno,String descOfLoss) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update msatlib.coverpagetb ");
		sql.append(" set description_of_loss = '"+descOfLoss.trim()+"' ");
		sql.append(" where claim_no = '"+claimno.trim()+"' ");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			return pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		
		
	}
	
	public CoverpagePrinter createCoverpagePrinter() throws Exception{
		Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
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
