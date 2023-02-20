package th.co.msat.campaign.accumulate;

import java.text.NumberFormat;

public class GeneralFunction {

	public StringBuffer[] splitNumberFormat(float fvalue) {
		StringBuffer[] value = new StringBuffer[2];
		float b = (fvalue * 100) % 100;
		float a = fvalue - (b / 100);
		NumberFormat nf = NumberFormat.getInstance();
		value[0] = new StringBuffer(nf.format(a));
		value[1] = new StringBuffer(nf.format(b));
		if (value[0].toString().equals("0")) {
			value[0] = new StringBuffer("");
			value[1] = new StringBuffer("");
		} else {
			if (value[1].toString().equals("0"))
				value[1] = new StringBuffer("-");
		}
		return value;
	}

	public double usrMRounded(double dbvalue) {
		boolean swap = false;
		if (dbvalue < 0) {
			swap = true;
			dbvalue = dbvalue * (-1);
		}
		double dec = ((dbvalue * 100) % 100) / 100;
		if ((dec >= .01) && (dec < .5))
			dbvalue = dbvalue - dec;
		if ((dec >= .5) && (dec < 1))
			dbvalue = dbvalue + (1 - dec);
		if (swap)
			dbvalue = dbvalue * (-1);
		return dbvalue;
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/00 17:09:19)
	 */
	public double usrMRoundedUp(double dbvalue) {
		boolean swap = false;
		if (dbvalue < 0) {
			swap = true;
			dbvalue = dbvalue * (-1);
		}
		double dec = ((dbvalue * 100) % 100) / 100;
		if ((dec >= .01) && (dec < 1))
			dbvalue = dbvalue + (1 - dec);
		if (swap)
			dbvalue = dbvalue * (-1);
		return dbvalue;
	}

	public String cnvD_M_YtoYMD(String dateSt) {
		if(dateSt == null || dateSt.equals(""))
			return "";
		String[] date = dateSt.split("\\/");
		String dateTemp = "";

		if (dateSt.length() > 10 || dateSt.length() < 10) {
			if (date[0].length() == 1) {
				dateTemp += "0" + date[0] + "/";
			} else {
				dateTemp += date[0] + "/";
			}
			if (date[1].length() == 1) {
				dateTemp += "0" + date[1] + "/";
			} else {
				dateTemp += date[1] + "/";

			}
			dateTemp += date[2];
		}
		StringBuffer temp = new StringBuffer(dateTemp.substring(6));
	
		temp.append(dateTemp.substring(3, 5));
		temp.append(dateTemp.substring(0, 2));
		return temp.toString();

	}
	
	public Long dateToLong(String dateSt) {
		//Convert DD/MM/YYYY to YYYYMMDD
		if(dateSt == null)
			return new Long(0);
		String[] date = dateSt.split("\\/");
		String dateTemp = "";

		if (dateSt.length() > 10 || dateSt.length() < 10) {
			if (date[0].length() == 1) {
				dateTemp += "0" + date[0] + "/";
			} else {
				dateTemp += date[0] + "/";
			}
			if (date[1].length() == 1) {
				dateTemp += "0" + date[1] + "/";
			} else {
				dateTemp += date[1] + "/";
			}
			dateTemp += date[2];
		}else{
			dateTemp = dateSt;
		}
		System.out.println("dateTemp : " + dateTemp);
		StringBuffer temp = new StringBuffer(dateTemp.substring(6));
		temp.append(dateTemp.substring(3, 5));
		temp.append(dateTemp.substring(0, 2));
		return Long.valueOf(temp.toString());

	}

	public String cnvYMDtoDMY_StringFormat(String dateSt) {
		// Conver string format YYYYMMDD to DD/MM/YYYY
		if (dateSt.length() != 8) {
			return "";
		} else {
			StringBuffer temp = new StringBuffer(dateSt.substring(6));
			temp.append("/");
			temp.append(dateSt.substring(4, 6));
			temp.append("/");
			temp.append(dateSt.substring(0, 4));
			return temp.toString();
		}
	}
	
