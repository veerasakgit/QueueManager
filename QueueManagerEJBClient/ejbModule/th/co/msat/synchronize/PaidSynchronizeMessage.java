/*
 * Created on 3 ¸.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.synchronize;

import java.math.BigDecimal;

import th.co.msat.motor.queuemanager.message.Message;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PaidSynchronizeMessage implements Message{
	BigDecimal id;
	String claimno;
	String notificationno;
	String username;
	
	/**
	 * @return Returns the username.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return Returns the notificationno.
	 */
	public String getNotificationno() {
		return notificationno;
	}
	/**
	 * @param notificationno The notificationno to set.
	 */
	public void setNotificationno(String notificationno) {
		this.notificationno = notificationno;
	}
	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.message.Message#getReferenceId()
	 */
	public String getReferenceId() {
		// TODO Auto-generated method stub
		return claimno;
	}

	/**
	 * @return Returns the claimno.
	 */
	public String getClaimno() {
		return claimno;
	}
	/**
	 * @param claimno The claimno to set.
	 */
	public void setClaimno(String claimno) {
		this.claimno = claimno;
	}
	/**
	 * @return Returns the id.
	 */
	public BigDecimal getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	
}
