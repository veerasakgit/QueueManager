/*
 * Created on 3 ต.ค. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.scrapexecutor;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

import th.co.msat.motor.as400interface.action.Action;
import th.co.msat.motor.as400interface.action.ActionFactory;
import th.co.msat.motor.as400interface.process.ResCode;
import th.co.msat.motor.as400interface.util.Cache;
import th.co.msat.motor.as400interface.util.ConfigurationLoader;
import th.co.msat.motor.as400interface.util.DateUtil;

import th.co.msat.motor.config.ActionQueueConstant;
import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.database.FacingSheetSB;
import th.co.msat.motor.database.FacingSheetSBHome;
import th.co.msat.motor.database.vo.Notification;

import th.co.msat.motor.queuemanager.WorkQueueController;
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.msat.motor.queuemanager.executor.ExecutorImpl;
import th.co.msat.motor.scrap.database.ReserveDAOImpl;
//import th.co.msat.motor.scrap.database.Cache;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ScrapExecutor extends ExecutorImpl{
	private boolean alive = false;
	private th.co.msat.motor.as400interface.action.Action ac =null;
	public static final String COVERPAGE_PRINTING_QUEUE = "Coverpage Printing Queue                          ";
	public static final String PROGRAM_ID = "ScrapExecutor";
	Connection conn = null;
	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#execute()
	 */
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		alive = true;
		String claimno = null;
		try {
			final ScrapMessage message = (ScrapMessage)getMessage();
			String notificationNo = message.getReferenceId();
			Notification n = new th.co.msat.motor.queuemanager.database.NotificationDAOImpl()
					.getNotificationForScrap(notificationNo);
			if(null==n){
				throw new Exception(" Notification is not valid.");
			}
			if(n.getClaimNo()!=null&&!"".equals(n.getClaimNo().trim())){
				throw new Exception("Notification is registered.:"+notificationNo+" with Claim No."+n.getClaimNo());
			}
			Cache cache = new Cache();
			cache.put(DefaultQueueConstants.SCRAP_QUEUE_NAME, PROGRAM_ID);
			System.err.println("@@@@@@ScrapExecutor@@@@@@===starting input to AS400 "+notificationNo);
			try{
				conn = getConnection();
				scrap(n,cache);
				ConfigurationLoader loader = new ConfigurationLoader();
				//loader.load("ClaimRegisterMaintenanceMenu.xml",ScrapExecutor.class.getResourceAsStream("/xmlconfig/claimregister/ClaimRegisterMaintenanceMenu.xml"));   
				loader.load("CR_IMNUCL02S.xml",ScrapExecutor.class.getResourceAsStream("/xmlconfig/claimregister/CR_IMNUCL02S.xml"));
				loader.load("CR_ICL0010S.xml",ScrapExecutor.class.getResourceAsStream("/xmlconfig/claimregister/CR_ICL0010S.xml"));
				loader.load("CR_ICL0011S.xml",ScrapExecutor.class.getResourceAsStream("/xmlconfig/claimregister/CR_ICL0011S.xml"));                       
				loader.load("CR_ICL0020S.xml",ScrapExecutor.class.getResourceAsStream("/xmlconfig/claimregister/CR_ICL0020S.xml"));
				loader.load("CR_ICL1070S.xml",ScrapExecutor.class.getResourceAsStream("/xmlconfig/claimregister/CR_ICL1070S.xml"));                       
				loader.load("CR_LogConfig.xml",ScrapExecutor.class.getResourceAsStream("/xmlconfig/claimregister/CR_LogConfig.xml"));
				loader.load("CR_ManiMenu.xml",ScrapExecutor.class.getResourceAsStream("/xmlconfig/claimregister/CR_ManiMenu.xml"));                       
				loader.load("CR_ManiMenu2.xml",ScrapExecutor.class.getResourceAsStream("/xmlconfig/claimregister/CR_ManiMenu2.xml"));
				loader.load("PageValidator.xml",ScrapExecutor.class.getResourceAsStream("/xmlconfig/claimregister/PageValidator.xml"));                  
				loader.load("CR_SignOn.xml",ScrapExecutor.class.getResourceAsStream("/xmlconfig/claimregister/CR_SignOn.xml"));
				loader.load("User Properties",ScrapExecutor.class.getResourceAsStream("/xmlconfig/claimregister/user.properties"));
				cache.put("working_page","CR_ICL0010S");
				ac = ActionFactory.getInstance().getSingletonAction(PROGRAM_ID);
				ac.setConnection(conn);
				ac.setConfigurationLoader(loader);
				ac.setCache(cache);
				ac.loadConfiguration();
				cache.setReady(true);
				if(!ac.isSignon())
					ac.signon();
				ac.execute();
				System.out.println(" Finish Thread :"+this.hashCode()+" "+new Date());
			}finally{
				if(null!=conn)
					conn.close();
			}
			JobQueueDAO jqdao = new JobQueueDAO();
			try {
				System.out.println(" ===================================Job Complete Update Status ==============================");
				jqdao.updateJobStatus(getJobid(),DefaultQueueConstants.JOB_STATUS_COMPLETE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			final int noti = n.getTransactionIndex();
			
			alive = false;
			//if(null==jqdao.findNextJob(getQueueName()))
			ac.signoff();
			claimno = new th.co.msat.motor.queuemanager.database.NotificationDAOImpl().getClaimNo(noti);
			System.err.println("@@@@@@ScrapExecutor@@@@@@===finished input to AS400 "+notificationNo);
			if(null!=claimno&&!"".equals(claimno.trim())){
/* Comment by Thanaseth ...Waiting for implement*/				
				////สร้าง Action Attach Survey
			System.err.println("@@@@@@ScrapExecutor@@@@@@===process sentToActionQueue "+notificationNo);	
				if(n.getSurveyorCode()!=null&&!n.getSurveyorCode().trim().equals("")){
					String [] res1 = null;
					if(!isExistsAction(notificationNo,n.getSurveyorCode())){
						 res1 = sentToActionQueue(notificationNo,message.getUsername());
					}
					if(null==res1)
						System.out.println(" insert action dfs not complete!");
				}
				/*sentToMotorParty(notificationNo
								,message.getInsureName()
								,message.getLicense()
								,message.getContractno()
								,"System"
								
								,Float.toString( n.getSurveryFee())
								,"0"
							    ,"0"
								,ActionQueueConstant.UNDEFINE_LOSS_RESERVE);*/
/* Modify by Thanaseth ...Auto a create loss reserve to authorization system */
/* Modify date : 28-10-2009    Effective date : 01-11-2009
 * Modify description : Add the default claim reserve 9999 to authorizatio system * 
 */
				String dfReserveAmount = "0";
				if(cache.get("N999") != null){
					dfReserveAmount= (String)cache.get("N999");
				}else{
					if(cache.get("N201") != null){
						dfReserveAmount= (String)cache.get("N201");
					}
				}
				System.err.println("@@@@@@ScrapExecutor@@@@@@===process sentTo eClaim "+notificationNo);	
				sentToMotorParty(notificationNo
						,message.getInsureName()
						,message.getLicense()
						,message.getContractno()
						,"System"
						,dfReserveAmount
						,"0"
						,"0"
						,ActionQueueConstant.UNDEFINE_LOSS_RESERVE);
				System.err.println("@@@@@@ScrapExecutor@@@@@@===process sentTo eClaim finished "+notificationNo);
 /* Modify by Thanaseth ...Auto a create loss reserve to authorization system */
 /* Modify date : 28-10-2009    Effective date : 01-11-2009
  * Modify description : Add the default claim reserve 9999 to authorizatio system
    Modify end statement*/ 
				Thread t = new Thread(new Runnable(){
				public void run() {
					
						try {
							System.out.println("--------------------- put printing ----------------------");
							String claimno = new th.co.msat.motor.queuemanager.database.NotificationDAOImpl()
							.getClaimNo(noti);
							System.err.println("@@@@@@ScrapExecutor@@@@@@===Put thread COVERPAGE " +claimno);
							putPrintingJob(claimno,message.getUsername(),DefaultQueueConstants.JOB_STATUS_RELEASE,5,message.getJobgroup());
						
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				t.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if(null==claimno&&"".equals(claimno)){
				throw e;
			}
			System.out.println("+++++++++++++++++++++ Starting ScrapExecutor ++++EXCEPTION++++++++++++");
			e.printStackTrace();
			ac.signoff();
			ac.destroy();
			throw e;
			
			
		}
	}
	
	private String [] createSurveyFeeAction(String notificationno,String username) throws UnsupportedEncodingException{
		String result ="";
		URL url_;
		InputStream inputStream =null;
		try {
//			System.out.println("=================Sent to Action Queue==================");
//			System.out.println(ActionQueueConstant.URL_ACTION_QUEUE
//					+"?"+ActionQueueConstant.PARAM_NAME_USER+"="+username.replaceAll(" ","%20")
//					+"&"+ActionQueueConstant.PARAM_NAME_ACTION_ID+"="+ActionQueueConstant.PARAM_ACTION_ID
//					+"&"+ActionQueueConstant.PARAM_NAME_REFER_ID+"="+notificationno
//					+"&"+ActionQueueConstant.PARAM_NAME_SET_ID+"="+ActionQueueConstant.PARAM_SET_ID
//					+"&"+ActionQueueConstant.PARAM_NAME_GROUP_OF_WORK+"="+ActionQueueConstant.PARAM_GROUP_OF_WORK);
			url_ = new URL(ActionQueueConstant.URL_ACTION_QUEUE
					+"?"+ActionQueueConstant.PARAM_NAME_USER+"="+username.replaceAll(" ","%20")
					+"&"+ActionQueueConstant.PARAM_NAME_ACTION_ID+"="+ActionQueueConstant.ACTION_SURVEY_FEE
					+"&"+ActionQueueConstant.PARAM_NAME_REFER_ID+"="+notificationno
					+"&"+ActionQueueConstant.PARAM_NAME_SET_ID+"="+ActionQueueConstant.PARAM_SET_ID
					+"&"+ActionQueueConstant.PARAM_NAME_GROUP_OF_WORK+"="+ActionQueueConstant.PARAM_GROUP_OF_WORK
					+"&condition=ClaimReg"
					);
			
			URLConnection connection = url_.openConnection();
		 	if (connection instanceof HttpURLConnection) {
	            HttpURLConnection httpConnection = (HttpURLConnection)connection;
	            httpConnection.setRequestMethod("GET");
	            httpConnection.setDoOutput(true);
	            httpConnection.connect();
	
	             /*
	              * Initialize output stream
	              */
	            DataOutputStream outttt = new DataOutputStream( httpConnection.getOutputStream() );
	
	            /*
	             * write dataList  
	             */
	            outttt.write(123);                         
	            outttt.flush();
	            //System.out.print("httpConnection=="+httpConnection);
	            //prin out data
	            inputStream = httpConnection.getInputStream();
	            //System.out.print("inputStream=="+inputStream);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	            
	            String line = null;
                while ((line = reader.readLine()) != null) {
                   //System.out.println(line);
                	result+=line;
                }
                
	        }
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return result.split(",");
		
	}
	
	private static String encode(String s,String encoding){
//		if(NO.equals(isEncoding)){
//			return s;
//		}
		Charset in = Charset.forName(encoding);
//	    Charset out = Charset.forName(outEncoding);
//	    CharsetDecoder decoder = out.newDecoder();
	    CharsetEncoder encoder = in.newEncoder();
	    
	    try {
	        ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(s));
//	        CharBuffer cbuf = decoder.decode(bbuf);
	        return bbuf.toString();
	    } catch (CharacterCodingException e) {
	    	return s;
	    }
	}
	
	
	private void putPrintingJob(String no,String username,String status,int piority,long jobgroup) throws Exception{

		FacingSheetSB f=  createFacingSheetSB();
		f.putJob(no,username,status,piority,jobgroup);
		//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
		f.callQueueRunningHost(COVERPAGE_PRINTING_QUEUE);
		//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]		
//		CommentByLek//WorkQueueController.getInstance().onPut(COVERPAGE_PRINTING_QUEUE);
		
		

	}
	
	

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#onTimeout()
	 * 
	 */
	public void onTimeout() {
		try{
			if(null!=conn&& !conn.isClosed()){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ac.setTimeout(true);
		alive = false;
//		try {
//			Thread.currentThread().sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			ac.signoff();
		ac.destroy();
//		} catch (ECLErr e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		while(ac.isSignon()){
//			
//		}
		Action a =ActionFactory.getInstance().getNewAction();
		ActionFactory.getInstance().setInstance(PROGRAM_ID,a);
		
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#isAlive()
	 */
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return alive;
	}
	
	private String sentToMotorParty(String notino,String insureName,String license,String contractno,String username,String resurveAmt,String qinyear,String qname,String scode) throws UnsupportedEncodingException{
		String header = notino.trim()+"::"+insureName.trim()+"::"+license.trim()+"::"+contractno.trim()+"::"+username.trim()
		+"::"+qinyear+"::"+qname+"::"+scode+"!!"+ActionQueueConstant.PARAM_LOSS_CODE+"::"+resurveAmt.trim()+"::"+username;
		
//		header = encode(header,"ISO-8859-1");
		header = URLEncoder.encode(header,"UTF-8"); 
//		header = URLDecoder.decode(s,"UTF-8");

		
		String result="";
		URL url_;
		InputStream inputStream =null;
		try {
			System.out.println("=================Sent to Authorize==================");
			System.out.println(header);
			url_ = new URL(ActionQueueConstant.URL_AUTHORIZE);
			
			URLConnection connection = url_.openConnection();
		 	if (connection instanceof HttpURLConnection) {
//		 		System.out.println("==============What the hell is going on due!=============");
	            HttpURLConnection httpConnection = (HttpURLConnection)connection;
	            httpConnection.setRequestMethod("PUT");
	            httpConnection.setDoOutput(true);
	            httpConnection.connect();
	
	             /*
	              * Initialize output stream
	              */
	            DataOutputStream outttt = new DataOutputStream( httpConnection.getOutputStream() );
	            OutputStreamWriter w = new OutputStreamWriter(outttt);
	            w.write(header);
//	            outttt.
	            /*
	             * write dataList  
	             */
//	            outttt.writeBytes(header);       
//	            outttt.writeUTF(header);
//	            outttt.flush();
	            w.flush();
	            //System.out.print("httpConnection=="+httpConnection);
	            //prin out data
	            inputStream = httpConnection.getInputStream();
	            //System.out.print("inputStream=="+inputStream);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	            
	            String line = null;
                while ((line = reader.readLine()) != null) {
                   //System.out.println(line);
                	result+=line;
                }
	        }
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return result;
		
	}
	
	
	private String [] sentToActionQueue(String notificationno,String username){
		String result ="";
		URL url_;
		InputStream inputStream =null;
		try {
//			System.out.println("=================Sent to Action Queue==================");
//			System.out.println(ActionQueueConstant.URL_ACTION_QUEUE
//					+"?"+ActionQueueConstant.PARAM_NAME_USER+"="+username.replaceAll(" ","%20")
//					+"&"+ActionQueueConstant.PARAM_NAME_ACTION_ID+"="+ActionQueueConstant.PARAM_ACTION_ID
//					+"&"+ActionQueueConstant.PARAM_NAME_REFER_ID+"="+notificationno
//					+"&"+ActionQueueConstant.PARAM_NAME_SET_ID+"="+ActionQueueConstant.PARAM_SET_ID
//					+"&"+ActionQueueConstant.PARAM_NAME_GROUP_OF_WORK+"="+ActionQueueConstant.PARAM_GROUP_OF_WORK);
			url_ = new URL(ActionQueueConstant.URL_ACTION_QUEUE
					+"?"+ActionQueueConstant.PARAM_NAME_USER+"="+username.replaceAll(" ","%20")
					+"&"+ActionQueueConstant.PARAM_NAME_ACTION_ID+"="+ActionQueueConstant.PARAM_ACTION_ID
					+"&"+ActionQueueConstant.PARAM_NAME_REFER_ID+"="+notificationno
					+"&"+ActionQueueConstant.PARAM_NAME_SET_ID+"="+ActionQueueConstant.PARAM_SET_ID
					+"&"+ActionQueueConstant.PARAM_NAME_GROUP_OF_WORK+"="+ActionQueueConstant.PARAM_GROUP_OF_WORK
					+"&condition=ClaimReg"
					);
			System.out.println("====== URL ======" + url_);
			URLConnection connection = url_.openConnection();
		 	if (connection instanceof HttpURLConnection) {
		 		
	            HttpURLConnection httpConnection = (HttpURLConnection)connection;
	            httpConnection.setRequestMethod("GET");
	            httpConnection.setDoOutput(true);
	            httpConnection.connect();
	
	             /*
	              * Initialize output stream
	              */
	            DataOutputStream outttt = new DataOutputStream( httpConnection.getOutputStream() );
	
	            /*
	             * write dataList  
	             */
	            outttt.write(123);                         
	            outttt.flush();
	            //System.out.print("httpConnection=="+httpConnection);
	            //prin out data
	            inputStream = httpConnection.getInputStream();
	            //System.out.print("inputStream=="+inputStream);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	            
	            String line = null;
                while ((line = reader.readLine()) != null) {
                   //System.out.println(line);
                	result+=line;
                }
                
	        }
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return result.split(",");
		
	}
	
	private void scrap(Notification n,Cache cache) throws Exception {
		
		cache.put("MSITB POLICY NO.", n.getRegisPolicyNo().trim());
		cache
				.put("RISK NO.", Integer.toString(n.getRegisRiskNo()));
		cache.put(
				"accidentDate",
				DateUtil.dateToString(DateUtil.toDate((String) Integer
						.toString(n.getAccidentDate()), "yyyyMMdd", Locale.US),
						Locale.US, "ddMMyy")
						+ "  ");
		cache.put(
				"notificationDate",
				DateUtil.dateToString(DateUtil.toDate((String) Integer
						.toString(n.getNotificationDate()), "yyyyMMdd",
						Locale.US), Locale.US, "ddMMyy")
						+ "  ");

		cache.put("ACCIDENT PLACE1", n.getAccidentPlace());
		cache.put("ACCIDENT PLACE2",
				convertTime(n.getAccidentTime()));

		cache.put("natureCode1",
				n.getRegisNatureOfLoss().trim());
		cache.put("natureCode2", th.co.msat.motor.scrap.database.Cache.getInstance().getNatureOfLostDesc(n.getRegisNatureOfLoss().trim()));
//				n.getDescriptionLine1().trim().length()<38?n.getDescriptionLine1().trim():n.getDescriptionLine1().trim().substring(0,38));
		cache.put("DRIVER_NAME", n.getDriverName().trim());
		cache.put("DEFAULT_BY",n.getRegisNatureOfLoss().indexOf("W")!=-1? "Insured":
			n.getRegisNatureOfLoss().indexOf("R")!=-1?"Third Party":"");
		cache.put("NOTIFICATION NO.", n.getNotificationNo());
		cache.put("NOTIFICATION_NO",n.getNotificationNo());
		cache.put("PROVINCE",n.getRegisProvinceCode());
		cache.put("ACCIDENT_PLACE",n.getAccidentPlace());
		cache.put("DESC LINE 2", n.getDescriptionLine2());
		cache.put("province", n.getRegisProvinceCode());
		
		Date currentDate = new Date();
		String currentDateString = new SimpleDateFormat("yyyyMMdd",Locale.US).format(currentDate);
		int currentDateInt = Integer.parseInt(currentDateString);
		
		cache.put("N999",new ReserveDAOImpl().getReserveAmount(currentDateInt,"N999"));
		cache.put("N201",new ReserveDAOImpl().getReserveAmount(currentDateInt,"N201"));
		cache.put("V100",new ReserveDAOImpl().getReserveAmount(currentDateInt,"V100"));
		
		
		cache.put("tel",n.getTelephone().trim());
		cache.put("descline1",n.getDescriptionLine1().trim());
		cache.put("descline2",n.getDescriptionLine2().trim());
		cache.put("descline3",n.getDescriptionLine3().trim());
		cache.put("descline4",n.getDescriptionLine4().trim());
		cache.put("DESC_LINE_1",n.getDescriptionLine1().trim());
		cache.put("DESC_LINE_2",n.getDescriptionLine2().trim());
		cache.put("DESC_LINE_3",n.getDescriptionLine3().trim());
		cache.put("DESC_LINE_4",n.getDescriptionLine4().trim());
		cache.put("survyor_name",n.getSurveyorName().trim());
		cache.put("survyor_office",n.getSurveyorOffice().trim());
		

		cache.setTopProcessState(Cache.STATE_START);
	
		
		
	}
	
	public boolean isExistsAction(String referId,String code400){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn= null;
		try{
			conn = getConnection();
			if (conn != null){
	    		StringBuffer SQL = new StringBuffer("");
	    		//motor
	    		if(code400!=null&&!"".equals(code400)){
	    			//Start Fix duplicate exchange Prapan Jandarak 2009/11/03[yyyy/mm/dd]
	    			/*SQL.append(" select count(q.REFER_ID) as total");   
		    		SQL.append(" from TBL_ACTION_Q q inner join tbl_file_set s on q.set_id=s.set_id ");
		    		SQL.append(" left join index_detail d on d.INDEX_DETAIL_ID = s.INDEX_DETAIL_ID ");
		    		SQL.append(" where q.REFER_ID='"+referId.trim()+"' and q.STATUS in ('I','O') and q.action_id = "+ActionQueueConstant.PARAM_ACTION_ID+" and d.code400='"+code400+"' and INDEXTEXT <> 'LS' ");*/
	    			SQL.append(" select count(q.REFER_ID) as total");   
		    		SQL.append(" from TBL_ACTION_Q q inner join tbl_file_set s on q.set_id=s.set_id ");
		    		SQL.append(" where q.REFER_ID='"+referId.trim()+"' and q.STATUS in ('I','O') and q.action_id = "+ActionQueueConstant.PARAM_ACTION_ID+" and s.INDEX_DETAIL_ID="+code400);
		    		//End Fix duplicate exchange Prapan Jandarak 2009/11/03[yyyy/mm/dd]
	    		}
		        pstmt = conn.prepareStatement(SQL.toString());
		        rs = pstmt.executeQuery();
		        if (rs != null && rs.next()) {
		        	return (rs.getInt("total")>0);
		        }
	
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
			if(null!=pstmt)
				try {
					pstmt.close();
				} catch (SQLException e2) {
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
		return false;
	}
	public final static String MSATLIB_DATA_SOURCE="MSATLIB";
	public Connection getConnection() throws Exception{
		InitialContext ctx=new InitialContext();
		return ((DataSource)ctx.lookup(MSATLIB_DATA_SOURCE)).getConnection();
	}
	
	String convertTime(int time) throws Exception{
		String s = Integer.toString(time);
		while(s.length()<6)
			s="0"+s;
		SimpleDateFormat ss = new SimpleDateFormat("HHmmss");
		Date sss = ss.parse(s);
		return DateUtil.dateToString(sss,Locale.US,"HH:mm:ss");
	}
	
	protected FacingSheetSB createFacingSheetSB() throws CreateException, RemoteException, NamingException {
		Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
        //env.put(Context.PROVIDER_URL, SystemEnvironment.getInstance().getBootStrapAddressServerWIN());
        env.put(Context.PROVIDER_URL, SystemEnvironment.getInstance().getBootStrapAddress());
        try {
			InitialContext ctx = new InitialContext(env);
//			System.out.println(" ============================= hrere is an lookup for remote test ===================================");
			Object s = ctx.lookup("ejb/th/co/msat/motor/database/FacingSheetSBHome");
			System.out.println(s);
			FacingSheetSBHome frHome = (FacingSheetSBHome) PortableRemoteObject.narrow(s,FacingSheetSBHome.class);
			return (FacingSheetSB) frHome.create();
			
			
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
