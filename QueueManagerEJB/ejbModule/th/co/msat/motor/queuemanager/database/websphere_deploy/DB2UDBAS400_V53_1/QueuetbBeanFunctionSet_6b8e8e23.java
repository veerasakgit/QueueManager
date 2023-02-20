package th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1;

import com.ibm.websphere.rsadapter.WSInteractionSpec;
import com.ibm.ws.rsadapter.exceptions.DataStoreAdapterException;
import javax.resource.ResourceException;
import javax.resource.cci.Record;
import javax.resource.cci.IndexedRecord;
import java.sql.*;

/**
 * QueuetbBeanFunctionSet_6b8e8e23
 */
public class QueuetbBeanFunctionSet_6b8e8e23 extends com.ibm.ws.rsadapter.cci.WSResourceAdapterBase implements com.ibm.websphere.rsadapter.DataAccessFunctionSet {
	private java.util.HashMap functionHash;
	/**
	 * Create
	 */
	public void Create(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		try {
			pstmt = prepareStatement(connection,"INSERT INTO MSATLIB.QUEUETB (QUEUEID, QUEUENAME, SIZE, AVAILABLE, ISTERMINATE, WORKINGTIME, MESSAGECLASS, ACTIVATECLASS, CREATEDATE, CREATETIME, CREATEUSER, UPDATEDATE, UPDATETIME, UPDATEUSER, UPDATEPROGRAM, RECORDSTATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			// For column QUEUEID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(1,tempBigDecimal);
				else
					pstmt.setNull(1,java.sql.Types.NUMERIC);
			}
			// For column QUEUENAME
			{
				String tempString;


				tempString=(String)inputRecord.get(1);
				if(tempString != null)
					pstmt.setString(2,tempString);
				else
					pstmt.setNull(2,java.sql.Types.CHAR);
			}
			// For column SIZE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(2);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(3,tempBigDecimal);
				else
					pstmt.setNull(3,java.sql.Types.NUMERIC);
			}
			// For column AVAILABLE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(3);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(4,tempBigDecimal);
				else
					pstmt.setNull(4,java.sql.Types.NUMERIC);
			}
			// For column ISTERMINATE
			{
				String tempString;


				tempString=(String)inputRecord.get(4);
				if(tempString != null)
					pstmt.setString(5,tempString);
				else
					pstmt.setNull(5,java.sql.Types.CHAR);
			}
			// For column WORKINGTIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(5);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(6,tempBigDecimal);
				else
					pstmt.setNull(6,java.sql.Types.NUMERIC);
			}
			// For column MESSAGECLASS
			{
				String tempString;


				tempString=(String)inputRecord.get(6);
				if(tempString != null)
					pstmt.setString(7,tempString);
				else
					pstmt.setNull(7,java.sql.Types.CHAR);
			}
			// For column ACTIVATECLASS
			{
				String tempString;


				tempString=(String)inputRecord.get(7);
				if(tempString != null)
					pstmt.setString(8,tempString);
				else
					pstmt.setNull(8,java.sql.Types.CHAR);
			}
			// For column CREATEDATE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(8);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(9,tempBigDecimal);
				else
					pstmt.setNull(9,java.sql.Types.NUMERIC);
			}
			// For column CREATETIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(9);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(10,tempBigDecimal);
				else
					pstmt.setNull(10,java.sql.Types.NUMERIC);
			}
			// For column CREATEUSER
			{
				String tempString;


				tempString=(String)inputRecord.get(10);
				if(tempString != null)
					pstmt.setString(11,tempString);
				else
					pstmt.setNull(11,java.sql.Types.CHAR);
			}
			// For column UPDATEDATE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(11);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(12,tempBigDecimal);
				else
					pstmt.setNull(12,java.sql.Types.NUMERIC);
			}
			// For column UPDATETIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(12);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(13,tempBigDecimal);
				else
					pstmt.setNull(13,java.sql.Types.NUMERIC);
			}
			// For column UPDATEUSER
			{
				String tempString;


				tempString=(String)inputRecord.get(13);
				if(tempString != null)
					pstmt.setString(14,tempString);
				else
					pstmt.setNull(14,java.sql.Types.CHAR);
			}
			// For column UPDATEPROGRAM
			{
				String tempString;


				tempString=(String)inputRecord.get(14);
				if(tempString != null)
					pstmt.setString(15,tempString);
				else
					pstmt.setNull(15,java.sql.Types.CHAR);
			}
			// For column RECORDSTATUS
			{
				String tempString;


				tempString=(String)inputRecord.get(15);
				if(tempString != null)
					pstmt.setString(16,tempString);
				else
					pstmt.setNull(16,java.sql.Types.CHAR);
			}
			if (executeUpdate(connection, pstmt)==0)
				throw createResourceException(new javax.ejb.NoSuchEntityException(), this.getClass());

		}
		catch (SQLException e) {
			throw createResourceException(e, this.getClass());
		}
		finally {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored)
			{}
		}
	}
	/**
	 * Remove
	 */
	public void Remove(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		try {
			pstmt = prepareStatement(connection,"DELETE FROM MSATLIB.QUEUETB  WHERE QUEUEID = ?");

			// For column QUEUEID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(1,tempBigDecimal);
				else
					pstmt.setNull(1,java.sql.Types.NUMERIC);
			}
			if (executeUpdate(connection, pstmt)==0)
				throw createResourceException(new javax.ejb.NoSuchEntityException(), this.getClass());

		}
		catch (SQLException e) {
			throw createResourceException(e, this.getClass());
		}
		finally {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored)
			{}
		}
	}
	/**
	 * Store
	 */
	public void Store(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		try {
			pstmt = prepareStatement(connection,"UPDATE MSATLIB.QUEUETB  SET QUEUENAME = ?, SIZE = ?, AVAILABLE = ?, ISTERMINATE = ?, WORKINGTIME = ?, MESSAGECLASS = ?, ACTIVATECLASS = ?, CREATEDATE = ?, CREATETIME = ?, CREATEUSER = ?, UPDATEDATE = ?, UPDATETIME = ?, UPDATEUSER = ?, UPDATEPROGRAM = ?, RECORDSTATUS = ? WHERE QUEUEID = ?");

			// For column QUEUEID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(16,tempBigDecimal);
				else
					pstmt.setNull(16,java.sql.Types.NUMERIC);
			}
			// For column QUEUENAME
			{
				String tempString;


				tempString=(String)inputRecord.get(1);
				if(tempString != null)
					pstmt.setString(1,tempString);
				else
					pstmt.setNull(1,java.sql.Types.CHAR);
			}
			// For column SIZE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(2);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(2,tempBigDecimal);
				else
					pstmt.setNull(2,java.sql.Types.NUMERIC);
			}
			// For column AVAILABLE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(3);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(3,tempBigDecimal);
				else
					pstmt.setNull(3,java.sql.Types.NUMERIC);
			}
			// For column ISTERMINATE
			{
				String tempString;


				tempString=(String)inputRecord.get(4);
				if(tempString != null)
					pstmt.setString(4,tempString);
				else
					pstmt.setNull(4,java.sql.Types.CHAR);
			}
			// For column WORKINGTIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(5);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(5,tempBigDecimal);
				else
					pstmt.setNull(5,java.sql.Types.NUMERIC);
			}
			// For column MESSAGECLASS
			{
				String tempString;


				tempString=(String)inputRecord.get(6);
				if(tempString != null)
					pstmt.setString(6,tempString);
				else
					pstmt.setNull(6,java.sql.Types.CHAR);
			}
			// For column ACTIVATECLASS
			{
				String tempString;


				tempString=(String)inputRecord.get(7);
				if(tempString != null)
					pstmt.setString(7,tempString);
				else
					pstmt.setNull(7,java.sql.Types.CHAR);
			}
			// For column CREATEDATE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(8);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(8,tempBigDecimal);
				else
					pstmt.setNull(8,java.sql.Types.NUMERIC);
			}
			// For column CREATETIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(9);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(9,tempBigDecimal);
				else
					pstmt.setNull(9,java.sql.Types.NUMERIC);
			}
			// For column CREATEUSER
			{
				String tempString;


				tempString=(String)inputRecord.get(10);
				if(tempString != null)
					pstmt.setString(10,tempString);
				else
					pstmt.setNull(10,java.sql.Types.CHAR);
			}
			// For column UPDATEDATE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(11);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(11,tempBigDecimal);
				else
					pstmt.setNull(11,java.sql.Types.NUMERIC);
			}
			// For column UPDATETIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(12);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(12,tempBigDecimal);
				else
					pstmt.setNull(12,java.sql.Types.NUMERIC);
			}
			// For column UPDATEUSER
			{
				String tempString;


				tempString=(String)inputRecord.get(13);
				if(tempString != null)
					pstmt.setString(13,tempString);
				else
					pstmt.setNull(13,java.sql.Types.CHAR);
			}
			// For column UPDATEPROGRAM
			{
				String tempString;


				tempString=(String)inputRecord.get(14);
				if(tempString != null)
					pstmt.setString(14,tempString);
				else
					pstmt.setNull(14,java.sql.Types.CHAR);
			}
			// For column RECORDSTATUS
			{
				String tempString;


				tempString=(String)inputRecord.get(15);
				if(tempString != null)
					pstmt.setString(15,tempString);
				else
					pstmt.setNull(15,java.sql.Types.CHAR);
			}
			if (executeUpdate(connection, pstmt)==0)
				throw createResourceException(new javax.ejb.NoSuchEntityException(), this.getClass());

		}
		catch (SQLException e) {
			throw createResourceException(e, this.getClass());
		}
		finally {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored)
			{}
		}
	}
	/**
	 * StoreUsingOCC
	 */
	public void StoreUsingOCC(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		try {
			pstmt = prepareStatement(connection,"UPDATE MSATLIB.QUEUETB  SET QUEUENAME = ?, SIZE = ?, AVAILABLE = ?, ISTERMINATE = ?, WORKINGTIME = ?, MESSAGECLASS = ?, ACTIVATECLASS = ?, CREATEDATE = ?, CREATETIME = ?, CREATEUSER = ?, UPDATEDATE = ?, UPDATETIME = ?, UPDATEUSER = ?, UPDATEPROGRAM = ?, RECORDSTATUS = ? WHERE QUEUEID = ?");

			// For column QUEUEID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(16,tempBigDecimal);
				else
					pstmt.setNull(16,java.sql.Types.NUMERIC);
			}
			// For column QUEUENAME
			{
				String tempString;


				tempString=(String)inputRecord.get(1);
				if(tempString != null)
					pstmt.setString(1,tempString);
				else
					pstmt.setNull(1,java.sql.Types.CHAR);
			}
			// For column SIZE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(2);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(2,tempBigDecimal);
				else
					pstmt.setNull(2,java.sql.Types.NUMERIC);
			}
			// For column AVAILABLE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(3);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(3,tempBigDecimal);
				else
					pstmt.setNull(3,java.sql.Types.NUMERIC);
			}
			// For column ISTERMINATE
			{
				String tempString;


				tempString=(String)inputRecord.get(4);
				if(tempString != null)
					pstmt.setString(4,tempString);
				else
					pstmt.setNull(4,java.sql.Types.CHAR);
			}
			// For column WORKINGTIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(5);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(5,tempBigDecimal);
				else
					pstmt.setNull(5,java.sql.Types.NUMERIC);
			}
			// For column MESSAGECLASS
			{
				String tempString;


				tempString=(String)inputRecord.get(6);
				if(tempString != null)
					pstmt.setString(6,tempString);
				else
					pstmt.setNull(6,java.sql.Types.CHAR);
			}
			// For column ACTIVATECLASS
			{
				String tempString;


				tempString=(String)inputRecord.get(7);
				if(tempString != null)
					pstmt.setString(7,tempString);
				else
					pstmt.setNull(7,java.sql.Types.CHAR);
			}
			// For column CREATEDATE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(8);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(8,tempBigDecimal);
				else
					pstmt.setNull(8,java.sql.Types.NUMERIC);
			}
			// For column CREATETIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(9);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(9,tempBigDecimal);
				else
					pstmt.setNull(9,java.sql.Types.NUMERIC);
			}
			// For column CREATEUSER
			{
				String tempString;


				tempString=(String)inputRecord.get(10);
				if(tempString != null)
					pstmt.setString(10,tempString);
				else
					pstmt.setNull(10,java.sql.Types.CHAR);
			}
			// For column UPDATEDATE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(11);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(11,tempBigDecimal);
				else
					pstmt.setNull(11,java.sql.Types.NUMERIC);
			}
			// For column UPDATETIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(12);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(12,tempBigDecimal);
				else
					pstmt.setNull(12,java.sql.Types.NUMERIC);
			}
			// For column UPDATEUSER
			{
				String tempString;


				tempString=(String)inputRecord.get(13);
				if(tempString != null)
					pstmt.setString(13,tempString);
				else
					pstmt.setNull(13,java.sql.Types.CHAR);
			}
			// For column UPDATEPROGRAM
			{
				String tempString;


				tempString=(String)inputRecord.get(14);
				if(tempString != null)
					pstmt.setString(14,tempString);
				else
					pstmt.setNull(14,java.sql.Types.CHAR);
			}
			// For column RECORDSTATUS
			{
				String tempString;


				tempString=(String)inputRecord.get(15);
				if(tempString != null)
					pstmt.setString(15,tempString);
				else
					pstmt.setNull(15,java.sql.Types.CHAR);
			}
			IndexedRecord oldRecord = interactionSpec.getOldRecord();
			if (executeUpdate(connection, pstmt)==0)
				throw createResourceException(new javax.ejb.NoSuchEntityException(), this.getClass());

		}
		catch (SQLException e) {
			throw createResourceException(e, this.getClass());
		}
		finally {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored)
			{}
		}
	}
	/**
	 * FindByQueueName
	 */
	public javax.resource.cci.Record FindByQueueName(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		ResultSet result=null;
		try {
			/* select object(o) from Queuetb o where  o.queuename = ?1 */
			pstmt = prepareStatement(connection,"select  q1.\"QUEUEID\",  q1.\"QUEUENAME\",  q1.\"SIZE\",  q1.\"AVAILABLE\",  q1.\"ISTERMINATE\",  q1.\"WORKINGTIME\",  q1.\"MESSAGECLASS\",  q1.\"ACTIVATECLASS\",  q1.\"CREATEDATE\",  q1.\"CREATETIME\",  q1.\"CREATEUSER\",  q1.\"UPDATEDATE\",  q1.\"UPDATETIME\",  q1.\"UPDATEUSER\",  q1.\"UPDATEPROGRAM\",  q1.\"RECORDSTATUS\" from MSATLIB.QUEUETB q1 where  ( q1.\"QUEUENAME\" = ?)");

			// For ?1 (queueName)
			{
				String tempString;


				tempString=(String)inputRecord.get(0);
				if(tempString != null)
					pstmt.setString(1,tempString);
				else
					pstmt.setNull(1,java.sql.Types.VARCHAR);
			}
			result = pstmt.executeQuery();

		}
		catch (java.lang.Throwable e) {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored){}
			throw createResourceException(e, this.getClass());
		}
		return createCCIRecord(connection, result);
	}
	/**
	 * FindByQueueName444Update
	 */
	public javax.resource.cci.Record FindByQueueName444Update(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		ResultSet result=null;
		try {
			/* select object(o) from Queuetb o where  o.queuename = ?1 */
			pstmt = prepareStatement(connection,"select  q1.\"QUEUEID\",  q1.\"QUEUENAME\",  q1.\"SIZE\",  q1.\"AVAILABLE\",  q1.\"ISTERMINATE\",  q1.\"WORKINGTIME\",  q1.\"MESSAGECLASS\",  q1.\"ACTIVATECLASS\",  q1.\"CREATEDATE\",  q1.\"CREATETIME\",  q1.\"CREATEUSER\",  q1.\"UPDATEDATE\",  q1.\"UPDATETIME\",  q1.\"UPDATEUSER\",  q1.\"UPDATEPROGRAM\",  q1.\"RECORDSTATUS\" from MSATLIB.QUEUETB q1 where  ( q1.\"QUEUENAME\" = ?)  for update of \"QUEUENAME\"");

			// For ?1 (queueName)
			{
				String tempString;


				tempString=(String)inputRecord.get(0);
				if(tempString != null)
					pstmt.setString(1,tempString);
				else
					pstmt.setNull(1,java.sql.Types.VARCHAR);
			}
			result = pstmt.executeQuery();

		}
		catch (java.lang.Throwable e) {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored){}
			throw createResourceException(e, this.getClass());
		}
		return createCCIRecord(connection, result);
	}
	/**
	 * FindAllQueue
	 */
	public javax.resource.cci.Record FindAllQueue(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		ResultSet result=null;
		try {
			/* select object(o) from Queuetb o */
			pstmt = prepareStatement(connection,"select  q1.\"QUEUEID\",  q1.\"QUEUENAME\",  q1.\"SIZE\",  q1.\"AVAILABLE\",  q1.\"ISTERMINATE\",  q1.\"WORKINGTIME\",  q1.\"MESSAGECLASS\",  q1.\"ACTIVATECLASS\",  q1.\"CREATEDATE\",  q1.\"CREATETIME\",  q1.\"CREATEUSER\",  q1.\"UPDATEDATE\",  q1.\"UPDATETIME\",  q1.\"UPDATEUSER\",  q1.\"UPDATEPROGRAM\",  q1.\"RECORDSTATUS\" from MSATLIB.QUEUETB q1");

			result = pstmt.executeQuery();

		}
		catch (java.lang.Throwable e) {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored){}
			throw createResourceException(e, this.getClass());
		}
		return createCCIRecord(connection, result);
	}
	/**
	 * FindAllQueue444Update
	 */
	public javax.resource.cci.Record FindAllQueue444Update(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		ResultSet result=null;
		try {
			/* select object(o) from Queuetb o */
			pstmt = prepareStatement(connection,"select  q1.\"QUEUEID\",  q1.\"QUEUENAME\",  q1.\"SIZE\",  q1.\"AVAILABLE\",  q1.\"ISTERMINATE\",  q1.\"WORKINGTIME\",  q1.\"MESSAGECLASS\",  q1.\"ACTIVATECLASS\",  q1.\"CREATEDATE\",  q1.\"CREATETIME\",  q1.\"CREATEUSER\",  q1.\"UPDATEDATE\",  q1.\"UPDATETIME\",  q1.\"UPDATEUSER\",  q1.\"UPDATEPROGRAM\",  q1.\"RECORDSTATUS\" from MSATLIB.QUEUETB q1 for update of \"QUEUENAME\"");

			result = pstmt.executeQuery();

		}
		catch (java.lang.Throwable e) {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored){}
			throw createResourceException(e, this.getClass());
		}
		return createCCIRecord(connection, result);
	}
	/**
	 * FindByPrimaryKey
	 */
	public javax.resource.cci.Record FindByPrimaryKey(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		ResultSet result=null;
		try {
			pstmt = prepareStatement(connection," SELECT T1.QUEUEID, T1.QUEUENAME, T1.SIZE, T1.AVAILABLE, T1.ISTERMINATE, T1.WORKINGTIME, T1.MESSAGECLASS, T1.ACTIVATECLASS, T1.CREATEDATE, T1.CREATETIME, T1.CREATEUSER, T1.UPDATEDATE, T1.UPDATETIME, T1.UPDATEUSER, T1.UPDATEPROGRAM, T1.RECORDSTATUS FROM MSATLIB.QUEUETB  T1 WHERE T1.QUEUEID = ?");

			// For column QUEUEID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(1,tempBigDecimal);
				else
					pstmt.setNull(1,java.sql.Types.NUMERIC);
			}
			result = executeQuery(connection, pstmt);

		}
		catch (java.lang.Throwable e) {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored){}
			throw createResourceException(e, this.getClass());
		}
		return createCCIRecord(connection, result);
	}
	/**
	 * FindByPrimaryKey444Update
	 */
	public javax.resource.cci.Record FindByPrimaryKey444Update(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		ResultSet result=null;
		try {
			pstmt = prepareStatement(connection," SELECT T1.QUEUEID, T1.QUEUENAME, T1.SIZE, T1.AVAILABLE, T1.ISTERMINATE, T1.WORKINGTIME, T1.MESSAGECLASS, T1.ACTIVATECLASS, T1.CREATEDATE, T1.CREATETIME, T1.CREATEUSER, T1.UPDATEDATE, T1.UPDATETIME, T1.UPDATEUSER, T1.UPDATEPROGRAM, T1.RECORDSTATUS FROM MSATLIB.QUEUETB  T1 WHERE T1.QUEUEID = ? FOR UPDATE  OF QUEUENAME WITH RS");

			// For column QUEUEID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(1,tempBigDecimal);
				else
					pstmt.setNull(1,java.sql.Types.NUMERIC);
			}
			result = executeQuery(connection, pstmt);

		}
		catch (java.lang.Throwable e) {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored){}
			throw createResourceException(e, this.getClass());
		}
		return createCCIRecord(connection, result);
	}
	/**
	 * QueuetbBeanFunctionSet_6b8e8e23
	 */
	public QueuetbBeanFunctionSet_6b8e8e23() {
		functionHash=new java.util.HashMap(14);

		functionHash.put("Create",new Integer(0));
		functionHash.put("Remove",new Integer(1));
		functionHash.put("Store",new Integer(2));
		functionHash.put("StoreUsingOCC",new Integer(3));
		functionHash.put("FindByQueueName",new Integer(4));
		functionHash.put("FindByQueueName444Update",new Integer(5));
		functionHash.put("FindAllQueue",new Integer(6));
		functionHash.put("FindAllQueue444Update",new Integer(7));
		functionHash.put("FindByPrimaryKey",new Integer(8));
		functionHash.put("FindByPrimaryKey444Update",new Integer(9));
		functionHash.put("ReadReadChecking",new Integer(10));
		functionHash.put("ReadReadCheckingForUpdate",new Integer(11));
		functionHash.put("PartialStore",new Integer(12));
		functionHash.put("PartialStoreUsingOCC",new Integer(13));
	}
	/**
	 * execute
	 */
	public Record execute(WSInteractionSpec interactionSpec, IndexedRecord inputRecord, Object connection) throws javax.resource.ResourceException {
		String functionName=interactionSpec.getFunctionName();
		Record outputRecord=null;
		int functionIndex=((Integer)functionHash.get(functionName)).intValue();

		switch (functionIndex) {
		case 0:
			Create(inputRecord,connection,interactionSpec);
			break;
		case 1:
			Remove(inputRecord,connection,interactionSpec);
			break;
		case 2:
			Store(inputRecord,connection,interactionSpec);
			break;
		case 3:
			StoreUsingOCC(inputRecord,connection,interactionSpec);
			break;
		case 4:
			outputRecord=FindByQueueName(inputRecord,connection,interactionSpec);
			break;
		case 5:
			outputRecord=FindByQueueName444Update(inputRecord,connection,interactionSpec);
			break;
		case 6:
			outputRecord=FindAllQueue(inputRecord,connection,interactionSpec);
			break;
		case 7:
			outputRecord=FindAllQueue444Update(inputRecord,connection,interactionSpec);
			break;
		case 8:
			outputRecord=FindByPrimaryKey(inputRecord,connection,interactionSpec);
			break;
		case 9:
			outputRecord=FindByPrimaryKey444Update(inputRecord,connection,interactionSpec);
			break;
		case 10:
			ReadReadChecking(inputRecord,connection,interactionSpec);
			break;
		case 11:
			ReadReadCheckingForUpdate(inputRecord,connection,interactionSpec);
			break;
		case 12:
			PartialStore(inputRecord,connection,interactionSpec);
			break;
		case 13:
			PartialStoreUsingOCC(inputRecord,connection,interactionSpec);
			break;
		}
		return outputRecord;
	}
	/**
	 * ReadReadChecking
	 */
	public void ReadReadChecking(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		try {
			pstmt = prepareStatement(connection," SELECT 1 FROM MSATLIB.QUEUETB  T1 WHERE T1.QUEUEID = ?");

			// For column QUEUEID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(1,tempBigDecimal);
				else
					pstmt.setNull(1,java.sql.Types.NUMERIC);
			}
			executeQuery(connection, pstmt);

		}
		catch (SQLException e) {
			throw createResourceException(e, this.getClass());
		}
		finally {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored)
			{}
		}
	}
	/**
	 * ReadReadCheckingForUpdate
	 */
	public void ReadReadCheckingForUpdate(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		try {
			pstmt = prepareStatement(connection," SELECT 1 FROM MSATLIB.QUEUETB  T1 WHERE T1.QUEUEID = ? FOR UPDATE  OF QUEUENAME WITH RS");

			// For column QUEUEID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(1,tempBigDecimal);
				else
					pstmt.setNull(1,java.sql.Types.NUMERIC);
			}
			executeQuery(connection, pstmt);

		}
		catch (SQLException e) {
			throw createResourceException(e, this.getClass());
		}
		finally {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored)
			{}
		}
	}
	/**
	 * initializeUpdateTemplates
	 */
	private void initializeUpdateTemplates() throws javax.resource.ResourceException {
		updateTemplateList = QueuetbBeanPartialUpdateQueryHelper.getUpdateTemplates();
	}
	private java.util.List updateTemplateList = null;
	/**
	 * PartialStore
	 */
	public void PartialStore(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		try {
			boolean[] changedFieldIndexes = interactionSpec.getChangedFieldsIndexes();
			if (updateTemplateList == null)
				initializeUpdateTemplates();
			{
				com.ibm.ws.ejbdeploy.JQueueManagerEJB.DB2UDBAS400_V53_1.RdbRuntimeUpdateTemplate aTemplate = (com.ibm.ws.ejbdeploy.JQueueManagerEJB.DB2UDBAS400_V53_1.RdbRuntimeUpdateTemplate) updateTemplateList.get(0);
				String updateQuery = aTemplate.nativeQuery(changedFieldIndexes);
				if (updateQuery != null){
					pstmt = prepareStatement(connection, updateQuery);

					int stmtIndex = 1;
					if (changedFieldIndexes[1])
					{
						// For column QUEUENAME
						String tempString;


						tempString=(String)inputRecord.get(1);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[2])
					{
						// For column SIZE
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(2);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[3])
					{
						// For column AVAILABLE
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(3);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[4])
					{
						// For column ISTERMINATE
						String tempString;


						tempString=(String)inputRecord.get(4);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[5])
					{
						// For column WORKINGTIME
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(5);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[6])
					{
						// For column MESSAGECLASS
						String tempString;


						tempString=(String)inputRecord.get(6);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[7])
					{
						// For column ACTIVATECLASS
						String tempString;


						tempString=(String)inputRecord.get(7);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[8])
					{
						// For column CREATEDATE
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(8);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[9])
					{
						// For column CREATETIME
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(9);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[10])
					{
						// For column CREATEUSER
						String tempString;


						tempString=(String)inputRecord.get(10);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[11])
					{
						// For column UPDATEDATE
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(11);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[12])
					{
						// For column UPDATETIME
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(12);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[13])
					{
						// For column UPDATEUSER
						String tempString;


						tempString=(String)inputRecord.get(13);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[14])
					{
						// For column UPDATEPROGRAM
						String tempString;


						tempString=(String)inputRecord.get(14);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[15])
					{
						// For column RECORDSTATUS
						String tempString;


						tempString=(String)inputRecord.get(15);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					//Now set the primary key columns
					{
						// For column QUEUEID
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
						pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
							stmtIndex++;

					}
					if (executeUpdate(connection, pstmt)==0)
						throw createResourceException(new javax.ejb.NoSuchEntityException(), this.getClass());

				}
			}
		}
		catch (SQLException e) {
			throw createResourceException(e, this.getClass());
		}
		finally {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored)
			{}
		}
	}
	/**
	 * PartialStoreUsingOCC
	 */
	public void PartialStoreUsingOCC(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		try {
			boolean[] changedFieldIndexes = interactionSpec.getChangedFieldsIndexes();
			if (updateTemplateList == null)
				initializeUpdateTemplates();
			{
				com.ibm.ws.ejbdeploy.JQueueManagerEJB.DB2UDBAS400_V53_1.RdbRuntimeUpdateTemplate aTemplate = (com.ibm.ws.ejbdeploy.JQueueManagerEJB.DB2UDBAS400_V53_1.RdbRuntimeUpdateTemplate) updateTemplateList.get(0);
				String updateQuery = aTemplate.nativeQuery(changedFieldIndexes);
				if (updateQuery != null){
					pstmt = prepareStatement(connection, updateQuery);

					int stmtIndex = 1;
					if (changedFieldIndexes[1])
					{
						// For column QUEUENAME
						String tempString;


						tempString=(String)inputRecord.get(1);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[2])
					{
						// For column SIZE
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(2);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[3])
					{
						// For column AVAILABLE
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(3);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[4])
					{
						// For column ISTERMINATE
						String tempString;


						tempString=(String)inputRecord.get(4);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[5])
					{
						// For column WORKINGTIME
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(5);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[6])
					{
						// For column MESSAGECLASS
						String tempString;


						tempString=(String)inputRecord.get(6);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[7])
					{
						// For column ACTIVATECLASS
						String tempString;


						tempString=(String)inputRecord.get(7);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[8])
					{
						// For column CREATEDATE
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(8);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[9])
					{
						// For column CREATETIME
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(9);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[10])
					{
						// For column CREATEUSER
						String tempString;


						tempString=(String)inputRecord.get(10);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[11])
					{
						// For column UPDATEDATE
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(11);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[12])
					{
						// For column UPDATETIME
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(12);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[13])
					{
						// For column UPDATEUSER
						String tempString;


						tempString=(String)inputRecord.get(13);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[14])
					{
						// For column UPDATEPROGRAM
						String tempString;


						tempString=(String)inputRecord.get(14);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[15])
					{
						// For column RECORDSTATUS
						String tempString;


						tempString=(String)inputRecord.get(15);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					//Now set the primary key columns
					{
						// For column QUEUEID
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
						pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
							stmtIndex++;

					}
					if (executeUpdate(connection, pstmt)==0)
						throw createResourceException(new javax.ejb.NoSuchEntityException(), this.getClass());

				}
			}
		}
		catch (SQLException e) {
			throw createResourceException(e, this.getClass());
		}
		finally {
			try {
				if(pstmt != null) {
					returnPreparedStatement(connection, pstmt);
				}
			}
			catch (SQLException ignored)
			{}
		}
	}
}
