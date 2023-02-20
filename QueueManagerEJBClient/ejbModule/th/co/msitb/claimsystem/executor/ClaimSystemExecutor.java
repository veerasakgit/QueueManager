/****************************************************/
/* ---------------- Modify History ---------------- */
/* ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2010/02/09[yyyy/mm/dd]
 * @description Add claim number
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2010/02/12[yyyy/mm/dd]
 * @description Write tran log
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since	2010/02/12[yyyy/mm/dd]
 * @description Add user update
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since	2010/02/12[yyyy/mm/dd]
 * @description claim number length more than 12
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since	2010/02/18[yyyy/mm/dd]
 * @description Add nature name
 * -------------------------------------------------
 * @modifier Praijittra K. Nok
 * @since	2010/02/25[yyyy/mm/dd]
 * @description Add Claimant
 * -------------------------------------------------*/



package th.co.msitb.claimsystem.executor;

import java.math.BigDecimal;
import java.sql.Connection;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import th.co.msat.motor.as400interface.action.Action;
import th.co.msat.motor.as400interface.action.ActionFactory;

import th.co.msat.motor.as400interface.util.Cache;
import th.co.msat.motor.as400interface.util.ConfigurationLoader;
import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.database.TranLogDAO;
import th.co.msat.motor.database.TranLogDAOImpl;
import th.co.msat.motor.database.vo.TranLog;

import th.co.msat.motor.log.TranLogFactory;
import th.co.msat.motor.logger.AS400InterfaceLogger;
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.executor.ExecutorImpl;


import th.co.msat.motor.services.MSATServiceLocator;
import th.co.msat.motor.util.DateUtil;
import th.co.msat.motor.util.Util;

public class ClaimSystemExecutor extends ExecutorImpl{

	
	public static final String PROCESS_CONTROL_STATUS = SystemEnvironment.getInstance().getDefaultHostName()+"ProcessController/UpdateProcessLogDtlServlet";
	//public static final String PROCESS_CONTROL_STATUS = "http://it13:9080/ProcessController/UpdateProcessLogDtlServlet";
	
	boolean alive = false;
	public static final String PROGRAM_ID = "ClaimSystemExecutor";
	Connection conn = null;
	Action ac = null;
	Cache cache = null;
	ClaimRegiterMessage message = null;
	
	//Start Claim number length 12 Veerasak Boonchern Aey 2010/02/12[yyyy/mm/dd]
	public static final int CLAIM_LENGTH = 12;
	//End Claim number length 12 Veerasak Boonchern Aey 2010/02/12[yyyy/mm/dd]
	
