/*
 * Created on 8 µ.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import th.co.msat.motor.database.services.MSATServiceLocator;
import th.co.msat.motor.database.services.MSATServiceLocatorException;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExecutorDAO {
	private MSATServiceLocator services = new MSATServiceLocator();
	/**
	 * MSATLIB Connection to be use in this class
	 * @return A java.sql.Connection lookup from java:comp/env/MSATLIB 
	 * @throws MSATServiceLocatorException
	 */
	private Connection getConnection() throws MSATServiceLocatorException{
		
		return services.getConnection(MSATServiceLocator.MSATLIB);
		
	}
	
	public boolean hasNext(String qname) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from queuetb ");
		sql.append(" where queuename = ? ");
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,qname);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt("available")>0;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=conn)
				conn.close();
		}
		return false;
	}
	public void decreaseAvailabel(String qname,int count) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update queuetb ");
		sql.append(" set available = available-? ");
		sql.append(" where queuename = ? ");
		Connection conn=null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1,count);
			pstmt.setString(2,qname);
			System.out.println("Update "+qname+"'s available for "+pstmt.executeUpdate()+" record(s).");
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=conn)
				conn.close();
		}
	}
	
	public void addAvailabel(String qname,int count) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update queuetb ");
		sql.append(" set available = available+? ");
		sql.append(" where queuename = ? ");
		Connection conn=null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1,count);
			pstmt.setString(2,qname);
			System.out.println("Update "+qname+"'s available for "+pstmt.executeUpdate()+" record(s).");
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=conn)
				conn.close();
		}
	}
	
	public void updateAvailabel(String qname,int count) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update queuetb ");
		sql.append(" set available =? ");
		sql.append(" where queuename = ? ");
		Connection conn=null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1,count);
			pstmt.setString(2,qname);
			System.out.println("Update "+qname+"'s available for "+pstmt.executeUpdate()+" record(s).");
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=conn)
				conn.close();
		}
	}
}
