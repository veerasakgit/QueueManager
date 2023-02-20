/*
 * Created on 10 ¡.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/****************************************************/
/* ----------------- Modify History -----------------*/
/****************************************************/
/* ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/10/19[yyyy/mm/dd]
 * @description Re-tune SQL
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/11/05[yyyy/mm/dd]
 * @description Close connection
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/11/05[yyyy/mm/dd]
 * @description Re-tune SQL
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2010/02/03[yyyy/mm/dd]
 * @description Remove log for registered
 * --------------------------------------------------*/

package th.co.msat.motor.queuemanager.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.log4j.Logger;


//import th.co.msat.motor.database.util.DateConverter;
import th.co.msat.motor.database.vo.Notification;
import th.co.msat.motor.database.vo.RegisteredClaim;
import th.co.msat.motor.services.MSATServiceLocator;
import th.co.msat.motor.services.MSATServiceLocatorException;
/**
 * @author ituser3
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class NotificationDAOImpl implements NotificationDAO {
//	Logger logger = Logger.getLogger(NotificationDAOImpl.class);

	private MSATServiceLocator services = new MSATServiceLocator();

	/*
	 * (non-Javadoc)
	 * 
	 * @see th.co.msat.motor.database.NotificationDAO#getNotification()
	 */
	public Notification getNotification(String notificationNo) throws Exception{
		Connection conn = null;
		try{
			conn = getConnection();
			return getNotification(notificationNo,conn);
		}finally{
			if(null!=conn)
				conn.close();
		}
	}
	
	public boolean isExistsClaimNo(String claimno) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from NOTIFICATIONTB ");
		sql.append(" where claimno = ? ");
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
//			logger.error(e);
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			//Start Close connection Veerasak Boonchern Aey 2009/11/05[yyyy/mm/dd]
			if (conn != null)
				conn.close();
			//End Close connection Veerasak Boonchern Aey 2009/11/05[yyyy/mm/dd]
		}
		return false;
	}
	
	public Collection getNotificationForScrap(String from,String to)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from NOTIFICATIONTB where NOTIFICATIONNO >= ?  ");
		sql.append(" and NOTIFICATIONNO <= ? ");
		sql.append(" and TRANSACTIONSTATUS = 'C' ");
		sql.append(" and REGIS_POLICYNO <> '' ");
		sql.append(" and REGIS_NOFLOSS <> '' ");
		sql.append(" and REGIS_PROVINCE <> '' ");
		sql.append(" and REGIS_RISKNO  <> 0 ");
		sql.append(" and claimno not like 'BT%' ");
		sql.append(" and is_scrip is null ");
		System.out.println(sql.toString());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection c = new ArrayList();
		Connection conn = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,from.trim());
			pstmt.setString(2,to.trim());
			rs = pstmt.executeQuery();
			while(rs.next()){
				Notification n = null;
				n = new Notification();
				n.setNotificationNo(rs.getString("NOTIFICATIONNO"    ));
				n.setAccidentDate(rs.getInt("ACCIDENTDATE"         ));
				n.setAccidentTime(rs.getInt("ACCIDENTTIME"         ));
				n.setNotificationDate(rs.getInt("NOTIFICATIONDATE"     ));
				n.setNotificationTime(rs.getInt("NOTIFICATIONTIME"     ));
				n.setNotificationName(rs.getString("NOTIFICATIONNAME"  ));
				n.setTelephone(rs.getString("TELEPHONE"         ));
				n.setAccidentPlace(rs.getString("ACCIDENTPLACE"     ));
				n.setDescriptionLine1(rs.getString("DESCRIPTIONLINE1"  ));
				n.setDescriptionLine2(rs.getString("DESCRIPTIONLINE2"  ));
				n.setDescriptionLine3(rs.getString("DESCRIPTIONLINE3"  ));
				n.setDescriptionLine4(rs.getString("DESCRIPTIONLINE4"  ));
				n.setDescriptionLine5(rs.getString("DESCRIPTIONLINE5"  ));
				n.setBranch(rs.getString("BRANCH"            ));
				n.setReferencePolicy(rs.getString("REFERENCEPOLICY"   ));
				n.setRiskNo(rs.getInt("RISKNO"            ));
				n.setPoicyNo(rs.getString("POLICYNO"          ));
				n.setCarRegisterNo(rs.getString("CARREGISTERNO"     ));
				n.setInsurename(rs.getString("INSURENAME"        ));
				n.setCarBrand(rs.getString("CARBRAND"          ));
				n.setInceptionDate(rs.getInt("INCEPTIONDATE"        ));
				n.setExpireDate(rs.getInt("EXPIRYDATE"           ));
				n.setDriverName(rs.getString("DRIVERNAME"        ));
				n.setSurveyorName(rs.getString("SURVEYORNAME"      ));
				n.setSurveyorOffice(rs.getString("SURVEYOROFFICE"    ));
				n.setAssignDate(rs.getInt("ASSIGNDATE"           ));
				n.setEstimateDate(rs.getInt("ESTIMATEDATE"         ));
				n.setTransactionStatus(rs.getString("TRANSACTIONSTATUS" ));
				n.setCompletedDate(rs.getInt("COMPLETEDDATE"        ));
				n.setTransactionIndex(rs.getInt("TRANSACTIONINDEX"));
				n.setRegisPolicyNo(rs.getString("REGIS_POLICYNO"));
				n.setRegisNatureOfLoss(rs.getString("REGIS_NOFLOSS"));
				n.setRegisProvinceCode(rs.getString("REGIS_PROVINCE"));
				n.setRegisRiskNo(rs.getInt("REGIS_RISKNO"));
				n.setSurveryFee(rs.getFloat("SURVEYFEE"));
				n.setOd(rs.getFloat("OD"));
				n.setClaimNo(rs.getString("CLAIMNO"));
				n.setIsScrap(rs.getString("is_scrip")); 
				n.setSurveyorCode(rs.getString("surveyorcode"));
				c.add(n);
			}
		}catch(SQLException e){
			e.printStackTrace();
//			logger.error(e);
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return c;
	}
	
	public Notification getNotificationForScrap(String notificationNo) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from NOTIFICATIONTB where NOTIFICATIONNO = ? ");
		sql.append(" and TRANSACTIONSTATUS = 'C' ");
		sql.append(" and REGIS_POLICYNO <> '' ");
		sql.append(" and REGIS_NOFLOSS <> '' ");
		sql.append(" and REGIS_PROVINCE <> '' ");
		sql.append(" and REGIS_RISKNO  <> 0 ");
		sql.append(" and claimno not like 'BT%' ");
		sql.append(" and is_scrip = 'M' "); 
		System.out.println(sql.toString());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notification n = null;
		Connection conn = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,notificationNo);
			rs = pstmt.executeQuery();
			if(rs.next()){
				n = new Notification();
				n.setNotificationNo(rs.getString("NOTIFICATIONNO"    ));
				n.setAccidentDate(rs.getInt("ACCIDENTDATE"         ));
				n.setAccidentTime(rs.getInt("ACCIDENTTIME"         ));
				n.setNotificationDate(rs.getInt("NOTIFICATIONDATE"     ));
				n.setNotificationTime(rs.getInt("NOTIFICATIONTIME"     ));
				n.setNotificationName(rs.getString("NOTIFICATIONNAME"  ));
				n.setTelephone(rs.getString("TELEPHONE"         ));
				n.setAccidentPlace(rs.getString("ACCIDENTPLACE"     ));
				n.setDescriptionLine1(rs.getString("DESCRIPTIONLINE1"  ));
				n.setDescriptionLine2(rs.getString("DESCRIPTIONLINE2"  ));
				n.setDescriptionLine3(rs.getString("DESCRIPTIONLINE3"  ));
				n.setDescriptionLine4(rs.getString("DESCRIPTIONLINE4"  ));
				n.setDescriptionLine5(rs.getString("DESCRIPTIONLINE5"  ));
				n.setBranch(rs.getString("BRANCH"            ));
				n.setReferencePolicy(rs.getString("REFERENCEPOLICY"   ));
				n.setRiskNo(rs.getInt("RISKNO"            ));
				n.setPoicyNo(rs.getString("POLICYNO"          ));
				n.setCarRegisterNo(rs.getString("CARREGISTERNO"     ));
				n.setInsurename(rs.getString("INSURENAME"        ));
				n.setCarBrand(rs.getString("CARBRAND"          ));
				n.setInceptionDate(rs.getInt("INCEPTIONDATE"        ));
				n.setExpireDate(rs.getInt("EXPIRYDATE"           ));
				n.setDriverName(rs.getString("DRIVERNAME"        ));
				n.setSurveyorName(rs.getString("SURVEYORNAME"      ));
				n.setSurveyorOffice(rs.getString("SURVEYOROFFICE"    ));
				n.setAssignDate(rs.getInt("ASSIGNDATE"           ));
				n.setEstimateDate(rs.getInt("ESTIMATEDATE"         ));
				n.setTransactionStatus(rs.getString("TRANSACTIONSTATUS" ));
				n.setCompletedDate(rs.getInt("COMPLETEDDATE"        ));
				n.setTransactionIndex(rs.getInt("TRANSACTIONINDEX"));
				n.setRegisPolicyNo(rs.getString("REGIS_POLICYNO"));
				n.setRegisNatureOfLoss(rs.getString("REGIS_NOFLOSS"));
				n.setRegisProvinceCode(rs.getString("REGIS_PROVINCE"));
				n.setRegisRiskNo(rs.getInt("REGIS_RISKNO"));
				n.setSurveryFee(rs.getFloat("SURVEYFEE"));
				n.setOd(rs.getFloat("OD"));
				n.setClaimNo(rs.getString("CLAIMNO"));
				n.setIsScrap(rs.getString("is_scrip"));
				n.setSurveyorCode(rs.getString("surveyorcode"));
				
				
			}
		}catch(SQLException e){
			e.printStackTrace();
//			logger.error(e);
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return n;
	}

	
	/* (non-Javadoc)
	 * @see th.co.msat.motor.database.NotificationDAO#getNotification(java.lang.String, java.sql.Connection)
	 */
	public Notification getNotification(String notificationNo, Connection conn) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from NOTIFICATIONTB where NOTIFICATIONNO = ? ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notification n = null;
		try{
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,notificationNo);
			rs = pstmt.executeQuery();
			if(rs.next()){
				n = new Notification();
				n.setNotificationNo(rs.getString("NOTIFICATIONNO"    ));
				n.setAccidentDate(rs.getInt("ACCIDENTDATE"         ));
				n.setAccidentTime(rs.getInt("ACCIDENTTIME"         ));
				n.setNotificationDate(rs.getInt("NOTIFICATIONDATE"     ));
				n.setNotificationTime(rs.getInt("NOTIFICATIONTIME"     ));
				n.setNotificationName(rs.getString("NOTIFICATIONNAME"  ));
				n.setTelephone(rs.getString("TELEPHONE"         ));
				n.setAccidentPlace(rs.getString("ACCIDENTPLACE"     ));
				n.setDescriptionLine1(rs.getString("DESCRIPTIONLINE1"  ));
				n.setDescriptionLine2(rs.getString("DESCRIPTIONLINE2"  ));
				n.setDescriptionLine3(rs.getString("DESCRIPTIONLINE3"  ));
				n.setDescriptionLine4(rs.getString("DESCRIPTIONLINE4"  ));
				n.setDescriptionLine5(rs.getString("DESCRIPTIONLINE5"  ));
				n.setBranch(rs.getString("BRANCH"            ));
				n.setReferencePolicy(rs.getString("REFERENCEPOLICY"   ));
				n.setRiskNo(rs.getInt("RISKNO"            ));
				n.setPoicyNo(rs.getString("POLICYNO"          ));
				n.setCarRegisterNo(rs.getString("CARREGISTERNO"     ));
				n.setInsurename(rs.getString("INSURENAME"        ));
				n.setCarBrand(rs.getString("CARBRAND"          ));
				n.setInceptionDate(rs.getInt("INCEPTIONDATE"        ));
				n.setExpireDate(rs.getInt("EXPIRYDATE"           ));
				n.setDriverName(rs.getString("DRIVERNAME"        ));
				n.setSurveyorName(rs.getString("SURVEYORNAME"      ));
				n.setSurveyorOffice(rs.getString("SURVEYOROFFICE"    ));
				n.setAssignDate(rs.getInt("ASSIGNDATE"           ));
				n.setEstimateDate(rs.getInt("ESTIMATEDATE"         ));
				n.setTransactionStatus(rs.getString("TRANSACTIONSTATUS" ));
				n.setCompletedDate(rs.getInt("COMPLETEDDATE"        ));
				n.setTransactionIndex(rs.getInt("TRANSACTIONINDEX"));
				n.setRegisPolicyNo(rs.getString("REGIS_POLICYNO"));
				n.setRegisNatureOfLoss(rs.getString("REGIS_NOFLOSS"));
				n.setRegisProvinceCode(rs.getString("REGIS_PROVINCE"));
				n.setRegisRiskNo(rs.getInt("REGIS_RISKNO"));
				n.setSurveryFee(rs.getFloat("SURVEYFEE"));
				n.setOd(rs.getFloat("OD"));
				n.setClaimNo(rs.getString("CLAIMNO"));
				n.setIsScrap(rs.getString("is_scrip"));
				n.setSurveyorCode(rs.getString("SURVEYORCODE"));
			}
		}catch(SQLException e){
			e.printStackTrace();
//			logger.error(e);
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
		}
		return n;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see th.co.msat.motor.database.NotificationDAO#insert(th.co.msat.motor.database.vo.Notification)
	 */
	public void insert(Connection conn,Notification n) throws Exception {
		// TODO Auto-generated method stub
		
		if(n==null||"".equals(n.getNotificationNo().trim())){
			return;
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into NOTIFICATIONTB ");
		sql.append(" (NOTIFICATIONNO ,  ");
		sql.append("	ACCIDENTDATE , ");
		sql.append("	ACCIDENTTIME  , ");
		sql.append("	NOTIFICATIONDATE  , ");
		sql.append("	NOTIFICATIONTIME  , ");
		sql.append("		NOTIFICATIONNAME  , ");
		sql.append("	TELEPHONE  , ");
		sql.append("	ACCIDENTPLACE  , ");
		sql.append("	DESCRIPTIONLINE1 , ");
		sql.append("	DESCRIPTIONLINE2 , ");
		sql.append("	DESCRIPTIONLINE3 , ");
		sql.append("	DESCRIPTIONLINE4 , ");
		sql.append("	DESCRIPTIONLINE5 , ");
		sql.append("	BRANCH , ");
		sql.append("	REFERENCEPOLICY , ");
		sql.append("	RISKNO, ");
		sql.append("		POLICYNO , ");
		sql.append("	CARREGISTERNO , ");
		sql.append(" INSURENAME , ");
		sql.append(" CARBRAND , ");
		sql.append("INCEPTIONDATE  ,  ");
		sql.append("EXPIRYDATE , ");
		sql.append("DRIVERNAME , ");
		sql.append("SURVEYORNAME , ");
		sql.append("SURVEYOROFFICE  , ");
		sql.append("ASSIGNDATE , ");
		sql.append("ESTIMATEDATE  , ");
		sql.append("TRANSACTIONSTATUS  , ");
		sql.append("COMPLETEDDATE  , ");
		sql.append("CLAIMNO,");
		sql.append("TRANSACTIONINDEX )  ");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
//			conn = services
//					.getConnection(MSATServiceLocator.MSATLIB);
			int index = getMaxNotificationIndex(conn);
			pstmt = conn.prepareStatement(sql.toString());
			int i=1;
			pstmt.setString(i++,n.getNotificationNo().trim());
			pstmt.setInt(i++,n.getAccidentDate());
			pstmt.setInt(i++,n.getAccidentTime());
			pstmt.setInt(i++,n.getNotificationDate());
			pstmt.setInt(i++,n.getNotificationTime());
			pstmt.setString(i++,n.getNotificationName().trim());
			pstmt.setString(i++,correctPhoneNumber(n.getTelephone()));
			pstmt.setString(i++,n.getAccidentPlace().trim());
			pstmt.setString(i++,n.getDescriptionLine1().trim());
			pstmt.setString(i++,n.getDescriptionLine2().trim());
			pstmt.setString(i++,n.getDescriptionLine3().trim());
			pstmt.setString(i++,n.getDescriptionLine4().trim());
			pstmt.setString(i++,n.getDescriptionLine5().trim());
			pstmt.setString(i++,n.getBranch().trim());
			pstmt.setString(i++,n.getReferencePolicy().trim());
			pstmt.setInt(i++,n.getRiskNo());
			pstmt.setString(i++,n.getPoicyNo().trim());
			pstmt.setString(i++,n.getCarRegisterNo().trim());
			pstmt.setString(i++,n.getInsurename().trim().length()>50?n.getInsurename().trim().substring(0,49): n.getInsurename().trim());
			pstmt.setString(i++,n.getCarBrand().trim());
			pstmt.setInt(i++,n.getInceptionDate());
			pstmt.setInt(i++,n.getExpireDate());
			pstmt.setString(i++,n.getDriverName().trim());
			pstmt.setString(i++,n.getSurveyorName().trim());
			pstmt.setString(i++,n.getSurveyorOffice().trim());
			pstmt.setInt(i++,n.getAssignDate());
			pstmt.setInt(i++,n.getEstimateDate());
			pstmt.setString(i++,n.getTransactionStatus().trim());
			pstmt.setInt(i++,n.getCompletedDate());
			pstmt.setString(i++,"");
			pstmt.setInt(i++,index);
			if(0==pstmt.executeUpdate()){
//				logger.error(" Record cannot be insert:"+n.getNotificationNo());
			}
		} catch (Exception e) {
//			logger.error(e);
			throw e;
		} finally {
			if (null != pstmt)
				pstmt.close();
//			if (null != conn)
//				conn.close();
		}
	}
	
	int getMaxNotificationIndex(Connection conn) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select max(TRANSACTIONINDEX)+1 as i from NOTIFICATIONTB ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt("i");
			}
		} catch (SQLException e) {
//			logger.error(e);
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.database.NotificationDAO#insert(java.util.Collection)
	 */
	public void insert(Collection c) throws Exception {
		// TODO Auto-generated method stub
		for (Iterator iter = c.iterator(); iter.hasNext();) {
			Notification n = (Notification) iter.next();
			insert(n);
		}
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.database.NotificationDAO#getService()
	 */
	public MSATServiceLocator getService() {
		// TODO Auto-generated method stub
		return services;
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.database.NotificationDAO#insert(java.sql.Connection, th.co.msat.motor.database.vo.Notification)
	 */
	public void insert( Notification notification) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		try{
			conn = getConnection();
			insert(conn,notification);
		}catch(Exception e){
//			logger.error(e);
			throw e;
		}finally{
			if(conn!=null)
				conn.close();
		}
		
	}
	
	private Connection getConnection() throws MSATServiceLocatorException{
		return services
		.getConnection(MSATServiceLocator.MSATLIB);

	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.database.NotificationDAO#update(th.co.msat.motor.database.vo.Notification)
	 */
	public void update(Notification notification) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		try{
			update(conn,notification);
		}catch(Exception e){
//			logger.error(e);
			throw e;
		}finally{
			if(null!=conn)
				conn.close();
		}
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.database.NotificationDAO#update(java.sql.Connection, th.co.msat.motor.database.vo.Notification)
	 */
	public void update(Connection conn, Notification n) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" update NOTIFICATIONTB ");
		sql.append(" set ACCIDENTDATE=? , ");
		sql.append("	ACCIDENTTIME=?  , ");
		sql.append("	NOTIFICATIONDATE=?  , ");
		sql.append("	NOTIFICATIONTIME =? , ");
		sql.append("	NOTIFICATIONNAME=?  , ");
		sql.append("	TELEPHONE =? , ");
		sql.append("	ACCIDENTPLACE =? , ");
		sql.append("	DESCRIPTIONLINE1 =?, ");
		sql.append("	DESCRIPTIONLINE2 =?, ");
		sql.append("	DESCRIPTIONLINE3 =? , ");
		sql.append("	DESCRIPTIONLINE4 =? , ");
		sql.append("	DESCRIPTIONLINE5 =? , ");
		sql.append("	BRANCH =? , ");
		sql.append("	REFERENCEPOLICY =? , ");
		sql.append("	RISKNO  =?, ");
		sql.append("	POLICYNO =? , ");
		sql.append("	CARREGISTERNO =? , ");
		sql.append(" INSURENAME =? , ");
		sql.append(" CARBRAND =? , ");
		sql.append("INCEPTIONDATE =?  ,  ");
		sql.append("EXPIRYDATE  =?, ");
		sql.append("DRIVERNAME  =?, ");
		sql.append("SURVEYORNAME  =?, ");
		sql.append("SURVEYOROFFICE   =?, ");
		sql.append("ASSIGNDATE =? , ");
		sql.append("ESTIMATEDATE  =? , ");
		sql.append("TRANSACTIONSTATUS  =? , ");
		sql.append("COMPLETEDDATE   =? ");
//		sql.append("TRANSACTIONINDEX   ");
		sql.append(" where NOTIFICATIONNO = ?" );
		PreparedStatement pstmt = null;
		try {
			int index = getMaxNotificationIndex(conn);
			pstmt = conn.prepareStatement(sql.toString());
			int i=1;
			
			pstmt.setInt(i++,n.getAccidentDate());
			pstmt.setInt(i++,n.getAccidentTime());
			pstmt.setInt(i++,n.getNotificationDate());
			pstmt.setInt(i++,n.getNotificationTime());
			pstmt.setString(i++,nullToString(n.getNotificationName()).trim());
			pstmt.setString(i++,correctPhoneNumber(n.getTelephone()));
			pstmt.setString(i++,nullToString(n.getAccidentPlace()).trim());
			pstmt.setString(i++,nullToString(n.getDescriptionLine1()).trim());
			pstmt.setString(i++,nullToString(n.getDescriptionLine2()).trim());
			pstmt.setString(i++,nullToString(n.getDescriptionLine3()).trim());
			pstmt.setString(i++,nullToString(n.getDescriptionLine4()).trim());
			pstmt.setString(i++,nullToString(n.getDescriptionLine5()).trim());
			pstmt.setString(i++,nullToString(n.getBranch()).trim());
			pstmt.setString(i++,nullToString(n.getReferencePolicy()).trim());
			pstmt.setInt(i++,n.getRiskNo());
			pstmt.setString(i++,nullToString(n.getPoicyNo()).trim());
			pstmt.setString(i++,nullToString(n.getCarRegisterNo()).trim());
			pstmt.setString(i++,nullToString(n.getInsurename()).trim());
			pstmt.setString(i++,nullToString(n.getCarBrand()).trim());
			pstmt.setInt(i++,n.getInceptionDate());
			pstmt.setInt(i++,n.getExpireDate());
			pstmt.setString(i++,nullToString(n.getDriverName()).trim());
			pstmt.setString(i++,nullToString(n.getSurveyorName()).trim());
			pstmt.setString(i++,nullToString(n.getSurveyorOffice()).trim());
			pstmt.setInt(i++,n.getAssignDate());
			pstmt.setInt(i++,n.getEstimateDate());
			pstmt.setString(i++,nullToString(n.getTransactionStatus()).trim());
			pstmt.setInt(i++,n.getCompletedDate());
//			pstmt.setInt(i++,index);
			pstmt.setString(i++,nullToString(n.getNotificationNo()).trim());
			if(0==pstmt.executeUpdate()){
//				logger.error(" Record cannot be insert:"+n.getNotificationNo());
			}
		} catch (Exception e) {
//			logger.error(e);
			throw e;
		} finally {
			if (null != pstmt)
				pstmt.close();
//			if (null != conn)
//				conn.close();
		}
		
	}
	
	private static String nullToString(String s){
		return null==s? "":s;
	}
	/*public List searchNotification(String no,String policy) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from notificationtb ");
		sql.append(" where TRANSACTIONSTATUS = 'C' ");
		if(null!=no&&!"".equals(no)){
			sql.append(" and NOTIFICATIONNO "+SQLStringUtil.convertToLikeCommand(no));
		}
		if(null!=policy&&!"".equals(policy)){
			sql.append(" and POLICYNO "+SQLStringUtil.convertToLikeCommand(policy));
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try{
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				Map m = new HashMap();
				m.put("NOTIFICATIONNO",rs.getString("NOTIFICATIONNO"));
				m.put("ACCIDENTDATE",DateUtil.dateToStringFromDB(rs.getString("ACCIDENTDATE")
						,rs.getString("ACCIDENTTIME"),"dd-MM-yyyy : HH:mm:ss",Locale.US));
				m.put("NOTIFICATIONDATE",DateUtil.dateToStringFromDB(rs.getString("NOTIFICATIONDATE")
						,rs.getString("NOTIFICATIONTIME"),"dd-MM-yyyy : HH:mm:ss",Locale.US));
				m.put("POLICYNO",rs.getString("POLICYNO"));
				m.put("ACCIDENTPLACE",rs.getString("ACCIDENTPLACE"));
				m.put("TRANSACTIONINDEX",rs.getString("TRANSACTIONINDEX"));
				list.add(m);
			}
			
		}catch(Exception e){
			logger.error(e);
			throw e;
		}finally{
			if(null!=pstmt){
				pstmt.close();
			}
			if(null!=conn){
				conn.close();
			}
		}
		return list;
	}*/
	
	public int updateRegisterDataWithSurvery(String policyNo
			,int riskNo
			,String province
			,String natureOfLossCode
			,float n999
			,float v100
			,String notificationno
			,boolean updateO
			,String surveryCode) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update notificationtb set regis_policyno = '"+policyNo.trim()+"' ");
		sql.append(" , regis_province = '"+province.trim()+"' ");
		sql.append(" , regis_nofloss =  '"+natureOfLossCode.trim()+"' ");
		sql.append(" , surveyfee = "+n999+" ");
		sql.append(" , od = "+v100+" ");
		sql.append(" , regis_riskno = "+riskNo+" ");
		sql.append(" , SURVEYORCODE = '"+surveryCode+"' ");
		if(updateO)
			sql.append(" , transactionstatus = 'C' ");
		sql.append(" where notificationno = '"+notificationno.trim()+"' ");
		int i =1;
		System.out.println(sql.toString());
		Connection conn = null;
		PreparedStatement pstmt= null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(i++,policyNo.trim());
//			pstmt.setString(i++,province.trim());
//			pstmt.setString(i++,natureOfLossCode.trim());
//			pstmt.setFloat(i++,n999);
//			pstmt.setFloat(i++,v100);
//			pstmt.setInt(i++,riskNo);
//			pstmt.setString(i++,notificationno.trim());
			return pstmt.executeUpdate();
		
			
		} catch (MSATServiceLocatorException e) {
//			logger.error(e);
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
			
		}
	}
	
	public int updateRegisterData(String policyNo,int riskNo,String province,String natureOfLossCode,float n999,float v100,String notificationno,boolean updateO) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update notificationtb set regis_policyno = '"+policyNo.trim()+"' ");
		sql.append(" , regis_province = '"+province.trim()+"' ");
		sql.append(" , regis_nofloss =  '"+natureOfLossCode.trim()+"' ");
		sql.append(" , surveyfee = "+n999);
		sql.append(" , od = "+v100);
		sql.append(" , regis_riskno = "+riskNo);
		if(updateO)
			sql.append(" , transactionstatus = 'C' ");
		sql.append(" where notificationno = '"+notificationno.trim()+"' ");
		int i =1;
		System.out.println(sql.toString());
		Connection conn = null;
		PreparedStatement pstmt= null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(i++,policyNo.trim());