	public void execute() throws Exception {
		alive = true;
		String executeMessage = "";
		// TODO Auto-generated method stub
		
		message = (ClaimRegiterMessage)getMessage();
		
		cache = new Cache();
		
		cache.put(DefaultQueueConstants.SCRAP_QUEUE_NAME, PROGRAM_ID);
		cache.put("BRANCH",message.getBranch());
		cache.put("CLASSES",message.getClasses());
		cache.put("YEAR",message.getYear());
		cache.put("POLICYNO",message.getPolicyNo());
		cache.put("LOSSDATE",message.getLossDate());
		cache.put("NOTIFICATIONDATE",message.getNotificationDate());
		cache.put("INTEREST",message.getInterest());
		cache.put("NATURECODE",message.getNatureCode());
		cache.put("REVIEWCODE",message.getReviewCode());
		
		cache.put("CLAUSECODE",message.getClauseCode());
		cache.put("SURVEYEDBY",message.getSurveyedBy());
		cache.put("LOCATION_OF_LOSS", message.getLocationOfLoss());
		cache.put("LOCATION_OF_LOSS2", message.getLocationOfLoss2());
		cache.put("PROVINCE", message.getProvince());
		cache.put("ITEM_NO", message.getItemNo());
		cache.put("SURVEY_REPORT_NO", message.getSurveyReportNo());
		
		//Start Add Claimant Praijittra K. nok 2010/02/25[yyyy/mm/dd]
		cache.put("CLAIMANT", message.getClaimant());
		//End Add Claimant Praijittra K. nok 2010/02/25[yyyy/mm/dd]	

		cache.put("INVOICENO",message.getInvoiceNo());
		cache.put("RECOVERY",message.getRecovery());
		cache.put("TIMEBAR", message.getTimeBar());
		
		cache.put("RESCODE1",message.getResCode1());
		cache.put("RESERVEAMOUNT1",message.getReserveAmount1());
		cache.put("RESCODE2",message.getResCode2());
		cache.put("RESERVEAMOUNT2",message.getReserveAmount2());
		cache.put("RESCODE3",message.getResCode3());
		cache.put("RESERVEAMOUNT3",message.getReserveAmount3());
		cache.put("RESCODE4",message.getResCode4());
		cache.put("RESERVEAMOUNT4",message.getReserveAmount4());
		cache.put("RESCODE5",message.getResCode5());
		cache.put("RESERVEAMOUNT5",message.getReserveAmount5());
		cache.put("RESCODE6",message.getResCode6());
		cache.put("RESERVEAMOUNT6",message.getReserveAmount6());	
		
		cache.put("NOTIFICATION NO.", message.getNotificationNo());
		
		//Start Add claim number Veerasak Boonchern 2010/02/09[yyyy/mm/dd]
		cache.put("CLM_BNC", message.getClaimBranch());
		cache.put("CLM_CLS", message.getClaimClass());
		cache.put("CLM_YR", message.getClaimYear());
		cache.put("CLM_NO", message.getClaimNo());
		//End Add claim number Veerasak Boonchern 2010/02/09[yyyy/mm/dd]
		
		//Start Add nature name Veerasak Boonchern 2010/02/18[yyyy/mm/dd]
		cache.put("NATURENAME", message.getNatureName());
		//End Add nature name Veerasak Boonchern 2010/02/18[yyyy/mm/dd]
	
		cache.setReady(true);
		
	//	String claimno = message.getBranch()+"C"+message.getClasses()+message.getYear()+message.getClaimno();
		
		ConfigurationLoader loader = new ConfigurationLoader();
		
		loader.load("PageValidator.xml",ClaimSystemExecutor.class.getResourceAsStream("/xmlconfig/claimsystem/PageValidator.xml"));             
		loader.load("CLS_SignOns.xml",ClaimSystemExecutor.class.getResourceAsStream("/xmlconfig/claimsystem/CLS_SignOns.xml"));
		loader.load("CLS_Menu.xml",ClaimSystemExecutor.class.getResourceAsStream("/xmlconfig/claimsystem/CLS_Menu.xml"));
		loader.load("CLS_IMNUCL02Ss.xml",ClaimSystemExecutor.class.getResourceAsStream("/xmlconfig/claimsystem/CLS_IMNUCL02Ss.xml"));
		loader.load("CLS_ICL0010Ss.xml",ClaimSystemExecutor.class.getResourceAsStream("/xmlconfig/claimsystem/CLS_ICL0010Ss.xml"));
		
		
		loader.load("CLS_ICL0011Ss.xml",ClaimSystemExecutor.class.getResourceAsStream("/xmlconfig/claimsystem/CLS_ICL0011Ss.xml"));
		loader.load("CLS_ICL0020Ss.xml",ClaimSystemExecutor.class.getResourceAsStream("/xmlconfig/claimsystem/CLS_ICL0020Ss.xml"));
		loader.load("CLS_ICL0025Ss.xml",ClaimSystemExecutor.class.getResourceAsStream("/xmlconfig/claimsystem/CLS_ICL0025Ss.xml"));
		loader.load("CLS_ICL1070Ss.xml",ClaimSystemExecutor.class.getResourceAsStream("/xmlconfig/claimsystem/CLS_ICL1070Ss.xml"));
		                  
		
		loader.load("User Properties",ClaimSystemExecutor.class.getResourceAsStream("/xmlconfig/claimsystem/user.properties"));

		Properties p = new Properties();
		p.load(ClaimSystemExecutor.class.getResourceAsStream("/xmlconfig/claimsystem/user.properties"));
		cache.put("group.for.not.replace",p.get("group.for.not.replace"));
		
		cache.put("working_page","CLS_ICL0010S");
		
		
		try{
			conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);
			

			
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
				//cache.put("ERROR_MESSAGE","");
				if(null!=cache.get(Cache.ERROR_MESSAGE)&& !"".equals(cache.get(Cache.ERROR_MESSAGE))){
					executeMessage = (String)cache.get(Cache.ERROR_MESSAGE);
					throw new Exception((String)cache.get(Cache.ERROR_MESSAGE));
				}else{
					executeMessage = "Process state error";
					throw new Exception(executeMessage);
				}
			}
		
			ac.signoff();
			
//			 Test update ClaimNo
			String claimNo = (String) cache.get("Claim Number");
			// notification for log
			
			String notificationNo =  message.getNotificationNo();
			
