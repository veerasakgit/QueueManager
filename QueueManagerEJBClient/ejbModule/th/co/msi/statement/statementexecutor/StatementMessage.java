package th.co.msi.statement.statementexecutor;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

import th.co.msat.motor.queuemanager.message.Message;
import th.co.msitb.statement.model.PatternVO;
import th.co.msitb.statement.model.StatementCriteriaVO;

public class StatementMessage implements Message {

	private String statementId;
	private String startDate;
	private String endDate;
	private String asAt;
	private int patternId;
	private int deleteFlag;
	private int subStatementId;
	private String userId;
	private String status;
	private int statementNo;
	private int receiptTaxStatus;
	private String[] dealer;
	private String[] broker;
	private int sortBy;
	private String classType;
	private int searchById;
	private String coverStartDate;
	private String coverEndDate;
	private String[] classTypeArr;
	private int runningNo;
	
	public StatementMessage(
				String statementId,
				String startDate,
				String endDate,
				String asAt,
				int patternId,
				int deleteFlag,
				int subStatementId,
				String userId,
				String status,
				int statementNo,
				int receiptTaxStatus,
				String[] broker,
				String[] dealer,
				int sortBy,
				String classType,
				int searchById,
				String coverStartDate,
				String coverEndDate,
				String[] classTypeArr,
				int runningNo){
		
		
		this.statementId = statementId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.asAt = asAt;
		this.patternId = patternId;
		this.deleteFlag = deleteFlag;
		this.subStatementId = subStatementId;
		this.userId = userId;
		this.status = status;
		this.statementNo = statementNo;
		this.receiptTaxStatus = receiptTaxStatus;
		this.broker = broker;
		this.dealer = dealer;
		this.sortBy = sortBy;
		this.classType = classType;
		this.searchById = searchById;
		this.coverStartDate = coverStartDate;
		this.coverEndDate = coverEndDate;
		this.classTypeArr = classTypeArr;
		this.runningNo = runningNo;
		
	}

	
	public String[] getClassTypeArr() {
		return classTypeArr;
	}


	public void setClassTypeArr(String[] classTypeArr) {
		this.classTypeArr = classTypeArr;
	}


	public int getRunningNo() {
		return runningNo;
	}


	public void setRunningNo(int runningNo) {
		this.runningNo = runningNo;
	}


	public String[] getBroker() {
		return broker;
	}

	public void setBroker(String[] broker) {
		this.broker = broker;
	}

	public String getCoverEndDate() {
		return coverEndDate;
	}

	public void setCoverEndDate(String coverEndDate) {
		this.coverEndDate = coverEndDate;
	}

	public String getCoverStartDate() {
		return coverStartDate;
	}

	public void setCoverStartDate(String coverStartDate) {
		this.coverStartDate = coverStartDate;
	}

	public int getSearchById() {
		return searchById;
	}

	public void setSearchById(int searchById) {
		this.searchById = searchById;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public int getSortBy() {
		return sortBy;
	}

	public void setSortBy(int sortBy) {
		this.sortBy = sortBy;
	}

	public String[] getDealer() {
		return dealer;
	}

	public void setDealer(String[] dealer) {
		this.dealer = dealer;
	}

	public int getReceiptTaxStatus() {
		return receiptTaxStatus;
	}

	public void setReceiptTaxStatus(int receiptTaxStatus) {
		this.receiptTaxStatus = receiptTaxStatus;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getStatementNo() {
		return statementNo;
	}

	public void setStatementNo(int statementNo) {
		this.statementNo = statementNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getSubStatementId() {
		return subStatementId;
	}

	public void setSubStatementId(int subStatementId) {
		this.subStatementId = subStatementId;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getStatementId() {
		return statementId;
	}

	public void setStatementId(String batchNo) {
		this.statementId = batchNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getAsAt() {
		return asAt;
	}
	
	public void setAsAt(String asAt) {
		this.asAt = asAt;
	}
	
	public String getReferenceId() {
		return this.statementId;
	}

	public int getPatternId() {
		return patternId;
	}

	public void setPatternId(int patternId) {
		this.patternId = patternId;
	}
	
}
