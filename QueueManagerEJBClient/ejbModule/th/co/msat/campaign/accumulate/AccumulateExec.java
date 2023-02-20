package th.co.msat.campaign.accumulate;


import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;





import th.co.msat.campaign.accumulate.gent.GenerateDocumentImplement;
import th.co.msat.motor.queuemanager.executor.ExecutorImpl;


public class AccumulateExec extends ExecutorImpl {

	private boolean alive = false;


	
	AccumulateMessage am = null;
	
	AccumulateEstimatesDAO estimatesDAO = new AccumulateEstimatesDAO();
	AccumulateDAO accumulateDAO = new AccumulateDAO();
	
	FormatUtil fmtUtil = null;
	
	
	public void execute() throws Exception {
		
		alive = true;

		long run_time = System.currentTimeMillis();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US );
		String sysdate = (String) dateFormat.format( new Date()); 
		
		System.out.println("================================================================================");
		System.out.println("================================================================================");
		System.out.println("Campaign Accumulate Process : " + sysdate);
		System.out.println("================================================================================");
		
			
		am = (AccumulateMessage) getMessage();
	
	
		accumulateDAO = new AccumulateDAO();

		fmtUtil = new FormatUtil();		
		
		int campaignId = am.getCampaignId().intValue();
		String dateFrom = am.getDateFrom();
		String dateTo = am.getDateTo();	
		int dateType = 0;
		try{	
	
			//ดึง campaign, client, model ตาม campaignId ออกมา
			List clientModelList = accumulateDAO.getCampaignClient(campaignId);		
		
			List models = accumulateDAO.getModel(campaignId);		
			
			Campaign campaign = accumulateDAO.getCampaign(campaignId);	

			//วนทุก client
			for (int i = 0; i < clientModelList.size(); i++) {
	
				CampaignClient clientModel = (CampaignClient) clientModelList.get(i);
				
				System.out.println(clientModel.getClientCode() + ", " + clientModel.getClientCode2());
				
				//กดมาแบบ estimates หรือไม่
				if(am.getFunctionType().equals("estimates")){
					processByIssue(clientModel, campaign, dateFrom, dateTo, campaignId, models, 1);					
				}else{
					//class type ของ campaign เป็น VCI หรือไม่
					if(campaign.getClassType().trim().equals("VCI")){
						processCVI(clientModel, dateFrom, dateTo, campaign, campaignId);
					}else{
						processALLMode(clientModel, dateFrom, dateTo, campaign, campaignId, models);
					}
					
				}
			}
			System.out.println("[Campaign] SentFileToSpoll Param : " + campaignId + ", " + campaign.getCampaignBroker());
			if(am.getFunctionType().equals("estimates")){
				sentFileToSpoll(campaignId, dateFrom, dateTo, 1, campaign.getCampaignBroker(), 1);
			}else{
				sentFileToSpoll(campaignId, dateFrom, dateTo, 0, campaign.getCampaignBroker(), 0);
			}
			
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("[Campaign] Complete : Use Time : " + (System.currentTimeMillis() - run_time) / 1000.0 / 60.0 + " min");
			System.out.println("--------------------------------------------------------------------------------");
			
				
		}catch (Exception e) {
			System.out.println("Error : error on accumulate method!");	
			System.out.println(e);
			throw e;
		}		
		
	}

	public void processALLMode(CampaignClient clientModel, String dateFrom, String dateTo, Campaign campaign, int campaignId, List models) throws Exception{

		//ดึงข้อมูล policy จาก as400
		List policyList = accumulateDAO.getPremiumFrom400(clientModel.getClientCode()
														, clientModel.getClientCode2()
														, fmtUtil.dateToString(dateFrom)
														, fmtUtil.dateToString(dateTo)
														, campaign
														, clientModel.getClassType()
														, clientModel.getBroker());		
		//วนตาม policy
		for (Iterator iter2 = policyList.iterator(); iter2.hasNext();) {
			
			CampaignRec cr = (CampaignRec) iter2.next();
			
			if (cr.getPolicyNo().trim().equals("BKD/VHP/9-4854")
				|| cr.getPolicyNo().trim().equals("BKD/VHP/9-4855"))
			{
				System.out.println("\n" + cr.getPolicyNo().trim() + "------------------------------------------> ");
			}
			
			String endtCode = "0";
			//มี endt เป็น 0 หรือไม่
			if(!cr.getLastEndt().trim().equals("0")){
				endtCode = accumulateDAO.getEndtCode(fmtUtil.fmtPolicy(cr.getPolicyNo()), cr.getLastEndt().trim());
			}				
				
			//วนตาม model
			for (Iterator modelList = models.iterator(); modelList.hasNext();) {
			
				Model model = (Model) modelList.next();										
				
				//ถ้า model ตรงกัน และ class type ของ campaign ไม่เป็น VCI
				if (model.getModelId().trim().equals(cr.getModel().trim()) && (!campaign.getClassType().trim().equals("VCI")) ) {
					
					//ถ้า submodel ของ model เป็น - หรือ (submodel เท่ากับ vihiclecode ของ policy แล้ว class type ของ campaign ต้องไม่เป็น CVI ด้วย) 
					if(model.getSubModel().trim().equals("-") || model.getSubModel().trim().equals(cr.getVehicleCode().trim()) && (!campaign.getClassType().trim().equals("VCI")) ){
					
						int pomotionFrom = fmtUtil.dateToInt(campaign.getEffectiveDate());

						int pomotionTo = fmtUtil.dateToInt(campaign.getExpiryDate());

						int effecDate = fmtUtil.fmtDateValue(cr.getEffectiveFrom());
						if (cr.getPolicyNo().trim().equals("BKD/VHP/9-4854")
							|| cr.getPolicyNo().trim().equals("BKD/VHP/9-4855"))
						{
							System.out.println("\n" + cr.getPolicyNo().trim() + "------------------------------------------> " + pomotionFrom + " " + effecDate + " " + pomotionTo);
						}
						//ถ้า pomotionFrom น้อยกว่า effecDate และ pomotionTo มากกว่า effecDate
						//ถ้า effDate ของ policy อยู่ในช่วงของ promotion ของ campaign
						if ((pomotionFrom <= effecDate) && (effecDate <= pomotionTo)) {
							
							cr.setPolicyNo(fmtUtil.fmtPolicy(cr.getPolicyNo()));
							
//							---------------------------------------------------------------------
							//จ่ายได้หรือไม่
						    if(accumulateDAO.checkUnpaid(cr.getPolicyNo())){
						    	System.out.println("[CAMAPIGN] UNPAID POLICY : " + cr.getPolicyNo());
						    	continue;
						    }
						    
							String[] strIncentive = accumulateDAO.getIntetive(campaignId, clientModel.getClientCode(), clientModel.getClientCode2(), model.getModelId(), clientModel.getBroker());

							//มี pertype ของ campaign เป็น C หรือไม่
							if (campaign.getPerType().equals("C")) {		
								cr.setIncentive(Double.parseDouble(strIncentive[0]) * (cr.getPremium()) / 100.0f);			
							} else {			
								cr.setIncentive(Double.parseDouble(strIncentive[0]));
							}

							cr.setCampaignId(campaignId);
							cr.setModel(strIncentive[1]);
							cr.setClientName(strIncentive[2]);
							cr.setTaxId(strIncentive[3]);
							cr.setVat(strIncentive[5]);
							cr.setModel(strIncentive[6]);
							cr.setTax(Float.parseFloat(strIncentive[7]));
							cr.setPolicyNo(cr.getPolicyNo() );
							cr.setEffectiveFrom(fmtUtil.fmtDateString(cr.getEffectiveFrom()));
							cr.setEffectiveTo(fmtUtil.fmtDateString(cr.getEffectiveTo()));
							cr.setReceiedDate(fmtUtil.fmtDateString(cr.getReceiedDate()));						
						   
							 //--------------------------------------------------------------------
						    //ตรวจสอบว่า เป็น
						    // P = ต่อคัน
						    // C = เปอร์เซ็น
						    //--------------------------------------------------------------------
						    if(campaign.getPerType().equals("P")){
								if(!accumulateDAO.checkPolicyInstallment(cr.getPolicyNo())){										
									accumulateDAO.insertIncentvie(cr, clientModel.getCampaignName(),"N", campaign.getCampaignBroker());
								}
							}else{										
								accumulateDAO.insertIncentvie(cr, clientModel.getCampaignName(),"N", campaign.getCampaignBroker());
							}	
						    //---------------------------------------------------------------------
						    // ตรวจสอบการยกเลิก policy
						    // endt code 1 or 6 คือ ยกเลิก
							//---------------------------------------------------------------------
							if( endtCode.trim().equals("1") || endtCode.trim().equals("6")){	
								cr.setIncentive(cr.getIncentive() * -1);
								cr.setPremium(cr.getPremium() * -1);								
								accumulateDAO.insertIncentvie(cr, clientModel.getCampaignName(),"C", campaign.getCampaignBroker());
								
							}
								
						    
						}
					}								
				}
			}				
		
		
		}

	}
	
	public void processCVI(CampaignClient clientModel, String dateFrom, String dateTo, Campaign campaign, int campaignId) throws Exception{
		
		List temp = null;
		
		try{
			temp = accumulateDAO.getPremiumCVI(clientModel.getClientCode(), clientModel.getClientCode2(), fmtUtil.dateToString(dateFrom), 
					fmtUtil.dateToString(dateTo), campaign, clientModel.getClassType(), clientModel.getBroker());
		}catch (Exception e) {
			System.out.println("[CAMPAIGN] Method Get PremiumCVI Error ! -> processCVI() : " + e);
		}
		
		for (Iterator iter2 = temp.iterator(); iter2.hasNext();) {
			
			CampaignRec cr = (CampaignRec) iter2.next();		
							
			int pomotionFrom = fmtUtil.dateToIntYYYY(campaign.getEffectiveDate());

			int pomotionTo = fmtUtil.dateToIntYYYY(campaign.getExpiryDate());

			int effecDate = Integer.parseInt(cr.getEffectiveFrom());					

			if ((pomotionFrom <= effecDate) && (effecDate <= pomotionTo)) {					
			
				String[] strIncentive = accumulateDAO.getIntetiveVCI(campaignId, clientModel.getClientCode(), clientModel.getClientCode2(), cr.getModel(), clientModel.getBroker());
				
				if (campaign.getPerType().equals("C")) {		
					cr.setIncentive(Double.parseDouble(strIncentive[0]) * (cr.getPremium()) / 100.0f);			
				} else {			
					cr.setIncentive(Double.parseDouble(strIncentive[0]));
				}

				cr.setCampaignId(campaignId);
				cr.setModel("");
				cr.setClientName(strIncentive[2]);
				cr.setTaxId(strIncentive[3]);
				cr.setVat(strIncentive[5]);
				cr.setModel("N/A");
				cr.setTax(Float.parseFloat(strIncentive[4]));
				cr.setPolicyNo(cr.getPolicyNo());	
				//System.out.println(cr.getEffectiveFrom() + ", " + fmtUtil.fmtDateString(cr.getEffectiveFrom()));
				cr.setEffectiveFrom(fmtUtil.fmtDateStringYYYYMMDD(cr.getEffectiveFrom()));
				cr.setEffectiveTo(fmtUtil.fmtDateStringYYYYMMDD(cr.getEffectiveTo()));
				cr.setReceiedDate(fmtUtil.fmtDateString(cr.getReceiedDate()));	
				cr.setInstallNo(1);

				//----insert
				String endtCode = accumulateDAO.getEndtVCI(cr.getPolicyNo());
				accumulateDAO.insertIncentvie(cr, clientModel.getCampaignName(), "N", campaign.getCampaignBroker());
				if(endtCode.trim().equals("8") || endtCode.trim().equals("9")){
					accumulateDAO.insertIncentvie(cr, clientModel.getCampaignName(), "N", campaign.getCampaignBroker());					
					cr.setIncentive(cr.getIncentive() * -1);
					cr.setPremium(cr.getPremium() * -1);
					accumulateDAO.insertIncentvie(cr, clientModel.getCampaignName(), "C", campaign.getCampaignBroker());															
				}
			
			}	
			
		}
		
	
	}
	
	public void sentFileToSpoll(int campaignId, String dateFrom, String dateTo, int dateType, int campaignBroker, int processType) throws Exception{
		
		try{
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[Campaign] Sent File To Spool Management!");	
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("[Campaign] Campaign Id : " + campaignId + ", " + campaignBroker + ", " + processType);
			GenerateDocumentImplement g = new GenerateDocumentImplement();
			System.out.println("DATA BETWEEN " + fmtUtil.dateToString(dateFrom) + ":" + fmtUtil.dateToString(dateTo));
			g.generatePDFDocument(campaignId, "Undefine",fmtUtil.dateToString(dateFrom),fmtUtil.dateToString(dateTo), dateType, campaignBroker, processType);			
			System.out.println("Sent File To Spool Management : Complate!");
			
		}catch (Exception e) {
			System.out.println("Sent To Spool Management : Error!");
			throw e;
		}
		
	}
	
	public void processByIssue(CampaignClient clientModel, Campaign campaign, String dateFrom, String dateTo , int campaignId, List models, int processType ) throws Exception{
		
		List policyList = estimatesDAO.getPremium(clientModel.getClientCode(),	clientModel.getClientCode2(), fmtUtil.dateToString(dateFrom), 
				fmtUtil.dateToString(dateTo), campaign,	clientModel.getClassType(), clientModel.getBroker());
		
		if(policyList == null){
			return;
		}		
		
		for (Iterator iter2 = policyList.iterator(); iter2.hasNext();) {
		
			CampaignRec cr = (CampaignRec) iter2.next();	
			cr.setPolicyNo(fmtUtil.fmtPolicy(cr.getPolicyNo()));							
//			if(cr.getPolicyNo().equals("BKD/VGPF/09-005197")){
//				System.out.println("BKD/VGPF/09-005197");
//			}
			String endtCode = "0";
			
			if(!cr.getLastEndt().trim().equals("0")){					
				endtCode = accumulateDAO.getEndtCode(fmtUtil.fmtPolicy(cr.getPolicyNo()), cr.getLastEndt().trim());
			}				
					
			for (Iterator modelList = models.iterator(); modelList.hasNext();) {
			
				Model model = (Model) modelList.next();					
				
				if (model.getModelId().trim().equals(cr.getModel().trim()) && (!campaign.getClassType().trim().equals("VCI")) ) {
					
					if(model.getSubModel().trim().equals("-") || model.getSubModel().trim().equals(cr.getVehicleCode().trim()) && (!campaign.getClassType().trim().equals("VCI")) ){
					
						int pomotionFrom = fmtUtil.dateToInt(campaign.getEffectiveDate());
	
						int pomotionTo = fmtUtil.dateToInt(campaign.getExpiryDate());
	
						int effecDate = fmtUtil.fmtDateValue(cr.getEffectiveFrom());
						
						
				
						if ((pomotionFrom <= effecDate) && (effecDate <= pomotionTo)) {
							
							
							
							// ตรวจสอบ policy ที่ lock ไม่จ่าย
						    if(accumulateDAO.checkUnpaid(cr.getPolicyNo())){
						    	System.out.println("[CAMAPIGN] UNPAID POLICY : " + cr.getPolicyNo());
						    	continue;
						    }
	
							String[] strIncentive = accumulateDAO.getIntetive(campaignId, clientModel.getClientCode(), clientModel.getClientCode2(), model.getModelId(), clientModel.getBroker());
	
							if (campaign.getPerType().equals("C")) {		
								cr.setIncentive(Double.parseDouble(strIncentive[0]) * (cr.getPremium()) / 100.0f);			
							} else {			
								cr.setIncentive(Double.parseDouble(strIncentive[0]));
							}
	
							cr.setCampaignId(campaignId);
							cr.setModel(strIncentive[1]);
							cr.setClientName(strIncentive[2]);
							cr.setTaxId(strIncentive[3]);
							cr.setVat(strIncentive[5]);
							cr.setModel(strIncentive[6]);
							cr.setTax(Float.parseFloat(strIncentive[7]));
							cr.setPolicyNo(cr.getPolicyNo() );
							cr.setEffectiveFrom(fmtUtil.fmtDateString(cr.getEffectiveFrom()));
							cr.setEffectiveTo(fmtUtil.fmtDateString(cr.getEffectiveTo()));
							cr.setReceiedDate(fmtUtil.fmtDateString(cr.getReceiedDate()));	
							
						    System.out.println("[Campaign] insert incentive to policy : " + cr.getPolicyNo() + "-0" + endtCode + "-0" + cr.getInstallNo());
						    
							if(campaign.getPerType().equals("P")){										
								if(!accumulateDAO.checkPolicyInstallment(cr.getPolicyNo())){										
									accumulateDAO.insertIncentvie(cr, clientModel.getCampaignName(),"N", campaign.getCampaignBroker(),processType);
								}	
							}else{										
								accumulateDAO.insertIncentvie(cr, clientModel.getCampaignName(),"N", campaign.getCampaignBroker(),processType);
							}	
								
						    //---------------------------------------------------------------------
						    // ตรวจสอบการยกเลิก policy
						    // endt code 1 or 6 คือ ยกเลิก
							//---------------------------------------------------------------------
						    if( endtCode.trim().equals("1") || endtCode.trim().equals("6") ){					
						    	accumulateDAO.insertIncentvie(cr, clientModel.getCampaignName(),"N", campaign.getCampaignBroker(),processType);
						    	cr.setIncentive(cr.getIncentive() * -1);
								cr.setPremium(cr.getPremium() * -1);								
								accumulateDAO.insertIncentvie(cr, clientModel.getCampaignName(),"C", campaign.getCampaignBroker(),processType);
						    }
						    
						}
					}								
				}
			}				
		}
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void onTimeout() {
		alive = false;
		System.out.println("Queue Time out");
	}

}
