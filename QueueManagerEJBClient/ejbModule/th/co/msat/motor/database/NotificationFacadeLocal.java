package th.co.msat.motor.database;
import java.util.Collection;
/**
 * Local interface for Enterprise Bean: NotificationFacade
 */
public interface NotificationFacadeLocal extends javax.ejb.EJBLocalObject {
	public Collection getRegisteredClaim(long jobgroup) throws Exception;
	public Collection searchUnAdjustClaim(String notiKeyword, boolean isold)
		throws Exception;
}
