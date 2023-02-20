package th.co.msat.motor.database;
/**
 * Home interface for Enterprise Bean: NotificationFacade
 */
public interface NotificationFacadeHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: NotificationFacade
	 */
	public th.co.msat.motor.database.NotificationFacade create()
		throws javax.ejb.CreateException,
		java.rmi.RemoteException;
}
