  package th.co.msat.mpd.renewpolicy;

import java.sql.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import th.co.msat.motor.as400interface.action.Action;
import th.co.msat.motor.as400interface.action.ActionFactory;

import th.co.msat.motor.as400interface.util.Cache;
import th.co.msat.motor.as400interface.util.ConfigurationLoader;

import th.co.msat.motor.queuemanager.executor.ExecutorImpl;


import th.co.msat.motor.services.MSATServiceLocator;
import th.co.msitb.underwritting.motor.scrap.executor.UnderwrittingExecutor;
import th.co.msitb.underwritting.motor.scrap.executor.UnderwrittingScrapMessage;

public class RenewPolicyExecutor extends ExecutorImpl{

	boolean alive = false;
	public static final String PROGRAM_ID = "Renew Policy Queue                                ";
	
	private RenewPolicyMessage message;
	

	Connection conn = null;
	Action ac = null;
	Cache cache = null;

	public void execute() throws Exception {
		message = (RenewPolicyMessage)getMessage();	
		if(message.getScrapMethod() == null || message.getScrapMethod().equals("RENEW FLEET")){
			executeFleet();
		}else{			
			executeSingle();			
		}		
	}
	
	public void executeSingle() throws Exception{
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("[RenewPolicy Scrap Log] =             RenewPolicy Scrap With RENEW SINGLE METHOD");
		System.out.println("-----------------------------------------------------------------------------------");
		
		alive = true;
		
		String executeMessage = "";
		
		message = (RenewPolicyMessage)getMessage();		
		
		cache = new Cache();


		//------------------------------------------------------------------
		//- IUW0010S
		//------------------------------------------------------------------
		cache.put("space", " ");
		
		cache.put("new_renew", message.getNewRenew());
		
		cache.put("method", message.getScrapMethod());
		cache.put("package_code", message.getPackageCode());
		cache.put("agent_code", message.getAgentCode());
		cache.put("customer_code", message.getCustomerCode());
		cache.put("customer_sub_code", message.getCustomerSubCode());
		cache.put("customer_name", message.getCustomerName());
		cache.put("customer_add1", message.getCustomerAddress1());
		cache.put("customer_add2", message.getCustomerAddress2());
		cache.put("customer_add3", message.getCustomerAddress3());
		cache.put("customer_add4", message.getCustomerAddress4());
		
		cache.put("incept_date", message.getInceptDate());
		cache.put("expiry_date", message.getExpiryDate());
		cache.put("cover_note_date", message.getCoverNoteDate());
		cache.put("old_policy_no", message.getOldPolicyNo());
		
		//------------------------------------------------------------------
		cache.put("occupation", message.getPolOccupation());
		cache.put("id_number", message.getIdNumber());
		
		cache.put("policy_type", message.getPolicyTepe());
		cache.put("std_new", message.getStdNew());
		cache.put("title", message.getTitle());
		cache.put("additional_name", message.getAdditionalName());
		//------------------------------------------------------------------
		//- IUW1105S
		//------------------------------------------------------------------				
		cache.put("cover_type", message.getCoverType());
		cache.put("class_use", message.getClassUser());
		cache.put("licen_no", message.getLicNo());
		cache.put("chassis_no", message.getChassisNo());
		cache.put("veh_code", message.getVehCode());	
		
		cache.put("driver_no", message.getDriverNo());	
		
		cache.put("body", message.getBody());
		cache.put("engine", message.getEngineNo());
		cache.put("year", message.getMfrYear());
		cache.put("seat", message.getSeat());
		cache.put("displ", message.getDispl()); 
		cache.put("gvw", message.getGvw());		
		cache.put("medical", message.getMedicalExp());
		cache.put("passenger", message.getPassengerNo());
		cache.put("benef", message.getBeneficiary());
		
		cache.put("dob1", message.getDob1());
		cache.put("occupation1", message.getOccupation1());
		cache.put("lic_no1", message.getLicNo1());
		cache.put("id_no1", message.getIdNo1());
		
		cache.put("dob2", message.getDob2());
		cache.put("occupation2", message.getOccupation2());
		cache.put("lic_no2", message.getLicNo2());
		cache.put("id_no2", message.getIdNo2());
		
		cache.put("sex1", message.getSex1());
		cache.put("name1", message.getName1());
		cache.put("sex2", message.getSex2());
		cache.put("name2", message.getName2());
		
		cache.put("basis", message.getBasis());		
		cache.put("brand", message.getBrand());
		cache.put("model", message.getModel());
		cache.put("sub_model", message.getSubModel());
		cache.put("color", message.getColor());
		cache.put("regdate", message.getRegDate());
		cache.put("access", message.getAccessories());
		cache.put("tarrif", message.getTerritory());
		//------------------------------------------------------------------
		//- IUW1205S
		//------------------------------------------------------------------		
		cache.put("premium1", message.getPremium1());
		cache.put("premium2", message.getPremium2());
		cache.put("premium3", message.getPremium3());
		cache.put("premium4", message.getPremium4());
		cache.put("premium5", message.getPremium5());
		cache.put("premium6", message.getPremium6());
		cache.put("premium7", message.getPremium7());
		cache.put("premium8", message.getPremium8());
		cache.put("premium9", message.getPremium9());
		cache.put("premium10", message.getPremium10());
		cache.put("premium11", message.getPremium11());
	
		cache.put("ncd_bg", message.getNcdGB());
		cache.put("fleet", message.getFleet().trim());
		cache.put("ncd", message.getNcd().trim());
		cache.put("other_discount", message.getOtherDiscount().trim());
		//------------------------------------------------------------------
		//- IUW0070S
		//------------------------------------------------------------------	
		cache.put("freetext1", message.getFreeText1());
		cache.put("freetext2", message.getFreeText2());
		cache.put("freetext3", message.getFreeText3());
		cache.put("freetext4", message.getFreeText4());
		cache.put("freetext5", message.getFreeText5());
		cache.put("freetext6", message.getFreeText6());
		cache.put("freetext20", message.getFreeText20());	
		
		cache.put("freetext34", message.getFreeText34());
		
		ConfigurationLoader loader = new ConfigurationLoader();
		try{
  
		loader.load("PageValidator.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/PageValidator.xml"));                  
		loader.load("RN_IMNUUW03S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IMNUUW03S.xml"));
		loader.load("RN_IUW0010S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IUW0010S.xml"));
		loader.load("RN_SignOn.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_SignOn.xml"));
		
		loader.load("RN_IUW0070S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IUW0070S.xml"));
		loader.load("RN_IUW0080S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IUW0080S.xml"));
		loader.load("RN_IUW0090S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IUW0090S.xml"));
		loader.load("RN_IUW1010S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IUW1010S.xml"));
		loader.load("RN_IUW1105S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IUW1105S.xml"));
		loader.load("User Properties",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/user.properties"));
		
		Properties p = new Properties();
		p.load(RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/user.properties"));
		cache.put("group.for.not.replace",p.get("group.for.not.replace"));
		cache.put("working_page","RN_IUW0010S");
		
		//------------------------------------
		// cache for log
		cache.put("MSITB POLICY NO.", "");
		cache.put("NOTIFICATION NO.", "");
		cache.put("Claim No.", "");
		//------------------------------------
			ac = ActionFactory.getInstance().getSingletonAction(PROGRAM_ID);
			ac.setCache(cache);
			ac.setConnection(conn);
			ac.setConfigurationLoader(loader);
			ac.loadConfiguration();
			
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[RenewPolicy Scrap] = SING ON!");
			System.out.println("---------------------------------------------------------------------------------");
			
			if(!ac.isSignon())
				ac.signon();
			
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[RenewPolicy Scrap] = Execute!");
			System.out.println("---------------------------------------------------------------------------------");
			
			ac.execute();
			
			if(null!=cache.getTopProcessState()&&cache.getTopProcessState().equals(Cache.STATE_ERROR)){
				cache.put("ERROR_MESSAGE","");
				if(null!=cache.get(Cache.ERROR_MESSAGE)&& !"".equals(cache.get(Cache.ERROR_MESSAGE))){
					executeMessage = (String)cache.get(Cache.ERROR_MESSAGE);
					throw new Exception((String)cache.get(Cache.ERROR_MESSAGE));
				}else{
					executeMessage = "Process state error";
					throw new Exception(executeMessage);
				}
			}
			
			ac.signoff();
		
		}catch (Exception e) {
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[RenewPolicy Scrap] = Exec Error!");
			System.out.println("---------------------------------------------------------------------------------");
			
			cache.setTopProcessState(Cache.STATE_ERROR);
			e.printStackTrace();
			ac.signoff();
			ac.destroy();
			executeMessage = e.getMessage();
			throw e;
		}finally{
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[RenewPolicy Scrap] = Finally Close Connection!");
			System.out.println("---------------------------------------------------------------------------------");
		
			if(null!=conn)
				conn.close();
		}
	}

	//-----------------------------------------------------------------------------------------------------------------
	// Fleet
	//-----------------------------------------------------------------------------------------------------------------
	public void executeFleet() throws Exception{
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("[RenewPolicy Scrap Log] =             RenewPolicy Scrap With RENEW SINGLE METHOD");
		System.out.println("-----------------------------------------------------------------------------------");
		
		alive = true;
		
		String executeMessage = "";
		
		Map msgMap = new HashMap();
		
		message = (RenewPolicyMessage)getMessage();	
		
		msgMap = message.getDataMap();
		
		
		Map me = (HashMap) msgMap.get("1");
		
		
		
		cache = new Cache();
		
		cache.put("data_map", msgMap);
		
		//------------------------------------------------------------------
		//- IUW0010S
		//------------------------------------------------------------------	
		
		cache.put("space", " ");
		
//		map.put("brance", ren.getBr());
//		map.put("cat", ren.getCat());
//		map.put("package_code", ren.getPackageCode());
//		map.put("policy_year", ren.getPolicyYear());
//		map.put("policy_number", ren.getPolicyNo());
		
		
		//number for freetext line1
		cache.put("number", Integer.toString(msgMap.size()));
		
		cache.put("new_renew", me.get("new_renew"));
		
		cache.put("method", "RENEW FLEET");
		
		cache.put("brance", me.get("brance"));
		cache.put("cat", me.get("cat"));
		cache.put("package_code", me.get("package_code"));
		cache.put("old_package_code", me.get("old_package_code"));
		cache.put("policy_year", me.get("policy_year"));
		cache.put("policy_number", me.get("policy_number"));
		
		cache.put("sl_no", me.get("sl_no"));
		
		cache.put("agent_code", me.get("agent_code"));
		cache.put("customer_code",  me.get("customer_code"));
		cache.put("customer_sub_code",  me.get("customer_sub_code"));
		cache.put("customer_name",  me.get("customer_name"));
		cache.put("customer_add1",  me.get("customer_add1"));
		cache.put("customer_add2",  me.get("customer_add2"));
		cache.put("customer_add3",  me.get("customer_add3"));
		cache.put("customer_add4",  me.get("customer_add4"));
		
		cache.put("incept_date",  me.get("incept_date"));
		cache.put("expiry_date",  me.get("expiry_date"));
		cache.put("cover_note_date",  me.get("cover_note_date"));
		cache.put("old_policy_no",  me.get("old_policy_no"));
		
		//------------------------------------------------------------------
		cache.put("occupation",  me.get("occupation"));
		cache.put("id_number",  me.get("id_number"));
		
		cache.put("policy_type",  me.get("policy_type"));
		cache.put("std_new",  me.get("std_new"));
		cache.put("title",  me.get("title"));
		cache.put("additional_name",  me.get("additional_name"));
		//------------------------------------------------------------------
		//- IUW1105S
		//------------------------------------------------------------------				
		cache.put("cover_type",  me.get("cover_type"));
		cache.put("class_use",  me.get("class_use"));
		cache.put("licen_no",  me.get("licen_no"));
		cache.put("chassis_no",  me.get("chassis_no"));
		cache.put("veh_code",  me.get("veh_code"));
		
		cache.put("driver_no",  me.get("driver_no"));
		
		cache.put("body",  me.get("body"));
		cache.put("engine",  me.get("engine"));
		cache.put("year",  me.get("year"));
		cache.put("seat",  me.get("seat"));
		cache.put("displ",  me.get("displ"));
		cache.put("gvw",  me.get("gvw"));
		cache.put("medical",  me.get("medical"));
		cache.put("passenger",  me.get("passenger"));
		cache.put("benef", me.get("benef"));
		
		cache.put("dob1", me.get("dob1"));
		cache.put("occupation1", me.get("occupation1"));
		cache.put("lic_no1", me.get("lic_no1"));
		cache.put("id_no1", me.get("id_no1"));
		
		cache.put("dob2", me.get("dob2"));
		cache.put("occupation2", me.get("occupation2"));
		cache.put("lic_no2", me.get("lic_no2"));
		cache.put("id_no2", me.get("id_no2"));
		
		cache.put("sex1", me.get("sex1"));
		cache.put("name1", me.get("name1"));
		cache.put("sex2", me.get("sex2"));
		cache.put("name2", me.get("name2"));
		
		cache.put("basis", me.get("basis"));
		cache.put("brand", me.get("brand"));
		cache.put("model", me.get("model"));
		cache.put("sub_model", me.get("sub_model"));
		cache.put("color", me.get("color"));
		cache.put("regdate", me.get("regdate"));
		cache.put("access", me.get("access"));
		cache.put("tarrif", me.get("tarrif"));
		//------------------------------------------------------------------
		//- IUW1205S
		//------------------------------------------------------------------		
		cache.put("premium1", me.get("premium1"));
		cache.put("premium2", me.get("premium2"));
		cache.put("premium3", me.get("premium3"));
		cache.put("premium4", me.get("premium4"));
		cache.put("premium5", me.get("premium5"));
		cache.put("premium6", me.get("premium6"));
		cache.put("premium7", me.get("premium7"));
		cache.put("premium8", me.get("premium8"));
		cache.put("premium9", me.get("premium9"));
		cache.put("premium10", me.get("premium10"));
		cache.put("premium11", me.get("premium11"));
	
		cache.put("ncd_bg", me.get("ncd_bg"));
		cache.put("fleet", me.get("fleet"));
		cache.put("ncd", me.get("ncd"));
		cache.put("other_discount", me.get("other_discount"));
		//------------------------------------------------------------------
		//- IUW0070S
		//------------------------------------------------------------------	
		cache.put("freetext1", me.get("freetext1"));
		cache.put("freetext2", me.get("freetext2"));
		cache.put("freetext3", me.get("freetext3"));
		cache.put("freetext4", me.get("freetext4"));
		cache.put("freetext5", me.get("freetext5"));
		cache.put("freetext6", me.get("freetext6"));
		cache.put("freetext7", me.get("freetext7"));
		cache.put("freetext8", me.get("freetext8"));
		cache.put("freetext9", me.get("freetext9"));
		cache.put("freetext10", me.get("freetext10"));
		cache.put("freetext11", me.get("freetext11"));
		cache.put("freetext12", me.get("freetext12"));
		cache.put("freetext13", me.get("freetext13"));
		cache.put("freetext14", me.get("freetext14"));
		cache.put("freetext15", me.get("freetext15"));
		cache.put("freetext16", me.get("freetext16"));
		cache.put("freetext17", me.get("freetext17"));
		
		cache.put("freetext20", me.get("freetext20"));
		cache.put("freetext34", me.get("freetext34"));
		
		
		
		ConfigurationLoader loader = new ConfigurationLoader();
		try{
  
		loader.load("PageValidator.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/PageValidator.xml"));                  
		loader.load("RN_IMNUUW03S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IMNUUW03S.xml"));
		loader.load("RN_IUW0010S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IUW0010S.xml"));
		loader.load("RN_SignOn.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_SignOn.xml"));
		
		loader.load("RN_IUW0070S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IUW0070S.xml"));
		loader.load("RN_IUW0080S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IUW0080S.xml"));
		loader.load("RN_IUW0090S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IUW0090S.xml"));
		loader.load("RN_IUW1010S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IUW1010S.xml"));
		loader.load("RN_IUW1105S.xml",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/RN_IUW1105S.xml"));
		loader.load("User Properties",RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/user.properties"));
		
		Properties p = new Properties();
		p.load(RenewPolicyExecutor.class.getResourceAsStream("/xmlconfig/renewpolicy/newsingle/user.properties"));
		cache.put("group.for.not.replace",p.get("group.for.not.replace"));
		cache.put("working_page","RN_IUW0010S");
		
		//------------------------------------
		// cache for log
		cache.put("MSITB POLICY NO.", "");
		cache.put("NOTIFICATION NO.", "");
		cache.put("Claim No.", "");
		//------------------------------------
			ac = ActionFactory.getInstance().getSingletonAction(PROGRAM_ID);
			ac.setCache(cache);
			ac.setConnection(conn);
			ac.setConfigurationLoader(loader);
			ac.loadConfiguration();
			
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[RenewPolicy Scrap] = SING ON!");
			System.out.println("---------------------------------------------------------------------------------");
			
			if(!ac.isSignon())
				ac.signon();
			
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[RenewPolicy Scrap] = Execute!");
			System.out.println("---------------------------------------------------------------------------------");
			
			ac.execute();
			
			if(null!=cache.getTopProcessState()&&cache.getTopProcessState().equals(Cache.STATE_ERROR)){
				cache.put("ERROR_MESSAGE","");
				if(null!=cache.get(Cache.ERROR_MESSAGE)&& !"".equals(cache.get(Cache.ERROR_MESSAGE))){
					executeMessage = (String)cache.get(Cache.ERROR_MESSAGE);
					throw new Exception((String)cache.get(Cache.ERROR_MESSAGE));
				}else{
					executeMessage = "Process state error";
					throw new Exception(executeMessage);
				}
			}
			
			ac.signoff();
		
		}catch (Exception e) {
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[RenewPolicy Scrap] = Exec Error!");
			System.out.println("---------------------------------------------------------------------------------");
			
		
			
			cache.setTopProcessState(Cache.STATE_ERROR);
			e.printStackTrace();
			ac.signoff();
			ac.destroy();
			executeMessage = e.getMessage();
			throw e;
		}finally{
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[RenewPolicy Scrap] = Finally Close Connection!");
			System.out.println("---------------------------------------------------------------------------------");
		
			if(null!=conn)
				conn.close();
		}
	}
	
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return alive;
	}

	public void onTimeout() {
		// TODO Auto-generated method stub
		
	}

}
