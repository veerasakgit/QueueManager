/*
 * Created on 25 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager.database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataConverter {

	
	public static byte [] toByteArray(Serializable o) throws IOException{
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		 
		oos.writeObject(o);
		oos.flush();
		return baos.toByteArray();
		
	} 
	
	public static Object toObject(byte [] b) throws IOException{
		Object otherObject = null;
		try{
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		ObjectInputStream ois = new ObjectInputStream(bais);
		 
		 otherObject = ois.readObject();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}
		return otherObject;
		
	} 
	
	
	public static BigDecimal to400Date(Date d){
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd",Locale.US);
		return new BigDecimal(f.format(d));
	}
	
	public static BigDecimal to400Time(Date d){
		SimpleDateFormat f = new SimpleDateFormat("HHmmss",Locale.US);
		
//		SimpleDateFormat f2 = new SimpleDateFormat("HH-mm-ss",Locale.US);
//		System.out.println("==================================================");
//		System.out.println(f2.format(d));
		return new BigDecimal(f.format(d));
	}
	
	public static Date toDate(BigDecimal date ,BigDecimal time) throws ParseException{
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd-HHmmSS",Locale.US);
		return f.parse(date.setScale(0,BigDecimal.ROUND_HALF_UP).toString()+"-"+timeToString(time));
	}
	
	public static String toString(BigDecimal date,BigDecimal time) throws ParseException{
		Date d = toDate(date,time);
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HHmmSS");
		return f.format(d);
	}
	
	public static String toString(BigDecimal date) throws ParseException{
		if(date.compareTo(new BigDecimal(0))==0)
			return "";
		SimpleDateFormat fs = new SimpleDateFormat("yyyyMMdd",Locale.US);
		Date fss= fs.parse(date.setScale(0,BigDecimal.ROUND_HALF_UP).toString());
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		return f.format(fss);
	}
	
	static String timeToString(BigDecimal time){
		String s = time.setScale(0,BigDecimal.ROUND_HALF_UP).toString();
		while(s.length()<6){
			s="0"+s;
		}
		return s;
	}
	
	public static void main(String a[]){
		DataConverter.to400Time(new Date());
	}
	
	
}
