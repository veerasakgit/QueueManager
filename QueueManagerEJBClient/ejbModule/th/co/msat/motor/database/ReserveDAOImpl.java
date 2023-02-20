/*
 * Created on 18 ¡.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.log4j.Logger;

import th.co.msat.motor.services.MSATServiceLocator;
import th.co.msat.motor.services.MSATServiceLocatorException;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReserveDAOImpl {
	//Logger logger = Logger.getLogger(NatureOfLossCodeDAOImpl.class);
	private MSATServiceLocator services = new MSATServiceLocator();
	private Connection getConnection() throws MSATServiceLocatorException{
		return services
		.getConnection(MSATServiceLocator.MSATLIB);

	}
	public String getN201Amount() throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from  RESERVECODETB where code = 'N201'");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String amount="0.00";
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				amount = rs.getString("AMOUNT");
			}
			
		}catch(Exception e){
			//logger.error(e);
		}finally{
			if(rs!=null)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return amount;
	}
	public String getN999Amount() throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from  RESERVECODETB where code = 'N999'");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String amount="0.00";
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				amount = rs.getString("AMOUNT");
			}
			
		}catch(Exception e){
			//logger.error(e);
		}finally{
			if(rs!=null)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return amount;
	}
	public String getV100Amount() throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from  RESERVECODETB where code = 'V100'");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String amount="0.00";
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				amount = rs.getString("AMOUNT");
			}
			
		}catch(Exception e){
			//logger.error(e);
		}finally{
			if(rs!=null)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return amount;
	}
}
