/*
 * Created on 5 Ê.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.database.vo;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotificationPK {
	private String	branchCode;        
	private String	categories;
	private String	classes;
	private String	year;   
	private String	policyNo; 
	
	public NotificationPK(){
		
	}
	
	public NotificationPK(String policyNo){
		setBranchCode(getPolicyNo1(policyNo));
		setCategories(getPolicyNo2(policyNo));
		setClasses(getPolicyNo3(policyNo));
		setYear(getPolicyNo3(policyNo));
		setPolicyNo(getPolicyNo4(policyNo));
	}
	
	public String getPlanPolicyNo(){
		return branchCode+categories+classes+year+policyNo;
	}
	
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
//	public String getEnddos() {
//		return enddos;
//	}
//	public void setEnddos(String enddos) {
//		this.enddos = enddos;
//	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getPolicyNo1(String policy){
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(policy);
		if(m.find()){
//			System.out.println(m.group());
			String s = m.group();
			return s.substring(0,3);
		}else
			return "";
	}
	public String getPolicyNo2(String policy){
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(policy);
		if(m.find()){
//			System.out.println(m.group());
			String s = m.group();
			return s.substring(3);
		}else
			return "";
	}
	public String getPolicyNo3(String policy){
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(policy);
		if(m.find()){
//			System.out.println(m.group());
			String s = m.group();
			return s.substring(0,2);
		}else
			return "";
	}
	public String getPolicyNo4(String policy){
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(policy);
		if(m.find()){
//			System.out.println(m.group());
			String s = m.group();
			s= s.substring(2);
			s=new BigDecimal(s).toString();
			while(s.length()<6){
				s="0"+s;
			}
			return s;
		}else
			return "";
	}
	public String getRelation(String desc){
		desc =desc.trim();
		if("R".equals(desc.substring(1,2))){
			return "Third party";
		}else if("F".equals(desc.substring(1,2))){
			return "Insured";
		}else{
			return "";
		}
		
	}
	
}
