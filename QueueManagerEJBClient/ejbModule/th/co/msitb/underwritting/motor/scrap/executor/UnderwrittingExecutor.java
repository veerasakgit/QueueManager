package th.co.msitb.underwritting.motor.scrap.executor;

import java.sql.Connection;
import java.util.Properties;


import th.co.msat.motor.as400interface.action.Action;
import th.co.msat.motor.as400interface.action.ActionFactory;

import th.co.msat.motor.as400interface.util.Cache;
import th.co.msat.motor.as400interface.util.ConfigurationLoader;
import th.co.msat.motor.queuemanager.executor.ExecutorImpl;



public class UnderwrittingExecutor extends ExecutorImpl {
	
	boolean alive = false;
	public static final String PROGRAM_ID = "Underwritting Scrap                               ";
	
	private UnderwrittingScrapMessage message;
	Connection conn = null;
	Action ac = null;
	Cache cache = null;
	
	

	public void execute() throws Exception {
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("[Underwritting Scrap Log] =             Underwritting Scrap");
		System.out.println("-----------------------------------------------------------------------------------");
		
		alive = true;
		
		String executeMessage = "";
		
		message = (UnderwrittingScrapMessage)getMessage();
		
		System.out.println("USERNAME : " + message.getUsername());
		System.out.println("PASSWORD : " + message.getPassword());
		
		cache = new Cache();
		
		/*
		cache.put("username",message.getUsername());
		cache.put("password",message.getPassword());

		cache.put("branch", message.getBranch());
		cache.put("package_code", message.getPackageCode());
		cache.put("policy_year", message.getPolicyYear());
		cache.put("policy_no",  message.getPolicyNo() );
		cache.put("agent_code", message.getAgentCode());
		cache.put("customer_code", message.getCustomerCode());
		cache.put("customer_sub_code", message.getCustomerSubCode());
		cache.put("customer_name", message.getCustomerNameEng());
		cache.put("customer_add1", message.getCustomerAddress1());
		cache.put("customer_add2", message.getCustomerAddress2());
		cache.put("customer_add3", message.getCustomerAddress3());
		cache.put("customer_add4", message.getCustomerAddress4());
		cache.put("incept_date", message.getInceptDate());
		cache.put("expiry_date", message.getExpiryDate());
		cache.put("occupation", message.getPolOccupation());
		cache.put("customer_type", message.getCustomerType());
		cache.put("tarrif", message.getTarrif());
		cache.put("customer_title", message.getCustomerTitle());
		cache.put("customer_name", message.getCustomerName());
		cache.put("cover_type", message.getCoverType());
		cache.put("class_type", message.getClassType());
		cache.put("veh_code", message.getVehicleCode());
		cache.put("year", message.getCarYear());
		cache.put("licen_no", message.getLicenceNo());
		cache.put("engine", message.getEngineNo());
		cache.put("chassis_no", message.getChassisNo());
		cache.put("brand", message.getBrand());
		cache.put("model", message.getModel());
		cache.put("seat", message.getSeat());
		cache.put("submodel", message.getSubModel());
		cache.put("color", message.getColor());
		cache.put("regdate", message.getRegDate());
		cache.put("accessories", message.getAccessories());
		cache.put("territory", message.getTerritoty());
		cache.put("suminsured", message.getSumInSured());
		cache.put("basis", message.getPremiumFinal()); // premium final
		cache.put("premiumry", message.getPremiumry());
		cache.put("othdiscpc", message.getOthDiscPc());
		cache.put("client_cd", message.getClientId());
		cache.put("dealer_code", message.getDealerCode());
		cache.put("out_txt1", message.getOutTxt1());
		cache.put("out_txt2", message.getOutTxt2());
		cache.put("ry01", message.getRy1());
		cache.put("ry02", message.getRy2());
		cache.put("ry03", message.getRy3());
		cache.put("line07", message.getLine7());
		cache.put("line08", message.getLine8());
		cache.put("line09", message.getLine9());
		cache.put("line10", message.getLine10());
		cache.put("line11", message.getLine11());
		cache.put("engkey2", message.getEngKey2());
		cache.put("batch", message.getBatch());
		cache.put("model_desc", message.getModelDesc());
		cache.put("user_send1", message.getUserSend());
		cache.put("name1", message.getName1());
		cache.put("sex1", message.getSex1());
		cache.put("dob1", message.getDob1());
		cache.put("occ1", message.getOcc1());
		cache.put("licno1", message.getLicNo1());
		cache.put("name2", message.getIdNo1());
		cache.put("sex2", message.getSex2());
		cache.put("dob2", message.getDob2());
		cache.put("occ2", message.getOcc2());
		cache.put("licno2", message.getLicNo2());
		cache.put("idno2", message.getIdNo2());
		*/
		
		cache.put("username", "UVT");
		cache.put("password", "UVTUVT11");

		cache.put("branch", ""); // ไม่ใช้
		cache.put("package_code", "VGPA");
		cache.put("policy_year", "9");
		cache.put("policy_no",  "1494" );
		cache.put("agent_code", "B0084");
		cache.put("customer_code", "L6638");
		cache.put("customer_sub_code", "071");
		cache.put("customer_name", "คุณ สุรภี คำตา");
		cache.put("customer_add1", "132/1 ม.1 บ้านถาวร");
		cache.put("customer_add2", "ต.หนองขมาร อ.คูเมือง");
		cache.put("customer_add3", "จ.บุรีรัมย์ 31190");
		cache.put("customer_add4", "");
		cache.put("incept_date", "211109");
		cache.put("expiry_date", "211110");
		cache.put("occupation", "21");
		cache.put("customer_type", "3310200201958");
		//cache.put("tarrif", "");
		//cache.put("customer_title", "");
		//cache.put("customer_name", "");
		cache.put("cover_type", "2A");
		cache.put("class_type", "1");
		cache.put("licen_no", "บธ 5841");
		cache.put("chassis_no", "MR0GR19G");		
		cache.put("veh_code", "210");
		cache.put("body", "M.BUS");//---------
		cache.put("engine", "8070993012");
		cache.put("year", "8");		
		cache.put("seat", "12");
		cache.put("displ", "");//---------
		cache.put("gvw", "0.000");//---------
		cache.put("medical", "Y");//---------
		cache.put("passenger", "4");//---------
		cache.put("benef", "TOYOTA LEASING(T.) CO.,LTD.");//---------
		cache.put("basis", "12000");//---------		
		cache.put("brand", "TOYOT");
		cache.put("model", "VIGO");	
		//cache.put("submodel", "");
		cache.put("color", "99");
		cache.put("regdate", "21112008");
		cache.put("accessories", "N");
		cache.put("territory", "00");
		
		cache.put("suminsured", "470000");
	
	//	cache.put("premium_final", ""); //--------------
	//	cache.put("premiumry", "");
		cache.put("othdiscpc", "");
		
		
		cache.put("client_cd", "");
		cache.put("dealer_code", "");
		cache.put("out_txt1", "");
		cache.put("out_txt2", "");
		cache.put("ry01", "");
		cache.put("ry02", "");
		cache.put("ry03", "");
		cache.put("line07", "");
		cache.put("line08","");
		cache.put("line09", "");
		cache.put("line10", "");
		cache.put("line11", "");
		cache.put("engkey2", "");
		cache.put("batch", "");
		cache.put("model_desc", "");
		cache.put("user_send1", "");
		cache.put("name1", "");
		cache.put("sex1", "");
		cache.put("dob1", "");
		cache.put("occ1", "");
		cache.put("licno1", "");
		cache.put("name2", "");
		cache.put("sex2", "");
		cache.put("dob2","");
		cache.put("occ2","");
		cache.put("licno2", "");
		cache.put("idno2", "");
		
		
		ConfigurationLoader loader = new ConfigurationLoader();
		try{
  
		loader.load("PageValidator.xml",UnderwrittingExecutor.class.getResourceAsStream("/xmlconfig/underwritting/PageValidator.xml"));                  
		loader.load("UNW_IMNUUW03S.xml",UnderwrittingExecutor.class.getResourceAsStream("/xmlconfig/underwritting/UNW_IMNUUW03S.xml"));
		loader.load("UNW_IUW0010S.xml",UnderwrittingExecutor.class.getResourceAsStream("/xmlconfig/underwritting/UNW_IUW0010S.xml"));
		loader.load("UNW_SignOn.xml",UnderwrittingExecutor.class.getResourceAsStream("/xmlconfig/underwritting/UNW_SignOn.xml"));
		
		
		
		
		loader.load("UNW_IUW0070S.xml",UnderwrittingExecutor.class.getResourceAsStream("/xmlconfig/underwritting/UNW_IUW0070S.xml"));
		loader.load("UNW_IUW0080S.xml",UnderwrittingExecutor.class.getResourceAsStream("/xmlconfig/underwritting/UNW_IUW0080S.xml"));
		loader.load("UNW_IUW0090S.xml",UnderwrittingExecutor.class.getResourceAsStream("/xmlconfig/underwritting/UNW_IUW0090S.xml"));
		loader.load("UNW_IUW1010S.xml",UnderwrittingExecutor.class.getResourceAsStream("/xmlconfig/underwritting/UNW_IUW1010S.xml"));
		loader.load("UNW_IUW1105S.xml",UnderwrittingExecutor.class.getResourceAsStream("/xmlconfig/underwritting/UNW_IUW1105S.xml"));
		loader.load("User Properties",UnderwrittingExecutor.class.getResourceAsStream("/xmlconfig/underwritting/user.properties"));
		
		Properties p = new Properties();
		p.load(UnderwrittingExecutor.class.getResourceAsStream("/xmlconfig/underwritting/user.properties"));
		cache.put("group.for.not.replace",p.get("group.for.not.replace"));
		cache.put("working_page","UNW_IUW0010S");
		
			ac = ActionFactory.getInstance().getSingletonAction(PROGRAM_ID);
			ac.setCache(cache);
			ac.setConnection(conn);
			ac.setConfigurationLoader(loader);
			ac.loadConfiguration();
			
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[Underwritting Scrap] = SING ON!");
			System.out.println("---------------------------------------------------------------------------------");
			
			if(!ac.isSignon())
				ac.signon();
			
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[Underwritting Scrap] = Execute!");
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
			System.out.println("[Underwritting Scrap] = Exec Error!");
			System.out.println("---------------------------------------------------------------------------------");
			
			cache.setTopProcessState(Cache.STATE_ERROR);
			e.printStackTrace();
			ac.signoff();
			ac.destroy();
			executeMessage = e.getMessage();
			throw e;
		}finally{
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[Underwritting Scrap] = Finally Close Connection!");
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
