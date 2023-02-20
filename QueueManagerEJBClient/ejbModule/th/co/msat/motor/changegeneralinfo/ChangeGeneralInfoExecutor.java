/*
 * Created on 20 ¡.¤. 2552
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.changegeneralinfo;

import java.sql.Connection;

import th.co.msat.motor.as400interface.action.Action;
import th.co.msat.motor.as400interface.action.ActionFactory;
import th.co.msat.motor.as400interface.util.Cache;
import th.co.msat.motor.as400interface.util.ConfigurationLoader;
import th.co.msat.motor.database.services.MSATServiceLocator;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.msat.motor.queuemanager.executor.ExecutorImpl;


/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ChangeGeneralInfoExecutor extends ExecutorImpl{

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#execute()
	 */
	public void execute() throws Exception {
		ChangeGeneralInfoMessage message = (ChangeGeneralInfoMessage)getMessage();
		Connection conn =null;
		try {
			Cache cache = new Cache();
			
			cache.put("BRANCH",message.getBranch());
			cache.put("CLASS", message.getClasses());
			cache.put("YEAR", message.getYear()); 
			cache.put("CLAIMNO",message.getClaimNo());
			cache.put("LOSSDATE",message.getLossDate());
			cache.put("NATCODE",message.getNatCode());
			cache.put("AGE",message.getDriverAge());
			cache.put("BIRTHDATE",message.getDriverBirthDay());
			cache.put("IDNO",message.getIdNo());
			
			cache.put("MSITB POLICY NO.","");
			cache.put("NOTIFICATION NO.","");
			cache.put("Claim No.","");
			
			conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);
			
			ConfigurationLoader loader = new ConfigurationLoader();
			//loader.load("ClaimRegisterMaintenanceMenu.xml",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/ClaimRegisterMaintenanceMenu.xml"));  
			loader.load("CHGN_IMNUCL02S.xml",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/CHGN_IMNUCL02S.xml"));
			loader.load("CHGN_ICL0010S.xml",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/CHGN_ICL0010S.xml"));
			loader.load("CHGN_ICL0011S.xml",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/CHGN_ICL0011S.xml"));                       
			loader.load("CHGN_ICL0020S.xml",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/CHGN_ICL0020S.xml"));
			loader.load("CHGN_ICL1070S.xml",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/CHGN_ICL1070S.xml"));  
			loader.load("CHGN_ICL0060S.xml",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/CHGN_ICL0060S.xml")); 
			loader.load("CHGN_LogConfig.xml",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/CHGN_LogConfig.xml"));
			loader.load("CHGN_ManiMenu.xml",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/CHGN_ManiMenu.xml"));                       
			loader.load("CHGN_ManiMenu2.xml",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/CHGN_ManiMenu2.xml"));
			loader.load("PageValidator.xml",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/PageValidator.xml"));                  
			loader.load("CHGN_SignOn.xml",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/CHGN_SignOn.xml"));
			loader.load("User Properties",ChangeGeneralInfoExecutor.class.getResourceAsStream("/xmlconfig/changegeneral/user.properties"));
			cache.put("working_page", "CHGN_ICL0060S");
			Action ac = ActionFactory.getInstance().getSingletonAction("ChangeGeneralInfoExecutor");
			ac.setConnection(conn);
			ac.setConfigurationLoader(loader);
			ac.setCache(cache);

			ac.loadConfiguration();

			cache.setReady(true);
			if (!ac.isSignon())
				ac.signon();
			ac.execute();
			
			//JobQueueDAO jqdao = new JobQueueDAO();
			//if(null==jqdao.findNextJob(getQueueName()))
			ac.signoff();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null!=conn)
				conn.close();
		}
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#onTimeout()
	 */
	public void onTimeout() {
		
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#isAlive()
	 */
	public boolean isAlive() {
		return false;
	}

	
}
