/*
 * Created on 18 �.�. 2551
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
public class ProvinceDAOImpl {
	//Logger logger = Logger.getLogger(NatureOfLossCodeDAOImpl.class);
	private MSATServiceLocator services = new MSATServiceLocator();
	private Connection getConnection() throws MSATServiceLocatorException{
		return services
		.getConnection(MSATServiceLocator.MSATLIB);

	}
	public List getAll() throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from  PROVINCECODETB");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				Map m = new HashMap();
				m.put("ProvinceCode",rs.getString("CODE").trim()+"-"+rs.getString("DESCRIPTION").trim());
				m.put("ProvinceDesc",rs.getString("DESCRIPTION").trim()+"-"+rs.getString("CODE").trim());
				list.add(m);
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
		return list;
	}
}
