package th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1;

import com.ibm.websphere.rsadapter.WSInteractionSpec;
import com.ibm.ws.rsadapter.exceptions.DataStoreAdapterException;
import javax.resource.ResourceException;
import javax.resource.cci.Record;
import javax.resource.cci.IndexedRecord;
import java.sql.*;

/**
 * JobtbBeanFunctionSet_db815d18
 */
public class JobtbBeanFunctionSet_db815d18 extends com.ibm.ws.rsadapter.cci.WSResourceAdapterBase implements com.ibm.websphere.rsadapter.DataAccessFunctionSet {
	private java.util.HashMap functionHash;
	/**
	 * Create
	 */
	public void Create(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		try {
			pstmt = prepareStatement(connection,"INSERT INTO MSATLIB.JOBTB (JOBID, MESSAGE, STATUS, PIORITY, SUBMITDATE, SUBMITTIME, SUBMITUSER, UPDATEDATE, UPDATETIME, UPDATEUSER, UPDATEPROGRAM, RECORDSTATUS, REFERENCENO, JOBGROUP, QUEUEID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			// For column JOBID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(1,tempBigDecimal);
				else
					pstmt.setNull(1,java.sql.Types.NUMERIC);
			}
			// For column MESSAGE
			{
				byte[] tempbyteArray;


				tempbyteArray=(byte[])inputRecord.get(1);
				if(tempbyteArray != null)
					pstmt.setBinaryStream(2,new java.io.ByteArrayInputStream(tempbyteArray),tempbyteArray.length);
				else
					pstmt.setNull(2,java.sql.Types.BLOB);
			}
			// For column STATUS
			{
				String tempString;


				tempString=(String)inputRecord.get(2);
				if(tempString != null)
					pstmt.setString(3,tempString);
				else
					pstmt.setNull(3,java.sql.Types.CHAR);
			}
			// For column PIORITY
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(3);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(4,tempBigDecimal);
				else
					pstmt.setNull(4,java.sql.Types.NUMERIC);
			}
			// For column SUBMITDATE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(4);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(5,tempBigDecimal);
				else
					pstmt.setNull(5,java.sql.Types.NUMERIC);
			}
			// For column SUBMITTIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(5);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(6,tempBigDecimal);
				else
					pstmt.setNull(6,java.sql.Types.NUMERIC);
			}
			// For column SUBMITUSER
			{
				String tempString;


				tempString=(String)inputRecord.get(6);
				if(tempString != null)
					pstmt.setString(7,tempString);
				else
					pstmt.setNull(7,java.sql.Types.CHAR);
			}
			// For column UPDATEDATE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(7);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(8,tempBigDecimal);
				else
					pstmt.setNull(8,java.sql.Types.NUMERIC);
			}
			// For column UPDATETIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(8);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(9,tempBigDecimal);
				else
					pstmt.setNull(9,java.sql.Types.NUMERIC);
			}
			// For column UPDATEUSER
			{
				String tempString;


				tempString=(String)inputRecord.get(9);
				if(tempString != null)
					pstmt.setString(10,tempString);
				else
					pstmt.setNull(10,java.sql.Types.CHAR);
			}
			// For column UPDATEPROGRAM
			{
				String tempString;


				tempString=(String)inputRecord.get(10);
				if(tempString != null)
					pstmt.setString(11,tempString);
				else
					pstmt.setNull(11,java.sql.Types.CHAR);
			}
			// For column RECORDSTATUS
			{
				String tempString;


				tempString=(String)inputRecord.get(11);
				if(tempString != null)
					pstmt.setString(12,tempString);
				else
					pstmt.setNull(12,java.sql.Types.CHAR);
			}
			// For column REFERENCENO
			{
				String tempString;


				tempString=(String)inputRecord.get(12);
				if(tempString != null)
					pstmt.setString(13,tempString);
				else
					pstmt.setNull(13,java.sql.Types.CHAR);
			}
			// For column JOBGROUP
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(13);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(14,tempBigDecimal);
				else
					pstmt.setNull(14,java.sql.Types.NUMERIC);
			}
			// For column QUEUEID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(14);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(15,tempBigDecimal);
				else
					pstmt.setNull(15,java.sql.Types.NUMERIC);
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
			pstmt = prepareStatement(connection,"DELETE FROM MSATLIB.JOBTB  WHERE JOBID = ?");

			// For column JOBID
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
			pstmt = prepareStatement(connection,"UPDATE MSATLIB.JOBTB  SET MESSAGE = ?, STATUS = ?, PIORITY = ?, SUBMITDATE = ?, SUBMITTIME = ?, SUBMITUSER = ?, UPDATEDATE = ?, UPDATETIME = ?, UPDATEUSER = ?, UPDATEPROGRAM = ?, RECORDSTATUS = ?, REFERENCENO = ?, JOBGROUP = ?, QUEUEID = ? WHERE JOBID = ?");

			// For column JOBID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(15,tempBigDecimal);
				else
					pstmt.setNull(15,java.sql.Types.NUMERIC);
			}
			// For column MESSAGE
			{
				byte[] tempbyteArray;


				tempbyteArray=(byte[])inputRecord.get(1);
				if(tempbyteArray != null)
					pstmt.setBinaryStream(1,new java.io.ByteArrayInputStream(tempbyteArray),tempbyteArray.length);
				else
					pstmt.setNull(1,java.sql.Types.BLOB);
			}
			// For column STATUS
			{
				String tempString;


				tempString=(String)inputRecord.get(2);
				if(tempString != null)
					pstmt.setString(2,tempString);
				else
					pstmt.setNull(2,java.sql.Types.CHAR);
			}
			// For column PIORITY
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(3);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(3,tempBigDecimal);
				else
					pstmt.setNull(3,java.sql.Types.NUMERIC);
			}
			// For column SUBMITDATE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(4);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(4,tempBigDecimal);
				else
					pstmt.setNull(4,java.sql.Types.NUMERIC);
			}
			// For column SUBMITTIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(5);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(5,tempBigDecimal);
				else
					pstmt.setNull(5,java.sql.Types.NUMERIC);
			}
			// For column SUBMITUSER
			{
				String tempString;


				tempString=(String)inputRecord.get(6);
				if(tempString != null)
					pstmt.setString(6,tempString);
				else
					pstmt.setNull(6,java.sql.Types.CHAR);
			}
			// For column UPDATEDATE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(7);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(7,tempBigDecimal);
				else
					pstmt.setNull(7,java.sql.Types.NUMERIC);
			}
			// For column UPDATETIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(8);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(8,tempBigDecimal);
				else
					pstmt.setNull(8,java.sql.Types.NUMERIC);
			}
			// For column UPDATEUSER
			{
				String tempString;


				tempString=(String)inputRecord.get(9);
				if(tempString != null)
					pstmt.setString(9,tempString);
				else
					pstmt.setNull(9,java.sql.Types.CHAR);
			}
			// For column UPDATEPROGRAM
			{
				String tempString;


				tempString=(String)inputRecord.get(10);
				if(tempString != null)
					pstmt.setString(10,tempString);
				else
					pstmt.setNull(10,java.sql.Types.CHAR);
			}
			// For column RECORDSTATUS
			{
				String tempString;


				tempString=(String)inputRecord.get(11);
				if(tempString != null)
					pstmt.setString(11,tempString);
				else
					pstmt.setNull(11,java.sql.Types.CHAR);
			}
			// For column REFERENCENO
			{
				String tempString;


				tempString=(String)inputRecord.get(12);
				if(tempString != null)
					pstmt.setString(12,tempString);
				else
					pstmt.setNull(12,java.sql.Types.CHAR);
			}
			// For column JOBGROUP
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(13);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(13,tempBigDecimal);
				else
					pstmt.setNull(13,java.sql.Types.NUMERIC);
			}
			// For column QUEUEID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(14);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(14,tempBigDecimal);
				else
					pstmt.setNull(14,java.sql.Types.NUMERIC);
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
			pstmt = prepareStatement(connection,"UPDATE MSATLIB.JOBTB  SET MESSAGE = ?, STATUS = ?, PIORITY = ?, SUBMITDATE = ?, SUBMITTIME = ?, SUBMITUSER = ?, UPDATEDATE = ?, UPDATETIME = ?, UPDATEUSER = ?, UPDATEPROGRAM = ?, RECORDSTATUS = ?, REFERENCENO = ?, JOBGROUP = ?, QUEUEID = ? WHERE JOBID = ? AND JOBGROUP = ?");

			// For column JOBID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(15,tempBigDecimal);
				else
					pstmt.setNull(15,java.sql.Types.NUMERIC);
			}
			// For column MESSAGE
			{
				byte[] tempbyteArray;


				tempbyteArray=(byte[])inputRecord.get(1);
				if(tempbyteArray != null)
					pstmt.setBinaryStream(1,new java.io.ByteArrayInputStream(tempbyteArray),tempbyteArray.length);
				else
					pstmt.setNull(1,java.sql.Types.BLOB);
			}
			// For column STATUS
			{
				String tempString;


				tempString=(String)inputRecord.get(2);
				if(tempString != null)
					pstmt.setString(2,tempString);
				else
					pstmt.setNull(2,java.sql.Types.CHAR);
			}
			// For column PIORITY
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(3);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(3,tempBigDecimal);
				else
					pstmt.setNull(3,java.sql.Types.NUMERIC);
			}
			// For column SUBMITDATE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(4);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(4,tempBigDecimal);
				else
					pstmt.setNull(4,java.sql.Types.NUMERIC);
			}
			// For column SUBMITTIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(5);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(5,tempBigDecimal);
				else
					pstmt.setNull(5,java.sql.Types.NUMERIC);
			}
			// For column SUBMITUSER
			{
				String tempString;


				tempString=(String)inputRecord.get(6);
				if(tempString != null)
					pstmt.setString(6,tempString);
				else
					pstmt.setNull(6,java.sql.Types.CHAR);
			}
			// For column UPDATEDATE
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(7);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(7,tempBigDecimal);
				else
					pstmt.setNull(7,java.sql.Types.NUMERIC);
			}
			// For column UPDATETIME
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(8);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(8,tempBigDecimal);
				else
					pstmt.setNull(8,java.sql.Types.NUMERIC);
			}
			// For column UPDATEUSER
			{
				String tempString;


				tempString=(String)inputRecord.get(9);
				if(tempString != null)
					pstmt.setString(9,tempString);
				else
					pstmt.setNull(9,java.sql.Types.CHAR);
			}
			// For column UPDATEPROGRAM
			{
				String tempString;


				tempString=(String)inputRecord.get(10);
				if(tempString != null)
					pstmt.setString(10,tempString);
				else
					pstmt.setNull(10,java.sql.Types.CHAR);
			}
			// For column RECORDSTATUS
			{
				String tempString;


				tempString=(String)inputRecord.get(11);
				if(tempString != null)
					pstmt.setString(11,tempString);
				else
					pstmt.setNull(11,java.sql.Types.CHAR);
			}
			// For column REFERENCENO
			{
				String tempString;


				tempString=(String)inputRecord.get(12);
				if(tempString != null)
					pstmt.setString(12,tempString);
				else
					pstmt.setNull(12,java.sql.Types.CHAR);
			}
			// For column JOBGROUP
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(13);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(13,tempBigDecimal);
				else
					pstmt.setNull(13,java.sql.Types.NUMERIC);
			}
			// For column QUEUEID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(14);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(14,tempBigDecimal);
				else
					pstmt.setNull(14,java.sql.Types.NUMERIC);
			}
			IndexedRecord oldRecord = interactionSpec.getOldRecord();
			// For column JOBGROUP
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)oldRecord.get(13);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(16,tempBigDecimal);
				else
					pstmt.setNull(16,java.sql.Types.NUMERIC);
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
	 * FindByPrimaryKey
	 */
	public javax.resource.cci.Record FindByPrimaryKey(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		ResultSet result=null;
		try {
			pstmt = prepareStatement(connection," SELECT T1.JOBID, T1.MESSAGE, T1.STATUS, T1.PIORITY, T1.SUBMITDATE, T1.SUBMITTIME, T1.SUBMITUSER, T1.UPDATEDATE, T1.UPDATETIME, T1.UPDATEUSER, T1.UPDATEPROGRAM, T1.RECORDSTATUS, T1.REFERENCENO, T1.JOBGROUP, T1.QUEUEID FROM MSATLIB.JOBTB  T1 WHERE T1.JOBID = ?");

			// For column JOBID
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
			pstmt = prepareStatement(connection," SELECT T1.JOBID, T1.MESSAGE, T1.STATUS, T1.PIORITY, T1.SUBMITDATE, T1.SUBMITTIME, T1.SUBMITUSER, T1.UPDATEDATE, T1.UPDATETIME, T1.UPDATEUSER, T1.UPDATEPROGRAM, T1.RECORDSTATUS, T1.REFERENCENO, T1.JOBGROUP, T1.QUEUEID FROM MSATLIB.JOBTB  T1 WHERE T1.JOBID = ? FOR UPDATE  OF STATUS WITH RS");

			// For column JOBID
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
	 * findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local
	 */
	public javax.resource.cci.Record findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		ResultSet result=null;
		try {
			pstmt = prepareStatement(connection," SELECT T1.JOBID, T1.MESSAGE, T1.STATUS, T1.PIORITY, T1.SUBMITDATE, T1.SUBMITTIME, T1.SUBMITUSER, T1.UPDATEDATE, T1.UPDATETIME, T1.UPDATEUSER, T1.UPDATEPROGRAM, T1.RECORDSTATUS, T1.REFERENCENO, T1.JOBGROUP, T1.QUEUEID FROM MSATLIB.JOBTB  T1 WHERE T1.QUEUEID = ?");

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
	 * findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local444Update
	 */
	public javax.resource.cci.Record findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local444Update(IndexedRecord inputRecord, Object connection, WSInteractionSpec interactionSpec) throws ResourceException {
		PreparedStatement pstmt = null;
		ResultSet result=null;
		try {
			pstmt = prepareStatement(connection," SELECT T1.JOBID, T1.MESSAGE, T1.STATUS, T1.PIORITY, T1.SUBMITDATE, T1.SUBMITTIME, T1.SUBMITUSER, T1.UPDATEDATE, T1.UPDATETIME, T1.UPDATEUSER, T1.UPDATEPROGRAM, T1.RECORDSTATUS, T1.REFERENCENO, T1.JOBGROUP, T1.QUEUEID FROM MSATLIB.JOBTB  T1 WHERE T1.QUEUEID = ? FOR UPDATE  OF STATUS WITH RS");

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
	 * JobtbBeanFunctionSet_db815d18
	 */
	public JobtbBeanFunctionSet_db815d18() {
		functionHash=new java.util.HashMap(12);

		functionHash.put("Create",new Integer(0));
		functionHash.put("Remove",new Integer(1));
		functionHash.put("Store",new Integer(2));
		functionHash.put("StoreUsingOCC",new Integer(3));
		functionHash.put("FindByPrimaryKey",new Integer(4));
		functionHash.put("FindByPrimaryKey444Update",new Integer(5));
		functionHash.put("findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local",new Integer(6));
		functionHash.put("findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local444Update",new Integer(7));
		functionHash.put("ReadReadChecking",new Integer(8));
		functionHash.put("ReadReadCheckingForUpdate",new Integer(9));
		functionHash.put("PartialStore",new Integer(10));
		functionHash.put("PartialStoreUsingOCC",new Integer(11));
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
			outputRecord=FindByPrimaryKey(inputRecord,connection,interactionSpec);
			break;
		case 5:
			outputRecord=FindByPrimaryKey444Update(inputRecord,connection,interactionSpec);
			break;
		case 6:
			outputRecord=findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local(inputRecord,connection,interactionSpec);
			break;
		case 7:
			outputRecord=findJobtbByQ_msatlib_jobtb_queueid_00001Key_Local444Update(inputRecord,connection,interactionSpec);
			break;
		case 8:
			ReadReadChecking(inputRecord,connection,interactionSpec);
			break;
		case 9:
			ReadReadCheckingForUpdate(inputRecord,connection,interactionSpec);
			break;
		case 10:
			PartialStore(inputRecord,connection,interactionSpec);
			break;
		case 11:
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
			pstmt = prepareStatement(connection," SELECT 1 FROM MSATLIB.JOBTB  T1 WHERE T1.JOBID = ? AND T1.JOBGROUP = ?");

			// For column JOBID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(1,tempBigDecimal);
				else
					pstmt.setNull(1,java.sql.Types.NUMERIC);
			}
			// For column JOBGROUP
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(1);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(2,tempBigDecimal);
				else
					pstmt.setNull(2,java.sql.Types.NUMERIC);
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
			pstmt = prepareStatement(connection," SELECT 1 FROM MSATLIB.JOBTB  T1 WHERE T1.JOBID = ? AND T1.JOBGROUP = ? FOR UPDATE  OF STATUS WITH RS");

			// For column JOBID
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(1,tempBigDecimal);
				else
					pstmt.setNull(1,java.sql.Types.NUMERIC);
			}
			// For column JOBGROUP
			{
				java.math.BigDecimal tempBigDecimal;


				tempBigDecimal=(java.math.BigDecimal)inputRecord.get(1);
				if(tempBigDecimal != null)
					pstmt.setBigDecimal(2,tempBigDecimal);
				else
					pstmt.setNull(2,java.sql.Types.NUMERIC);
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
		updateTemplateList = JobtbBeanPartialUpdateQueryHelper.getUpdateTemplates();
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
						// For column MESSAGE
						byte[] tempbyteArray;


						tempbyteArray=(byte[])inputRecord.get(1);
						if(tempbyteArray != null){
							pstmt.setBinaryStream(stmtIndex,new java.io.ByteArrayInputStream(tempbyteArray),tempbyteArray.length);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.BLOB);
						stmtIndex++;

					}
					if (changedFieldIndexes[2])
					{
						// For column STATUS
						String tempString;


						tempString=(String)inputRecord.get(2);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[3])
					{
						// For column PIORITY
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
						// For column SUBMITDATE
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(4);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[5])
					{
						// For column SUBMITTIME
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
						// For column SUBMITUSER
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
						// For column UPDATEDATE
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(7);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[8])
					{
						// For column UPDATETIME
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
						// For column UPDATEUSER
						String tempString;


						tempString=(String)inputRecord.get(9);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[10])
					{
						// For column UPDATEPROGRAM
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
						// For column RECORDSTATUS
						String tempString;


						tempString=(String)inputRecord.get(11);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[12])
					{
						// For column REFERENCENO
						String tempString;


						tempString=(String)inputRecord.get(12);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[13])
					{
						// For column JOBGROUP
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(13);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[15])
					{
						// For column QUEUEID
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(14);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					//Now set the primary key columns
					{
						// For column JOBID
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
				IndexedRecord oldRecord = interactionSpec.getOldRecord();
				Object[] oldValues = new Object[1];
				oldValues[0] = oldRecord.get(13);
				String updateQuery = aTemplate.nativeOptimisticQuery(changedFieldIndexes);
				if (updateQuery != null){
					pstmt = prepareStatement(connection, updateQuery);

					int stmtIndex = 1;
					if (changedFieldIndexes[1])
					{
						// For column MESSAGE
						byte[] tempbyteArray;


						tempbyteArray=(byte[])inputRecord.get(1);
						if(tempbyteArray != null){
							pstmt.setBinaryStream(stmtIndex,new java.io.ByteArrayInputStream(tempbyteArray),tempbyteArray.length);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.BLOB);
						stmtIndex++;

					}
					if (changedFieldIndexes[2])
					{
						// For column STATUS
						String tempString;


						tempString=(String)inputRecord.get(2);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[3])
					{
						// For column PIORITY
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
						// For column SUBMITDATE
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(4);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[5])
					{
						// For column SUBMITTIME
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
						// For column SUBMITUSER
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
						// For column UPDATEDATE
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(7);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[8])
					{
						// For column UPDATETIME
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
						// For column UPDATEUSER
						String tempString;


						tempString=(String)inputRecord.get(9);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[10])
					{
						// For column UPDATEPROGRAM
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
						// For column RECORDSTATUS
						String tempString;


						tempString=(String)inputRecord.get(11);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[12])
					{
						// For column REFERENCENO
						String tempString;


						tempString=(String)inputRecord.get(12);
						if(tempString != null){
							pstmt.setString(stmtIndex,tempString);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.CHAR);
						stmtIndex++;

					}
					if (changedFieldIndexes[13])
					{
						// For column JOBGROUP
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(13);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					if (changedFieldIndexes[15])
					{
						// For column QUEUEID
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(14);
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
						}
						else
							pstmt.setNull(stmtIndex,java.sql.Types.NUMERIC);
						stmtIndex++;

					}
					//Now set the primary key columns
					{
						// For column JOBID
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)inputRecord.get(0);
						pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
							stmtIndex++;

					}
					// optimistic predicate columns
					{
						// For column JOBGROUP
						java.math.BigDecimal tempBigDecimal;


						tempBigDecimal=(java.math.BigDecimal)oldValues[0];
						if(tempBigDecimal != null){
							pstmt.setBigDecimal(stmtIndex,tempBigDecimal);
							stmtIndex++;
						}

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
