/*
 * Created on 25 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/****************************************************/
/*----------------- Modify History -----------------*/
/****************************************************/
/*---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/07[yyyy/mm/dd]
 * @description Split queue host follow queue.host.running
 * 				in systemenv.properties
 * 				if QUEUETB.hostname = queue.host.running
 * 					run thread
 *---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/08[yyyy/mm/dd]
 * @description Queue real-time follow database
 * 				don't use server instance
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/18[yyyy/mm/dd]
 * @description Reset queue and recovery pool
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/10/19[yyyy/mm/dd]
 * @description Trim Queue name
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/10/21[yyyy/mm/dd]
 * @description Duplicate Queue tranlogtb
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/10/29[yyyy/mm/dd]
 * @description Close ResultSet when search job
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/11/04[yyyy/mm/dd]
 * @description Session Bean freeze cause
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/11/12[yyyy/mm/dd]
 * @description Reset when start
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2010/03/02[yyyy/mm/dd]
 * @description Queue Monitor
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2010/03/08[yyyy/mm/dd]
 * @description Order Queue name
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2010/03/23[yyyy/mm/dd]
 * @description Delete job by Ref.No.
 *--------------------------------------------------*/


package th.co.msat.motor.queuemanager.database;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.xerces.util.ParserConfigurationSettings;

import com.ibm.ws.tcp.channel.impl.NewConnectionInitialReadCallback;

import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.database.services.MSATServiceLocator;
import th.co.msat.motor.database.services.MSATServiceLocatorException;
import th.co.msat.motor.database.vo.TranLog;
import th.co.msat.motor.log.TranLogDAOImpl;
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.message.Message;
import th.co.msat.motor.queuemanager.vo.JobVO;
import th.co.msat.motor.queuemanager.vo.QueueVO;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
//public class JobQueueDAO implements java.io.Serializable{
public class JobQueueDAO
{
//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]

	private MSATServiceLocator services = new MSATServiceLocator();
	/**
	 * MSATLIB Connection to be use in this class
	 * @return A java.sql.Connection lookup from java:comp/env/MSATLIB 
	 * @throws MSATServiceLocatorException
	 */
	private Connection getConnection() throws MSATServiceLocatorException{
		
		return services.getConnection(MSATServiceLocator.MSATLIB);
		
	}
	
