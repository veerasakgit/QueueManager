/*
 * Created on 3 ¸.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.schedulejobmanager.synchronize;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.queuemanager.QueueSB;
import th.co.msat.motor.queuemanager.QueueSBHome;
import th.co.msat.motor.queuemanager.WorkQueueController;
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.schedulejobmanager.ScheduleTaskExecutor;
import th.co.msat.synchronize.PaidSynchronizeMessage;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PaidSynchronize extends ScheduleTaskExecutor {
	public void execute() {
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from coverpagetb ");
			sql.append(" where trim(premoum_paid) = 'NO' ");
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement(sql.toString());
				rs =pstmt.executeQuery();
				while(rs.next()){
					PreparedStatement pstmt1 = null;
					ResultSet rs1 = null;
					try{
						String branch = rs.getString("policy_pmbr");
						String cat = rs.getString("policy_pmcat");
						String classes = rs.getString("policy_pmclass");
						String year = rs.getString("policy_pmyr");
						String no = rs.getString("policy_pmpolnd");
						BigDecimal coverpateId = rs.getBigDecimal("coverpage_id");
						String claimno = rs.getString("claim_no");
						String notificationno = rs.getString("notificationno");
						
						StringBuffer sql2 = new StringBuffer();
						sql2.append(" select * from Impremiump o ");
						sql2.append(" where  o.prm_pol_br =  '"+branch+"' ");
						sql2.append(" and  o.prm_pol_catg = '"+cat+"' ");
						sql2.append(" and  o.prm_pol_cl = '"+classes+"' ");
						sql2.append(" and  o.prm_pol_yr = "+year+"  ");
						sql2.append(" and  o.prm_pol_no = "+no+"   ");
						sql2.append(" and o.prm_trans_cd = '04'  ");
						System.out.println(sql2.toString());
						pstmt1= conn.prepareStatement(sql2.toString());
						
						rs1 = pstmt1.executeQuery();
						
						if(rs1.next()){
							updatePaid(coverpateId,notificationno,claimno,conn);
						}
						
					}finally{
						if(null!=rs1)
							rs1.close();
						if(null!=pstmt1)
							pstmt1.close();
					}
				}
			}finally{
				if(null!=rs)
					rs.close();
				if(null!=pstmt)
					pstmt.close();
				if(null!=conn)
					conn.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void updatePaid(BigDecimal id,String claimno,String notificationno,Connection conn) throws Exception{
		QueueSB qsb = createQueueSB();
		PaidSynchronizeMessage m = new PaidSynchronizeMessage();
		m.setClaimno(claimno);
		m.setId(id);
		m.setNotificationno(notificationno);
		qsb.put(DefaultQueueConstants.QUEUE_NAME_SYNCHRONIZE,m,DefaultQueueConstants.PIORITY_NORMAL
				,"Schedule Task"
				,"Synchronize task"
				,DefaultQueueConstants.JOB_STATUS_RELEASE
				,System.currentTimeMillis());
		///////////////////
		//Start Split queue host Thanaseth Mahasitithumrongkul Lek 2009/10/09[yyyy/mm/dd]
		qsb.callQueueRunningHost(DefaultQueueConstants.QUEUE_NAME_SYNCHRONIZE);
		//End Split queue host Thanaseth Mahasitithumrongkul Lek 2009/10/09[yyyy/mm/dd]
//		CommentByLek//WorkQueueController.getInstance().onPut(DefaultQueueConstants.QUEUE_NAME_SYNCHRONIZE);
		
	}
	
	public QueueSB createQueueSB() throws Exception{
		Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
        //env.put(Context.PROVIDER_URL,SystemEnvironment.getInstance().getBootStrapAddressServerWIN());
        env.put(Context.PROVIDER_URL,SystemEnvironment.getInstance().getBootStrapAddress());
        try {
			InitialContext ctx = new InitialContext(env);
//			System.out.println(" ============================= hrere is an lookup for remote test ===================================");
			Object s = ctx.lookup("ejb/th/co/msat/motor/queuemanager/QueueSBHome");
			System.out.println(s);
			QueueSBHome frHome = (QueueSBHome) PortableRemoteObject.narrow(s,QueueSBHome.class);
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
