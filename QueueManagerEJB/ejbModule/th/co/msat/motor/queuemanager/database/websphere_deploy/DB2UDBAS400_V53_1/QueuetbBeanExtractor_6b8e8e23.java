package th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1;

/**
 * QueuetbBeanExtractor_6b8e8e23
 */
public class QueuetbBeanExtractor_6b8e8e23 extends com.ibm.ws.ejbpersistence.dataaccess.AbstractEJBExtractor {
	/**
	 * QueuetbBeanExtractor_6b8e8e23
	 */
	public QueuetbBeanExtractor_6b8e8e23() {
		int[] pkCols={1};
		setPrimaryKeyColumns(pkCols);

		int[] dataCols={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		setDataColumns(dataCols);
	}
	/**
	 * extractData
	 */
	public com.ibm.ws.ejbpersistence.cache.DataCacheEntry extractData(com.ibm.ws.ejbpersistence.dataaccess.RawBeanData dataRow) throws com.ibm.ws.ejbpersistence.utilpm.ErrorProcessingResultCollectionRow, com.ibm.ws.ejbpersistence.utilpm.PersistenceManagerInternalError {
		int[] dataColumns = getDataColumns();

		th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanCacheEntryImpl_6b8e8e23 entry=
			(th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanCacheEntryImpl_6b8e8e23) createDataCacheEntry();

		entry.setDataForQUEUEID(dataRow.getBigDecimal(dataColumns[0]));
		entry.setDataForQUEUENAME(dataRow.getString(dataColumns[1]));
		entry.setDataForSIZE(dataRow.getBigDecimal(dataColumns[2]));
		entry.setDataForAVAILABLE(dataRow.getBigDecimal(dataColumns[3]));
		entry.setDataForISTERMINATE(dataRow.getString(dataColumns[4]));
		entry.setDataForWORKINGTIME(dataRow.getBigDecimal(dataColumns[5]));
		entry.setDataForMESSAGECLASS(dataRow.getString(dataColumns[6]));
		entry.setDataForACTIVATECLASS(dataRow.getString(dataColumns[7]));
		entry.setDataForCREATEDATE(dataRow.getBigDecimal(dataColumns[8]));
		entry.setDataForCREATETIME(dataRow.getBigDecimal(dataColumns[9]));
		entry.setDataForCREATEUSER(dataRow.getString(dataColumns[10]));
		entry.setDataForUPDATEDATE(dataRow.getBigDecimal(dataColumns[11]));
		entry.setDataForUPDATETIME(dataRow.getBigDecimal(dataColumns[12]));
		entry.setDataForUPDATEUSER(dataRow.getString(dataColumns[13]));
		entry.setDataForUPDATEPROGRAM(dataRow.getString(dataColumns[14]));
		entry.setDataForRECORDSTATUS(dataRow.getString(dataColumns[15]));

		return entry;
	}
	/**
	 * extractPrimaryKey
	 */
	public Object extractPrimaryKey(com.ibm.ws.ejbpersistence.dataaccess.RawBeanData dataRow) throws com.ibm.ws.ejbpersistence.utilpm.ErrorProcessingResultCollectionRow, com.ibm.ws.ejbpersistence.utilpm.PersistenceManagerInternalError {
		int[] primaryKeyColumns = getPrimaryKeyColumns();

		th.co.msat.motor.queuemanager.database.QueuetbKey key=
			new th.co.msat.motor.queuemanager.database.QueuetbKey();

		key.queueid=dataRow.getBigDecimal(primaryKeyColumns[0]);

		return key;
	}
	/**
	 * getHomeName
	 */
	public String getHomeName() {
		return "Queuetb";
	}
	/**
	 * getChunkLength
	 */
	public int getChunkLength() {
		return 16;
	}
	/**
	 * createDataCacheEntry
	 */
	public com.ibm.ws.ejbpersistence.cache.DataCacheEntry createDataCacheEntry() {
		return new th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanCacheEntryImpl_6b8e8e23();
	}
}
