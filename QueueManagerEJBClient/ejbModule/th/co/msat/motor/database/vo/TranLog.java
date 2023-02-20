/*
 * Created on 14 ¡.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.database.vo;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TranLog {
//	public static final int LOG_CODE_001 = 1;
//	public static final int LOG_STATUS_01 = 1;
	private int logFor;
	private String logName;
	private int logDate;
	private int logTime;
	private int logStatus;
	private String logMessage;
	private String logRecordId;
	private int logIndex;
	public int getLogDate() {
		return logDate;
	}
	public void setLogDate(int logDate) {
		this.logDate = logDate;
	}
	public int getLogFor() {
		return logFor;
	}
	public void setLogFor(int logFor) {
		this.logFor = logFor;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public int getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(int logStatus) {
		this.logStatus = logStatus;
	}
	public int getLogTime() {
		return logTime;
	}
	public void setLogTime(int logTime) {
		this.logTime = logTime;
	}
	public String getLogRecordId() {
		return logRecordId;
	}
	public void setLogRecordId(String logRecordId) {
		this.logRecordId = logRecordId;
	}
	public int getLogIndex() {
		return logIndex;
	}
	public void setLogIndex(int logIndex) {
		this.logIndex = logIndex;
	}
}