//			pstmt.setString(i++,province.trim());
//			pstmt.setString(i++,natureOfLossCode.trim());
//			pstmt.setFloat(i++,n999);
//			pstmt.setFloat(i++,v100);
//			pstmt.setInt(i++,riskNo);
//			pstmt.setString(i++,notificationno.trim());
			return pstmt.executeUpdate();
		
			
		} catch (MSATServiceLocatorException e) {
//			logger.error(e);
			throw e;
		}finally{
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
			
		}
	}
	
	public Collection searchAllUnscrapRecord() throws Exception{
		Collection c = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from NOTIFICATIONTB where TRANSACTIONSTATUS = 'C'  ");
		sql.append(" and REGIS_POLICYNO <> '' ");
		sql.append(" and REGIS_NOFLOSS <> '' ");
		sql.append(" and REGIS_PROVINCE <> '' ");
		sql.append(" and REGIS_RISKNO <> 0 ");
		sql.append(" and claimno not like 'BT%' ");
		sql.append(" and  is_scrip is null ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		try{
			conn = getConnection();
			Notification n = null;
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				n = new Notification();
				n.setNotificationNo(rs.getString("NOTIFICATIONNO"    ));
				n.setAccidentDate(rs.getInt("ACCIDENTDATE"         ));
				n.setAccidentTime(rs.getInt("ACCIDENTTIME"         ));
				n.setNotificationDate(rs.getInt("NOTIFICATIONDATE"     ));
				n.setNotificationTime(rs.getInt("NOTIFICATIONTIME"     ));
				n.setNotificationName(rs.getString("NOTIFICATIONNAME"  ));
				n.setTelephone(rs.getString("TELEPHONE"         ));
				n.setAccidentPlace(rs.getString("ACCIDENTPLACE"     ));
				n.setDescriptionLine1(rs.getString("DESCRIPTIONLINE1"  ));
				n.setDescriptionLine2(rs.getString("DESCRIPTIONLINE2"  ));
				n.setDescriptionLine3(rs.getString("DESCRIPTIONLINE3"  ));
				n.setDescriptionLine4(rs.getString("DESCRIPTIONLINE4"  ));
				n.setDescriptionLine5(rs.getString("DESCRIPTIONLINE5"  ));
				n.setBranch(rs.getString("BRANCH"            ));
				n.setReferencePolicy(rs.getString("REFERENCEPOLICY"   ));
				n.setRiskNo(rs.getInt("RISKNO"            ));
				n.setPoicyNo(rs.getString("POLICYNO"          ));
				n.setCarRegisterNo(rs.getString("CARREGISTERNO"     ));
				n.setInsurename(rs.getString("INSURENAME"        ));
				n.setCarBrand(rs.getString("CARBRAND"          ));
				n.setInceptionDate(rs.getInt("INCEPTIONDATE"        ));
				n.setExpireDate(rs.getInt("EXPIRYDATE"           ));
				n.setDriverName(rs.getString("DRIVERNAME"        ));
				n.setSurveyorName(rs.getString("SURVEYORNAME"      ));
				n.setSurveyorOffice(rs.getString("SURVEYOROFFICE"    ));
				n.setAssignDate(rs.getInt("ASSIGNDATE"           ));
				n.setEstimateDate(rs.getInt("ESTIMATEDATE"         ));
				n.setTransactionStatus(rs.getString("TRANSACTIONSTATUS" ));
				n.setCompletedDate(rs.getInt("COMPLETEDDATE"        ));
				n.setTransactionIndex(rs.getInt("TRANSACTIONINDEX"));
				n.setRegisPolicyNo(rs.getString("REGIS_POLICYNO"));
				n.setRegisNatureOfLoss(rs.getString("REGIS_NOFLOSS"));
				n.setRegisProvinceCode(rs.getString("REGIS_PROVINCE"));
				n.setRegisRiskNo(rs.getInt("REGIS_RISKNO"));
				n.setSurveryFee(rs.getFloat("SURVEYFEE"));
				n.setClaimNo(rs.getString("CLAIMNO"));
			
				n.setOd(rs.getFloat("OD"));
				n.setIsScrap(rs.getString("is_scrip"));
				c.add(n);
			}
		}catch(Exception e){
			e.printStackTrace();
//			logger.error(e);
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.database.NotificationDAO#updateClaimNo(java.lang.String, java.lang.String)
	 */
	public int updateClaimNo(String notificationNo, String claimNo) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" update NOTIFICATIONTB set claimno = ? where NOTIFICATIONNO = ? ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1 ,claimNo.trim());
			pstmt.setString(2,notificationNo);
			return pstmt.executeUpdate();
		}catch(Exception e){
//			logger.error(e);
			throw e;
		}finally{
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.database.NotificationDAO#getRegisteredClaim(long)
	 */
	public Collection getRegisteredClaim(long jobgroup) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		//Start Re-tune SQL Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		/*sql.append(" select n.notificationno,n.claimno,n.regis_nofloss,n.regis_policyno,na.description,log.logmessage,j.status as jstatus from jobtb j ");

		sql.append(" left join notificationtb n on trim(n.notificationno) = trim(j.referenceno)  ");

		sql.append(" left join natureoflosscodetb na on trim(na.code) = trim(n.regis_nofloss)  ");
		sql.append(" left join( ");
		sql.append(" select b.logid,b.logindex,a.logmessage from  ( ");
		sql.append(" select  substr(t.logrecordid,1,10) as logid , max(logindex) as logindex ");
		sql.append("    from tranlogtb t ");
		sql.append(" group by  substr(t.logrecordid,1,10) ");
		sql.append("  ) b ");
		sql.append(" left outer join ( ");
		sql.append(" select substr(t.logrecordid,1,10) as logid ,logmessage,logindex ");
		sql.append("     from tranlogtb t) a ");
		sql.append(" on a.logid = b.logid ");
		sql.append(" and a.logindex = b.logindex ");
		sql.append(" ) log  on log.logid = n.notificationno ");
		sql.append(" where j.jobgroup = ? and j.queueid = 1 ");*/
		//Start Re-tune SQL Veerasak Boonchern 2009/11/05[yyyy/mm/dd]
		/*sql.append("with job as" +
					" (" +
						" select j.status" +
							" , TRIM(j.referenceno) AS referenceno" +
						" from jobtb j" +
						" where j.jobgroup = ?" +
							" and j.queueid = 1" +
					" )" +
					" , noti as" +
					" (" +
						" select TRIM(noti.notificationno) AS notificationno" +
							" , noti.claimno" +
							" , TRIM(noti.regis_nofloss) AS regis_nofloss" +
							" , noti.regis_policyno" +
						" from notificationtb noti" +
					" )" +
					" , natu as" +
					" (" +
						" select natu.description" +
							" , trim(natu.code) as code" +
						" from natureoflosscodetb natu" +
					" )" +
					" , log as" +
					" (" +
						" select substr(t.logrecordid,1,10) as logid" +
							" , t.logmessage" +
							" , t.logindex" +
						" from tranlogtb t" +
					" )" +
					" select n.notificationno" +
						" , n.claimno" +
						" , n.regis_nofloss" +
						" , n.regis_policyno" +
						" , nt.description" +
						" , l.logmessage" +
						" , j.status" +
					" from job j" +
						" left outer join noti n" +
						" on n.notificationno = j.referenceno" +
						" left outer join natu nt" +
						" on nt.code = n.regis_nofloss" +
						" left outer join" +
						" (" +
							" select b.logid" +
								" , b.logindex" +
								" , a.logmessage" +
							" from" +
							" (" +
								" select logid" +
									" , max(logindex) as logindex" +
								" from log" +
								" group by logid" +
							" ) b" +
								" left outer join" +
								" (" +
									" select logid" +
										" , logmessage" +
										" , logindex" +
									" from log" +
								" ) a" +
								" on a.logid = b.logid" +
									" and a.logindex = b.logindex" +
						" ) l" +
						" on l.logid = n.notificationno");*/
		//Start Remove log Veerasak Boonchern Aey 2010/02/03[yyyy/mm/dd]
		/*sql.append("WITH main AS (" +
						" SELECT j.*" +
							", nt.*" +
							", (" +
								" SELECT na.description" +
								" FROM natureoflosscodetb na" +
								" WHERE TRIM(na.code) = TRIM(nt.regis_nofloss)" +
							" ) AS description" +
						", (" +
							" SELECT MAX(t.LOGINDEX) AS mxindex" +
							" FROM tranlogtb t" +
							" WHERE SUBSTR(t.logrecordid,1,10) = nt.notificationno" +
						" )" +
						" FROM" +
						" (" +
							" SELECT j.status AS status" +
								", TRIM(j.referenceno) AS referenceno" +
							" FROM jobtb j" +
							" WHERE j.JOBGROUP = ?" +
								" AND j.queueid = 1" +
						" ) j" +
						" LEFT OUTER JOIN" +
						" (" +
							" SELECT n.claimno" +
								", n.regis_nofloss" +
								", n.regis_policyno" +
								", TRIM(n.notificationno) AS notificationno" +
							" FROM notificationtb n" +
							", (" +
								" SELECT TRIM(j.referenceno) AS referenceno" +
								" FROM jobtb j" +
								" WHERE j.JOBGROUP = ?" +
									" AND j.queueid = 1" +
							" ) j" +
							" WHERE TRIM(n.notificationno) = j.referenceno" +
						" ) nt ON nt.NOTIFICATIONNO = j.referenceno" +
					" )" +
					" SELECT mn.*" +
						", (" +
							" SELECT tl.LOGMESSAGE" +
							" FROM tranlogtb tl" +
							" WHERE tl.LOGINDEX = mn.mxindex" +
						" )" +
					" FROM main mn");*/
		sql.append("WITH job AS (" +
						" SELECT j.status AS status" +
							" , TRIM(j.referenceno) AS referenceno" +
						" FROM jobtb j" +
						" WHERE j.queueid = 1" +
							" AND j.JOBGROUP = ?" +
					" )" +
					
					" SELECT j.*" +
						", nt.claimno" +
						", nt.regis_nofloss" +
						", nt.regis_policyno" +
						", TRIM(nt.notificationno) AS notificationno" +
						", (" +
							" SELECT na.description" +
							" FROM natureoflosscodetb na" +
							" WHERE TRIM(na.code) = TRIM(nt.regis_nofloss)" +
							" ) AS description" +
					" FROM job j" +
						" LEFT OUTER JOIN notificationtb nt" +
							" ON nt.NOTIFICATIONNO = j.referenceno");
		//End Remove log Veerasak Boonchern Aey 2010/02/03[yyyy/mm/dd]
		//End Re-tune SQL Veerasak Boonchern 2009/11/05[yyyy/mm/dd]
		//End Re-tune SQL Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		Collection c = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		System.out.println(sql.toString());
		try{
			conn = getConnection();
			RegisteredClaim n = null;
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1,jobgroup);
			//Start Re-tune SQL Veerasak Boonchern 2009/11/05[yyyy/mm/dd]
			//Start Remove log Veerasak Boonchern Aey 2010/02/03[yyyy/mm/dd]
			//pstmt.setLong(2,jobgroup);
			//End Remove log Veerasak Boonchern Aey 2010/02/03[yyyy/mm/dd]
			//End Re-tune SQL Veerasak Boonchern 2009/11/05[yyyy/mm/dd]
			rs = pstmt.executeQuery();
			while(rs.next()){
				n = new RegisteredClaim();
				n.setNotificationNo(rs.getString("NOTIFICATIONNO"    ));
				n.setRegisPolicyNo(rs.getString("REGIS_POLICYNO"));
				n.setRegisNatureOfLoss(rs.getString("REGIS_NOFLOSS"));
				n.setClaimNo(rs.getString("CLAIMNO"));
				n.setDescriptionOfLoss(rs.getString("description"));
				//Start Remove log Veerasak Boonchern Aey 2010/02/03[yyyy/mm/dd]
				//n.setLogMessage(rs.getString("logmessage"));
				//End Remove log Veerasak Boonchern Aey 2010/02/03[yyyy/mm/dd]
				n.setJobStatus(rs.getString("status"));
				c.add(n);
			}
		}catch(Exception e){
			e.printStackTrace();
//			logger.error(e);
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return c;
	}
	
	public String getClaimNo(int index) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from notificationtb ");
		sql.append(" where transactionindex = ? ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1,index);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString("claimno");
			}
			
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.database.NotificationDAO#getUnAdjustClaim()
	 */
	public Collection getUnAdjustClaim() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from notificationtb ");
		sql.append(" where is_scrip is null ");
		sql.append(" and (regis_policyno is null or regis_policyno = '') ");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection c = new ArrayList();
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				Notification n = new Notification();
				n = new Notification();
				n.setNotificationNo(rs.getString("NOTIFICATIONNO"    ));
				n.setAccidentDate(rs.getInt("ACCIDENTDATE"         ));
				n.setAccidentTime(rs.getInt("ACCIDENTTIME"         ));
				n.setNotificationDate(rs.getInt("NOTIFICATIONDATE"     ));
				n.setNotificationTime(rs.getInt("NOTIFICATIONTIME"     ));
				n.setNotificationName(rs.getString("NOTIFICATIONNAME"  ));
				n.setTelephone(rs.getString("TELEPHONE"         ));
				n.setAccidentPlace(rs.getString("ACCIDENTPLACE"     ));
				n.setDescriptionLine1(rs.getString("DESCRIPTIONLINE1"  ));
				n.setDescriptionLine2(rs.getString("DESCRIPTIONLINE2"  ));
				n.setDescriptionLine3(rs.getString("DESCRIPTIONLINE3"  ));
				n.setDescriptionLine4(rs.getString("DESCRIPTIONLINE4"  ));
				n.setDescriptionLine5(rs.getString("DESCRIPTIONLINE5"  ));
				n.setBranch(rs.getString("BRANCH"            ));
				n.setReferencePolicy(rs.getString("REFERENCEPOLICY"   ));
				n.setRiskNo(rs.getInt("RISKNO"            ));
				n.setPoicyNo(rs.getString("POLICYNO"          ));
				n.setCarRegisterNo(rs.getString("CARREGISTERNO"     ));
				n.setInsurename(rs.getString("INSURENAME"        ));
				n.setCarBrand(rs.getString("CARBRAND"          ));
				n.setInceptionDate(rs.getInt("INCEPTIONDATE"        ));
				n.setExpireDate(rs.getInt("EXPIRYDATE"           ));
				n.setDriverName(rs.getString("DRIVERNAME"        ));
				n.setSurveyorName(rs.getString("SURVEYORNAME"      ));
				n.setSurveyorOffice(rs.getString("SURVEYOROFFICE"    ));
				n.setAssignDate(rs.getInt("ASSIGNDATE"           ));
				n.setEstimateDate(rs.getInt("ESTIMATEDATE"         ));
				n.setTransactionStatus(rs.getString("TRANSACTIONSTATUS" ));
				n.setCompletedDate(rs.getInt("COMPLETEDDATE"        ));
				n.setTransactionIndex(rs.getInt("TRANSACTIONINDEX"));
				n.setRegisPolicyNo(rs.getString("REGIS_POLICYNO"));
				n.setRegisNatureOfLoss(rs.getString("REGIS_NOFLOSS"));
				n.setRegisProvinceCode(rs.getString("REGIS_PROVINCE"));
				n.setRegisRiskNo(rs.getInt("REGIS_RISKNO"));
				n.setSurveryFee(rs.getFloat("SURVEYFEE"));
				n.setClaimNo(rs.getString("CLAIMNO"));
			
				n.setOd(rs.getFloat("OD"));
				n.setIsScrap(rs.getString("is_scrip"));
				c.add(n);
			}
			
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return c;
	}
	
	
	
	
	public Collection searchUnAdjustClaim(String notino,boolean isOld) throws Exception {
//		 TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from notificationtb ");
		sql.append(" where 1=1 ");
		if(!"".equals(notino)){
			sql.append(" and notificationno like '%"+notino+"%' ");
		}else{
			if(isOld){
				sql.append(" and is_scrip is null ");
				sql.append(" and (regis_policyno is null or regis_policyno = '') ");
			}else{
				sql.append(" and is_scrip is null ");
				sql.append(" and (regis_policyno is not null and regis_policyno <> '') ");
			}
		}
		System.out.println("::::::::::::::::::::::::::::::::::::");
		System.out.println(sql.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection c = new ArrayList();
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				Map m = new HashMap();
				m.put("notificationNo",rs.getString("notificationno"));
				m.put("notificationDateString",DataConverter.toString(rs.getBigDecimal("notificationdate"),rs.getBigDecimal("notificationtime")));
				m.put("accidentDateString",DataConverter.toString(rs.getBigDecimal("accidentdate"),rs.getBigDecimal("accidenttime")));
				m.put("policyNo",rs.getString("policyno"));
				m.put("riskNo",rs.getString("riskno"));
				m.put("carRegisterNo",rs.getString("carRegisterNo"));
				m.put("insurename",rs.getString("insurename"));
				m.put("inceptionDateString",DataConverter.toString(rs.getBigDecimal("inceptiondate")));
				m.put("expireDateString",DataConverter.toString(rs.getBigDecimal("expirydate")));
				c.add(m);
			}
			
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return c;
	}
	
	
	public Collection getSurveyorList() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from  index_detail ");
		sql.append(" where record_id = 5  ");
		sql.append(" and indextext = '1' ");
		
		Collection c = new ArrayList();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				Map m = new HashMap();
				m.put("DESC",rs.getString("DESC").trim());
				m.put("CODE",rs.getString("index_detail_id").trim());
				c.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return c;
	}
	
	public static String correctPhoneNumber(String s){
		s = nullToString(s);
		s = s.length()>20? s.substring(0,20):s;
		s = isValidPhoneNumber(s)? s :"";
		return s;
	}
	
	public static boolean isValidPhoneNumber(String s){
		String pattern = "[\\d]+[\\-][\\d]+";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		return m.matches();
	}


}
