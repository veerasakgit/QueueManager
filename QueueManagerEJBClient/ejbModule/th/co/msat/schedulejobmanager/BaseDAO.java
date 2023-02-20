/*
 * Created on 1 ¸.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.schedulejobmanager;

import java.sql.Connection;
import java.sql.SQLException;

import th.co.msat.schedulejobmanager.database.MSATServiceLocator;
import th.co.msat.schedulejobmanager.database.MSATServiceLocatorException;
//import th.co.msat.schedulejobmanager.test.ConnectionFactory;



/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseDAO {
	private MSATServiceLocator services = new MSATServiceLocator();
	/**
	 * MSATLIB Connection to be use in this class
	 * @return A java.sql.Connection lookup from java:comp/env/MSATLIB 
	 * @throws MSATServiceLocatorException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	protected Connection getConnection() throws Exception{
		
		return services.getConnection(MSATServiceLocator.MSATLIB);
//		return ConnectionFactory.getInstance().getAS400Connection();
		
	}
}
