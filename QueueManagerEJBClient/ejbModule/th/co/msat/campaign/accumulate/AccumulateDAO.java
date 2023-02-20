package th.co.msat.campaign.accumulate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccumulateDAO {

	private MSITBServiceLocator services = new MSITBServiceLocator();
	

	GeneralFunction gf = new GeneralFunction();
	
	public List getCampaignClient(int campaignId)
			throws MSITBServiceLocatorException, SQLException {
		List list = null;
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		try {

			sql = new StringBuffer();

			sql.append("SELECT CU.BROKER, CU.CLIENT, CU.CLIENT2 , CP.CLASS, CP.CAMPAIGN_NAME ");
			sql.append("FROM CAMPAIGN CP  ");
			sql.append(", CUSTOMER_GROUP CU , CAMPAIGN_MAP_CUSTOMER CC  ");			
			sql.append("WHERE ");	
			sql.append("CP.CAMPAIGN_ID = CC.CAMPAIGN_ID ");
			sql.append("AND CC.CUSTOMER_ID = CU.CUSTOMER_ID ");
			sql.append("AND CP.STATUS = 'Y' ");
			sql.append("AND CU.STATUS = 'Y' ");
			sql.append("AND CC.STATUS = 'Y' ");
			sql.append("AND CP.CAMPAIGN_ID = ?");
			//System.out.println(sql);
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);

			if (conn != null) {
				int i = 1;
				prep = conn.prepareStatement(sql.toString());
				prep.setInt(i++, campaignId);
				rs = prep.executeQuery();

				if (rs != null) {

					list = new ArrayList();

					while (rs.next()) {
						list.add(new CampaignClient(
								rs.getString(1)
								, rs.getString(2)
								, rs.getString(3)
								, rs.getString(4)
								, rs.getString(5)
							));

					}
				}
			}

		} catch (SQLException se) {

			se.printStackTrace();

			if (rs != null)
				rs.close();

			if (prep != null)
				prep.close();

		} finally {

			if (rs != null)
				rs.close();

			if (prep != null)
				prep.close();

			if (conn != null)
				conn.close();
		}

		return list;

	}
	
	public List getModel(int campaignId)
	throws MSITBServiceLocatorException, SQLException {
		List list = null;
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		try {
		
			sql = new StringBuffer();
		
			sql.append("SELECT  M.GEN400_ID , SM.GEN400_ID  ");
			sql.append("FROM CAMPAIGN CP, CAMPAIGN_MAP_CAR_GROUP CG, CAR_MAP_CAR_GROUP C  ");
			sql.append(", CAR R , CAR_MODEL M ,  CAR_MODEL_SUB SM ");
			sql.append("WHERE CP.CAMPAIGN_ID = CG.CAMPAIGN_ID ");
			sql.append("AND CG.CAR_GROUP_ID = C.CAR_GROUP_ID ");
			sql.append("AND C.CAR_ID = R.CAR_ID ");
			sql.append("AND R.SUB_MODEL_ID = SM.CAR_SUB_ID ");
			sql.append("AND R.CAR_MODEL_ID = M.CAR_MODEL_ID ");	
			sql.append("AND CG.STATUS = 'Y' ");
			sql.append("AND C.STATUS = 'Y' ");
			sql.append("AND SM.STATUS = 'Y' ");
			sql.append("AND CG.CAMPAIGN_ID = ?");
		
		//	System.out.println("[sql get model]" + sql);
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);
		
			if (conn != null) {
				int i = 1;
				prep = conn.prepareStatement(sql.toString());
				prep.setInt(i++, campaignId);
				rs = prep.executeQuery();
		
				if (rs != null) {
		
					list = new ArrayList();
		
					while (rs.next()) {
						list.add(new Model(
								rs.getString(1)
								, rs.getString(2)));
		
					}
				}
			}
		
		} catch (SQLException se) {
		
			se.printStackTrace();
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
		} finally {
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
			if (conn != null)
				conn.close();
		}
		
		return list;
		
	}

	public List getPremiumFrom400(String clientCode,String clientCode2,  String dateFrom, String dateTo, Campaign campaign, String classType, String broker)
			throws MSITBServiceLocatorException, SQLException {
		List list = null;
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		try {

			sql = new StringBuffer();

			
			sql.append("SELECT  PMT.PRM_MAIN_AC,PM.RCVDATE,( TRIM(PMT.PRM_POL_BR)||TRIM(PMT.PRM_POL_CATG)||'/'||TRIM(PMT.PRM_POL_CL)||'/'|| PMT.PRM_POL_YR||'-'||PMT.PRM_POL_NO) AS POLICY , PMT.PRM_ENDT_NO AS ENDT,PMT.PRM_INSTL_NO AS INST ");
			sql.append("  ,PMT.PRM_FILLER ,SUBSTRING(PO.AM6DETL2 ,63,5) AS MODEL ,TRIM(P.PM_CUST_NAME) AS INSURED  ,(PMT.PRM_GR_PREM - PMT.PRM_DISC) AS PREM ");
			sql.append(",TA.A10RCNO AS VOUCHER , P.PM_PERIOD_FROM AS FROM2, P.PM_PERIOD_TO AS TO , TRIM(TA.A10ADD1) AS A10ADD1 , TRIM(TA.A10ADD2) AS A10ADD2 ");
			sql.append(" , TRIM(TA.A10ADD3) AS A10ADD3 , P2.AM_YEAR_OF_MFR ,  CP.CT_REFERENCE , P.PM_LAST_ENDT_NO ");
			sql.append("FROM WEBBASELIB.IMPRM_WB01 PM, MMFLIB.IMPOLICY6P PO , MMFLIB.IMPOLICY1P P "); 
			sql.append(", MMFLIB.IMPOLICY7P INS , MISLIB.TAX10PF TA , MMFLIB.IMPOLICY2P P2  , MMFLIB.IXACCOUNTP TP , MMFLIB.IXCTCODEP CP "); 
			//-------------------------------------------------------------------------------------------
			sql.append(" LEFT  JOIN WEBBASELIB.IMPRM_WB01 PMT ON 	 ");			
			sql.append("(PMT.PRM_TRANS_CD = 1 ");	
			sql.append("AND PM.PRM_POL_BR  = PMT.PRM_POL_BR ");
			sql.append("AND PM.PRM_POL_CATG   = PMT.PRM_POL_CATG "); 
			sql.append("AND PM.PRM_POL_CL   = PMT.PRM_POL_CL  ");
			sql.append("AND PM.PRM_POL_YR   = PMT.PRM_POL_YR ");
			sql.append("AND PM.PRM_POL_NO   = PMT.PRM_POL_NO ");
			sql.append("AND PM.PRM_ENDT_NO  = PMT.PRM_ENDT_NO ");			
			sql.append("AND PM.PRM_INSTL_NO = PMT.PRM_INSTL_NO )");
						
			sql.append("AND  (((PMT.PRM_NET_PREM <= 0 ) OR ((PMT.PRM_NET_PREM + PM.PRM_COMM) = 0 )) ) ");
			//-------------------------------------------------------------------------------------------
			sql.append("WHERE  ");
			
			sql.append(" PM.RCVDATE BETWEEN " + dateFrom + " AND "+ dateTo +"  ");
			if(!campaign.getCarType().trim().equals("A")){
				sql.append("AND (PMT.PRM_NEW_RENEW = '"+  campaign.getCarType().trim() + "') ");	
			}
			
			
			if(campaign.getCarYear() != 0){
				sql.append("AND (P2.AM_YEAR_OF_MFR -  P2.AM_YEAR) <= " + campaign.getCarYear() + "  ");
			}
			
			
			if(clientCode.trim().equals("*") && (!clientCode2.trim().equals("*"))){
				sql.append("AND (TRIM(PMT.PRM_FILLER) LIKE '%" +  clientCode2.trim() + "') ");
			}
			if((!clientCode.trim().equals("*")) && clientCode2.trim().equals("*")){
				sql.append("AND (TRIM(PMT.PRM_FILLER) LIKE '" +  clientCode.trim() + "%') ");
			}
			
			if(!(clientCode.trim().equals("*")) && (!clientCode2.trim().equals("*"))){
				sql.append("AND (TRIM(PMT.PRM_FILLER) = '" +  clientCode.trim() + clientCode2.trim() + "') ");
			}
			
			
			if(!broker.trim().equals("*")){
				sql.append("AND (PMT.PRM_MAIN_AC = '" + broker.trim()+"') ");
			}
			sql.append("AND (PM.PRM_TRANS_CD = 4) ");
		
			
			
			if(campaign.getCampaignType().equals("F")){
				sql.append("AND (PMT.PRM_POL_CL LIKE '%F') ");
			}else if(campaign.getCampaignType().equals("N")){
				sql.append("AND (PMT.PRM_POL_CL NOT LIKE '%F') ");
				
			}
			

			if(!classType.trim().equals("A")){
				sql.append("AND (PM.PRM_POL_CL = '" +  classType.trim() + "') ");
			}
			
			
			sql.append("AND (PMT.PRM_POL_NO = PO.AM6_POL_NO AND PMT.PRM_POL_BR = PO.AM6_BR AND PMT.PRM_POL_CATG = PO.AM6_CAT ");  
			sql.append("AND PMT.PRM_POL_CL = PO.AM6_CLASS AND PMT.PRM_POL_YR = PO.AM6_YEAR AND PMT.PRM_ENDT_NO = PO.AM6_ENDT_NO ) "); 
			sql.append("AND ( PMT.PRM_POL_BR = INS.AM7_BR AND PMT.PRM_POL_CATG = INS.AM7_CAT  ");
			sql.append("AND PMT.PRM_POL_CL = INS.AM7_CLASS AND PMT.PRM_POL_YR = INS.AM7_YEAR  AND PMT.PRM_POL_NO = INS.AM7_POL_NO "); 
			sql.append("AND PMT.PRM_ENDT_NO =  INS.AM7_ENDT_NO) AND ( PMT.PRM_POL_BR = TA.A10BR AND PMT.PRM_POL_CATG = TA.A10CAT   ");
			sql.append("AND (TA.A10CLT = PMT.PRM_FILLER)AND PMT.PRM_POL_CL = TA.A10CLS AND PMT.PRM_POL_YR = TA.A10YR  ");
			sql.append("AND PMT.PRM_POL_NO = TA.A10NO AND PMT.PRM_ENDT_NO =  TA.A10END AND PMT.PRM_INSTL_NO = TA.A10INST)  AND ( PMT.PRM_POL_BR = P.PM_BR  ");
			sql.append("AND PMT.PRM_POL_CATG = P.PM_CAT AND PMT.PRM_POL_CL =P.PM_CLASS AND PMT.PRM_POL_YR = P.PM_YEAR  ");
			sql.append("AND PMT.PRM_POL_NO = P.PM_POL_NO AND PMT.PRM_ENDT_NO =  P.PM_ENDT_NO)  "); 
			sql.append("AND (SUBSTRING(PMT.PRM_FILLER,1,5 ) = TP.AC_MAIN AND SUBSTRING(PMT.PRM_FILLER,6,8 ) = TP.AC_SUB) ");  
			sql.append("AND (PMT.PRM_POL_NO = P2.AM_POL_NO AND PMT.PRM_POL_BR = P2.AM_BR AND PMT.PRM_POL_CATG = P2.AM_CAT    ");
			sql.append("AND PMT.PRM_POL_CL = P2.AM_CLASS AND PMT.PRM_POL_YR = P2.AM_YEAR AND PMT.PRM_ENDT_NO = P2.AM_ENDT_NO )  ");
			sql.append("AND CP.CT_CVR_TYPE = P2.AM_COVER_TYPE  "); //AND P.PM_ENDT_CODE = 0
			

			conn = this.services.getConnection(MSITBServiceLocator.WEBBASELIB);

//			log.debug("sql getPremiumFrom400() : " + sql);
			System.out.println("get premium 400");
			System.out.println(sql);
			
			if (conn != null) {
			//	int i = 1;
				prep = conn.prepareStatement(sql.toString());

				rs = prep.executeQuery();

				if (rs != null) {

					list = new ArrayList();
					CampaignRec campaignRec = null;
					campaignRec = new CampaignRec();
					while (rs.next()) {
						campaignRec = new CampaignRec();
						campaignRec.setBroker(rs.getString("PRM_MAIN_AC"));
						campaignRec.setClientCode(rs.getString("PRM_FILLER"));
						campaignRec.setClientAddress(rs.getString("A10ADD1") + " " + rs.getString("A10ADD2") + " " + rs.getString("A10ADD3") );				
						campaignRec.setEffectiveFrom(rs.getString("FROM2"));
						campaignRec.setEffectiveTo(rs.getString("TO"));				
						campaignRec.setInsuredName(rs.getString("INSURED"));
						campaignRec.setModel(rs.getString("MODEL"));
						campaignRec.setPolicyNo(rs.getString("POLICY"));
						campaignRec.setEndtNo(rs.getString("ENDT"));
						campaignRec.setPremium(rs.getDouble("PREM"));
						campaignRec.setReceiedDate(rs.getString("RCVDATE"));					
						campaignRec.setVoucherNo(rs.getString("VOUCHER"));
						campaignRec.setVehicleCode(rs.getString("CT_REFERENCE"));
						campaignRec.setLastEndt(rs.getString("PM_LAST_ENDT_NO"));
						campaignRec.setInstallNo(rs.getInt("INST"));						
					
						list.add(campaignRec);

					}
				}
			}

		} catch (SQLException se) {

			se.printStackTrace();

			if (rs != null)
				rs.close();

			if (prep != null)
				prep.close();

		} finally {

			if (rs != null)
				rs.close();

			if (prep != null)
				prep.close();

			if (conn != null)
				conn.close();
		}

		return list;
	}

	public List getPremiumCVI(String clientCode,String clientCode2,  String dateFrom, String dateTo, Campaign campaign, String classType, String broker)
	throws MSITBServiceLocatorException, SQLException {
		List list = null;
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		try {
		
			sql = new StringBuffer();
		
			sql.append("SELECT CVPAGTM, CVPCLTM, CVPCLTS, CVPADDR1 ,CVPADDR2, CVPADDR3, CVPADDR4 ");
			sql.append(",CVPDATE, CVPEXPD, CVPNAMT1, CVPNAMT2, CVPCARMAK ");
			sql.append(",CVPCARNM2 , CVPNET "); //CVPTOTPRM
			sql.append(",CVPBR, CVPCLS, CVPYR, CVPPOLNO1, CVPPOLNO2, CVPENDT ");
			sql.append(",CVPDATR, CVPETYP, CVPSET ");
			sql.append("FROM CVILIB.CVF01PF ");
			sql.append("WHERE ");		
			sql.append("CVPSET IN (2,9) ");	
			sql.append("AND CVPNET <> 0 ");
			
			//sql.append("CVPSET = 2 AND (CVPETYP NOT IN(8,9))");	
			
			sql.append("AND (CVPDATR BETWEEN  "+  "20" + dateFrom +"  AND  " +  "20" +  dateTo+ ") ");
			sql.append("AND (CVPCLS = 'VCI') ");	

			if(!clientCode.trim().equals("*")){
				sql.append("AND (TRIM(CVPCLTM) LIKE '" +  clientCode.trim() + "') ");
			}
			if(!clientCode2.trim().equals("*")){
				sql.append("AND (TRIM(CVPCLTS) LIKE '" +  clientCode2.trim() + "') ");
			}
		

			if(!broker.trim().equals("*")){
				sql.append("AND (CVPAGTM = '" + broker.trim()+"') ");
			}
		
			conn = this.services.getConnection(MSITBServiceLocator.WEBBASELIB);
		
			System.out.println(sql);
			if (conn != null) {
				
				prep = conn.prepareStatement(sql.toString());
		
		
				rs = prep.executeQuery();
		
				if (rs != null) {
		
					list = new ArrayList();
					CampaignRec campaignRec = null;
					campaignRec = new CampaignRec();
					while (rs.next()) {
						campaignRec = new CampaignRec();
						campaignRec.setBroker(rs.getString("CVPAGTM"));
						campaignRec.setClientCode(rs.getString("CVPCLTM") + rs.getString("CVPCLTS"));
						campaignRec.setClientAddress(rs.getString("CVPADDR1")
								+ " " + rs.getString("CVPADDR2") + " "
								+ rs.getString("CVPADDR3") + " "
								+ rs.getString("CVPADDR4"));
				
						campaignRec.setEffectiveFrom(rs.getString("CVPDATE"));
						campaignRec.setEffectiveTo(rs.getString("CVPEXPD"));
				
						campaignRec.setInsuredName(rs.getString("CVPNAMT1") + "  " + rs.getString("CVPNAMT2"));
						campaignRec.setModel(rs.getString("CVPCARMAK") + " " + rs.getString("CVPCARNM2"));
						
						campaignRec.setPolicyNo(gf.formatPolicy(rs.getString("CVPBR"), rs.getString("CVPCLS"), rs.getString("CVPYR"), rs.getString("CVPPOLNO1"), rs.getString("CVPPOLNO2"), ""));
						campaignRec.setEndtNo(rs.getString("CVPENDT"));
						campaignRec.setPremium(rs.getDouble("CVPNET"));
						campaignRec.setReceiedDate(rs.getString("CVPDATR"));					
						campaignRec.setVoucherNo("");// emtry string
						campaignRec.setVehicleCode("");
						campaignRec.setLastEndt("");
					
						list.add(campaignRec);
		
					}
				}
			}
		
		} catch (SQLException se) {
		
			se.printStackTrace();
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
		} finally {
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
			if (conn != null)
				conn.close();
		}
		
		return list;
	}

	public String[] getIntetive(int campaignId, String clientCode, String clientCode2,String modelId, String broker) throws MSITBServiceLocatorException, SQLException {
		String[] result = new String[8];
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		try {

			sql = new StringBuffer();

			sql.append("SELECT CP.AMOUT , M.DESCRIPTION , CU.CUSTOMER_NAME , CU.TAX_ID, CU.TAX_PERCENT ,CU.VAT, CU.TAX_PERCENT ");
			sql.append(",( TRIM(CM.DESCRIPTION) ||' '||TRIM(M.DESCRIPTION)) AS MARK ");
			sql.append("FROM CAMPAIGN CP, CAMPAIGN_MAP_CAR_GROUP CG, CAR_MAP_CAR_GROUP C  ");
			sql.append(", CAR R , CAR_MODEL M , CUSTOMER_GROUP CU , CAMPAIGN_MAP_CUSTOMER CC ");
			sql.append(", CAR_MARK CM ");
			sql.append("WHERE ");
			sql.append("(CP.CAMPAIGN_ID = CG.CAMPAIGN_ID  ");
			sql.append("AND CG.CAR_GROUP_ID = C.CAR_GROUP_ID ");
			sql.append("AND C.CAR_ID = R.CAR_ID ");
			sql.append("AND M.CAR_MARK_ID = CM.CAR_MARK_ID ");
			sql.append("AND R.CAR_MODEL_ID = M.CAR_MODEL_ID ");
			sql.append("AND CP.CAMPAIGN_ID = CC.CAMPAIGN_ID ");
			sql.append("AND CC.CUSTOMER_ID = CU.CUSTOMER_ID) ");
			sql.append("AND CG.CAMPAIGN_ID = ? ");
			sql.append("AND M.GEN400_ID = ? ");	
			if(!clientCode.trim().equals("*")){
				sql.append("AND CU.CLIENT = '" + clientCode.trim() + "' ");
			}
			if(!clientCode2.trim().equals("*")){
				sql.append("AND CU.CLIENT2 = '" + clientCode2.trim() + "' ");
			}
		
			if(!broker.trim().equals("*")){
				sql.append("AND CU.BROKER = '" + broker.trim() + "' ");
			}
			
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);

			if (conn != null) {
				int i = 1;
				prep = conn.prepareStatement(sql.toString());
				prep.setInt(i++, campaignId);
				prep.setString(i++, modelId);
				rs = prep.executeQuery();

				if (rs != null) {

					while (rs.next()) {
						result[0] = rs.getString("AMOUT");
						result[1] = rs.getString("DESCRIPTION");
						result[2] = rs.getString("CUSTOMER_NAME");
						result[3] = rs.getString("TAX_ID");
						result[4] = rs.getString("TAX_PERCENT");
						result[5] = rs.getString("VAT");
						result[6] = rs.getString("MARK");
						result[7] = rs.getString("TAX_PERCENT");
					}
				}
			}

		} catch (SQLException se) {

			se.printStackTrace();

			if (rs != null)
				rs.close();

			if (prep != null)
				prep.close();

		} finally {

			if (rs != null)
				rs.close();

			if (prep != null)
				prep.close();

			if (conn != null)
				conn.close();
		}

		return result;

	}

	public String[] getIntetiveVCI(int campaignId, String clientCode, String clientCode2,String modelId, String broker) throws MSITBServiceLocatorException, SQLException {
		String[] result = new String[8];
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		try {

			sql = new StringBuffer();

			sql.append("SELECT CP.AMOUT  , CU.CUSTOMER_NAME , CU.TAX_ID, CU.TAX_PERCENT ,CU.VAT ");

			sql.append("FROM CAMPAIGN CP , CUSTOMER_GROUP CU , CAMPAIGN_MAP_CUSTOMER CC  ");
			sql.append("WHERE CP.CAMPAIGN_ID = CC.CAMPAIGN_ID AND CC.CUSTOMER_ID = CU.CUSTOMER_ID ");
		
			sql.append("AND CP.CAMPAIGN_ID = ? ");

			if(!clientCode.trim().equals("*")){
				sql.append("AND CU.CLIENT = '" + clientCode.trim() + "' ");
			}
			if(!clientCode2.trim().equals("*")){
				sql.append("AND CU.CLIENT2 = '" + clientCode2.trim() + "' ");
			}
		
			if(!broker.trim().equals("*")){
				sql.append("AND CU.BROKER = '" + broker.trim() + "' ");
			}	
			
			System.out.println("getIntetiveVCI : " + sql);
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);

			if (conn != null) {
				int i = 1;
				prep = conn.prepareStatement(sql.toString());
				prep.setInt(i++, campaignId);
				rs = prep.executeQuery();

				if (rs != null) {

					while (rs.next()) {
						result[0] = rs.getString("AMOUT");
				
						result[2] = rs.getString("CUSTOMER_NAME");
						result[3] = rs.getString("TAX_ID");
						result[4] = rs.getString("TAX_PERCENT");
						result[5] = rs.getString("VAT");
				
						return result;
					}
				}
			}

		} catch (SQLException se) {

			se.printStackTrace();

			if (rs != null)
				rs.close();

			if (prep != null)
				prep.close();

		} finally {

			if (rs != null)
				rs.close();

			if (prep != null)
				prep.close();

			if (conn != null)
				conn.close();
		}

		return result;

	}
	
	public void insertIncentvie(CampaignRec rec, String campaignName, String campaignType, int campaignBroker)
			throws MSITBServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		int result = 0;
		try {

			sql = new StringBuffer();
			sql.append("INSERT INTO CAMPAIGN_INCENTIVE (  CAMPAIGN_ID ,CLIENT_CODE, CLIENT_NAME, CLIENT_ADDRESS, TAX_ID, RECEIVED_DATE, VOUCHER_NO "
							+ ",EFFECTIVE_FROM, EFFECTIVE_TO, POLICY_NO, INSURED_NAME, PREMIUM, MODEL, INCENTIVE , VAT ,TAX, CAMPAIGN_NAME, CAMPAIGN_TYPE, INSTALL_NO, CAMPAIGN_BROKER ) ");
			sql.append("VALUES ( ? , ? , ? , ? , ? , ? , ? ,?, ?, ?, ?, ?, ? , ? ,?, ?, ?, ?, ?, ?) ");
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);

			if (conn != null) {
				int i = 1;
				prep = conn.prepareStatement(sql.toString());
				prep.setInt(i++, rec.getCampaignId());
				prep.setString(i++, rec.getClientCode());
				prep.setString(i++, rec.getClientName());
				prep.setString(i++, rec.getClientAddress());
				prep.setString(i++, rec.getTaxId());
				prep.setString(i++, rec.getReceiedDate());
				prep.setString(i++, rec.getVoucherNo());
				prep.setString(i++, rec.getEffectiveFrom());
				prep.setString(i++, rec.getEffectiveTo());
				prep.setString(i++, rec.getPolicyNo());
				prep.setString(i++, rec.getInsuredName());
				prep.setDouble(i++, rec.getPremium());
				prep.setString(i++, rec.getModel());
				prep.setDouble(i++, rec.getIncentive());
				prep.setFloat(i++, Float.parseFloat(rec.getVat()));
				prep.setFloat(i++, rec.getTax());
				prep.setString(i++, campaignName);
				prep.setString(i++, campaignType);
				prep.setInt(i++, rec.getInstallNo());
				prep.setInt(i++, campaignBroker);
				
				result = prep.executeUpdate();
			}
			//System.out.println(sql);
		} catch (SQLException se) {

			if( se.getErrorCode() != -803){
				se.printStackTrace();
				throw se;
			}

			if (rs != null)
				rs.close();

			if (prep != null)
				prep.close();
			

		} finally {

			if (rs != null)
				rs.close();

			if (prep != null)
				prep.close();

			if (conn != null)
				conn.close();
		}

	}
	
	public void insertIncentvie(CampaignRec rec, String campaignName, String campaignType, int campaignBroker, int processType)
	throws MSITBServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		int result = 0;
		try {
		
			sql = new StringBuffer();
			sql.append("INSERT INTO CAMPAIGN_INCENTIVE (  CAMPAIGN_ID ,CLIENT_CODE, CLIENT_NAME, CLIENT_ADDRESS, TAX_ID, RECEIVED_DATE, VOUCHER_NO "
							+ ",EFFECTIVE_FROM, EFFECTIVE_TO, POLICY_NO, INSURED_NAME, PREMIUM, MODEL, INCENTIVE , VAT ,TAX, CAMPAIGN_NAME, CAMPAIGN_TYPE, INSTALL_NO, CAMPAIGN_BROKER, PROCESS_TYPE ) ");
			sql.append("VALUES ( ? , ? , ? , ? , ? , ? , ? ,?, ?, ?, ?, ?, ? , ? ,?, ?, ?, ?, ?, ?, ?) ");
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);
		
			if (conn != null) {
				int i = 1;
				prep = conn.prepareStatement(sql.toString());
				prep.setInt(i++, rec.getCampaignId());
				prep.setString(i++, rec.getClientCode());
				prep.setString(i++, rec.getClientName());
				prep.setString(i++, rec.getClientAddress());
				prep.setString(i++, rec.getTaxId());
				prep.setString(i++, rec.getReceiedDate());
				prep.setString(i++, rec.getVoucherNo());
				prep.setString(i++, rec.getEffectiveFrom());
				prep.setString(i++, rec.getEffectiveTo());
				prep.setString(i++, rec.getPolicyNo());
				prep.setString(i++, rec.getInsuredName());
				prep.setDouble(i++, rec.getPremium());
				prep.setString(i++, rec.getModel());
				prep.setDouble(i++, rec.getIncentive());
				prep.setFloat(i++, Float.parseFloat(rec.getVat()));
				prep.setFloat(i++, rec.getTax());
				prep.setString(i++, campaignName);
				prep.setString(i++, campaignType);
				prep.setInt(i++, rec.getInstallNo());
				prep.setInt(i++, campaignBroker);
				prep.setInt(i++, processType);
				
				result = prep.executeUpdate();
			}
			
		} catch (SQLException se) {
		
			if( se.getErrorCode() != -803){
				se.printStackTrace();
				throw se;
			}
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
			
		
		} finally {
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
			if (conn != null)
				conn.close();
		}
		
	}
	
	public Campaign getCampaign(int campaignId) throws MSITBServiceLocatorException, SQLException {
		Campaign campaign = null;
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;		
		try {

			sql = new StringBuffer();
			sql.append("SELECT * ");	
			sql.append("FROM CAMPAIGN WHERE CAMPAIGN_ID = ? AND STATUS = 'Y'");	
			
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);

			if (conn != null) {
				int i = 1;
				prep = conn.prepareStatement(sql.toString());
				prep.setInt(i++, campaignId);

				rs = prep.executeQuery();

				if (rs != null) {
					while (rs.next()) {
					campaign = new Campaign(				
								rs.getInt("CAMPAIGN_ID"),
								rs.getString("CAMPAIGN_NAME"),	
								gf.longToDate(Long.valueOf( rs.getString("EFFECTIVE_DATE") )),
								gf.longToDate(Long.valueOf(rs.getString("EXPIRY_DATE"))),
								rs.getFloat("AMOUT"),	
								rs.getString("CAMPAIGN_TYPE"),
								rs.getString("STATUS"),
								rs.getString("PER_TYPE"),
								rs.getString("CLASS") ,
								rs.getString("CAR_TYPE"),
								rs.getInt("CAR_YEAR"),
								rs.getInt("CAMPAIGN_BROKER")
							);
					}
				}
				
			}
		} catch (SQLException se) {

			se.printStackTrace();

			if (rs != null)
				rs.close();

			if (prep != null)
				prep.close();

		} finally {

			if (rs != null)
				rs.close();

			if (prep != null)
				prep.close();

			if (conn != null)
				conn.close();
		}

		return campaign;	
	}
	
	public String getEndtCode(String policy, String lastEndt)
	throws MSITBServiceLocatorException, SQLException {
		
		String endtCode = "0";
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		try {
			FormatUtil fmt = new FormatUtil();
			String [] str = fmt.splitPolicy(policy);
			sql = new StringBuffer();

			sql.append("SELECT PM_ENDT_CODE FROM MMFLIB.IMPOLICY1P ");
			sql.append("WHERE PM_BR = '" + str[0]+"' ");
			sql.append("AND PM_CAT = '" + str[1] + "' ");			
			sql.append("AND PM_CLASS = '" + str[2]+ "' ");	
			sql.append("AND PM_YEAR = "+ str[3] +" ");		
			sql.append("AND PM_POL_NO = " + Integer.parseInt(str[4]) + " ");
			sql.append("AND PM_ENDT_NO = " +lastEndt+" ");

			conn = this.services.getConnection(MSITBServiceLocator.MMFLIB);
		
			if (conn != null) {
			
				prep = conn.prepareStatement(sql.toString());
			
				rs = prep.executeQuery();
		
				if (rs != null) {
		
					while (rs.next()) {
						
						endtCode = rs.getString(1);
						
					}
				}
			}
		
		} catch (SQLException se) {
		
			se.printStackTrace();
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
		} finally {
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
			if (conn != null)
				conn.close();
		}
		
		return endtCode;
		
	}
	
	public String checkPaymentPolicy(String policy, int instNo)throws MSITBServiceLocatorException, SQLException {
		String strResult = "N";		
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		try {	
			
			sql = new StringBuffer();
			
			sql.append("SELECT STATUS FROM CAMPAIGN_POLICY_STATUS ");
			sql.append("WHERE POLICY_NO = '"+ policy + "' AND INST_NO = " + instNo+ "");	

			
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);
		
			if (conn != null) {
			
				prep = conn.prepareStatement(sql.toString());
			
				rs = prep.executeQuery();
		
				
				if (rs != null) {
					while (rs.next()) {
						
						strResult = rs.getString(1);
						
					}
				}
			}
		
		} catch (SQLException se) {
		
			se.printStackTrace();
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
		} finally {
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
			if (conn != null)
				conn.close();
		}
		
		return strResult; 
		
	}
	
	public void updatePolicyStatus(String policyNo, int instNo, String status)
		throws MSITBServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		
		try {
		
			sql = new StringBuffer();
			sql.append("UPDATE CAMPAIGN_POLICY_STATUS  ");
			sql.append("SET STATUS = '"+ status+ "' " );
			sql.append("WHERE POLICY_NO = '" + policyNo + "' AND INST_NO = " + instNo + " ");
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);
		
			if (conn != null) {				
				prep = conn.prepareStatement(sql.toString());			
			
				prep.executeUpdate();
			}
		} catch (SQLException se) {
		
			if( se.getErrorCode() != -803){
				se.printStackTrace();
			}
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
		} finally {
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
			if (conn != null)
				conn.close();
		}
	
	}
	
	public void insertPolicyStatus(String policyNo, int instNo, String status)
		throws MSITBServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		
		try {
		
			sql = new StringBuffer();
			sql.append("INSERT INTO CAMPAIGN_POLICY_STATUS (  POLICY_NO, INST_NO, STATUS ) ");
			sql.append("VALUES ( ? , ? , ?) ");
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);
		
			if (conn != null) {
				int i = 1;
				prep = conn.prepareStatement(sql.toString());
			
				prep.setString(i++, policyNo);
				prep.setInt(i++, instNo);
				prep.setString(i++, status);
				
				
				prep.executeUpdate();
			}
		} catch (SQLException se) {
		
			if( se.getErrorCode() != -803){
				se.printStackTrace();
			}
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
		} finally {
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
			if (conn != null)
				conn.close();
		}
	
	}
	
	public void updateIncentvie(String policyNo, String status)
		throws MSITBServiceLocatorException, SQLException {
		String newStatus = "";
		if(status.equals("C")){
			newStatus = "R";
		}else{
			newStatus="C";
		}
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		
		try {
		
			sql = new StringBuffer();
			sql.append("UPDATE CAMPAIGN_INCENTIVE  ");
			sql.append("SET CAMPAIGN_TYPE = '"+ newStatus + "'  ");
			sql.append("WHERE POLICY_NO = '" + policyNo + "' AND CAMPAIGN_TYPE = '" + status + "'"  );
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);
		
			if (conn != null) {				
				prep = conn.prepareStatement(sql.toString());			
			
				prep.executeUpdate();
			}
		} catch (SQLException se) {
		
			if( se.getErrorCode() != -803){
				se.printStackTrace();
			}
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
		} finally {
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
			if (conn != null)
				conn.close();
		}
	
	}
	
	public String getEndtVCI(String policy)
	throws MSITBServiceLocatorException, SQLException {
		
		String endtCode = "0";
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		try {
			GeneralFunction gf = new GeneralFunction();
			String [] str = gf.splitPolicy(policy);
			sql = new StringBuffer();

			sql.append("SELECT CVPETYP FROM CVILIB.CVF01PF ");
			sql.append("WHERE ");
			sql.append("CVPBR = '" + str[0] + "' ");
			sql.append("AND CVPCLS = '" + str[1] + "' ");
			sql.append("AND CVPYR = " + str[2] + " ");
			sql.append("AND CVPPOLNO1 = " + str[3] + " ");
			sql.append("AND CVPPOLNO2 = " + str[4] + " ");

		
			conn = this.services.getConnection(MSITBServiceLocator.MMFLIB);
		
			if (conn != null) {
			
				prep = conn.prepareStatement(sql.toString());
			
				rs = prep.executeQuery();
		
				if (rs != null) {
		
					
		
					while (rs.next()) {
						
						endtCode = rs.getString(1);
						
					}
				}
			}
		
		} catch (SQLException se) {
		
			se.printStackTrace();
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
		} finally {
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
			if (conn != null)
				conn.close();
		}
		
		return endtCode;
		
	}
	
	public boolean checkUnpaid(String policy)
	throws MSITBServiceLocatorException, SQLException {
		

		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		try {

			sql = new StringBuffer();

			sql.append("SELECT * FROM CAMPAIGN_UNPAID ");
			sql.append("WHERE POLICY_NO = '" + policy.trim() + "' " );
			sql.append("AND STATUS = 'Y'" );

		
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);
		
			if (conn != null) {
			
				prep = conn.prepareStatement(sql.toString());
			
				rs = prep.executeQuery();
		
				if (rs != null) {
		
					
		
					while (rs.next()) {
						
						return true;
						
					}
				}
			}
		
		} catch (SQLException se) {
		
			se.printStackTrace();
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
		} finally {
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
			if (conn != null)
				conn.close();
		}
		
		return false;
		
	}
	
	public boolean checkPolicyInstallment(String policy)
	throws MSITBServiceLocatorException, SQLException {
		

		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		try {

			sql = new StringBuffer();
			sql.append("SELECT * FROM CAMPAIGN_INCENTIVE ");
			sql.append("WHERE POLICY_NO = '" + policy.trim() + "' " );
		
			conn = this.services.getConnection(MSITBServiceLocator.MSATLIB);
		
			if (conn != null) {
			
				prep = conn.prepareStatement(sql.toString());
			
				rs = prep.executeQuery();
		
				if (rs != null) {
		
					while (rs.next()) {
						
						return true;
						
					}
				}
			}
		
		} catch (SQLException se) {
		
			se.printStackTrace();
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
		} finally {
		
			if (rs != null)
				rs.close();
		
			if (prep != null)
				prep.close();
		
			if (conn != null)
				conn.close();
		}
		
		return false;
		
	}
	
}
