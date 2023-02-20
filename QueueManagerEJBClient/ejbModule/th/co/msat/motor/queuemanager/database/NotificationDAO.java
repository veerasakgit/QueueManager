/*
 * Created on 10 ¡.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager.database;

import java.sql.Connection;
import java.util.Collection;

import th.co.msat.motor.database.vo.Notification;
import th.co.msat.motor.services.MSATServiceLocator;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface NotificationDAO {
	public static final String TRANSACTION_STATUS_O = "O";
	public static final String TRANSACTION_STATUS_C = "C";
	public Notification getNotification(String notificationNo)throws Exception;
	public Notification getNotification(String notificationNo,Connection conn)throws Exception;
	public void insert(Connection conn,Notification notification) throws Exception;
	public void insert(Notification notification) throws Exception;
	public void insert(Collection c)throws Exception;
	public void update(Notification notification)throws Exception;
	public void update(Connection conn,Notification notification)throws Exception;
	public MSATServiceLocator getService();
	public int updateClaimNo(String notificationNo,String claimNo)throws Exception;
	public Collection getNotificationForScrap(String from,String to)throws Exception;
	public Collection getRegisteredClaim(long jobgroup)throws Exception;
	public Collection getUnAdjustClaim()throws Exception;
	public Collection searchUnAdjustClaim(String notino,boolean isold)throws Exception;
}
