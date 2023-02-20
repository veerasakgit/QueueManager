/***************************************************/
/* -------------- Create Description ------------- */
/***************************************************/
/* --------------------------------------------------
 * @author Veerasak Boonchern Aey
 * @since 2009/12/08[yyyy/mm/dd]
 * @description Executor for Payment System
 ---------------------------------------------------*/
/****************************************************/
/* ---------------- Modify History ---------------- */
/* ---------------------------------------------------
 * @modifier
 * @since
 * @description
 * -------------------------------------------------*/

package th.co.msat.paymentsystem;

import java.util.Date;
import java.util.List;

import th.co.msat.motor.queuemanager.message.*;
import th.co.msat.motor.util.Util;

public class PaymentSystemMessage implements Message
{
	private String referenceId = null;
	
	private int functionId = 0;
	private String tranType = null;
	private String currency = null;
	private String currRate = null;
	private String[] claimNo = null;
	private int runningNo = 0;
	private List payeeList = null;
	private String remark = null;
	private String payeeCode = null;
	private String payeeName = null;
	private String invDate = null;
	private String invNo = null;
	private String chqPayment = null;
	private String bankCode = null;
	private String clmSttmCode = null;
	private String payeeId = null;
	
	public String getClmSttmCode() {
		return clmSttmCode;
	}
	public void setClmSttmCode(String clmSttmCode) {
		this.clmSttmCode = clmSttmCode;
	}
	public String getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	public void setClaimNo(String[] claimNo) {
		this.claimNo = claimNo;
	}
	public PaymentSystemMessage()
	{
		claimNo = new String[5];
	}
	
	/*public PaymentSystemMessage(String pn)
	{
		setClaimNo(pn);
		System.out.println(this.getBr());
		System.out.println(this.getCat());
		System.out.println(this.getClaim());
		System.out.println(this.getYear());
		System.out.println(this.getPolNo());
		System.out.println(getClaimNo());
	}*/
	
	public String getReferenceId() {return this.referenceId;}
	public void setReferenceId(String referenceId) {this.referenceId = referenceId;}
	
	public void setFunctionId(int functionId) {this.functionId = functionId;}
	public int getFunctionId() {return this.functionId;}
	
	public void setTranType(String tranType) {this.tranType = tranType;}
	public String getTranType() {return this.tranType;}
	
	public void setCurrency(String currency) {this.currency = currency;}
	public String getCurrency() {return this.currency;}
	
	public void setCurrRate(String currRate) {this.currRate = currRate;}
	public String getCurrRate() {return this.currRate;}
	
	/*public void setClaimNo(String clmNo)
	{
		if (clmNo.length() >= 12)
		{
			claimNo[0] = clmNo.substring(0, 2);
			claimNo[1] = clmNo.substring(2, 3);
			
			claimNo[4] = Util.endSubString(clmNo, 6);
			clmNo = clmNo.replaceAll(claimNo[4], "");
			claimNo[3] = Util.endSubString(clmNo, 2);
			clmNo = clmNo.replaceAll(claimNo[3], "");
			
			claimNo[2] = clmNo.replaceAll(claimNo[0] + claimNo[1], "");
		} else {}
	}*/
	public String getClaimNo()
	{
		if (claimNo != null)
			return claimNo[0] + claimNo[1] + claimNo[2] + claimNo[3] + claimNo[4];
		else
			return null;
	}
	
	public void setBr(String br) {this.claimNo[0] = br;}
	public String getBr() {return claimNo[0];}
	
	public void setCat(String cat) {this.claimNo[1] = cat;}
	public String getCat() {return claimNo[1];}
	
	public void setClasses(String classes) {this.claimNo[2] = classes;}
	public String getClasses() {return claimNo[2];}
	
	public void setYear(String year) {this.claimNo[3] = year;}
	public String getYear() {return claimNo[3];}
	
	public void setClmNo(String polNo) {this.claimNo[4] = polNo;}
	public String getClmNo() {return claimNo[4];}
	
	public void setRunningNo(int runningNo) {this.runningNo = runningNo;}
	public int getRunningNo() {return this.runningNo;}
	
	public void setPayeeList(List payeeList) {this.payeeList = payeeList;}
	public List getPayeeList() {return this.payeeList;}
	
	public void setRemark(String remark) {this.remark = remark;}
	public String getRemark() {return this.remark;}
	
	public void setPayeeCode(String payeeCode) {this.payeeCode = payeeCode;}
	public String getPayeeCode() {return this.payeeCode;}
	
	public void setPayeeName(String payeeName) {this.payeeName = payeeName;}
	public String getPayeeName() {return this.payeeName;}
	
	public void setInvDate(String invDate) {this.invDate = invDate;}
	public String getInvDate() {return this.invDate;}
	
	public void setInvNo(String invNo) {this.invNo = invNo;}
	public String getInvNo() {return this.invNo;}
	
	public void setChqPayment(String chqPayment) {this.chqPayment = chqPayment;}
	public String getChqPayment() {return this.chqPayment;}
	
	public void setBankCode(String bankCode) {this.bankCode = bankCode;}
	public String getBankCode() {return this.bankCode;}
	
	public void setClmSettlementCode(String clmSttmCode) {this.clmSttmCode = clmSttmCode;}
	public String getClmSettlementCode() {return this.clmSttmCode;}
	
	/*public static void main(String[] args)
	{
		new PaymentSystemMessage("BKCV08008276");
	}*/
}