	public BigDecimal getMaxQueutbId() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select max(queueid) from queuetb ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				return (rs.getBigDecimal(1)==null? new BigDecimal(0):rs.getBigDecimal(1))
				.add(new BigDecimal(1));
			}
		} catch (MSATServiceLocatorException e) {
			// TODO Auto-generated catch block
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
		return new BigDecimal(1);
	}
	
	public  BigDecimal getMaxJobtbId()  throws Exception{
		StringBuffer sql = new StringBuffer();
		StringBuffer sql_updated = new StringBuffer();
		sql.append(" select * from jobrunning where jobtype = '1' ");
		sql_updated.append(" update jobrunning set jobrunning = jobrunning + 1 where jobtype = '1' ");
		Connection conn = null;
		Connection conn_upd = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_upd = null;
		ResultSet rs = null;
		BigDecimal result = new BigDecimal(1);
		try {
			conn = getConnection();conn_upd = getConnection();
			pstmt_upd = conn_upd.prepareStatement(sql_updated.toString());
			pstmt = conn.prepareStatement(sql.toString());
			pstmt_upd.executeUpdate();
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getBigDecimal("jobrunning");
			}
		} catch (MSATServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(null!=pstmt_upd)
				pstmt_upd.close();
			if(null!=conn_upd){
				conn_upd.close();
			}
			if(null!=conn){
				conn.close();
			}
		}
		
		return result;
	}
	
	public  BigDecimal getMaxJobtbId(String jobtype)  throws Exception{
		StringBuffer sql = new StringBuffer();
		StringBuffer sql_updated = new StringBuffer();
		sql.append(" select * from jobrunning where jobtype = '"+jobtype.trim()+"' ");
		sql_updated.append(" update jobrunning set jobrunning = jobrunning + 1 where jobtype = '"+jobtype.trim()+"' ");
		Connection conn = null;
		Connection conn_upd = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_upd = null;
		ResultSet rs = null;
		BigDecimal result = new BigDecimal(1);
		try {
			conn = getConnection();conn_upd = getConnection();
			pstmt_upd = conn_upd.prepareStatement(sql_updated.toString());
			pstmt = conn.prepareStatement(sql.toString());
			pstmt_upd.executeUpdate();
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getBigDecimal("jobrunning");
			}
		} catch (MSATServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(null!=pstmt_upd)
				pstmt_upd.close();
			if(null!=conn_upd){
				conn_upd.close();
			}
			if(null!=conn){
				conn.close();
			}
		}
		
		return result;
	}
	
	
	public String getQueueName(BigDecimal jobid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select q.queuename from jobtb j ");
		sql.append(" left join queuetb q on q.queueid = j.queueid ");
		sql.append(" where j.jobid = ? ");
		sql.append(" and j.recordstatus = '"+DefaultQueueConstants.RECORD_STATUS_AVAILABLE+"' ");
		//Start Order queue name Veerasak Boonchern Aey 2010/03/08[yyyy/mm/dd]
		sql.append(" ORDER BY q.queuename");
		//End Order queue name Veerasak Boonchern Aey 2010/03/08[yyyy/mm/dd]

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setBigDecimal(1,jobid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString(1);
			}
		}catch(Exception e){
			throw e;
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
	
	public boolean isQueueTerminate(String queueName) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select isTerminate from queuetb ");
		//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		//sql.append(" where queuename = ? ");
		sql.append(" where TRIM(queuename) = ? ");
		//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		sql.append(" and recordstatus = '"+DefaultQueueConstants.RECORD_STATUS_AVAILABLE+"' ");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
			//pstmt.setString(1,queueName);
			pstmt.setString(1,queueName.trim());
			//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString(1).equals(DefaultQueueConstants.QUEUE_TERMINATE);
			}
			
		}catch(Exception e){
			throw e;
		}finally{
			if(null!=rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return true;
	}
	
	public void updateJobStatus(Collection c,String jobstatus)throws Exception{
		if(c.size()==0)
			return ;
		StringBuffer jobidsql = new StringBuffer();
//		jobidsql.append("'");
		Iterator iter = c.iterator();
		if(iter.hasNext()){
			BigDecimal jobid = (BigDecimal) iter.next();
			jobidsql.append(jobid.toBigInteger().intValue());
//			jobidsql.append("'");
		}
		for ( c.iterator(); iter.hasNext();) {
			BigDecimal jobid = (BigDecimal) iter.next();
			jobidsql.append(",");
			jobidsql.append(jobid.toBigInteger().intValue());
//			jobidsql.append("'");
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update jobtb ");
		sql.append(" set status = ? ");
		sql.append(" where jobid in (");
		sql.append(jobidsql);
		sql.append(" ) ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,jobstatus);
			pstmt.executeUpdate();
		}catch(Exception e){
			throw e;
		}finally{
			if(null != pstmt)
				pstmt.close();
			if(null!= conn){
				conn.close();
			}
		}
	}
	
	public Map getJobStatus(Collection c) throws Exception{
		if(c == null||c.size()==0)
			return new HashMap();
		StringBuffer jobidsql = new StringBuffer();
		Iterator iter = c.iterator();
		if(iter.hasNext()){
			BigDecimal jobid = (BigDecimal) iter.next();
			jobidsql.append(jobid.toBigInteger().intValue());
		}
		for ( c.iterator(); iter.hasNext();) {
			BigDecimal jobid = (BigDecimal) iter.next();
			jobidsql.append(",");
			jobidsql.append(jobid.toBigInteger().intValue());
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select jobid,status from jobtb ");
		sql.append(" where jobid in (");
		sql.append(jobidsql);
		sql.append(" ) ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map m = new HashMap();
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				m.put(new Integer(rs.getInt("jobid")),rs.getString("status"));

		
			}
			
		}catch(Exception e){
			throw e;
		}finally{
			if(null!=rs)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(null != pstmt)
				try {
					pstmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			if(null!= conn){
				try {
					conn.close();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
		}
		return m;
	}
	
	
	public String getJobStatus(BigDecimal jobid) throws Exception{

		StringBuffer sql = new StringBuffer();
		sql.append(" select jobid,status from jobtb ");
		sql.append(" where jobid  = ? ");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1,jobid.intValue());
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString("status");
		
			}
			
		}catch(Exception e){
			throw e;
		}finally{
			if(null!=rs)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(null != pstmt)
				try {
					pstmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			if(null!= conn){
				try {
					conn.close();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
		}
		return "";
	}
	
	
	public void updateJobStatus(BigDecimal jobid,String jobstatus) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update jobtb ");
		sql.append(" set status = ? ");
		
		sql.append(" where jobid = ?  ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,jobstatus);
			pstmt.setBigDecimal(2,jobid);
			pstmt.executeUpdate();
		}catch(Exception e){
			throw e;
		}finally{
			if(null != pstmt)
				pstmt.close();
			if(null!= conn){
				conn.close();
			}
		}
	}
	public void updateJobStatusForceProcess(BigDecimal jobid,String jobstatus) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update jobtb ");
		sql.append(" set status = ? ");
		
		sql.append(" where jobid = ?  ");
		sql.append(" and status = 'R'  ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,jobstatus);
			pstmt.setBigDecimal(2,jobid);
			pstmt.executeUpdate();
		}catch(Exception e){
			throw e;
		}finally{
			if(null != pstmt)
				pstmt.close();
			if(null!= conn){
				conn.close();
			}
		}
	}
	
	public JobVO findNextJob(String queueName) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select job.* from jobtb job  ");
		sql.append(" left join queuetb q on job.queueid = q.queueid ");
		//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		//sql.append(" where q.queuename = '"+queueName+"' ");
		sql.append(" where TRIM(q.queuename) = '"+queueName.trim()+"' ");
		//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		sql.append(" and q.recordstatus  = '"+DefaultQueueConstants.RECORD_STATUS_AVAILABLE+"' ");
		sql.append(" and q.isterminate <> '"+DefaultQueueConstants.QUEUE_TERMINATE+"' " );
		sql.append(" and job.recordstatus <> '"+DefaultQueueConstants.RECORD_STATUS_CANCEL+"' ");
		//sql.append(" and q.available > 0 ");
		sql.append(" and job.status = '"+DefaultQueueConstants.JOB_STATUS_RELEASE+"' ");
		sql.append(" order by  job.jobid ");
		sql.append(" fetch first 1 rows only ");
		System.out.println(sql.toString());

		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JobVO job = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1,queueName);
//			pstmt.setString(2,DefaultQueueConstants.JOB_STATUS_RELEASE);
			rs = pstmt.executeQuery();
			if(rs.next()){
				job = new JobVO();
//				System.out.println(" ===============================================================================");
//				System.out.println("jobid:"+rs.getBigDecimal("jobid"));
				job.setJobid(rs.getBigDecimal("jobid"));
				Blob blob = rs.getBlob("message");
				if (blob != null)
				{
					job.setMessage((Message)DataConverter.toObject(blob.getBytes(1,(int) blob.length())));
				}
				job.setPiority(rs.getInt("piority"));
				job.setRecordStatus(rs.getString("recordstatus").equals(DefaultQueueConstants.JOB_STATUS_RELEASE));
				job.setStatus(rs.getString("status"));
				job.setSubmintUser(rs.getString("submituser"));
				job.setSubmitDate(DataConverter.toDate(rs.getBigDecimal("submitdate"),rs.getBigDecimal("submittime")));
				if(null!=rs.getBigDecimal("updatedate")){
					job.setUpdateDate(DataConverter.toDate(rs.getBigDecimal("updatedate"),rs.getBigDecimal("updatedate")));
				}
				job.setUpdateProgram(rs.getString("updateprogram"));
				job.setUpdateUser(rs.getString("updateuser"));
				job.setJobgroup(rs.getLong("jobgroup"));
				
				//Start Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]
				job.setReferenceno(rs.getString("referenceno"));
				//End Queue real-time Veerasak Boonchern 2009/09/08[yyyy/mm/dd]

			}
		} catch (MSATServiceLocatorException e) {
			// TODO Auto-generated catch block
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
		
		return job;
		
	}
	
	
	public Collection findAllQueue() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from queuetb ");
		sql.append(" where recordstatus <> '"+DefaultQueueConstants.RECORD_STATUS_CANCEL+"' ");
		sql.append(" ORDER BY queuename");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection c = new ArrayList();
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs =pstmt.executeQuery();
			while(rs.next()){
				QueueVO qvo = new QueueVO();
				qvo.setQueueId(rs.getBigDecimal("queueid"));
				qvo.setQueueName(rs.getString("queuename"));
				qvo.setSize(rs.getBigDecimal("size"));
				qvo.setAvailable(rs.getBigDecimal("available"));
				qvo.setTerminate(rs.getString("isterminate").equals(DefaultQueueConstants.QUEUE_TERMINATE));
				qvo.setWorkingTime(rs.getBigDecimal("workingtime"));
				qvo.setMessageClass(rs.getString("messageclass"));
				qvo.setAcitvateClass(rs.getString("activateclass"));
				qvo.setCreateDate(DataConverter.toDate(rs.getBigDecimal("createdate"),
				rs.getBigDecimal("createtime")));
				qvo.setCreateUser("createuser");
				qvo.setUpdateDate(DataConverter.toDate(rs.getBigDecimal("updatedate"),rs.getBigDecimal(
				"updatetime")));
				qvo.setUpdateUser(rs.getString("updateuser"));
				qvo.setUpdateProgram(rs.getString("updateprogram"));
				qvo.setRecordStatus(rs.getString("recordstatus").equals(DefaultQueueConstants.RECORD_STATUS_AVAILABLE));
				//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
				qvo.setHostName(rs.getString("hostname"));
				qvo.setBootStrap(rs.getString("bootstrap"));
				//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
				c.add(qvo);
			}
		}catch(Exception e){
			throw e;
		}finally{
			//Start Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if (null != rs)
				rs.close();
			//End Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return c;
	}
	
	public void insertJob(JobVO jobvo,BigDecimal parentid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into jobtb ");
		sql.append(" (jobid,queueid,message,status,piority,submitdate,submittime,submituser ");
		sql.append(" ,recordstatus,referenceno,jobgroup) ");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?) ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			//pstmt.setBigDecimal(1,jobvo.getJobid());
			pstmt.setBigDecimal(1,getMaxJobtbId());
			pstmt.setBigDecimal(2,parentid);
			pstmt.setBytes(3,DataConverter.toByteArray(jobvo.getMessage()));
			pstmt.setString(4,jobvo.getStatus());
			pstmt.setInt(5,jobvo.getPiority());
			pstmt.setBigDecimal(6,DataConverter.to400Date(jobvo.getSubmitDate()));
			pstmt.setBigDecimal(7,DataConverter.to400Time(jobvo.getSubmitDate()));
			pstmt.setString(8,jobvo.getSubmintUser());
			pstmt.setString(9,DefaultQueueConstants.RECORD_STATUS_AVAILABLE);
			pstmt.setString(10,jobvo.getReferenceno()==null?"":jobvo.getReferenceno().trim());
			pstmt.setLong(11,jobvo.getJobgroup());
			System.out.println("Insert for :"+pstmt.executeUpdate());
			
		}catch(Exception e){
			throw e;
		}finally{
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
	}
	
	public int getReleaseJob(String qname) throws Exception{
		return getNumberOfJobByStatus(qname,DefaultQueueConstants.JOB_STATUS_RELEASE);
	}
	
	public int getHoldJob(String qname) throws Exception{
		return getNumberOfJobByStatus(qname,DefaultQueueConstants.JOB_STATUS_HOLD);
	}
	public int getProcessingJob(String qname) throws Exception{
		return getNumberOfJobByStatus(qname,DefaultQueueConstants.JOB_STATUS_PROCESSING);
	}
	
	public int getNumberOfJobByStatus(String qname,String status) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from jobtb j ");
		sql.append(" left join queuetb q on q.queueid = j.queueid ");
		sql.append(" where j.status = ? ");
		//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		//sql.append(" and q.queuename = ? ");
		sql.append(" and TRIM(q.queuename) = ? ");
		//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		sql.append(" and j.recordstatus <> '"+DefaultQueueConstants.RECORD_STATUS_CANCEL+"'");
		sql.append(" and q.recordstatus <> '"+DefaultQueueConstants.RECORD_STATUS_CANCEL+"'");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,status);
			//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
			//pstmt.setString(2,qname);
			pstmt.setString(2,qname.trim());
			//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			//Start Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if (null != rs)
				rs.close();
			//End Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return 0;
		
	}
	
	public int getQueueSize(String qname) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select size from queuetb ");
		//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		//sql.append(" where queuename = ? ");
		sql.append(" where TRIM(queuename) = ? ");
		//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
			//pstmt.setString(1,qname);
			pstmt.setString(1,qname.trim());
			//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			//Start Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if (null != rs)
				rs.close();
			//End Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return 0;
	}
	
	public String setQueueTerminateStatus(String qname,String status) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update queuetb ");
		sql.append(" set isterminate = ? ");
		//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		//sql.append(" where queuename = ? ");
		sql.append(" where TRIM(queuename) = ? ");
		//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,status);
			//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
			//pstmt.setString(2,qname);
			pstmt.setString(2,qname.trim());
			//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return DefaultQueueConstants.QUEUE_TERMINATE;
	}
	
	public String deleteJob(BigDecimal jobid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update jobtb ");
		sql.append(" set recordstatus =  '"+DefaultQueueConstants.RECORD_STATUS_CANCEL+"' ");
		sql.append(" where jobid = ? ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setBigDecimal(1,jobid);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			//Start Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if (null != rs)
				rs.close();
			//End Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return DefaultQueueConstants.QUEUE_TERMINATE;
	}
	
	public String getQueueTerminateStatus(String qname) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select isterminate from queuetb ");
		//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		//sql.append(" where queuename = ? ");
		sql.append(" where TRIM(queuename) = ? ");
		//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		sql.append(" and recordstatus <> '"+DefaultQueueConstants.RECORD_STATUS_CANCEL+"'");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
			//pstmt.setString(1,qname);
			pstmt.setString(1,qname.trim());
			//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			//Start Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if (null != rs)
				rs.close();
			//End Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return DefaultQueueConstants.QUEUE_TERMINATE;
	}
	
	//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
	public Collection searchJob( String qname
								, String jobGroup
								, String submitUser
								, Date submitDateFrom
								, Date submitDateTo
								, String[] status
								, String refNo) throws Exception
	{
		return searchJob(qname, jobGroup, submitUser, submitDateFrom, submitDateTo, status, refNo, false);
	}
	/*public Collection searchJob(
			String qname
			,String jobGroup
			,String submitUser
			,Date submitDateFrom
			,Date submitDateTo
			,String[] status
			,String refNo) throws Exception{*/
	public Collection searchJob(
			String qname
			,String jobGroup
			,String submitUser
			,Date submitDateFrom
			,Date submitDateTo
			,String[] status
			,String refNo
			, boolean errDetail) throws Exception{
		//End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
		Collection c = new ArrayList();
		StringBuffer sql = new StringBuffer();
		//Start Re-tune SQL Veerasak Boonchern Aey 2009/10/22[yyyy/mm/dd]
		sql.append(" select j.* from jobtb j ");
		sql.append(" left join queuetb q on q.queueid = j.queueid ");
		
		//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
		/*sql.append("with queue AS" +
					" (" +
						" select q.QUEUEID" +
						" from queuetb q" +
						" where TRIM(q.queuename) = ?" +
					" )" +
					" select j.*" +
					" from jobtb j, queue q");*/
		//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		//sql.append(" where q.queuename = ? ");
		sql.append(" where TRIM(q.queuename) = ? ");
		//sql.append(" where j.queueid = q.queueid");
		//End Re-tune SQL Veerasak Boonchern Aey 2009/10/22[yyyy/mm/dd]
		//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
		if(jobGroup == null || jobGroup.length() == 0){
			//jobGroup = "0";
		}else{
			sql.append(" and j.jobgroup ="+  new Long(jobGroup));
		}
		sql.append(nullToSql(" and j.submituser ",submitUser));
		sql.append(nullToSql(" and j.referenceno ",refNo));
		sql.append(" and j.recordstatus <> '"+DefaultQueueConstants.RECORD_STATUS_CANCEL+"' ");
		if(null!=submitDateFrom){
			sql.append(" and j.submitdate > ? ");
			sql.append(" and j.submittime > ? ");
		}
		
		if(null!=submitDateTo){
			sql.append(" and j.submitdate < ? ");
			sql.append(" and j.submittime < ? ");
		}
		
		if(null!=status && status.length!=0){
			sql.append(" and j.status in ( ");
			sql.append(insertCommaSeparate(status));
			sql.append(" ) ");
		}
		
		sql.append(" order by jobid ");
		
		/*sql.append("WITH queue AS (" +
						" SELECT q.QUEUEID FROM queuetb q WHERE TRIM(q.queuename) = ?" +
					" )" +
					", job AS (" +
						" SELECT j.*" +
						" FROM jobtb j" +
						" WHERE 1 = 1");
		if(jobGroup == null || jobGroup.length() == 0)
		{
			//jobGroup = "0";
		}else
		{
			sql.append(" AND j.jobgroup = "+  new Long(jobGroup));
		}
		
		sql.append(nullToSql(" AND j.submituser ", submitUser));
		sql.append(nullToSql(" AND j.referenceno ", refNo));
		sql.append(" AND j.recordstatus <> '" + DefaultQueueConstants.RECORD_STATUS_CANCEL + "'");
		
		if(null!=submitDateFrom)
		{
			sql.append(" AND j.submitdate > ? ");
			sql.append(" AND j.submittime > ? ");
		}
		
		if(null!=submitDateTo)
		{
			sql.append(" AND j.submitdate < ? ");
			sql.append(" AND j.submittime < ? ");
		}
		
		if(null!=status && status.length!=0)
		{
			sql.append(" AND (");
			for (int i = 0; i < status.length; i++)
			{
				if (i != 0)
					sql.append(" OR");
				
				sql.append(" j.status = '" + status[i] + "'");
			}
			sql.append(" )");
		}
		
		sql.append(" )" +
					" SELECT j.*" +
						", NVL(tl.LOGMESSAGE,'') AS logmessage" +
					" FROM job j" +
						" LEFT OUTER JOIN" +
						" (" +
							" SELECT a.*" +
							", (" +
								" SELECT tl3.LOGMESSAGE" +
								" FROM tranlogtb tl3" +
								" WHERE a.LOGRECORDID = tl3.LOGRECORDID" +
									" AND tl3.LOGINDEX = a.mx" +
							" )" +
							" FROM" +
							" (" +
								" SELECT tl.LOGRECORDID" +
								", (" +
									" SELECT MAX(tl2.LOGINDEX)" +
									" FROM tranlogtb tl2" +
									" WHERE tl.LOGRECORDID = tl2.LOGRECORDID" +
								" ) AS mx" +
								" FROM tranlogtb tl" +
								" WHERE tl.LOGFOR = 999" +
									" AND tl.LOGNAME = ?" +
								" GROUP BY tl.LOGRECORDID" +
							" ) a" +
						" ) tl ON int(tl.LOGRECORDID) = j.jobid" +
						", queue q " +
					" WHERE j.queueid = q.queueid" +
					" ORDER BY j.jobid");*/
		//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
		
		System.out.println(" search sql:"+sql.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			//Start Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
			//pstmt.setString(1,qname);
			
			//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
			pstmt.setString(1,qname.trim());
			//End Trim queue name Veerasak Boonchern 2009/10/19[yyyy/mm/dd]
			if(null!=submitDateFrom){
				pstmt.setBigDecimal(2,DataConverter.to400Date(submitDateFrom));
				pstmt.setBigDecimal(3,DataConverter.to400Time(submitDateFrom));
			}
			if(null!=submitDateTo){
				pstmt.setBigDecimal(4,DataConverter.to400Date(submitDateTo));
				pstmt.setBigDecimal(5,DataConverter.to400Time(submitDateTo));
			}
			
			/*int i = 0;
			pstmt.setString(++i, qname);
			
			if(null!=submitDateFrom)
			{
				pstmt.setBigDecimal(++i,DataConverter.to400Date(submitDateFrom));
				pstmt.setBigDecimal(++i,DataConverter.to400Time(submitDateFrom));
			}
			if(null!=submitDateTo)
			{
				pstmt.setBigDecimal(++i,DataConverter.to400Date(submitDateTo));
				pstmt.setBigDecimal(++i,DataConverter.to400Time(submitDateTo));
			}
			
			pstmt.setString(++i, qname);*/
			//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
			rs = pstmt.executeQuery();
			while(rs.next()){
				JobVO job = new JobVO();
//				System.out.println(" ===============================================================================");
//				System.out.println("jobid:"+rs.getBigDecimal("jobid"));
				job.setJobid(rs.getBigDecimal("jobid"));
				Blob blob = rs.getBlob("message");
//				try{
//					job.setMessage((Message)DataConverter.toObject(blob.getBytes(1,(int) blob.length())));
//				}catch(Exception e){
//					e.printStackTrace();
//				}
				job.setPiority(rs.getInt("piority"));
				job.setRecordStatus(rs.getString("recordstatus").equals(DefaultQueueConstants.JOB_STATUS_RELEASE));
				job.setStatus(rs.getString("status"));
				job.setSubmintUser(rs.getString("submituser"));
				job.setSubmitDate(DataConverter.toDate(rs.getBigDecimal("submitdate"),rs.getBigDecimal("submittime")));
				if(null!=rs.getBigDecimal("updatedate")){
					job.setUpdateDate(DataConverter.toDate(rs.getBigDecimal("updatedate"),rs.getBigDecimal("updatedate")));
				}
				job.setUpdateProgram(rs.getString("updateprogram"));
				job.setUpdateUser(rs.getString("updateuser"));
				job.setJobgroup(rs.getLong("jobgroup"));
				job.setReferenceno(rs.getString("referenceno"));
				
				//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
				//Start Queue Tranlog Veerasak Boonchern Aey 2009/10/22[yyyy/mm/dd]
				//TranLogDAOImpl tranlogDAO = new  TranLogDAOImpl();
				//TranLog tranlog = tranlogDAO.getQueueTranLogByRecordId(rs.getBigDecimal("jobid"), conn);
				//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
				/*Collection tranlogCol = new  TranLogDAOImpl().getQueueTranLogByRecordId(rs.getBigDecimal("jobid"), conn);
				
				if (tranlogCol != null)
				{
					Iterator iter = tranlogCol.iterator();
					if (iter.hasNext())
					{
						TranLog tranlog = (TranLog)iter.next();
						job.setResultMessage(tranlog.getLogMessage());
					} else
					{
						job.setResultMessage("");
					}
				} else
				{
					job.setResultMessage("");
				}*/
				//job.setResultMessage(rs.getString("logmessage"));
				job.setResultMessage("");
				//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
				//End Queue Tranlog Veerasak Boonchern Aey 2009/10/22[yyyy/mm/dd]
				//End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
				
				c.add(job);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			//Start Close ResultSet Veerasak Boonchern Aey 2009/10/29[yyyy/mm/dd]
			if (rs != null)
				rs.close();
			//End Close ResultSet Veerasak Boonchern Aey 2009/10/29[yyyy/mm/dd]
			if(null!=pstmt)
				pstmt.close();
			if(null!=conn)
				conn.close();
		}
		return c;
	}
	
	
	String nullToSql(String sql,String val){
		if(null==val || "".equals(val)){
			return "";
		}else if(val.indexOf("*")!=-1){
			return sql+" like '"+val.replaceAll("\\*","%")+"' ";
		}else {
			return sql+" = '"+val+"' ";
		}
	}
	
	String insertCommaSeparate(String [] val){
		StringBuffer s = new StringBuffer();
		for(int i =0;i< val.length;i++){
			if(i!=0){
				s.append(",'");
				s.append(val[i]);
				s.append("'");
			}else{
				s.append("'");
				s.append(val[i]);
				s.append("'");
			}
		}
		
		return s.toString();
	}
	
	
	public List getAllQueue() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from queuetb ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				QueueVO qvo = null;
				qvo = new QueueVO();
				qvo.setQueueId(rs.getBigDecimal("QUEUEID")); 
				qvo.setQueueName(rs.getString("QUEUENAME")); 
				qvo.setSize(rs.getBigDecimal("SIZE"));
				qvo.setAvailable(rs.getBigDecimal("AVAILABLE")); 
				qvo.setTerminate(rs.getString("ISTERMINATE").trim().equals(DefaultQueueConstants.QUEUE_TERMINATE)); 
				qvo.setWorkingTime(rs.getBigDecimal("WORKINGTIME"	  )); 
				qvo.setMessageClass(rs.getString("MESSAGECLASS"));
				qvo.setAcitvateClass(rs.getString("ACTIVATECLASS"	)); 
//				qvo.setCreateDate (rs.getString("CREATEDATE"	  )  );
//				qvo.set(rs.getString("CREATETIME"	  )  );
//				qvo.set (rs.getString("CREATEUSER"	  )  );
//				qvo.set (rs.getString("UPDATEDATE"	  )  );
//				qvo.set (rs.getString("UPDATETIME"	  )  );
//				qvo.set (rs.getString("UPDATEUSER"	  )  );
//				qvo.set (rs.getString("UPDATEPROGRAM"	)); 
				qvo.setRecordStatus(rs.getString("RECORDSTATS"	  ).equals(DefaultQueueConstants.RECORD_STATUS_AVAILABLE));
				//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
				qvo.setHostName(rs.getString("hostname"));
				qvo.setBootStrap(rs.getString("bootstrap"));
				//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
				list.add(qvo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//Start Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if (null != rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			/*if(rs!=null)
				rs.close();*/
			//End Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if(null!=conn)
				conn.close();
		}
		return list;
	}
	
	
	
	public QueueVO getQueue(String queuename) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from queuetb ");
		sql.append(" where trim(queuename) = ? ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QueueVO qvo = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,queuename.trim());
			rs = pstmt.executeQuery();
			if(rs.next()){
				qvo = new QueueVO();
				qvo.setQueueId(rs.getBigDecimal("QUEUEID")); 
				qvo.setQueueName(rs.getString("QUEUENAME")); 
				qvo.setSize(rs.getBigDecimal("SIZE"));
				qvo.setAvailable(rs.getBigDecimal("AVAILABLE")); 
				qvo.setTerminate(rs.getString("ISTERMINATE").trim().equals(DefaultQueueConstants.QUEUE_TERMINATE)); 
				qvo.setWorkingTime(rs.getBigDecimal("WORKINGTIME"	  )); 
				qvo.setMessageClass(rs.getString("MESSAGECLASS"));
				qvo.setAcitvateClass(rs.getString("ACTIVATECLASS"	)); 
//				qvo.setCreateDate (rs.getString("CREATEDATE"	  )  );
//				qvo.set(rs.getString("CREATETIME"	  )  );
//				qvo.set (rs.getString("CREATEUSER"	  )  );
//				qvo.set (rs.getString("UPDATEDATE"	  )  );
//				qvo.set (rs.getString("UPDATETIME"	  )  );
//				qvo.set (rs.getString("UPDATEUSER"	  )  );
//				qvo.set (rs.getString("UPDATEPROGRAM"	)); 
				qvo.setRecordStatus(rs.getString("recordstatus"	  ).equals(DefaultQueueConstants.RECORD_STATUS_AVAILABLE));
				//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
				qvo.setHostName(rs.getString("hostname"));
				qvo.setBootStrap(rs.getString("bootstrap"));
				//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
				                                  
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//Start Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if (null != rs)
				rs.close();
			if(null!=pstmt)
				pstmt.close();
			/*if(rs!=null)
				rs.close();*/
			//End Close ResultSet Veerasak Boonchern Aey 2009/11/04[yyyy/mm/dd]
			if(null!=conn)
				conn.close();
		}
		return qvo;
	}
	
	
