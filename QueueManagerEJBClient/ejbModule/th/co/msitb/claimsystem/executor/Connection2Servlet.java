package th.co.msitb.claimsystem.executor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;




/**
 * Connection to Servlet
 * @author OS.Ta
 * @since 03/11/2009
 * @version 1.0
 *
 */
public class Connection2Servlet {
	
	/**
	 * Connection to Servlet with method doPut();
	 * @param servletNamePath
	 * @param data
	 * @return result 
	 */
	public String connection2Servlet(String servletNamePath,String data){
		   
    	String result="";
		URL url_;
		InputStream inputStream =null;

		try { 
			url_ = new URL(servletNamePath);
			System.out.println("url_   "+url_);
			URLConnection connection = url_.openConnection();
		 	if (connection instanceof HttpURLConnection) {
	            HttpURLConnection httpConnection = (HttpURLConnection)connection;
	            httpConnection.setRequestMethod("PUT");
	            httpConnection.setDoOutput(true);
	            httpConnection.connect();

	             /*
	              * Initialize output stream
	              */
	            DataOutputStream outttt = new DataOutputStream( httpConnection.getOutputStream() );

	            /*
	             * write dataList  
	             */
	            outttt.writeBytes(data);                
	            outttt.flush();
	            //System.out.print("httpConnection=="+httpConnection);
	            //prin out data
	            inputStream = httpConnection.getInputStream();
	            //System.out.print("inputStream=="+inputStream);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	            
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	               //System.out.println(line);
	            	result+=line;
	            }
	            System.out.print("result=="+result);
	            
	        }
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        try {
	            inputStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
 
    	return result;
    }
	
	/**
	 * Connection to Servlet with method doPost();
	 * @param servletNamePath
	 * @param data
	 * @return result
	 */
	public String connection2PostServlet(String servletNamePath, List data){
		   
    	String result="";

		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(servletNamePath);
			
			List params = new ArrayList();
			
	        for (Iterator iter = data.iterator(); iter.hasNext(); ) {
	        	String [] xx = (String[]) iter.next();
	        	params.add(new NameValuePair(xx[0], xx[1]));
	        }

			method.setRequestBody((NameValuePair[])params.toArray(new NameValuePair[0]));

			client.executeMethod(method);
			
			result = method.getResponseBodyAsString();

			method.releaseConnection();
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//	        try {
//	            inputStream.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
	    }
 
    	return result;
    }

}
