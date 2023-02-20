/*
 * Created on 25 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/****************************************************/
/* ----------------- Modify History -----------------*/
/****************************************************/
/* ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/18[yyyy/mm/dd]
 * @description Reset queue and recovery pool
 * --------------------------------------------------*/

package th.co.msat.motor.queuemanager.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import th.co.msat.motor.queuemanager.message.Message;



/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JobVO implements Serializable {
	
	public BigDecimal getJobid() {
		return jobid;
	}
	public void setJobid(BigDecimal jobid) {
		this.jobid = jobid;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public int getPiority() {
		return piority;
	}
	public void setPiority(int piority) {
		this.piority = piority;
	}
	public boolean isRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(boolean recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubmintUser() {
		return submintUser;
	}
	public void setSubmintUser(String submintUser) {
		this.submintUser = submintUser;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateProgram() {
		return updateProgram;
	}
	public void setUpdateProgram(String updateProgram) {
		this.updateProgram = updateProgram;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	private BigDecimal jobid;
	private Message message;
	private String status;
	private int piority;
	private Date submitDate;
	private String submintUser;
	private Date updateDate;
	private String updateProgram;
	private String updateUser;
	private boolean recordStatus;
	private String referenceno;
	private long jobgroup;
	private String displaySubmitDate;
	
	public String getDisplaySubmitDate() {
		String d = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a",Locale.US).format(submitDate);
		return d;
	}
	public void setDisplaySubmitDate(String displaySubmitDate) {
		this.displaySubmitDate = displaySubmitDate;
	}
	public long getJobgroup() {
		return jobgroup;
	}
	public void setJobgroup(long jobgroup) {
		this.jobgroup = jobgroup;
	}
	public String getReferenceno() {
		return referenceno;
	}
	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	
	//Start Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
	private String resultMessage = null;
	
	public void setResultMessage(String resultMessage) {this.resultMessage = resultMessage;}
	public String getResultMessage() {return this.resultMessage;}
	//End Reset queue Veerasak Boonchern Aey 2009/09/18[yyyy/mm/dd]
}