//	public void applyQueueStatus(QueueConfigurationVO qcvo){
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select cou from ");
//	}
	
//	public void updateReference(BigDecimal job)
	
	//Start Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]
	public boolean increaseQueuePool(BigDecimal queueId, int increase) throws Exception
	{
		return increaseQueuePool(queueId, null, increase);
	}
	public boolean increaseQueuePool(String queueName, int increase) throws Exception
	{
		return increaseQueuePool(null, queueName, increase);
	}
	public boolean increaseQueuePool(BigDecimal queueId, String queueName, int increase) throws Exception
	{
		boolean output = false;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE QUEUETB q");
		buffer.append(" SET q.available = (q.available+(" + increase + "))");
		buffer.append(" WHERE");
		if (queueId != null)
		{
			buffer.append(" q.queueid = ?");
			if (queueName != null && !queueName.equals(""))
				buffer.append(" AND");
		}
		if (queueName != null && !queueName.equals(""))
			buffer.append(" TRIM(q.queuename) = ?");
		
		//print log
		/*System.out.println("UPDATE QUEUETB");
		System.out.println(buffer.toString());
		System.out.println("queueId: " + queueId);
		System.out.println("queueName: " + queueName);
		System.out.println("value: " + value);*/
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try
		{
			conn = getConnection();
			
			pstmt = conn.prepareStatement(buffer.toString());
			if (queueId != null)
			{
				pstmt.setBigDecimal(1, queueId);
				if (queueName != null && !queueName.equals(""))
					pstmt.setString(2, queueName.trim());
			} else
			{
				if (queueName != null && !queueName.equals(""))
					pstmt.setString(1, queueName.trim());
			}
			
			int i = pstmt.executeUpdate();
			if (i != 0)
				output = true;
			
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			if (pstmt != null)
				pstmt.close();
			
			if (conn != null)
				conn.close();
		}
		
		return output;
	}
	//End Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]

	//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
	public Collection getAllQueueName() throws Exception
	{
		Collection c = new ArrayList();
		try
		{
			Collection aCollection = new JobQueueDAO().findAllQueue();
			for (Iterator iter = aCollection.iterator(); iter.hasNext();)
			{
				QueueVO element = (QueueVO) iter.next();
				String hostName = SystemEnvironment.getInstance().getDefaultHostName();
				if (!hostName.endsWith("/"))
				{
					hostName = hostName.trim() + "/";
				}
				
				String queueHostName = element.getHostName();
				if (!element.getHostName().endsWith("/"))
				{
					queueHostName = queueHostName.trim() + "/";
				}
				
				//if (hostName.equals(queueHostName))
					c.add(element.getQueueName());
			}
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
		return c;
	}
	//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
	
	//Start Reset when start Veerasak Boonchern Aey 2009/11/12[yyyymmdd]
	public void resetQueue(String queueName) throws Exception
	{
		String queueId = getQueueId(queueName);
		
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		StringBuffer sql = null;
		
		try
		{
			conn = getConnection();
			
			sql = new StringBuffer();
			sql.append("UPDATE JOBTB j" +
						" SET j.status = 'E'" +
						" WHERE j.status = 'P'" +
							" AND j.queueid = ?");
			
			pstmt1 = conn.prepareStatement(sql.toString());
			pstmt1.setBigDecimal(1, new BigDecimal(queueId));
			pstmt1.executeUpdate();
			
			sql = new StringBuffer();
			sql.append("UPDATE QUEUETB q" +
						" SET q.available = q.size" +
						" WHERE q.queueid = ?");
			pstmt2 = conn.prepareStatement(sql.toString());
			pstmt2.setBigDecimal(1, new BigDecimal(queueId));
			pstmt2.executeUpdate();
			
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			if (conn != null)
				conn.close();
			
			if (pstmt1 != null)
				pstmt1.close();
			
			if (pstmt2 != null)
				pstmt2.close();
		}
	}
	
	public String getQueueId(String queueName) throws Exception
	{
		String queueId = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT q.queueid" +
					" FROM QUEUETB q" +
					" WHERE TRIM(q.queuename) = ?");
		
		try
		{
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, queueName.trim());
			
			rs = pstmt.executeQuery();
			
			if (rs.next())
				queueId = rs.getString("queueid");
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			if (conn != null)
				conn.close();
			
			if (pstmt != null)
				pstmt.close();
			
			if (rs != null)
				rs.close();
		}
		
		return queueId;
	}
	//End Reset when start Veerasak Boonchern Aey 2009/11/12[yyyymmdd]
	
	//Start Queue monitor Veerasak Boonchern Aey 2010/03/02[yyyymmdd]
	public List getAllQueueOnServer() throws Exception
	{
		List dataList = new ArrayList();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = getConnection();
			
			String hostName = SystemEnvironment.getInstance().getDefaultHostName();
			if (hostName.endsWith("/"))
			{
				hostName = hostName.substring(0, hostName.length() - 1);
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append("WITH holdJob AS" +
					" (" +
						" SELECT j.queueid" +
							", COUNT(j.status) as hold" +
						" FROM JOBTB j" +
						" WHERE j.status = 'H'" +
							" AND j.recordstatus = 'Y'" + 
						" GROUP BY j.queueid" +
						" ORDER BY j.queueid" +
					" )" +
					", releaseJob as" +
					" (" +
						" SELECT j.queueid" +
							", COUNT(j.status) as release" +
						" FROM JOBTB j" +
						" WHERE j.status = 'R'" +
							" AND j.recordstatus = 'Y'" + 
						" GROUP BY j.queueid" +
						" ORDER BY j.queueid" +
					" )" +
					", processJob as" +
					" (" +
						" SELECT j.queueid" +
							", COUNT(j.status) as process" +
						" FROM JOBTB j" +
						" WHERE j.status = 'P'" +
							" AND j.recordstatus = 'Y'" + 
						" GROUP BY j.queueid" +
						" ORDER BY j.queueid" +
					" )" +
					" SELECT q.queueid" +
							", q.queuename" +
							", q.size" +
							", q.available" +
							", q.isterminate" +
							", h.hold" +
							", r.release" +
							", p.process" +
					" FROM QUEUETB q" +
						" LEFT OUTER JOIN holdJob h" +
						" ON h.queueid = q.queueid" +
						" LEFT OUTER JOIN releaseJob r" +
						" ON r.queueid = q.queueid" +
						" LEFT OUTER JOIN processJob p" +
						" ON p.queueid = q.queueid" +
					" WHERE q.hostname = ?" +
					" ORDER BY q.queuename");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, hostName);
			
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Map map = new Hashtable();
				map.put("queueid", rs.getBigDecimal("queueid"));
				map.put("queuename", rs.getString("queuename"));
				map.put("size", rs.getBigDecimal("size"));
				map.put("available", rs.getBigDecimal("available"));
				map.put("isterminate", rs.getString("isterminate"));
				BigDecimal tmp = rs.getBigDecimal("hold");
				if (tmp != null)
					map.put("hold", tmp);
				tmp = rs.getBigDecimal("release");
				if (tmp != null)
					map.put("release", tmp);
				tmp = rs.getBigDecimal("process");
				if (tmp != null)
					map.put("process", tmp);
				
				dataList.add(map);
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			if (conn != null)
				conn.close();
			
			if (pstmt != null)
				pstmt.close();
			
			if (rs != null)
				rs.close();
		}
		
		return dataList;
	}
	
	public Map getQueueInfo(BigDecimal queueId) throws Exception
	{
		Map output = new Hashtable();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = getConnection();
			
			String hostName = SystemEnvironment.getInstance().getDefaultHostName();
			if (hostName.endsWith("/"))
			{
				hostName = hostName.substring(0, hostName.length() - 1);
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT q.queueid" +
							", q.queuename" +
							", q.size" +
							", q.available" +
							", q.isterminate" +
					" FROM QUEUETB q" +
					" WHERE q.hostname = ?" +
						" AND queueid = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, hostName);
			pstmt.setBigDecimal(2, queueId);
			
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				output.put("queueid", rs.getBigDecimal("queueid"));
				output.put("queuename", rs.getString("queuename"));
				output.put("size", rs.getBigDecimal("size"));
				output.put("available", rs.getBigDecimal("available"));
				output.put("isterminate", rs.getString("isterminate"));
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			if (conn != null)
				conn.close();
			
			if (pstmt != null)
				pstmt.close();
			
			if (rs != null)
				rs.close();
		}
		
		return output;
	}
	//End Queue monitor Veerasak Boonchern Aey 2010/03/02[yyyymmdd]
	
	//Start Delete job by Ref.No. Veerasak Boonchern Aey 2010/03/23[yyyy/mm/dd]
	public int deleteJobByReferenceNo(String refNo) throws Exception
	{
		int output = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM JOBTB j" +
						" WHERE j.referenceno = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, refNo);
			
			output = pstmt.executeUpdate();
			
		} catch (Exception e)
		{
		} finally
		{
			if (conn != null)
				conn.close();
			
			if (pstmt != null)
				pstmt.close();
			
			if (rs != null)
				rs.close();
		}
		
		return output;
	}
	//End Delete job by Ref.No. Veerasak Boonchern Aey 2010/03/23[yyyy/mm/dd]
	
	//Start Delete job by Ref.No. Veerasak Boonchern Aey 2010/03/23[yyyy/mm/dd]
	public int deleteJobByReferenceNo(BigDecimal queueId, String refNo) throws Exception
	{
		int output = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM JOBTB j" +
						" WHERE j." +
							" AND j.referenceno = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, refNo);
			
			output = pstmt.executeUpdate();
			
		} catch (Exception e)
		{
		} finally
		{
			if (conn != null)
				conn.close();
			
			if (pstmt != null)
				pstmt.close();
			
			if (rs != null)
				rs.close();
		}
		
		return output;
	}
	//End Delete job by Ref.No. Veerasak Boonchern Aey 2010/03/23[yyyy/mm/dd]
}
