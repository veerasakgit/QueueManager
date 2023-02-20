package th.co.msitb.claimsystem.executor;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.services.MSATServiceLocator;

public class ClaimRegiterDAO {
	
	

	public void updateClaimNo(String notificationNO, String claimNo, String userName, String status) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update TBL_NOTIFICATION  ");
		sql.append(" set CLAIM_NO = ?,USER_UPDATE = ? ,UPDATE_DATE  = ?, UPDATE_TIME = ?, STATUS = ? ");
		sql.append(" where NOTIFICATION_NO = ? ");	
		PreparedStatement pstmt = null;
		Connection conn = null;
		try{
			conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, claimNo);
			pstmt.setString(2, userName);
			pstmt.setString(3, toDayString());
			pstmt.setString(4, timeString());
			pstmt.setString(5, status);
			pstmt.setString(6, notificationNO);
			
		
			pstmt.executeUpdate();
			
		}finally{
			if(null!=pstmt)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(null!=conn)
					try {
						conn.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}	
		}
		
	}
	
	public String getQyearQnumberImp( String notiNo , int actionId ) throws Exception, SQLException{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String result = "";
		ResultSet rs = null;
		
		StringBuffer SQL = new StringBuffer();
		SQL.append( " select qinyear,qnumber ");
		SQL.append( " from tbl_action_q "); 
		SQL.append( " where refer_id = ? ");
		SQL.append( " and ACTION_ID = ? " );

		
		try {
			conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);
			
			if(conn != null){
				
				pstmt = conn.prepareStatement( SQL.toString() );				
				int i=0;
				pstmt.setString( ++i,notiNo.trim() );
				pstmt.setInt(++i,actionId);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					result = rs.getString( "qinyear" ).concat( "," ).concat( rs.getString( "qnumber" ) );
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;		
		}finally{
			if(null!=pstmt)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(null!=conn)
					try {
						conn.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}	
		}
		
		return result;
	}
	
	public String toDayString() {
		java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
		String dte = d.toString();
		dte = dte.substring(0, 4) + dte.substring(5, 7) + dte.substring(8);
		return dte;
	}

	public String timeString() {
		String timeSt = "";
		String hoursStr = addString(new Integer(java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY))
				.toString(), "0", 2, false);
		String minutesStr = addString(new Integer(java.util.Calendar.getInstance().get(java.util.Calendar.MINUTE))
				.toString(), "0", 2, false);
		String secondsStr = addString(new Integer(java.util.Calendar.getInstance().get(java.util.Calendar.SECOND))
				.toString(), "0", 2, false);
		timeSt = hoursStr.trim() + minutesStr.trim() + secondsStr.trim();
		return timeSt;
	}
	
	public String addString(String word, String str, int num, boolean rightText) {

		StringBuffer stb = new StringBuffer(num);
		int len = (word == null) ? 0 : word.length();
		int sp = num - len;
		word = (word == null) ? "" : word;
		str = (str == null) ? " " : str;
		if (sp > 0) {
			stb.append(word);
			if (!rightText)
				stb.reverse();
			for (int i = 0; i < sp; i++)
				stb.append(str);
			if (!rightText)
				stb.reverse();
		} else {
			if (rightText)
				stb.append(word.substring(0, num));
			else
				stb.append(word.substring(len - num));
		}
		return stb.toString();
	}
	
	
}
