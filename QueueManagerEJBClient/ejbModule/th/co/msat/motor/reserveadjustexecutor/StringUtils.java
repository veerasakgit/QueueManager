/*
 * Created on 28 ¾.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.reserveadjustexecutor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StringUtils {
	public static String getPolicyBranch(String policy){
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(policy);
		if(m.find()){
			String s = m.group();
			return s.substring(0,2);
		}else
			return "";
	}
	public static String getPolicyCategory(String policy){
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(policy);
		if(m.find()){
			
			String s = m.group();
			return s.substring(2,3);
		}else
			return "";
	}
	public static String getPolicyClasses(String policy){
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(policy);
		if(m.find()){
			String s = m.group();
			return s.substring(3);
		}else
			return "";
	}
	public static String getPolicyYear(String policy){
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(policy);
		if(m.find()){
			String s = m.group();
			return s.substring(0,2);
		}else
			return "";
	}
	public static String getPolicyNo(String policy){
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(policy);
		if(m.find()){
			String s = m.group();
			return s.substring(2,8);
		}else
			return "";
	}
	
	public static String getClaimBranch(String claimno){
		System.out.println("Claimn No:"+claimno+":");
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(claimno.trim());
		if(m.find()){
			String s = m.group();
			return s.substring(0,2);
		}else
			return "";
	}
	public static String getClaimCategory(String claimno){
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(claimno);
		if(m.find()){
			
			String s = m.group();
			return s.substring(2,3);
		}else
			return "";
	}
	public static String getClaimClasses(String claimno){
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(claimno);
		if(m.find()){
			String s = m.group();
			return s.substring(3);
		}else
			return "";
	}
	public static String getClaimYear(String claimno){
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(claimno);
		if(m.find()){
			String s = m.group();
			return s.substring(0,2);
		}else
			return "";
	}
	public static String getClaimNo(String claimno){
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(claimno);
		if(m.find()){
			String s = m.group();
			System.out.println(s);
			System.out.println(claimno);
			return s.substring(2,8);
		}else
			return "";
	}
	
	public static String convertZBToClaim(String noti){
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(noti);
		if(m.find()){
			String s = m.group();
			return s;
		}else
			return "";
	}
	
	
	public static void main(String a[]){
		System.out.println(StringUtils.getClaimBranch("BKCV08007846"));
		System.out.println(StringUtils.getClaimCategory("BKCV08007846"));
		System.out.println(StringUtils.getClaimClasses("BKCV08007846"));
//		System.out.println(StringUtils.getClaimNo("BKCV08007846"));
		System.out.println(StringUtils.getClaimYear("BKCV08007846"));
		System.out.println(StringUtils.getClaimNo("BKCV08007846"));
	}
}
