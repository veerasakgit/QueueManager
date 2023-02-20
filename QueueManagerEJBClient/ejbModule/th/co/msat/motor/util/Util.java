/***************************************************/
/* -------------- Create Description ------------- */
/***************************************************/
/* --------------------------------------------------
 * @author Veerasak Boonchern Aey
 * @since 2009/12/08[yyyy/mm/dd]
 * @description Message for Payment System
 ---------------------------------------------------*/
/****************************************************/
/* ---------------- Modify History ---------------- */
/* ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2010/02/12[yyyy/mm/dd]
 * @description Add chkStrNull() and zeroAtFront()
 * -------------------------------------------------*/

package th.co.msat.motor.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Util
{
	public static String endSubString(String input, int length)
	{
		String output = new String();
		for (int i = 0; i < length; i++)
		{
			output = input.charAt(input.length() - 1 - i) + output;
		}
		return output;
	}
	
	//Start Add method Veerasak Boonchern Aey 2010/02/12[yyyy/mm/dd]
	public static String chkStrNull(String input)
	{
		String output = "";
		if (input != null) output = input.trim();
		return output;
	}
	
	public static String zeroAtFront(String input, int length)
	{
		String output = input;
		
		for (int i = output.length(); i < length; i++)
			output = "0" + output;
		
		return output;
	}
	//End Add method Veerasak Boonchern Aey 2010/02/12[yyyy/mm/dd]
	
	public static String connection2Servlet(String servletNamePath, String param, String method)
	{ 
		String result = "";
		URL url_;
		InputStream inputStream =null;
		
		try
		{
			url_ = new URL(servletNamePath);
			URLConnection connection = url_.openConnection();
			if (connection instanceof HttpURLConnection)
			{
				HttpURLConnection httpConnection = (HttpURLConnection)connection;
				httpConnection.setRequestMethod(method);
				httpConnection.setDoOutput(true);
				httpConnection.connect();
				
				//Initialize output stream
				DataOutputStream outttt = new DataOutputStream( httpConnection.getOutputStream() );
				//write dataList
				outttt.writeBytes(param);
				outttt.flush();
				//wait response
				inputStream = httpConnection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				
				String line = null;
				while ((line = reader.readLine()) != null)
				{
					result += line;
				}
				httpConnection.disconnect();
			}
		} catch (MalformedURLException e1)
		{
			e1.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (inputStream != null)
			{
				try
				{
					inputStream.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
}
