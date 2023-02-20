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

package th.co.msat.reqsprinting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;

import th.co.msat.motor.as400interface.action.Action;
import th.co.msat.motor.as400interface.action.ActionFactory;
import th.co.msat.motor.as400interface.util.Cache;
import th.co.msat.motor.as400interface.util.ConfigurationLoader;
import th.co.msat.motor.queuemanager.executor.ExecutorImpl;
import th.co.msat.motor.services.MSATServiceLocator;
import th.co.msat.paymentsystem.PaymentSystemExecutor;

public class RequisitionPrintingExecutor extends ExecutorImpl
{
	private boolean alive = false;
	public static final String PROGRAM_ID = "RequisitionPrintingExecutor";
	
	//private RequisitionPrintingMessage message = null;
	
	Action action = null;
	Cache cache = null;
	Connection conn = null;
	
	public void execute() throws Exception
	{
		alive = true;
		
		String executeMessage = "";
			
		//message = (RequisitionPrintingMessage)getMessage();
		
		RequisitionPrintingMessage message = new RequisitionPrintingMessage();
		message.setBr("BK");
		message.setCat("C");
		message.setClasses("V");
		message.setYear("09");
		message.setClmNo("000001");
		message.setPayeeCode("N212");
		message.setUserId("C55");
		message.setTypeOfPayment(1);
		
		/*System.out.println(message.getReferenceId());
		System.out.println(message.getClaimNo());
		System.out.println(message.getPaymentDateFrm());
		System.out.println(message.getPaymentDateTo());
		System.out.println(message.getPayeeCode());
		System.out.println(message.getEntryDateFrm());
		System.out.println(message.getEntryDateTo());
		System.out.println(message.getUserId());
		System.out.println(message.getAmount());
		System.out.println(message.getTypeOfPayment());*/
		
		ConfigurationLoader loader = new ConfigurationLoader();
		loader.load("PageValidator.xml", RequisitionPrintingExecutor.class.getResourceAsStream("/xmlconfig/reqsprinting/PageValidator.xml"));
		loader.load("RQP_SignOn.xml", RequisitionPrintingExecutor.class.getResourceAsStream("/xmlconfig/reqsprinting/RQP_SignOn.xml"));
		loader.load("RQP_MainMenu.xml", RequisitionPrintingExecutor.class.getResourceAsStream("/xmlconfig/reqsprinting/RQP_MainMenu.xml"));
		loader.load("RQP_ClaimMenu.xml", RequisitionPrintingExecutor.class.getResourceAsStream("/xmlconfig/reqsprinting/RQP_ClaimMenu.xml"));
		loader.load("RQP_PaymentSlips.xml", RequisitionPrintingExecutor.class.getResourceAsStream("/xmlconfig/reqsprinting/RQP_PaymentSlips.xml"));
		loader.load("RQP_MCL970S.xml", RequisitionPrintingExecutor.class.getResourceAsStream("/xmlconfig/reqsprinting/RQP_MCL970S.xml"));
		loader.load("User Properties", RequisitionPrintingExecutor.class.getResourceAsStream("/xmlconfig/reqsprinting/user.properties"));
		
		Properties prop = new Properties();
		prop.load(RequisitionPrintingExecutor.class.getResourceAsStream("/xmlconfig/reqsprinting/user.properties"));
		
		cache = new Cache();
		
		cache.put("br", message.getBr());
		cache.put("cat", message.getCat());
		cache.put("classes", message.getClasses());
		cache.put("year", message.getYear());
		cache.put("clmNo", message.getClmNo());
		
		if (message.getPaymentDateFrm() != null && !message.getPaymentDateFrm().equals(""))
			cache.put("paymentDateFrm", message.getPaymentDateFrm());
		else
			cache.put("paymentDateFrm", new SimpleDateFormat("ddMMyy",Locale.US).format(Calendar.getInstance().getTime()));
		
		if (message.getPaymentDateTo() != null && !message.getPaymentDateTo().equals(""))
			cache.put("paymentDateTo", message.getPaymentDateTo());
		else
			cache.put("paymentDateTo", new SimpleDateFormat("ddMMyy",Locale.US).format(Calendar.getInstance().getTime()));
		
		cache.put("payeeCode", message.getPayeeCode());
		
		if (message.getEntryDateFrm() != null && !message.getEntryDateFrm().equals(""))
			cache.put("entryDateFrm", message.getEntryDateFrm());
		else
			cache.put("entryDateFrm", new SimpleDateFormat("ddMMyy",Locale.US).format(Calendar.getInstance().getTime()));
		
		if (message.getEntryDateTo() != null && !message.getEntryDateTo().equals(""))
			cache.put("entryDateTo", message.getEntryDateTo());
		else
			cache.put("entryDateTo", new SimpleDateFormat("ddMMyy",Locale.US).format(Calendar.getInstance().getTime()));
		
		cache.put("userId", message.getUserId());
		
		if (message.getAmount() == null)
			cache.put("amnt", "");
		else
			cache.put("amnt", "" + message.getAmount().doubleValue());
		
		cache.put("typeOfPayment", "" + message.getTypeOfPayment());
		
		cache.put("Claim No.", message.getClaimNo());
		
		cache.put("NOTIFICATION NO.", message.getClmNo());
		
		cache.put("group.for.not.replace", prop.get("group.for.not.replace"));
		cache.put("working_page", "RQP_MCL970S");
		
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
			
			System.out.println("RequisitionPrintingExecutor: SIGNON Starting");
			if(!action.isSignon())
				action.signon();
			
			System.out.println("RequisitionPrintingExecutor: Execute");
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
			System.out.println("RequisitionPrintingExecutor: Exeception");
			action.signoff();
			action.destroy();
			executeMessage = ex.getMessage();
			cache.setTopProcessState(Cache.STATE_ERROR);
			
			ex.printStackTrace();
			throw ex;
		} finally
		{
			System.out.println("RequisitionPrintingExecutor: Finally");
			if(null!=conn)
				conn.close();
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
