/***************************************************/
/* -------------- Create Description ------------- */
/***************************************************/
/* --------------------------------------------------
 * @author Veerasak Boonchern Aey
 * @since 2009/12/08[yyyy/mm/dd]
 * @description Executor for Payment System
 ---------------------------------------------------*/
/****************************************************/
/* ---------------- Modify History ---------------- */
/* ---------------------------------------------------
 * @modifier
 * @since
 * @description
 * -------------------------------------------------*/

package th.co.msat.paymentsystem;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import th.co.msat.motor.as400interface.action.Action;
import th.co.msat.motor.as400interface.action.ActionFactory;
import th.co.msat.motor.as400interface.util.Cache;
import th.co.msat.motor.as400interface.util.ConfigurationLoader;
import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.executor.ExecutorImpl;
import th.co.msat.motor.services.MSATServiceLocator;
import th.co.msat.motor.util.Util;

public class PaymentSystemExecutor extends ExecutorImpl
{
	private PaymentSystemMessage message = null;
	
	public static final String PROGRAM_ID = "PaymentSystemExecutor";
	
	Action action = null;
	Cache cache = null;
	Connection conn = null;
	
	private boolean alive = false;
	
	public void execute() throws Exception
	{
		alive = true;
		
		String executeMessage = "";
		
		message = (PaymentSystemMessage)getMessage();
		
		/*PaymentSystemMessage message = new PaymentSystemMessage();
		message.setFunctionId(0);
		message.setBr("BK");
		message.setCat("C");
		message.setClasses("V");
		message.setYear("09");
		message.setClmNo("000001");
		message.setTranType("R");
		//message.setCurrency("THB");
		//message.setCurrRate("1.00000");
		Map map1 = new Hashtable();
		map1.put("RESERVE_CODE", "N212");
		map1.put("PAID_CODE", "N212");
		map1.put("PAID_AMOUNT", new BigDecimal(9000));
		Map map2 = new Hashtable();
		map2.put("RESERVE_CODE", "O212");
		map2.put("PAID_CODE", "O212");
		map2.put("PAID_AMOUNT", new BigDecimal(6000));
		List list = new ArrayList();
		list.add(map1);
		list.add(map2);
		message.setPayeeList(list);
		message.setRemark("Test");
		message.setPayeeCode("N212");
		message.setPayeeName("");
		message.setInvNo("Test 2");
		//message.setInvDate("161209");
		message.setChqPayment("C");
		message.setBankCode("1");
		message.setClmSettlementCode("L");*/
		
		/*System.out.println(message.getReferenceId());
		System.out.println(message.getFunctionId());
		System.out.println(message.getClaimNo());
		System.out.println(message.getTranType());
		System.out.println(message.getCurrency());
		System.out.println(message.getCurrRate());
		System.out.println(message.getPayeeList());
		System.out.println(message.getRemark());
		System.out.println(message.getPayeeCode());
		System.out.println(message.getPayeeName());
		System.out.println(message.getInvNo());
		System.out.println(message.getInvDate());
		System.out.println(message.getChqPayment());
		System.out.println(message.getBankCode());
		System.out.println(message.getClmSettlementCode());*/
		
		ConfigurationLoader loader = new ConfigurationLoader();
		loader.load("PageValidator.xml", PaymentSystemExecutor.class.getResourceAsStream("/xmlconfig/paymentsystem/PageValidator.xml"));
		loader.load("PMS_SignOn.xml", PaymentSystemExecutor.class.getResourceAsStream("/xmlconfig/paymentsystem/PMS_SignOn.xml"));
		loader.load("PMS_MainMenu.xml", PaymentSystemExecutor.class.getResourceAsStream("/xmlconfig/paymentsystem/PMS_MainMenu.xml"));
		loader.load("PMS_SubMenu.xml", PaymentSystemExecutor.class.getResourceAsStream("/xmlconfig/paymentsystem/PMS_SubMenu.xml"));
		loader.load("PMS_ICL0310S.xml", PaymentSystemExecutor.class.getResourceAsStream("/xmlconfig/paymentsystem/PMS_ICL0310S.xml"));
		loader.load("User Properties", PaymentSystemExecutor.class.getResourceAsStream("/xmlconfig/paymentsystem/user.properties"));
		
		Properties prop = new Properties();
		prop.load(PaymentSystemExecutor.class.getResourceAsStream("/xmlconfig/paymentsystem/user.properties"));
		
		cache = new Cache();
		
		cache.put("function", "" + message.getFunctionId());
		cache.put("br", message.getBr());
		cache.put("cat", message.getCat());
		cache.put("classes", message.getClasses());
		cache.put("year", message.getYear());
		cache.put("clmNo", message.getClmNo());
		cache.put("transType", message.getTranType());
		cache.put("currency", message.getCurrency());
		cache.put("currRate", message.getCurrRate());
		cache.put("payeeList", message.getPayeeList());
		cache.put("remark", message.getRemark());
		cache.put("payeeCode", message.getPayeeCode());
		cache.put("payeeName", message.getPayeeName());
		cache.put("invNo", message.getInvNo());
		
		cache.put("Claim No.", message.getClaimNo());
		
		if (message.getInvDate() != null && !message.getInvDate().equals(""))
			cache.put("invDate", message.getInvDate());
		else
			cache.put("invDate", new SimpleDateFormat("ddMMyy",Locale.US).format(Calendar.getInstance().getTime()));
		
		cache.put("chqPayment", message.getChqPayment());
		cache.put("bankCode", message.getBankCode());
		cache.put("clmSttmCode", message.getClmSettlementCode());
		
		cache.put("NOTIFICATION NO.", message.getClmNo());
		
		cache.put("group.for.not.replace", prop.get("group.for.not.replace"));
		cache.put("working_page", "PMS_ICL0310S");
		
		cache.setReady(true);
		
		try
		{
			conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);
			
			cache.put("NOTIFICATIONDATE", getNotificationDate(message.getClaimNo(), conn));
			
			action = ActionFactory.getInstance().getSingletonAction(PROGRAM_ID);
			action.setCache(cache);
			action.setConnection(conn);
			action.setConfigurationLoader(loader);
			action.loadConfiguration();
			
			System.out.println("PaymentSystemExecutor: SIGNON Starting");
			if(!action.isSignon())
				action.signon();
			
			System.out.println("PaymentSystemExecutor: Execute");
			action.execute();
			
			if ( null != cache.getTopProcessState()
					&& cache.getTopProcessState().equals(Cache.STATE_ERROR) )
			{
				if ( null != cache.get(Cache.ERROR_MESSAGE)
					&& !"".equals(cache.get(Cache.ERROR_MESSAGE)) )
				{
					executeMessage = (String)cache.get(Cache.ERROR_MESSAGE);
					throw new Exception((String)cache.get(Cache.ERROR_MESSAGE));
				} else
				{
					executeMessage = "Process state error";
					throw new Exception(executeMessage);
				}
			}
			
			action.signoff();
			
		} catch (Exception ex)
		{
			System.out.println("PaymentSystemExecutor: Exeception");
			action.signoff();
			action.destroy();
			executeMessage = ex.getMessage();
			cache.setTopProcessState(Cache.STATE_ERROR);
			
			ex.printStackTrace();
			throw ex;
		} finally
		{
			System.out.println("PaymentSystemExecutor: Finally");
			if(null!=conn)
				conn.close();
			
			String hostName = SystemEnvironment.getInstance().getDefaultHostName();
			if (!hostName.endsWith("/"))//for sure, the end of host name is '/'
			{
				hostName = hostName.trim() + "/";
			}
			hostName = hostName.substring(0, hostName.length() - 1);//delete "/" at the end of text
			
			StringBuffer param = new StringBuffer();
			param.append("?referPayee=" + message.getPayeeId());
			if (!executeMessage.equals(""))
				param.append("&status=E");
			else
				param.append("&status=Y");
			
			Util.connection2Servlet(hostName + DefaultQueueConstants.PRINT_REQUISITION_SERVLET_PATH + param.toString(), "", "PUT");
		}
		
		alive = false;
	}

	public void onTimeout()
	{
	}

	public boolean isAlive()
	{
		return alive;
	}
	
	public Integer getNotificationDate(String no,Connection conn)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select create_date  from coverpagetb where trim(claim_no) = ? ");
		System.out.println(sql.toString()+" "+no);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1,no.trim());
			rs = ps.executeQuery();
			if(rs.next())
			{
				Integer in = new Integer(rs.getInt(1));
				return in;
			}	
			
		} catch(Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if(null!=rs)
			{
				try
				{
					rs.close();
				} catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
			
			if(null!=ps)
			{
				try
				{
					ps.close();
				} catch (SQLException e2)
				{
					e2.printStackTrace();
				}
			}
		}
		
		return new Integer(0);
	}
}
