/*
 * Created on 1 ¸.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.schedulejobmanager.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import th.co.msat.schedulejobmanager.BaseDAO;
import th.co.msat.schedulejobmanager.DefaultScheduledTaskConstant;
import th.co.msat.schedulejobmanager.ScheduleTaskExecutorFactory;
import th.co.msat.schedulejobmanager.ScheduleTaskVO;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ScheduleTaskDAOImpl extends BaseDAO implements ScheduleTaskDAO {

	/* (non-Javadoc)
	 * @see th.co.msat.schedulejobmanager.ScheduleTaskDAO#getAllScheduleTask()
	 */
	public Collection getAllScheduleTask() throws Exception {
		Collection c=null;
		/*Connection conn = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from scheduletasktb ");
		sql.append(" where recordstatus = '"+DefaultScheduledTaskConstant.RECORD_STATUS_AVAILABLE+"' and ");
		sql.append(" taskstatus = '"+DefaultScheduledTaskConstant.TASK_STATUS_READY+"' ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection c = new ArrayList();
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				ScheduleTaskVO task = new ScheduleTaskVO();
				task.setActivateClass(ScheduleTaskExecutorFactory.getInstance().getScheduleJobExecutor(rs.getString("activateclass")));
				task.setId(rs.getInt("scheduledtaskid"));
				task.setPeriod(rs.getLong("period"));
				task.setStartHours(rs.getInt("starthours"));
				task.setStartMinutes(rs.getInt("startminutes"));
				task.setStartSeconds(rs.getInt("startseconds"));
				task.setTaskname(rs.getString("scheduledtaskname").toString());
				task.setUpdatedate(rs.getInt("updatedate"));
				task.setUpdatetime(rs.getInt("updatetime"));
				task.setUpdateuser(rs.getString("updateuser"));
				c.add(task);
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
		}*/
		
		return c;
	}
	/* (non-Javadoc)
	 * @see th.co.msat.schedulejobmanager.ScheduleTaskDAO#getScheduleTask(int)
	 */
	public ScheduleTaskVO getScheduleTask(int id) throws Exception {
		Connection conn = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from scheduletasktb ");
		sql.append(" where recordstatus = '"+DefaultScheduledTaskConstant.RECORD_STATUS_AVAILABLE+"'  ");
		sql.append(" and taskstatus = '"+DefaultScheduledTaskConstant.TASK_STATUS_READY+"' ");
		sql.append(" and scheduletaskid = "+id);
		System.out.println(sql.toString());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ScheduleTaskVO task = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				task = new ScheduleTaskVO();
				task.setActivateClass(ScheduleTaskExecutorFactory.getInstance().getScheduleJobExecutor(rs.getString("activateclass")));
				task.setId(rs.getInt("scheduledtaskid"));
				task.setPeriod(rs.getLong("period"));
				task.setStartHours(rs.getInt("starthours"));
				task.setStartMinutes(rs.getInt("startminutes"));
				task.setStartSeconds(rs.getInt("startseconds"));
				task.setTaskname(rs.getString("scheduledtaskname").toString());
				task.setUpdatedate(rs.getInt("updatedate"));
				task.setUpdatetime(rs.getInt("updatetime"));
				task.setUpdateuser(rs.getString("updateuser"));

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
		
		return task;
	}
	
}
