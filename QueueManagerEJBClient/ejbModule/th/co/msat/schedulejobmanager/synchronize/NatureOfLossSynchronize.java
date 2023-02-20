/*
 * Created on 23 ¸.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.schedulejobmanager.synchronize;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import th.co.msat.synchronize.NatureOfLossSynchronizeMessage;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NatureOfLossSynchronize  extends ScheduleTaskExecutor  {

	/* (non-Javadoc)
	 * @see th.co.msat.schedulejobmanager.ScheduleTaskExecutor#execute()
	 */
	public void execute() {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append(" n.claimno, ");
		sql.append(" n.notificationno, ");
		sql.append(" n.regis_nofloss, ");
		sql.append(" clm.clm_nat_code , ");
		sql.append(" clm.clm_br, ");
		sql.append(" clm.clm_cat,  ");
		sql.append(" clm.clm_class,  ");
		sql.append(" clm.clm_yr , ");
		sql.append(" clm.clm_number  ");
		sql.append(" from msatlib.notificationtb n  ");
		sql.append(" left join mmflib.imclaimp clm   ");

		sql.append(" on substring(n.claimno ,1,2) = clm.clm_br  ");
		sql.append(" and substring(n.claimno ,3,1) = clm.clm_cat  ");
		sql.append(" and substring(n.claimno ,4,1) = clm.clm_class  ");
		sql.append(" and int(substring(n.claimno ,5,2)) = clm.clm_yr  ");
		sql.append(" and int(substring(n.claimno ,7)) = clm.clm_number  ");

		sql.append(" where trim(n.regis_nofloss) <> clm.clm_nat_code  ");
		sql.append(" and n.claimno<> ''   ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("================== nat code sync ====================");
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
//				not yet complete.
			
				System.out.println(rs.getString("claimno"));
				String natDesc = getDescriptionOfLoss(
						rs.getString("clm_br")
						,rs.getString("clm_cat")
						,rs.getString("clm_class")
						,rs.getString("clm_yr")
						,rs.getString("clm_number")
						,conn);
				String claimNo = rs.getString("claimno");
				String notino = rs.getString("notificationno");
				NatureOfLossSynchronizeMessage nmessage = new NatureOfLossSynchronizeMessage();
				nmessage.setClaimNo(claimNo);
				nmessage.setNatureOfLoss(natDesc);
				nmessage.setNotificationno(notino);
				nmessage.setUsername("Sys Admin");
				updateNatureOfloss(nmessage);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			
			
		}finally{
			if(null!=rs)
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(null!=pstmt)
				try {
					pstmt.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if(null!=conn)
				try {
					conn.close();
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
		}
	}
	
	public void updateNatureOfloss(NatureOfLossSynchronizeMessage m) throws Exception{
		QueueSB qsb = createFacingSheetSB();
		qsb.put(DefaultQueueConstants.QUEUE_NAME_PRINTING,m,DefaultQueueConstants.PIORITY_NORMAL,m.getUsername(),"Old Claim Reg",DefaultQueueConstants.JOB_STATUS_RELEASE,System.currentTimeMillis());
//		Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
		qsb.callQueueRunningHost(DefaultQueueConstants.QUEUE_NAME_PRINTING);
		//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]		
//		CommentByLek//WorkQueueController.getInstance().onPut(DefaultQueueConstants.QUEUE_NAME_PRINTING);
		
//		 calling nature of loss synchronize queue 
	} 
	
	private String getDescriptionOfLoss(String br,String cat,String cls,String yr,String no,Connection conn) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select '('||clm_nat_code||') '||clm_nature as nature_of_loss ");
		sql.append(" from mmflib.imclaimp  ");
		sql.append(" where clm_br = '"+br+"'  ");
		sql.append(" and clm_cat = '"+cat+"' ");
		sql.append(" and clm_class =  '"+cls+"' ");
		sql.append(" and clm_yr = "+yr+"  ");
		sql.append(" and clm_number = "+no+"  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
		}
		return "";
	}
	
	protected QueueSB createFacingSheetSB() throws CreateException, RemoteException, NamingException {
		Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
        env.put(Context.PROVIDER_URL, SystemEnvironment.getInstance().getBootStrapAddress());
        try {
			InitialContext ctx = new InitialContext(env);
//			System.out.println(" ============================= hrere is an lookup for remote test ===================================");
			Object s = ctx.lookup("ejb/th/co/msat/motor/queuemanager/QueueSBHome");
			System.out.println(s);
			QueueSBHome frHome = (QueueSBHome) PortableRemoteObject.narrow(s,QueueSBHome.class);
			return (QueueSB) frHome.create();
			
			
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