			//Start Add user update Veerasak Boonchern 2010/02/12[yyyy/mm/dd]
			//writeNotification(notificationNo, claimNo, message.getReviewCode());
			writeNotification(notificationNo, claimNo, message.getUserUpdate());
			//End Add user update Veerasak Boonchern 2010/02/12[yyyy/mm/dd]

		}catch(Exception e){
			
			ClaimRegiterDAO dao = new ClaimRegiterDAO();
			//Start Claim number length 12 Veerasak Boonchern Aey 2010/02/12[yyyy/mm/dd]
			//if( cache.get("Claim Number") != null && cache.get("Claim Number").toString().length() > 0){
			if( cache.get("Claim Number") != null && cache.get("Claim Number").toString().length() >= 12){
			//End Claim number length 12 Veerasak Boonchern Aey 2010/02/12[yyyy/mm/dd]
				//Start Add user update Veerasak Boonchern 2010/02/12[yyyy/mm/dd]
				//dao.updateClaimNo(message.getNotificationNo(), cache.get("Claim Number").toString(), message.getReviewCode(), "E");
				dao.updateClaimNo(message.getNotificationNo(), cache.get("Claim Number").toString(), message.getUserUpdate(), "E");
			}else{
				//dao.updateClaimNo(message.getNotificationNo(), "", message.getReviewCode(), "E");
				dao.updateClaimNo(message.getNotificationNo(), "", message.getUserUpdate(), "E");
				//End Add user update Veerasak Boonchern 2010/02/12[yyyy/mm/dd]
			}
			
			//Start Write tran log Veerasak Boonchern Aey 2010/02/12[yyyy/mm/dd]
			writeTranLog(e);
			//End Write tran log Veerasak Boonchern Aey 2010/02/12[yyyy/mm/dd]
			
			cache.setTopProcessState(Cache.STATE_ERROR);
			e.printStackTrace();
			ac.signoff();
			ac.destroy();
			executeMessage = e.getMessage();
			throw e;
			
		}finally{
			
			if(null!=conn)
				conn.close();
		}
	
		
		//---------------------------------------------------------------------------------------------------------
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Claim No : " + cache.get("Claim Number"));
		
		alive = false;
		
	}

	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	public void onTimeout() {
		// TODO Auto-generated method stub
		
	}
	
	
	// write message to database
	public void writeNotification(String notificationNo, String claimNo, String userName) throws Exception{
		ClaimRegiterDAO dao = new ClaimRegiterDAO();
		dao.updateClaimNo(notificationNo, claimNo, userName, "S");
		
//		###################### Complete Action Notification to SimpleWorkFlow ######################	

	 	
		try {
			 // param = status,userComplete,qInyear,qNumber,userCheckOut
			//for test
			//String qyearnumber = dao.getQyearQnumberImp( notificationNo, 27 );
		
			//for production
			String[] yn = dao.getQyearQnumberImp( notificationNo, 25 ).split(",");
			
			String data2= "C"+","+ userName +","+ yn[0] +","+ yn[1] +","+ userName;
			
			Connection2Servlet connection2Servlet = new Connection2Servlet();
			
			System.out.println("Connection2Servlet");
			connection2Servlet.connection2Servlet( PROCESS_CONTROL_STATUS,data2);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SENT TO PROCESS CONTROL ERROR!");
			e.printStackTrace();
		}
		
		  

	}
	
	
	//Start Write tran log Veerasak Boonchern Aey 2010/02/12[yyyy/mm/dd]
	private void writeTranLog(Throwable twb) throws Exception
	{	
		TranLog tranlog = new TranLog();
		
		tranlog.setLogFor(TranLogFactory.LOG_CODE_CLAIMSYSTEM);
		tranlog.setLogName(PROGRAM_ID);
		
		Calendar calendar = Calendar.getInstance();
		int dd = calendar.get(Calendar.DATE);
		int mm = calendar.get(Calendar.MONTH) + 1;
		int yyyy = calendar.get(Calendar.YEAR);
		if (yyyy > 2500)
			yyyy = yyyy - 543;
		String date = Util.zeroAtFront("" + yyyy, 4) + Util.zeroAtFront("" + mm, 2) + Util.zeroAtFront("" + dd, 2);
		tranlog.setLogDate(Integer.parseInt(date));
		
		int second = calendar.get(Calendar.SECOND);
		int minute = calendar.get(Calendar.MINUTE);
		int hour = calendar.get(Calendar.HOUR);
		String time = Util.zeroAtFront("" + hour, 2) + Util.zeroAtFront("" + minute, 2) + Util.zeroAtFront("" + second, 2);
		tranlog.setLogTime(Integer.parseInt(time));
		
		tranlog.setLogStatus(TranLogFactory.LOG_STATUS_ERROR);
		
		if( cache.get("Claim Number") != null && cache.get("Claim Number").toString().length() > 0)
		{
			tranlog.setLogMessage("Claim No.\n" + twb.getMessage());
			tranlog.setLogRecordId(cache.get("Claim Number").toString());
		} else
		{
			tranlog.setLogMessage("Policy No.\n" + twb.getMessage());
			String pol_no = cache.get("BRANCH").toString() +
							cache.get("CLASSES").toString() +
							cache.get("YEAR").toString() +
							cache.get("POLICYNO").toString();
			tranlog.setLogRecordId(pol_no);
		}
		
		TranLogDAO tranlogDAO = new TranLogDAOImpl();
		tranlogDAO.insertQueueLog(tranlog);
	}
	//End Write tran log Veerasak Boonchern Aey 2010/02/12[yyyy/mm/dd]
}
