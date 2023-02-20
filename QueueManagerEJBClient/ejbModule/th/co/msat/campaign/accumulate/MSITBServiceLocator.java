package th.co.msat.campaign.accumulate;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 * คลาสนี้ใช้สำหรับเลือก connection ของ LDAP
 * @author xxxx
 *
 */
public class MSITBServiceLocator {
	private static MSITBServiceLocator me;	
	
	public final static int CVILIB = 1;		
	public final static String CVILIB_DATA_SOURCE = "CVILIB";
	
	public final static int MSATLIB = 2;	
	public final static String MSATLIB_DATA_SOURCE = "MSATLIB";
	
	public final static int MMFLIB = 3;	
	public final static String MMFLIB_DATA_SOURCE = "MMFLIB";
	
	public final static int WEBBASELIB = 4;	
	public final static String WEBBASELIB_DATA_SOURCE = "WEBBASELIB";
	

	
	public static MSITBServiceLocator getInstance(){		
		if(me ==null) 
			me = new MSITBServiceLocator();

		return me;
	}
		
	/**
	 *  All connections get from this method 
	 * 
	 */
	public Connection getConnection(int dbType) throws MSITBServiceLocatorException{
		
		try{
			InitialContext ctx=new InitialContext();
			switch(dbType){
				case MSITBServiceLocator.CVILIB:
					return ((DataSource)ctx.lookup(CVILIB_DATA_SOURCE)).getConnection();
				case MSITBServiceLocator.MSATLIB:
					return ((DataSource)ctx.lookup(MSATLIB_DATA_SOURCE)).getConnection();	
				case MSITBServiceLocator.MMFLIB:
					return ((DataSource)ctx.lookup(MMFLIB_DATA_SOURCE)).getConnection();	
				case MSITBServiceLocator.WEBBASELIB:
					return ((DataSource)ctx.lookup(WEBBASELIB_DATA_SOURCE)).getConnection();	
			
			}

		}catch(Exception e){
			throw new MSITBServiceLocatorException(e.toString());			
		}
		
		return null;		
	}

}

