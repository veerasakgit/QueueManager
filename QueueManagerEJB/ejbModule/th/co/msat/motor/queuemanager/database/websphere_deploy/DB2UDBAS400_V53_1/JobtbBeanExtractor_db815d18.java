package th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1;

/**
 * JobtbBeanExtractor_db815d18
 */
public class JobtbBeanExtractor_db815d18 extends com.ibm.ws.ejbpersistence.dataaccess.AbstractEJBExtractor {
	/**
	 * JobtbBeanExtractor_db815d18
	 */
	public JobtbBeanExtractor_db815d18() {
		int[] pkCols={1};
		setPrimaryKeyColumns(pkCols);

		int[] dataCols={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		setDataColumns(dataCols);
	}
	/**
	 * extractData
	 */
	public com.ibm.ws.ejbpersistence.cache.DataCacheEntry extractData(com.ibm.ws.ejbpersistence.dataaccess.RawBeanData dataRow) throws com.ibm.ws.ejbpersistence.utilpm.ErrorProcessingResultCollectionRow, com.ibm.ws.ejbpersistence.utilpm.PersistenceManagerInternalError {
		int[] dataColumns = getDataColumns();

		th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.JobtbBeanCacheEntryImpl_db815d18 entry=
			(th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.JobtbBeanCacheEntryImpl_db815d18) createDataCacheEntry();

		entry.setDataForJOBID(dataRow.getBigDecimal(dataColumns[0]));
		entry.setDataForMESSAGE(dataRow.getBlobAsByteArray(dataColumns[1]));
		entry.setDataForSTATUS(dataRow.getString(dataColumns[2]));
		entry.setDataForPIORITY(dataRow.getBigDecimal(dataColumns[3]));
		entry.setDataForSUBMITDATE(dataRow.getBigDecimal(dataColumns[4]));
		entry.setDataForSUBMITTIME(dataRow.getBigDecimal(dataColumns[5]));
		entry.setDataForSUBMITUSER(dataRow.getString(dataColumns[6]));
		entry.setDataForUPDATEDATE(dataRow.getBigDecimal(dataColumns[7]));
		entry.setDataForUPDATETIME(dataRow.getBigDecimal(dataColumns[8]));
		entry.setDataForUPDATEUSER(dataRow.getString(dataColumns[9]));
		entry.setDataForUPDATEPROGRAM(dataRow.getString(dataColumns[10]));
		entry.setDataForRECORDSTATUS(dataRow.getString(dataColumns[11]));
		entry.setDataForREFERENCENO(dataRow.getString(dataColumns[12]));
		entry.setDataForJOBGROUP(dataRow.getBigDecimal(dataColumns[13]));
		entry.setDataForQUEUEID(dataRow.getBigDecimal(dataColumns[14]));

		return entry;
	}
	/**
	 * extractPrimaryKey
	 */
	public Object extractPrimaryKey(com.ibm.ws.ejbpersistence.dataaccess.RawBeanData dataRow) throws com.ibm.ws.ejbpersistence.utilpm.ErrorProcessingResultCollectionRow, com.ibm.ws.ejbpersistence.utilpm.PersistenceManagerInternalError {
		int[] primaryKeyColumns = getPrimaryKeyColumns();

		th.co.msat.motor.queuemanager.database.JobtbKey key=
			new th.co.msat.motor.queuemanager.database.JobtbKey();

		key.jobid=dataRow.getBigDecimal(primaryKeyColumns[0]);

		return key;
	}
	/**
	 * getHomeName
	 */
	public String getHomeName() {
		return "Jobtb";
	}
	/**
	 * getChunkLength
	 */
	public int getChunkLength() {
		return 15;
	}
	/**
	 * createDataCacheEntry
	 */
	public com.ibm.ws.ejbpersistence.cache.DataCacheEntry createDataCacheEntry() {
		return new th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.JobtbBeanCacheEntryImpl_db815d18();
	}
}
