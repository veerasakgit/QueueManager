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

package th.co.msat.reqsprinting;

import java.math.BigDecimal;

import th.co.msat.motor.queuemanager.message.Message;

public class RequisitionPrintingMessage implements Message
{
	private String referenceId = null;
	
	private String[] claimNo = null;
	private String paymentDateFrm = null;
	private String paymentDateTo = null;
	private String payeeCode = null;
	private String entryDateFrm = null;
	private String entryDateTo = null;
	private String userId = null;
	private BigDecimal amnt = null;
	private int typeOfPayment = 0;
	
	public RequisitionPrintingMessage() {claimNo = new String[5];}

	public String getReferenceId() {return referenceId;}
	public void setReferenceId(String referenceId) {this.referenceId = referenceId;}
	
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
	
	public void setPaymentDateFrm(String paymentDateFrm) {this.paymentDateFrm = paymentDateFrm;}
	public String getPaymentDateFrm() {return this.paymentDateFrm;}
	
	public void setPaymentDateTo(String paymentDateTo) {this.paymentDateTo = paymentDateTo;}
	public String getPaymentDateTo() {return this.paymentDateTo;}
	
	public void setPayeeCode(String payeeCode) {this.payeeCode = payeeCode;}
	public String getPayeeCode() {return this.payeeCode;}
	
	public void setEntryDateFrm(String entryDateFrm) {this.entryDateFrm = entryDateFrm;}
	public String getEntryDateFrm() {return this.entryDateFrm;}
	
	public void setEntryDateTo(String entryDateTo) {this.entryDateTo = entryDateTo;}
	public String getEntryDateTo() {return this.entryDateTo;}
	
	public void setUserId(String userId) {this.userId = userId;}
	public String getUserId() {return this.userId;}
	
	public void setAmount(BigDecimal amnt) {this.amnt = amnt;}
	public BigDecimal getAmount() {return this.amnt;}
	
	public void setTypeOfPayment(int typeOfPayment) {this.typeOfPayment = typeOfPayment;}
	public int getTypeOfPayment() {return this.typeOfPayment;}
}
