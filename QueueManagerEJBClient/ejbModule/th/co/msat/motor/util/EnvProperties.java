/*
 * Created on 14 ¡.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Properties;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class EnvProperties {
		Properties p = null;
		static EnvProperties instance;
		
		public EnvProperties(){
			p = new Properties();
//			System.out.println(EnvProperties.class.getResource("//"));
			try {
				p.load(EnvProperties.class.getResourceAsStream("env.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static EnvProperties getInstance(){
			if(null==instance)
				instance = new EnvProperties();
			return instance;
		}
		
		public String get(String key){
			return p.getProperty(key);
		}
		
		public InputStream getFileInputStream(String fileName){
			return EnvProperties.class.getResourceAsStream("../"+fileName);
		}
		public static void main(String a[]){
			try {
				System.out.println((
						new BigDecimal(0).compareTo(new BigDecimal("20080904"))==0?"":
						(new java.text.SimpleDateFormat("dd/MM/yyyy",java.util.Locale.US).format(
						new java.text.SimpleDateFormat("yyyyMMdd",java.util.Locale.US).parse((new BigDecimal("20080904").toString()).toString())))
						));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
