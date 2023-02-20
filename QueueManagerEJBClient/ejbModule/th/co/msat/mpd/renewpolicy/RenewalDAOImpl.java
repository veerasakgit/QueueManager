package th.co.msat.mpd.renewpolicy;

import java.sql.Connection;
import java.sql.PreparedStatement;

import th.co.msat.motor.services.MSATServiceLocator;


public class RenewalDAOImpl implements RenewalDAO {

	private MSATServiceLocator services = new MSATServiceLocator();	
	
	public boolean inserReNewtTB(PolicyStatus policyStatus) throws Exception {
		boolean status = false;
		StringBuffer SQL_INSERT = new StringBuffer();
		SQL_INSERT.append("INSERT INTO RENEWAL_POLICY (OLD_POLICY ,OLD_SL_NO ,NEW_POLICY ,NEW_SL_NO ,LIC_NO )");
		SQL_INSERT.append(" VALUES ( ? ,? ,? ,? ,? ");
		Connection conn =  null;
		PreparedStatement pstmt = null;
	
		try{
			conn = services.getConnection(MSITBServiceLocator.MSATLIB);
			if (conn != null){ 				
				pstmt = conn.prepareStatement(SQL_INSERT.toString());
				//pstmt.setInt(1, PolicyStatus.getOldPolicy());
				pstmt.setString(1, policyStatus.getOldPolicy().trim());	
				pstmt.setInt(2, policyStatus.getOldSlNo());
				pstmt.setString(3, policyStatus.getNewPolicy());
				pstmt.setInt(4, policyStatus.getNewSlNo());
				pstmt.setString(5, policyStatus.getLicNo());
				pstmt.executeUpdate();
				status = true;
			}

		}catch (Exception eSQL){
			SQL_INSERT = null;
			eSQL.printStackTrace();
			throw eSQL;
		}
		finally{
			if (conn != null ) conn.close();
		}	
		return status;
	}

	public boolean updateReNewTB(PolicyStatus policyStatus) throws Exception {
		boolean status = false;
		StringBuffer SQL_UPDATE = new StringBuffer();
		
		SQL_UPDATE.append("UPDATE RENEWAL_POLICY SET ");
		SQL_UPDATE.append(",NEW_POLICY = ? ");
		SQL_UPDATE.append(",NEW_SL_NO = ? ");
		SQL_UPDATE.append("WHERE OLD_POLICY = ? AND OLD_SL_NO = ? ");
		
		Connection conn =  null;
		PreparedStatement pstmt = null;
	
		try{
			conn = services.getConnection(MSITBServiceLocator.MSATLIB);
			if (conn != null){ 
				
				pstmt = conn.prepareStatement(SQL_UPDATE.toString());
				
				pstmt.setString(1, policyStatus.getNewPolicy());
				pstmt.setInt(2, policyStatus.getNewSlNo());
				pstmt.setString(3, policyStatus.getOldPolicy().trim());	
				pstmt.setInt(4, policyStatus.getOldSlNo());
		
				pstmt.executeUpdate();
				status = true;
			}

		}catch (Exception eSQL){
			SQL_UPDATE = null;
			eSQL.printStackTrace();
			throw eSQL;
		}
		finally{
			if (conn != null ) conn.close();
		}	
		return status;
	}





}
