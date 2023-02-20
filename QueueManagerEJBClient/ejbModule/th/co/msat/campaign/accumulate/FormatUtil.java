package th.co.msat.campaign.accumulate;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author ituser1
 *
 */
public class FormatUtil {
	

	/**
	 * @param date m/d/yyyy
	 * @return yymmdd
	 */
	public int dateToInt(String date){
		String dateResult ;
		String [] temp = date.split("\\/");
		dateResult = temp[2].substring(2) + fmtDate(temp[1]) + fmtDate(temp[0]);	
		return Integer.parseInt(dateResult);
	} 
	/**
	 * @param date m/d/yyyy
	 * @return yymmdd
	 */
	public int dateToIntYYYY(String date){
		String dateResult ;
		String [] temp = date.split("\\/");
		dateResult = "20" + temp[2].substring(2) + fmtDate(temp[1]) + fmtDate(temp[0]);	
		return Integer.parseInt(dateResult);
	} 
	

	/**
	 * @param date m/d/yyyy
	 * @return yymmdd
	 */
	public String dateToString(String date){

		String dateResult ;
		String [] temp = date.split("\\/");
		dateResult = temp[2].substring(2) + fmtDate(temp[1]) + fmtDate(temp[0]);	
		return dateResult;
	} 
	
	/**
	 * @param date m/d/yyyy
	 * @return yymmdd
	 */
	public String fmtDate(String str){
		if(str.length() == 1){
			return "0" + str;
		}
		return str;
	}
	
	public int fmtDateValue(String str){
		String temp = "";
		if(str.length() <= 5){
			temp = "0" + str;
		}else{
			temp = str;
		}
		//System.out.println(str+"," + temp.substring(4, 6)+ temp.substring(2,4) + temp.substring(0, 2));
		return Integer.parseInt(temp.substring(4, 6)+ temp.substring(2,4) + temp.substring(0, 2));
	}
	
	/**
	 * @param str dMMyy
	 * @return ddMMyy
	 */
	public String fmtDateString(String str){
		if(str.length() <= 5){
			return "0" + str;
		}
		if(str.length() == 8){
			str = str.substring(2, 8);
		}
		return str;
	}
	
	// YYYYMMDD to ddMMyy
	public String fmtDateStringYYYYMMDD(String str){
		return  str.substring(6,8) + str.substring(4,6)+ str.substring(2,4);
		
	}
	
	public static void main(String [] args){
		System.out.println(new FormatUtil().fmtDateStringYYYYMMDD("20090601"));
	}
	public String fmtPolicy(String str){
		// split policy
		String [] temp = str.split("\\/");
		
		//set 
		String policy = temp[0] + "/" + temp[1] + "/";

		if(!temp[2].substring(0,1).equals("0")){
			policy += "0" + temp[2].substring(0,1);
		}else{
			policy += temp[2].substring(0,2);
		}
		policy += "-";
		
		String [] temp2 =  temp[2].split("\\-");
		
		if(temp2[1].length() == 6){
			policy += temp2[1];
		}else if(temp2[1].length() == 5){
			policy += "0" + temp2[1];
		}else if(temp2[1].length() == 4){
			policy += "00" + temp2[1];
		}else if(temp2[1].length() == 3){
			policy += "000" + temp2[1];
		}else if(temp2[1].length() == 2){
			policy += "0000" + temp2[1];		
		}else {
			policy += "00000" + temp2[1];	
		}
		return policy;
	}
	
	public String[] splitPolicy(String policy){
		System.out.println("policy no : " + policy);
		String [] str = new String[5];
		String [] temp = policy.split("\\/");
		str[0]= temp[0].substring(0,2);
		str[1] = temp[0].substring(2,3);
		str[2] = temp [1];
		str[3] = temp[2].substring(0,2);
		str[4] = temp[2].substring(3,9);
		System.out.println(str[0] + ", " + str[1] + ", " + str[2] + ", " + str[3] + ", "+ str[4]); 
		return str;
	}
	
	public int dateToInt2(String str){
		
		String [] dateTemp  = str.split("\\/");		
		return Integer.parseInt( dateTemp[0] +  dateTemp[1] + dateTemp[2].substring(2));
	
	}
	
}
