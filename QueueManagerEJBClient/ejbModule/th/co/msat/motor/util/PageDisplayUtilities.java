/*
 * Created on 21 Ê.¤. 2551
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
public class PageDisplayUtilities {
	public static boolean isOdd(String row){
		return (new Integer(row).intValue() % 2 == 0);
	}
	public static boolean isOdd(Object row){
		return isOdd((String)row);
	}
	public static void main(String a[]){
		System.out.println(PageDisplayUtilities.isOdd("1"));
		
	}
}
