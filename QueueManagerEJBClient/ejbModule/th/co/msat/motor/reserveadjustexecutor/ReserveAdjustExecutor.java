/*
 * Created on 17 ก.พ. 2552
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.reserveadjustexecutor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;


import th.co.msat.motor.as400interface.action.Action;
import th.co.msat.motor.as400interface.action.ActionFactory;
import th.co.msat.motor.as400interface.process.ReservCodeEditProcess;
import th.co.msat.motor.as400interface.util.Cache;
import th.co.msat.motor.as400interface.util.ConfigurationLoader;
import th.co.msat.motor.queuemanager.database.DataConverter;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.msat.motor.queuemanager.executor.ExecutorImpl;
import th.co.msat.motor.services.MSATServiceLocator;



/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReserveAdjustExecutor extends ExecutorImpl{
	boolean alive = false;
	public static final String PROGRAM_ID = "ReserveAdjustExecutor";
	private ReserveAdjustMessage message;
	Connection conn = null;
	Action ac = null;
	Cache cache = null;
	BigDecimal pendingJob = new BigDecimal(0);
	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#execute()
	 */
	public void execute() throws Exception {
		System.out.println("+++++++++++++++++++++ Starting ReserveAdjustExecutor +++++++++++++++++++++++");
		alive = true;
		String executeMessage = "";
		// TODO Auto-generated method stub
		
		 message = (ReserveAdjustMessage)getMessage();
		 
		
		cache = new Cache();
		
		cache.put("BRANCH",message.getBranch());
		cache.put("CLASS", message.getClasses());
		cache.put("YEAR", message.getYear()); 
		cache.put("CLAIMNO",message.getClaimno());
		cache.put("NOTIFICATION NO.",message.getClaimno());
		cache.put("TRANSDATE",new SimpleDateFormat("ddMMyy",Locale.US).format(new java.util.Date()));
		cache.setReady(true);
		
		String claimno = message.getBranch()+"C"+message.getClasses()+message.getYear()+message.getClaimno();
		
		ConfigurationLoader loader = new ConfigurationLoader();
		
		//loader.load("ClaimRegisterMaintenanceMenus.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/ClaimRegisterMaintenanceMenus.xml"));   
		loader.load("CM_IMNUCL02S.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/CM_IMNUCL02S.xml"));
		loader.load("CM_ICL0010Ss.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/CM_ICL0010Ss.xml"));
		loader.load("CM_ICL0011Ss.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/CM_ICL0011Ss.xml"));
		loader.load("CM_ICL0020Ss.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/CM_ICL0020Ss.xml"));
		loader.load("CM_ICL0060Ss.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/CM_ICL0060Ss.xml"));                     
		//loader.load("ICL0310Ss.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/ICL0310Ss.xml"));
		loader.load("CM_ICL1070Ss.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/CM_ICL1070Ss.xml"));                       
		//loader.load("IMNUCL02Ss.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/IMNUCL02Ss.xml"));
		loader.load("CM_LogConfigs.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/CM_LogConfigs.xml"));                      
		loader.load("CM_ManiMenu2s.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/CM_ManiMenu2s.xml"));
		loader.load("CM_ManiMenus.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/CM_ManiMenus.xml"));                       
		//loader.load("MSIMN99s.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/MSIMN99s.xml"));
		loader.load("PageValidator.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/PageValidator.xml"));                  
		loader.load("CM_SignOns.xml",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/CM_SignOns.xml"));
		loader.load("User Properties",ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/user.properties"));

		Properties p = new Properties();
		p.load(ReserveAdjustExecutor.class.getResourceAsStream("/xmlconfig/claimmovement/user.properties"));
		cache.put("group.for.not.replace",p.get("group.for.not.replace"));
		
		cache.put("working_page","CM_ICL0060Ss");
		
		
		try{
			conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);
			/* Modify by Thanaseth M. 29-01-2010
			 * * รายการที่ยังไม่สามารถเอาเข้า GEN400 ได้  
			 * ถ้า Queue ที่ส่งมาอีกเป็น Claim เดียวกัน และ Loss Code เดียวกัน 
			 * ให้ holding ไว้ทั้งหมด เพราะ ระบบควรบังคับให้เอาเข้า GEN400 ตามลำดับFixed bug : 
			 */
			if(isholdingByPreviousJob(conn)){
				//Don't do anything===Skip this job
				cache.setTopProcessState(Cache.STATE_ERROR);
				cache.put(Cache.ERROR_MESSAGE,"Couldn't process the job.  Please try to completed job "+getJobid()+" before run the job.");
				executeMessage = "Couldn't process the job.  Please try to completed job "+pendingJob+" before run this job.";
				throw new Exception(executeMessage);
			}else{
				cache.put("NOTIFICATIONDATE",getNotificationDate(claimno,conn));
				String policyClass = getPolicyClass(claimno,conn);
				if(policyClass == null){
					policyClass = "";
				}
				if(policyClass.indexOf("VG")!=-1){
					for (Iterator iter = message.getPayeeList().iterator(); iter.hasNext();) {
						Map item = (Map) iter.next();
						if(((String)item.get("PAYEE_CODE_GROUP")).trim().equals("N999")){
							item.put("PAYEE_CODE_GROUP","N201");
						}
						if(((String)item.get("PAYEE_CODE_GROUP")).trim().equals("O999")){
							item.put("PAYEE_CODE_GROUP","O201");
						}
						if(((String)item.get("OLD_PAYEE_CODE")).trim().equals("N999")){
							item.put("OLD_PAYEE_CODE","N201");
						}
						if(((String)item.get("OLD_PAYEE_CODE")).trim().equals("O999")){
							item.put("OLD_PAYEE_CODE","O201");
						}	
					}
				}
			
				cache.put(ReservCodeEditProcess.PAYEE_LIST,message.getPayeeList());
			
				ac = ActionFactory.getInstance().getSingletonAction(PROGRAM_ID);
				ac.setCache(cache);
				ac.setConnection(conn);
				ac.setConfigurationLoader(loader);
				ac.loadConfiguration();
				System.out.println("+++++++++++++++++++++ Starting ReserveAdjustExecutor ++++SIGNON++++++++++++");
				if(!ac.isSignon())
					ac.signon();
				System.out.println("+++++++++++++++++++++ Starting ReserveAdjustExecutor ++++EXECUTE++++++++++++");
				ac.execute();
				if(null!=cache.getTopProcessState()&&cache.getTopProcessState().equals(Cache.STATE_ERROR)){
					if(null!=cache.get(Cache.ERROR_MESSAGE)&& !"".equals(cache.get(Cache.ERROR_MESSAGE))){
						executeMessage = (String)cache.get(Cache.ERROR_MESSAGE);
						throw new Exception((String)cache.get(Cache.ERROR_MESSAGE));
					}else{
						executeMessage = "Process state error";
						throw new Exception(executeMessage);
					}
				}
				JobQueueDAO jqdao = new JobQueueDAO();
				String errmsg = (String) cache.get("UNKNOW_MSG");
				if((errmsg != null && errmsg.length() > 0)||null==jqdao.findNextJob(getQueueName()) ){
				//if(null==jqdao.findNextJob(getQueueName())){
				ac.signoff();
			}
			
			}

		}catch(Exception e){
			System.out.println("+++++++++++++++++++++ Starting ReserveAdjustExecutor ++++EXCEPTION++++++++++++");
			cache.setTopProcessState(Cache.STATE_ERROR);
			e.printStackTrace();
			ac.signoff();
			ac.destroy();
			executeMessage = e.getMessage();
			throw e;
			
		}finally{
			System.out.println("+++++++++++++++++++++ Starting ReserveAdjustExecutor ++++FINAL++++++++++++");
			try{
				System.out.println("+++++++++++++++++++++ Starting ReserveAdjustExecutor ++++Reconcile++++");
				writeMessage(executeMessage);
				System.out.println("+++++++++++++++++++++ Starting ReserveAdjustExecutor ++++Reconcile finished++++");
			}catch(Exception e){
				System.out.println("+++++++++++++++++++++ Starting ReserveAdjustExecutor ++++Reconcile exception++++");
			}
			if(null!=conn)
				conn.close();
		}
	
		
		
		alive = false;
	}
	
	private void writeMessage(String executeMessage) throws Exception{

		ReservAmountReconcileDAO rardao = new ReservAmountReconcileDAO();
		boolean reconcile = true;
		String errmsg = (String)(cache.get(Cache.ERROR_MESSAGE) == null? "":cache.get(Cache.ERROR_MESSAGE));
		try{
			for(Iterator iter = message.getPayeeList().iterator();iter.hasNext();){
				Map m = (Map)iter.next();
//				{OLD_PAYEE_CODE=N056, PARTY_TYPE=I, GROUP_ID=2, PAYEE_AMOUNT=11209.8, LOSS_CODE=416, PAYEE_CODE_GROUP=N056}
				
//				BigDecimal authorizeAmount =rardao.getReservAmountFromAuthorize(aixConn,message.getReferenceId().trim()
//						,(String)m.get("LOSS_CODE")
//						,(String)m.get("PARTY_TYPE")
//						,(String)m.get("GROUP_ID")
//						) ;
				
				
				
				//insert message  >> tuta
				BigDecimal authorizeAmount = null;
//				if((errmsg!= null && errmsg.trim().length() > 0)|| cache.getTopProcessState().equals(Cache.STATE_ERROR)){
//					authorizeAmount =new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
//				}else{
					
					//int resultInsertMessage=rardao.insertMessageDataQueue25(message.getReferenceId().trim(),m,getJobid());
					int resultInsertMessage=rardao.messageDataQueue25Handler(message.getReferenceId().trim(),m,getJobid());
					if(resultInsertMessage<=0){
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!! insertMessageDataQueue25 fail !!!!!!!!!!!!!!!!!!!!!!");
					}
					authorizeAmount =rardao.getReservAmountFromMessageQueue25(getJobid());
//				}
				
				
/*				if(!errmsg.equals("Record Lock")){
					int resultInsertMessage=rardao.insertMessageDataQueue25(message.getReferenceId().trim(),m,getJobid());
					if(resultInsertMessage<=0){
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!! insertMessageDataQueue25 fail !!!!!!!!!!!!!!!!!!!!!!");
					}
					authorizeAmount =rardao.getReservAmountFromMessageQueue25(message.getReferenceId().trim(),(String)m.get("PAYEE_CODE_GROUP"));
				}else{
					authorizeAmount =new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
				}*/
				
				System.out.println("ReserveAdjustExecutor.writeMessage.authorizeAmout completed " + authorizeAmount);
				double payeeAmount = ((BigDecimal) m.get("PAYEE_AMOUNT")).doubleValue();
				BigDecimal gen400Amount = rardao.getReservAmount(message.getReferenceId(),(String)m.get("PAYEE_CODE_GROUP"));
				//overwrite gen400Amount is zero in case change payee code only.
				//overwrite gen400Amount is zero in case return value is null.
				String newCode = (String) m.get(ReservCodeEditProcess.PAYEE_CODE_GROUP);
				String oldCode = (String) m.get(ReservCodeEditProcess.PAYEE_CODE_OLD);
				if((!newCode.trim().equals(oldCode.trim()) || authorizeAmount.compareTo(new BigDecimal(0))==0)
						|| null==gen400Amount){
					gen400Amount = new BigDecimal(0);
				}
				float auth_float = authorizeAmount.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				float gen400_float = gen400Amount.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				//reconcile = reconcile&(
				//		authorizeAmount.setScale(2,BigDecimal.ROUND_HALF_UP)
				//		.compareTo(gen400Amount.setScale(2,BigDecimal.ROUND_HALF_UP))==0);
				if(auth_float == gen400_float){
					reconcile = true;
				}else{
					reconcile = false;
				}
				if(executeMessage != null && executeMessage.length() > 0){
					errmsg = executeMessage;	
				}else{
					if(!reconcile){
						System.err.println("AuthorizeAmount " + auth_float);
						System.err.println("GEN400Amount " + gen400_float);
						errmsg = "Reconcile result is unmatch!";
					}
				}
				rardao.insertReconcile(message.getReferenceId(),(String)m.get("PAYEE_CODE_GROUP"),authorizeAmount,gen400Amount,errmsg,getJobid());
			}
			if(!reconcile)
				throw new Exception(errmsg);
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}	
	}

	
	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#onTimeout()
	 */
	public void onTimeout() {
		try{
			writeMessage("Process time out");
			ac.signoff();
			ac.destroy();
		}catch(Exception e){}
		if(null!=conn)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		alive = false;
	}
	
	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#isAlive()
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/******************** Modify by Thanaseth
	 * รายการที่ยังไม่สามารถเอาเข้า GEN400 ได้  
	 * ถ้า Queue ที่ส่งมาอีกเป็น Claim เดียวกัน และ Loss Code เดียวกัน 
	 * ให้ holding ไว้ทั้งหมด เพราะ ระบบควรบังคับให้เอาเข้า GEN400 ตามลำดับ
	 * @return true or fause
	 */
	public boolean isholdingByPreviousJob(Connection conn){
		boolean result = false;
		String referenceno =message.getReferenceId();/* Claim No */
		String payeeCode = "";
		for (Iterator iter = message.getPayeeList().iterator(); iter.hasNext();) {
			Map item = (Map) iter.next();
			payeeCode = item.get("PAYEE_CODE_GROUP").toString();
			if(payeeCode == null) payeeCode = "";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select * from jobtb ");
		sql.append(" where queueid = 25");
		sql.append(" and jobid < ?");
		sql.append(" and status <> 'C'");
		sql.append(" and referenceno = ?");
		sql.append(" order by jobid asc");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = conn.prepareStatement(sql.toString());
			ps.setBigDecimal(1,getJobid());
			ps.setString(2,message.getReferenceId());
			rs = ps.executeQuery();
			String holdingByPreviousJob ="N";
			while(rs.next()){
				ReserveAdjustMessage msg = (ReserveAdjustMessage)DataConverter.toObject(rs.getBytes("message"));
				String payeeCodex = "";
				for (Iterator iter = msg.getPayeeList().iterator(); iter.hasNext();) {
					Map item = (Map) iter.next();
					payeeCodex = item.get("PAYEE_CODE_GROUP").toString();
					if(payeeCodex == null) payeeCodex = "";
				}
				if(payeeCode.trim().equals(payeeCodex.trim())){
					pendingJob =  rs.getBigDecimal("jobid");
					holdingByPreviousJob= "Y";
					break;
				}
					
			}
			if(holdingByPreviousJob.equals("Y")){
				result = true;
			}else{
				result = false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=rs)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(null!=ps)
				try {
					ps.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
		}
		return result;
	}
	public String getPolicyClass(String no,Connection conn){
		StringBuffer sql = new StringBuffer();
		sql.append(" select policy_pmclass from coverpagetb ");
		sql.append(" where trim(claim_no) = ? ");
		System.out.println(sql.toString()+" "+no);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1,no.trim());
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString(1);
			}	
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=rs)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(null!=ps)
				try {
					ps.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
		}
		return "";
	}
	
	public Integer getNotificationDate(String no,Connection conn){
		StringBuffer sql = new StringBuffer();
		sql.append(" select create_date  from coverpagetb where trim(claim_no) = ? ");
		System.out.println(sql.toString()+" "+no);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1,no.trim());
			rs = ps.executeQuery();
			if(rs.next()){
				Integer in = new Integer(rs.getInt(1));
				return in;
			}	
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=rs)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(null!=ps)
				try {
					ps.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
		}
		return new Integer(0);
	}

}
