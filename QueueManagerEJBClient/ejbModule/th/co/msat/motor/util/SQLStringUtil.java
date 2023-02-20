/*
 * Created on 14 ¡.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.util;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SQLStringUtil {
	public static String convertToLikeCommand(String sql){
		if(-1!=sql.indexOf("*"))
			return " like '"+sql.replaceAll("\\*","%")+"' ";
		else
			return " = '"+sql+"'";
	}
}
