package th.co.msat.motor.database;
import java.util.Collection;
/**
 * Remote interface for Enterprise Bean: NotificationFacade
 */
public interface NotificationFacade extends javax.ejb.EJBObject {
	public Collection getRegisteredClaim(long jobgroup)
		throws Exception,
		java.rmi.RemoteException;
	public Collection searchUnAdjustClaim(String notiKeyword, boolean isold)
		throws Exception,
		java.rmi.RemoteException;
}