	public String longToDate(Long strDate) {
		// Conver string format YYYYMMDD to DD/MM/YYYY
		String dateSt =  Long.toString(strDate.longValue());
		if (dateSt.length() != 8) {
			return "";
		} else {
			StringBuffer temp = new StringBuffer(dateSt.substring(6));
			temp.append("/");
			temp.append(dateSt.substring(4, 6));
			temp.append("/");
			temp.append(dateSt.substring(0, 4));
			return temp.toString();
		}
	}

	public String toDayString() {
		java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
		String dte = d.toString();
		dte = dte.substring(0, 4) + dte.substring(5, 7) + dte.substring(8);
		return dte;
	}

	public String timeString() {
		String timeSt = "";
		String hoursStr = addString(new Integer(java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY))
				.toString(), "0", 2, false);
		String minutesStr = addString(new Integer(java.util.Calendar.getInstance().get(java.util.Calendar.MINUTE))
				.toString(), "0", 2, false);
		String secondsStr = addString(new Integer(java.util.Calendar.getInstance().get(java.util.Calendar.SECOND))
				.toString(), "0", 2, false);
		timeSt = hoursStr.trim() + minutesStr.trim() + secondsStr.trim();
		return timeSt;
	}

	public String addString(String word, String str, int num, boolean rightText) {

		StringBuffer stb = new StringBuffer(num);
		int len = (word == null) ? 0 : word.length();
		int sp = num - len;
		word = (word == null) ? "" : word;
		str = (str == null) ? " " : str;
		if (sp > 0) {
			stb.append(word);
			if (!rightText)
				stb.reverse();
			for (int i = 0; i < sp; i++)
				stb.append(str);
			if (!rightText)
				stb.reverse();
		} else {
			if (rightText)
				stb.append(word.substring(0, num));
			else
				stb.append(word.substring(len - num));
		}
		return stb.toString();
	}

	public long captureYYMM(long yyyymmdd) {
		long yymm = 0;
		StringBuffer stb = new StringBuffer(String.valueOf(yyyymmdd));
		String yymmTemp = stb.substring(2, 6);
		yymm = Long.parseLong(yymmTemp);
		return yymm;
	}
	
	public  String formatPolicy(String bran, String cls, String year, String polNo, String polNo2, String entd){
		System.out.println(polNo + ", " + polNo2);
		
		String policyNo;
		
		if(polNo.length() == 1){
			policyNo = "0000000" + polNo;
		}else if(polNo.length() == 2){
			policyNo = "000000" + polNo;
		}else if(polNo.length() == 3){
			policyNo = "00000" + polNo;
		}else if(polNo.length() == 4){
			policyNo = "0000" + polNo;
		}else if(polNo.length() == 5){
			policyNo = "000" + polNo;
		}else if(polNo.length() == 6){
			policyNo = "00" + polNo;
		}else if(polNo.length() == 7){
			policyNo = "0" + polNo;
		}else{
			policyNo = polNo;
		}
		return bran.trim() + "/" + cls.trim() + "/" + ((year.length() == 1)? "0" + year:year) + "-" + policyNo.trim() + polNo2.trim();		
	}
	
	//for policy vci
	public String[] splitPolicy(String policy){
		String [] temp = policy.split("\\/"); 
		String [] str = new String[6];
		str[0] = temp[0];
		str[1] = temp[1];
		str[2] = temp[2].substring(0,2);
		str[3] = temp[2].substring(3,11);
		str[4] = temp[2].substring(11,12);
		//str[5] = temp[2].substring(12,13);
		return str;
	}
	
	public static void main(String [] args){
		System.out.println(new GeneralFunction().formatPolicy("BD", "VCI", "09", "9005118", "1", ""));
//		String [] str = new GeneralFunction().splitPolicy("BD/VCI/09-0090219231");
//		for (int i = 0; i < str.length; i++) {
//			System.out.println(str[i]);
//		}
	}

}
