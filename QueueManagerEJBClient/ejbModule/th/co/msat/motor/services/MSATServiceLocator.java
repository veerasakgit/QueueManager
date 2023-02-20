package th.co.msat.motor.services;

import java.sql.Connection;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MSATServiceLocator {
	private static MSATServiceLocator me;	
	
	public final static int WEBBASELIB=1;
	public final static int MSIGLIB= 2;
	public final static int MSATLIB= 3;
	public final static int MMFLIB = 4;
		
	public final static String WEBBASELIB_DATA_SOURCE="WEBBASELIB";
	public final static String MSIGLIB_DATA_SOURCE="MSIGLIB";
	public final static String MSATLIB_DATA_SOURCE="MSATLIB";
	public final static String MMFLIB_DATE_SOURCE = "MMFLIB";
	
	
	
	private Properties pp;
	
	public static MSATServiceLocator getInstance(){		
			if( me ==null ){ 
				me = new MSATServiceLocator();
			}
			return me;
	}
	
		
	/**
	 *  All connections get from this method 
	 * 
	 */

	public Connection getConnection(int dbType) throws MSATServiceLocatorException{
		try{
			InitialContext ctx=new InitialContext();
			switch(dbType){
				case MSATServiceLocator.WEBBASELIB:
					return ((DataSource)ctx.lookup(WEBBASELIB_DATA_SOURCE)).getConnection();
				case MSATServiceLocator.MSIGLIB:
					return ((DataSource)ctx.lookup(MSIGLIB_DATA_SOURCE)).getConnection();	
				case MSATServiceLocator.MSATLIB:
					return ((DataSource)ctx.lookup(MSATLIB_DATA_SOURCE)).getConnection();
				case MSATServiceLocator.MMFLIB:
					return ((DataSource)ctx.lookup(MMFLIB_DATE_SOURCE)).getConnection();
			}

		}
		catch(Exception e){
			throw new MSATServiceLocatorException(e.toString());
			
		}
		return null;
	}

}

