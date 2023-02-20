/*
 * Created on 14 ¡.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author ituser3
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DateUtil {
	public static String dateToString(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat(EnvProperties.getInstance()
				.get("date.format"));
		return sdf.format(d);
	}

	public static String dateToString(Date d, Locale local) {
		SimpleDateFormat sdf = new SimpleDateFormat(EnvProperties.getInstance()
				.get("date.format"), local);
		return sdf.format(d);
	}

	public static String timeToString(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat(EnvProperties.getInstance()
				.get("time.format"));
		return sdf.format(d);
	}

	public static String timeToString(Date d, Locale local) {
		SimpleDateFormat sdf = new SimpleDateFormat(EnvProperties.getInstance()
				.get("time.format"), local);
		return sdf.format(d);
	}

	public static String dateToStringFromDB(String date, String time,
			String format, Locale local) throws Exception {
		//		String dateStr = Integer.toString(date);
		//		String timeStr = Integer.toString(time);
		//		while(dateStr.length()!=6){
		//			dateStr = "0"+dateStr;
		//		}
		try{
		while (time.length() != 6) {
			time = "0" + time;
		}
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd-HHmmss", local);
		SimpleDateFormat f2 = new SimpleDateFormat(format, local);
		Date date2 = f.parse(date + "-" + time);
		return f2.format(date2);
		}catch(Exception e){
			return "";
		}
	}

	//	public static String timeToStringFormDB(String time,String format,Locale
	// local){
	//		while(time.length()!=6){
	//			time =0+time;
	//		}
	//		SimpleDateFormat f = new SimpleDateFormat("HHmmss",local);
	//		Date date2 = f.parse(date);
	//		return dateToString(date2,local);
	//	}
	public static int dateToInt6(Date date,Locale locale){
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy", locale);
		return Integer.parseInt(sdf.format(date));
	}
	
	public static int timeToInt(Date date,Locale locale){
		SimpleDateFormat sdf = new SimpleDateFormat(EnvProperties.getInstance()
				.get("time.format"), locale);
		return Integer.parseInt(sdf.format(date));
	}
	public static void main(String a[]) {
		String d= "20081203";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd",Locale.US);
		try {
			Date dd = df.parse(d);
			System.out.println(dd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
