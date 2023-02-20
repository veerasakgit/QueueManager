package th.co.msat.campaign.accumulate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AccumulateEstimatesDAO {

private MSITBServiceLocator services = new MSITBServiceLocator();
	

	
	GeneralFunction gf = new GeneralFunction();
	
	public List getPremium(String clientCode,String clientCode2,  String dateFrom, String dateTo, Campaign campaign, String classType, String broker)
			throws MSITBServiceLocatorException, SQLException {
		
		List list = null;
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		System.out.println("get premium for issue");
		try {		
			sql = new StringBuffer();			
			sql.append("SELECT  PMT.PRM_MAIN_AC,PMT.RCVDATE,(PMT.PRM_POL_BR || PMT.PRM_POL_CATG||'/'||PMT.PRM_POL_CL||'/'|| PMT.PRM_POL_YR||'-'||PMT.PRM_POL_NO) AS POLICY , PMT.PRM_ENDT_NO AS ENDT,PMT.PRM_INSTL_NO AS INST ");
			sql.append("  ,PMT.PRM_FILLER ,SUBSTRING(PO.AM6DETL2 ,63,5) AS MODEL ,TRIM(P.PM_CUST_NAME) AS INSURED  ,(PMT.PRM_GR_PREM - PMT.PRM_DISC) AS PREM ");
			sql.append(",TA.A10RCNO AS VOUCHER , P.PM_PERIOD_FROM AS FROM2, P.PM_PERIOD_TO AS TO , TRIM(TA.A10ADD1) AS A10ADD1 , TRIM(TA.A10ADD2) AS A10ADD2 ");
			sql.append(" , TRIM(TA.A10ADD3) AS A10ADD3 , P2.AM_YEAR_OF_MFR ,  CP.CT_REFERENCE , P.PM_LAST_ENDT_NO ");
			sql.append("FROM WEBBASELIB.IMPRM_WB01 PMT, MMFLIB.IMPOLICY6P PO , MMFLIB.IMPOLICY1P P "); 
			sql.append(", MMFLIB.IMPOLICY7P INS , MISLIB.TAX10PF TA , MMFLIB.IMPOLICY2P P2  , MMFLIB.IXACCOUNTP TP , MMFLIB.IXCTCODEP CP "); 

			sql.append("WHERE  ");			
			
			sql.append(" PMT.RCVDATE BETWEEN " + dateFrom + " AND "+ dateTo +"  ");
			
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
			sql.append("AND (PMT.PRM_TRANS_CD = 1) ");			
			
			if(campaign.getCampaignType().equals("F")){
				sql.append("AND (PMT.PRM_POL_CL LIKE '%F') ");
			}else if(campaign.getCampaignType().equals("N")){
				sql.append("AND (PMT.PRM_POL_CL NOT LIKE '%F') ");				
			}			
		
			if(!classType.trim().equals("A")){
				sql.append("AND (PMT.PRM_POL_CL = '" +  classType.trim() + "') ");
			}
			
			//sql.append("AND PMT.RCVDATE BETWEEN " + dateFrom + " AND "+ dateTo +"  ");
			
			sql.append("AND (PMT.PRM_POL_NO = PO.AM6_POL_NO AND PMT.PRM_POL_BR = PO.AM6_BR AND PMT.PRM_POL_CATG = PO.AM6_CAT ");  
			sql.append("AND PMT.PRM_POL_CL = PO.AM6_CLASS AND PMT.PRM_POL_YR = PO.AM6_YEAR AND PMT.PRM_ENDT_NO = PO.AM6_ENDT_NO ) "); 
			sql.append("AND ( PMT.PRM_POL_BR = INS.AM7_BR AND PMT.PRM_POL_CATG = INS.AM7_CAT  ");
			sql.append("AND PMT.PRM_POL_CL = INS.AM7_CLASS AND PMT.PRM_POL_YR = INS.AM7_YEAR  AND PMT.PRM_POL_NO = INS.AM7_POL_NO "); 
			sql.append("AND PMT.PRM_ENDT_NO =  INS.AM7_ENDT_NO) AND ( PMT.PRM_POL_BR = TA.A10BR AND PMT.PRM_POL_CATG = TA.A10CAT   ");
			sql.append("AND (TA.A10CLT = PMT.PRM_FILLER)AND PMT.PRM_POL_CL = TA.A10CLS AND PMT.PRM_POL_YR = TA.A10YR  ");
			sql.append("AND PMT.PRM_POL_NO = TA.A10NO AND PMT.PRM_ENDT_NO =  TA.A10END)  AND ( PMT.PRM_POL_BR = P.PM_BR  ");
			sql.append("AND PMT.PRM_POL_CATG = P.PM_CAT AND PMT.PRM_POL_CL =P.PM_CLASS AND PMT.PRM_POL_YR = P.PM_YEAR  ");
			sql.append("AND PMT.PRM_POL_NO = P.PM_POL_NO AND PMT.PRM_ENDT_NO =  P.PM_ENDT_NO)  "); 
			sql.append("AND (SUBSTRING(PMT.PRM_FILLER,1,5 ) = TP.AC_MAIN AND SUBSTRING(PMT.PRM_FILLER,6,8 ) = TP.AC_SUB) ");  
			sql.append("AND (PMT.PRM_POL_NO = P2.AM_POL_NO AND PMT.PRM_POL_BR = P2.AM_BR AND PMT.PRM_POL_CATG = P2.AM_CAT    ");
			sql.append("AND PMT.PRM_POL_CL = P2.AM_CLASS AND PMT.PRM_POL_YR = P2.AM_YEAR AND PMT.PRM_ENDT_NO = P2.AM_ENDT_NO )  ");
			sql.append("AND CP.CT_CVR_TYPE = P2.AM_COVER_TYPE  ");	
		
			conn = this.services.getConnection(MSITBServiceLocator.WEBBASELIB);
		
		
			System.out.println("----------------------------------------------------------------");
			System.out.println(sql);
			System.out.println("----------------------------------------------------------------");
			
			if (conn != null) {

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
//			log.error(se);
		
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

	public void insertIncentvieEstimates(CampaignRec rec, String campaignName, String campaignType)
			throws MSITBServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		int result = 0;
		try {
		
			sql = new StringBuffer();
			sql.append("INSERT INTO CAMPAIGN_INCENTIVE_ESTIMATES (  CAMPAIGN_ID ,CLIENT_CODE, CLIENT_NAME, CLIENT_ADDRESS, TAX_ID, RECEIVED_DATE, VOUCHER_NO "
							+ ",EFFECTIVE_FROM, EFFECTIVE_TO, POLICY_NO, INSURED_NAME, PREMIUM, MODEL, INCENTIVE , VAT ,TAX, CAMPAIGN_NAME, CAMPAIGN_TYPE, INSTALL_NO ) ");
			sql.append("VALUES ( ? , ? , ? , ? , ? , ? , ? ,?, ?, ?, ?, ?, ? , ? ,?, ?, ?, ?, ?) ");
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
				
				result = prep.executeUpdate();
			}

//			log.debug("SQL OF 'insertIncentvieEstimates' Method");
//			log.debug(sql);
			
		} catch (SQLException se) {
		
			if( se.getErrorCode() != -803){				
//				log.error(se);
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
	
	public boolean checkPolicyInstallment(String policy, int installNo)
	throws MSITBServiceLocatorException, SQLException {
		

		Connection conn = null;
		PreparedStatement prep = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		try {

			sql = new StringBuffer();
			sql.append("SELECT * FROM CAMPAIGN_INCENTIVE ");
			sql.append("WHERE POLICY_NO = '" + policy.trim() + "' ");
			sql.append("AND INSTALL_NO = " + installNo);
			//sql.append("AND PROCESS_TYPE = 1" );// type 1 for issue
		
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
