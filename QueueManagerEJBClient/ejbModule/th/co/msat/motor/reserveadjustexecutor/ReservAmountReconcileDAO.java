/*
 * Created on 15 ¾.¤. 2552
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.reserveadjustexecutor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import th.co.msat.motor.services.MSATServiceLocator;
//import th.co.msitb.core.services.MSITBServiceLocator;









/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReservAmountReconcileDAO {
//	String datefrom = "20090727";
//	String dateto = "40000101";
//	String output = "D:/Testse/Reserve_"+datefrom+".xls";
	
	final static short CELL_BT = 0;
	final static short CELL_CLAIM = 1;
	final static short CELL_CODE = 2;
	final static short CELL_AUTHORIZE_AMOUNT = 3;
	final static short CELL_GEN400_AMOUNT = 4;
	final static short CELL_DIFF = 5;
	final static String FIRST_RESERVE = "00";
	final static String ADJ_RESERVE = "01";

	public ReservAmountReconcileDAO() throws Exception{
		
	}


	String getAmountColumnFromMaster(String claimno,String code,Connection conn) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append(" case when crs_rec_code1 = '"+code.trim()+"' then 1 ");
		sql.append("        when crs_rec_code2 = '"+code.trim()+"' then 2 ");
		sql.append("        when crs_rec_code3 = '"+code.trim()+"' then 3 ");
		sql.append("        when crs_rec_code4 = '"+code.trim()+"' then 4 ");
		sql.append("        when crs_rec_code5 = '"+code.trim()+"' then 5 ");
		sql.append("        when crs_rec_code6 = '"+code.trim()+"' then 6 ");
		sql.append(" end as column_code ");
		sql.append(" from  mmflib.imclaimrsp ");
		sql.append(" where crs_br = '"+StringUtils.getClaimBranch(claimno)+"' ");
		sql.append(" and crs_cat = '"+StringUtils.getClaimCategory(claimno)+"' ");
		sql.append(" and crs_class = '"+StringUtils.getClaimClasses(claimno)+"' ");
		sql.append(" and crs_year =  "+StringUtils.getClaimYear(claimno));
		sql.append(" and crs_number =  "+StringUtils.getClaimNo(claimno));
		sql.append(" and crs_tr_type in ('00','01') ");
		
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try{
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				if(0!= rs.getInt(1)){
					return ""+rs.getInt(1);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
		}
		return null;
		
	}
	
	
	private List getListByDate(String dateFrom,String dateTo,Connection conn){
		return null;
	}


	BigDecimal getReservAmountFromMaster(String columnCode,String claimno, String code,Connection conn) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(crs_amount"+columnCode+") ");
		sql.append(" from  mmflib.imclaimrsp ");
		sql.append(" where crs_br = '"+StringUtils.getClaimBranch(claimno)+"' ");
		sql.append(" and crs_cat = '"+StringUtils.getClaimCategory(claimno)+"' ");
		sql.append(" and crs_class = '"+StringUtils.getClaimClasses(claimno)+"' ");
		sql.append(" and crs_year =  "+StringUtils.getClaimYear(claimno));
		sql.append(" and crs_number =  "+StringUtils.getClaimNo(claimno));
		sql.append(" and crs_rec_code"+columnCode+" = '"+code+"' ");
		sql.append(" and crs_tr_type in ('00','01') ");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try{
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				return rs.getBigDecimal(1);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
		}
		
		
		return new BigDecimal(0);
	}
	
	private List getListByClaimNo(String claimFrom,String claimTo,Connection conn) throws Exception{	
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append("  distinct(fs.claim_no) ");
		sql.append("  from claim_party_motor cpm        ");  
		sql.append("  left join  claim_loss_motor clm  on cpm.PARTY_ID = clm.REFER_PARTY_ID     ");
		sql.append("  inner join claim_payee cp on clm.REFER_PAYEE_ID = cp.PAYEE_ID  ");
		sql.append("  left join index_detail id on cp.PAYEE_CODE = id.code400  ");
		sql.append("   left join coverpagetb fs on cpm.notificationno = fs.NOTIFICATIONNO ");
		sql.append("   where fs.CLAIM_NO >= '"+claimFrom+"' and fs.CLAIM_NO <=  '"+claimTo+"' ");
		  
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try{
			System.out.println(sql.toString());
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
			
				list.add(rs.getString("claim_no"));
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=rs)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(null!=pstmt)
				try {
					pstmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
		}
		return list;
	}
	
	/*private List getReservadjustNotiList(Connection conn) throws Exception{

		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct(refer_id) from tbl_action_q ");
		sql.append(" where status = 'C' ");
		sql.append(" and action_id = 2  ");
		sql.append(" and assign_date = "+datefrom );
//		sql.append(" and assign_date < "+dateto);
//		sql.append(" fetch first 10 row only ");
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try{
			System.out.println(sql.toString());
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				Map m = new HashMap();
				m.put("refer_id",rs.getString("refer_id"));
//				m.put("assign_date",rs.getString("assign_date"));
//				m.put("assign_time",rs.getString("assign_time"));
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
		}
		return list;
		
	}*/
	
	private String getClaimno(String notino,Connection conn) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select claim_no from coverpagetb where notificationno = '"+notino+"' and record_status = 'Y' ");
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try{
			System.out.println(sql.toString());
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				return rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
		}
		return null;
	}
	
	
	
	

	public BigDecimal getReservAmount(String claimno, String code) throws Exception {
		BigDecimal daily = null;
		try{
			daily = getReservAmountFromDaily(claimno,code);
			if(null==daily)
				daily = new BigDecimal(0);
		}catch(Exception e){
			System.err.println("getReserveAmount from GEN400 exception " + e.getMessage());
			daily = new BigDecimal(0);
		}

		return daily.setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	public BigDecimal getReservAmountFromDaily(String claimno, String code) throws Exception {
		StringBuffer sql = new StringBuffer();
		BigDecimal result = new BigDecimal(0);
		sql.append(" select * ");
		sql.append(" from  mmflib.idclaimrsp ");
		sql.append(" where crsd_br = '"+StringUtils.getClaimBranch(claimno)+"' ");
		sql.append(" and crsd_cat = '"+StringUtils.getClaimCategory(claimno)+"' ");
		sql.append(" and crsd_class = '"+StringUtils.getClaimClasses(claimno)+"' ");
		sql.append(" and crsd_year =  "+StringUtils.getClaimYear(claimno));
		sql.append(" and crsd_number =  "+StringUtils.getClaimNo(claimno));
		//sql.append(" and crsd_rec_code"+columnCode+" = '"+code+"' ");
		sql.append(" and crsd_tr_type in ('00','01') ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try{
			
			conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MMFLIB);
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			BigDecimal newValue = null;
			BigDecimal existedValue = null;
			System.err.println("Try to getReserveAmountFromDaily :"+claimno+":"+code);
			while(rs.next()){
				for (int i = 1 ;i<=6;i++){
					if(rs.getString("crsd_rec_code"+i).trim().equals(code.trim())){
						newValue = rs.getBigDecimal("crsd_amount"+i);
						existedValue = rs.getBigDecimal("crsd_exist_res"+i);
						if(newValue == null) newValue = new BigDecimal(0);
						if(existedValue == null) existedValue = new BigDecimal(0);
						if(rs.getString("crsd_tr_type").trim().equals(FIRST_RESERVE)){
							result = newValue.setScale(2);
						}else{
							result = existedValue.setScale(2).add(newValue.setScale(2));
						}
						double db_newValue = newValue.setScale(2).doubleValue();
						double db_existedValue = existedValue.setScale(2).doubleValue();
						System.err.println("Try to getReserveAmountFromDaily newValue "+db_newValue);
						System.err.println("Try to getReserveAmountFromDaily existedValue "+ db_existedValue);
					}
				}
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
		return result;
	}
	
	String getAmountColumn(String claimno,String code,Connection conn) throws Exception{
		//String master = getAmountColumnFromMaster(claimno,code,conn);
		String daily = getAmountColumnFromDaily(claimno,code);
		
		//return null==master? daily:master;
		return daily;
	}
	
	String getAmountColumnFromDaily(String claimno,String code) throws Exception{
		StringBuffer sql = new StringBuffer();
		//sql.append(" select ");
		/*sql.append(" case when crsd_rec_code1 = '"+code.trim()+"' then 1 ");
		sql.append("        when crsd_rec_code2 = '"+code.trim()+"' then 2 ");
		sql.append("        when crsd_rec_code3 = '"+code.trim()+"' then 3 ");
		sql.append("        when crsd_rec_code4 = '"+code.trim()+"' then 4 ");
		sql.append("        when crsd_rec_code5 = '"+code.trim()+"' then 5 ");
		sql.append("        when crsd_rec_code6 = '"+code.trim()+"' then 6 ");
		sql.append(" end as column_code ");*/
		sql.append(" select * ");
		sql.append(" from  mmflib.idclaimrsp ");
		sql.append(" where crsd_br = '"+StringUtils.getClaimBranch(claimno)+"' ");
		sql.append(" and crsd_cat = '"+StringUtils.getClaimCategory(claimno)+"' ");
		sql.append(" and crsd_class = '"+StringUtils.getClaimClasses(claimno)+"' ");
		sql.append(" and crsd_year =  "+StringUtils.getClaimYear(claimno));
		sql.append(" and crsd_number =  "+StringUtils.getClaimNo(claimno));
		sql.append(" and crsd_tr_type in ('00','01') ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		String result = "";
		try{
			conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MMFLIB);
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				for (int i = 1 ;i<=6;i++){
					if(rs.getString("crsd_rec_code"+i).trim().equals(code.trim())){
						result = ""+i;
					}
					
				}
				/*if(0!= rs.getInt(1)){
					return ""+rs.getInt(1);
				}*/
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
		
		return result;
	}
	
	public BigDecimal getReservAmountFromAuthorize(Connection conn,String claimno,String lossCode,String partyType,String indextext) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append(" cp.PAYEE_CODE  ");
		sql.append("  ,sum(clm.LOSS_ADJ_AMT) as LOSS_ADJ_AMT,clm.LOSS_CODE ,id.record_id,cpm.PARTY_TYPE ,id.INDEXTEXT ");
		sql.append("  from claim_party_motor cpm         ");
		sql.append("  left join  claim_loss_motor clm  on cpm.PARTY_ID = clm.REFER_PARTY_ID    ");
		sql.append("  inner join claim_payee cp on clm.REFER_PAYEE_ID = cp.PAYEE_ID ");
		sql.append("  left join index_detail id on cp.PAYEE_CODE = id.code400 ");
		sql.append("   left join coverpagetb co on co.NOTIFICATIONNO = cpm.NOTIFICATIONNO  ");
		sql.append("  where co.CLAIM_NO = ? ");
		sql.append("  and loss_code = ? ");
		sql.append("  and party_type = ? ");
		sql.append("  and indextext = ? ");
		sql.append("  and co.RECORD_STATUS = 'Y' ");
		sql.append(" group by cp.PAYEE_CODE ,clm.LOSS_CODE ,id.record_id,cpm.PARTY_TYPE ,id.INDEXTEXT ");
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try{
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,claimno);
			pstmt.setString(2,lossCode);
			pstmt.setString(3,partyType);
			pstmt.setString(4,indextext);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getBigDecimal("LOSS_ADJ_AMT");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=rs)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(null!=pstmt)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return null;
		
	}
   //create by tuta
	public BigDecimal getReservAmountFromMessageQueue25(String claimno,String payeeCode) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(amount)as amount from message_queue_25  ");
		sql.append(" where claim_no=? and payee_code=? ");
		System.out.println("getReservAmountFromMessageQueue25 : claimno " + claimno.trim());
		System.out.println("getReservAmountFromMessageQueue25 : payeeCode " + payeeCode.trim());
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		Connection conn = null;
		try{
			conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,claimno.trim());
			pstmt.setString(2,payeeCode.trim());
			rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println("getReservAmountFromMessageQueue25 = " + rs.getBigDecimal("amount"));
				return rs.getBigDecimal("amount").setScale(2,BigDecimal.ROUND_HALF_UP);
			}
		}catch(Exception e){
			e.printStackTrace();
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			if(null!=rs)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(null!=pstmt)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(null!=conn)
				try{
					conn.close();
				}catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		return null;
	}
	// Create by Thanaseth
	public BigDecimal getReservAmountFromMessageQueue25(BigDecimal jobid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(amount)as amount from message_queue_25  ");
		sql.append(" where jobid=? ");
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		Connection conn = null;
		try{
			conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setBigDecimal(1,jobid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				System.err.println("getReservAmountFromMessageQueue25 = " + rs.getBigDecimal("amount"));
				return rs.getBigDecimal("amount").setScale(2,BigDecimal.ROUND_HALF_UP);
			}
		}catch(Exception e){
			e.printStackTrace();
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			if(null!=rs)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(null!=pstmt)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(null!=conn)
				try{
					conn.close();
				}catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		return null;
	}
	//create by tuta
	public int messageDataQueue25Handler(String claimNo,Map m,BigDecimal jobId) throws Exception{
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlSearch = new StringBuffer();
		StringBuffer sqlUpd = new StringBuffer();
		//
		sql.append(" insert into message_queue_25( ");
		sql.append(" CLAIM_NO,");
		sql.append(" SUBMIT_DATE,SUBMIT_TIME,");
		sql.append(" OLD_PAYEE_CODE,PAYEE_CODE,");
		sql.append(" PARTY_TYPE,GROUP_ID,");
		sql.append(" LOSS_CODE,AMOUNT,JOBID )");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?)");
		//
		sqlUpd.append(" update message_queue_25 ");
		sqlUpd.append(" set amount = ? ");
		sqlUpd.append(" where jobid = ?");
		//
		sqlSearch.append(" select * from message_queue_25 ");
		sqlSearch.append(" where jobid = ?");
		int result=-1;
		Connection conn = null;
		boolean isInsert = false;
		boolean isUpdate = false;
		PreparedStatement pstmtSearch =null;
		ResultSet rs = null; 
		try{
			conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);
			pstmtSearch = conn.prepareStatement(sqlSearch.toString());
			pstmtSearch.setBigDecimal(1,jobId);
			rs = pstmtSearch.executeQuery();
			if(rs != null && rs.next()){
				isUpdate=true;
			}else{
				isInsert=true;
			}
		}catch(Exception e){
			isInsert=true;
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){rs.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if(pstmtSearch!=null){pstmtSearch.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if(conn!=null){conn.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
//		Case Insert
		if(isInsert && !isUpdate){
	    
			String dateStr = new SimpleDateFormat("yyyyMMdd",Locale.US).format(new Date());
			String timeStr=new SimpleDateFormat("HHmmss",Locale.US).format(new Date()); 
			PreparedStatement pstmt =null;
			BigDecimal payee_amount = null;
			float jobid_float = jobId.floatValue();
			System.out.println("claimNo="+claimNo);
			System.out.println("dateStr="+dateStr);
			System.out.println("timeStr="+timeStr);
			System.out.println("OLD_PAYEE_CODE="+(String)m.get("OLD_PAYEE_CODE"));
			System.out.println("PAYEE_CODE_GROUP="+(String)m.get("PAYEE_CODE_GROUP"));
			System.out.println("PARTY_TYPE="+(String)m.get("PARTY_TYPE"));
			System.out.println("GROUP_ID="+(String)m.get("GROUP_ID"));
			System.out.println("LOSS_CODE="+(String)m.get("LOSS_CODE"));
/*			if(jobid_float < 985652)
				if(m.get("PAYEE_AMOUNT") !=  null){
					payee_amount = ((BigDecimal) m.get("PAYEE_AMOUNT"));
				}else{
					payee_amount = new BigDecimal(0.00);
				}
			else{
*/				if(m.get("EXIST_AMT") !=  null){
					payee_amount = ((BigDecimal) m.get("EXIST_AMT"));
				}else{
					payee_amount = new BigDecimal(0.00);
				}
//			}
			System.out.println("PAYEE_AMOUNT="+ payee_amount.setScale(2,BigDecimal.ROUND_HALF_UP));
			//System.out.println("PAYEE_AMOUNT="+new DecimalFormat("0.00").format((BigDecimal)m.get("PAYEE_AMOUNT")));
			
			/*
			CLAIM_NO	VARCHAR	20	No
			SUBMIT_DATE	INTEGER	4	No
			SUBMIT_TIME	INTEGER	4	No
			OLD_PAYEE_CODE	VARCHAR	5	No
			PAYEE_CODE	VARCHAR	5	No
			PARTY_TYPE	VARCHAR	1	No
			GROUP_ID	INTEGER	4	No
			LOSS_CODE	VARCHAR	5	No
			AMOUNT	DECIMAL	9	No
			*/
			try{
				conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1,claimNo);
				pstmt.setInt(2,Integer.parseInt(dateStr));
				pstmt.setInt(3,Integer.parseInt(timeStr));
				pstmt.setString(4,(String)m.get("OLD_PAYEE_CODE"));
				pstmt.setString(5,(String)m.get("PAYEE_CODE_GROUP"));
				pstmt.setString(6,(String)m.get("PARTY_TYPE"));
				pstmt.setInt(7,Integer.parseInt((String)m.get("GROUP_ID")));
				pstmt.setString(8,(String)m.get("LOSS_CODE"));
				pstmt.setBigDecimal(9,payee_amount.setScale(2,BigDecimal.ROUND_HALF_UP));
				pstmt.setBigDecimal(10,jobId);
				result=pstmt.executeUpdate();
			}catch(Exception e){
				result=-1;
				e.printStackTrace();
			}finally{
				try {
					if(pstmt!=null){pstmt.close();}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					if(conn!=null){conn.close();}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		}
		//Case Update
		if(!isInsert && isUpdate){
			PreparedStatement pstmt =null;
			BigDecimal payee_amount = null;
			float jobid_float = jobId.floatValue();
			if(jobid_float < 985652)
				if(m.get("PAYEE_AMOUNT") !=  null){
					payee_amount = ((BigDecimal) m.get("PAYEE_AMOUNT"));
				}else{
					payee_amount = new BigDecimal(0.00);
				}
			else{
				if(m.get("EXIST_AMT") !=  null){
					payee_amount = ((BigDecimal) m.get("EXIST_AMT"));
				}else{
					payee_amount = new BigDecimal(0.00);
				}
			}
			System.out.println("PAYEE_AMOUNT="+ payee_amount.setScale(2,BigDecimal.ROUND_HALF_UP));
			//System.out.println("PAYEE_AMOUNT="+new DecimalFormat("0.00").format((BigDecimal)m.get("PAYEE_AMOUNT")));
			
			/*
			CLAIM_NO	VARCHAR	20	No
			SUBMIT_DATE	INTEGER	4	No
			SUBMIT_TIME	INTEGER	4	No
			OLD_PAYEE_CODE	VARCHAR	5	No
			PAYEE_CODE	VARCHAR	5	No
			PARTY_TYPE	VARCHAR	1	No
			GROUP_ID	INTEGER	4	No
			LOSS_CODE	VARCHAR	5	No
			AMOUNT	DECIMAL	9	No
			*/
			try{
				conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);
				pstmt = conn.prepareStatement(sqlUpd.toString());
				pstmt.setBigDecimal(1,payee_amount.setScale(2,BigDecimal.ROUND_HALF_UP));
				pstmt.setBigDecimal(2,jobId);
				result=pstmt.executeUpdate();
			}catch(Exception e){
				result=-1;
				e.printStackTrace();
			}finally{
				try {
					if(pstmt!=null){pstmt.close();}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					if(conn!=null){conn.close();}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		}
		return result;
	}
	public void insertReconcile(String claimno,String payeeCode,BigDecimal authAmt,BigDecimal claimRspAmt,String message,BigDecimal jobId) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into RE_CONSILE_MT_CLAIM  ");
		sql.append(" (CLAIM_NO,PAYEE_CODE,AUTH_AMOUNT,CLAIMRSP_AMOUNT,RECONCILE_DATE,MESSAGE,RECONCILE_ID) ");
		sql.append(" values (?,?,?,?,?,?,?) ");
		String dateStr = new SimpleDateFormat("yyyyMMdd",Locale.US).format(new Date());
		PreparedStatement pstmt =null;
		Connection conn = null;
		try{
			conn = MSATServiceLocator.getInstance().getConnection(MSATServiceLocator.MSATLIB);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,claimno);
			pstmt.setString(2,payeeCode);
			pstmt.setBigDecimal(3,authAmt);
			pstmt.setBigDecimal(4,claimRspAmt);
			pstmt.setInt(5,Integer.parseInt(dateStr));
			pstmt.setString(6,message);
			pstmt.setBigDecimal(7,jobId);
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
	
	
	
	
	public static void main(String a[]){
		
		 try {
			new ReservAmountReconcileDAO();
			/*try{
				HSSFWorkbook wb = new HSSFWorkbook();
			    //Workbook wb = new XSSFWorkbook();

			    HSSFSheet sheet = wb.createSheet("Sheet1");

			    // Create a row and put some cells in it. Rows are 0 based.
			    HSSFRow row = sheet.createRow((short)0);
			    // Create a cell and put a value in it.
			    HSSFCell cell = row.createCell((short)0);
			    
			    cell.setCellValue("Test");

			    // Write the output to a file
			    FileOutputStream fileOut = new FileOutputStream("D:/Testse/workbook6.xls");
			    wb.write(fileOut);
			    fileOut.close();
			}catch(Exception e){
				e.printStackTrace();
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
}
