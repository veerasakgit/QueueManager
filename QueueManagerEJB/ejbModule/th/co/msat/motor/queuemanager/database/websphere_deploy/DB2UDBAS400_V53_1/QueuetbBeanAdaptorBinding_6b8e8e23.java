package th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1;

/**
 * QueuetbBeanAdaptorBinding_6b8e8e23
 */
public class QueuetbBeanAdaptorBinding_6b8e8e23 implements com.ibm.ws.ejbpersistence.beanextensions.EJBAdapterBinding {
	/**
	 * getExtractor
	 */
	public com.ibm.ws.ejbpersistence.dataaccess.EJBExtractor getExtractor() {
		// extractor for th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanExtractor_6b8e8e23
		com.ibm.ws.ejbpersistence.dataaccess.AbstractEJBExtractor extractor =  new th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanExtractor_6b8e8e23();
		extractor.setPrimaryKeyColumns(new int[] {1});
		extractor.setDataColumns(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
		return extractor;
	}
	/**
	 * getInjector
	 */
	public com.ibm.ws.ejbpersistence.beanextensions.EJBInjector getInjector() {
		return new th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanInjectorImpl_6b8e8e23();
	}
	/**
	 * getAdapter
	 */
	public com.ibm.websphere.ejbpersistence.EJBToRAAdapter getAdapter() {
		return com.ibm.ws.rsadapter.cci.WSRelationalRAAdapter.createAdapter();
	}
	/**
	 * getMetadata
	 */
	public Object[] getMetadata() {
		
		java.lang.String[] primarykey, subhomes, composedObjs, composedObjImpls;
		com.ibm.ObjectQuery.metadata.OSQLExternalCatalogEntry[] cat;
		com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef[] fields;
		cat = new com.ibm.ObjectQuery.metadata.OSQLExternalCatalogEntry[5];

		//-------------------------------------Alias-------------------------------------
		cat[0] = new com.ibm.ObjectQuery.metadata.OSQLExternalCatalogRDBAlias("Queuetb", "Queuetb1_Alias", "DB2NT", "MSATLIB.QUEUETB", "Queuetb_Queuetb1_Table", 5, 3, 3);


		//-----------------------------------Table Type-----------------------------------
		fields = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef[16];

		fields[0] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("QUEUEID", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._NUMERIC, -1, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, true, 9, 0, 0, false);
		fields[1] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("QUEUENAME", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 20, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[2] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("SIZE", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._NUMERIC, -1, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 9, 0, 0, false);
		fields[3] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("AVAILABLE", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._NUMERIC, -1, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 9, 0, 0, false);
		fields[4] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("ISTERMINATE", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 1, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[5] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("WORKINGTIME", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._NUMERIC, -1, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 15, 0, 0, false);
		fields[6] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("MESSAGECLASS", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 50, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[7] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("ACTIVATECLASS", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 50, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[8] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("CREATEDATE", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._NUMERIC, -1, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 8, 0, 0, false);
		fields[9] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("CREATETIME", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._NUMERIC, -1, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 6, 0, 0, false);
		fields[10] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("CREATEUSER", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 10, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[11] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("UPDATEDATE", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._NUMERIC, -1, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 8, 0, 0, false);
		fields[12] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("UPDATETIME", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._NUMERIC, -1, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 6, 0, 0, false);
		fields[13] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("UPDATEUSER", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 10, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[14] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("UPDATEPROGRAM", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 50, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[15] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("RECORDSTATUS", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 1, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		primarykey = new String[1];
		primarykey[0] = "QUEUEID";
		cat[1] = new com.ibm.ObjectQuery.metadata.OSQLExternalCatalogType("Queuetb", "Queuetb1_Table", null, fields, primarykey);


		//-----------------------------------Bean Type-----------------------------------
		fields = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef[17];

		fields[0] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("queueid", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._DECIMAL, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[1] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("queuename", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[2] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("size", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._DECIMAL, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[3] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("available", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._DECIMAL, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[4] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("isterminate", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[5] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("workingtime", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._DECIMAL, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[6] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("messageclass", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[7] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("activateclass", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[8] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("createdate", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._DECIMAL, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[9] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("createtime", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._DECIMAL, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[10] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("createuser", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[11] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("updatedate", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._DECIMAL, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[12] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("updatetime", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._DECIMAL, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[13] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("updateuser", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[14] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("updateprogram", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[15] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("recordstatus", new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, new String(), com.ibm.ObjectQuery.engine.OSQLConstants.NOT_APPLICABLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols._CHARACTER, 0, com.ibm.ObjectQuery.engine.OSQLConstants.NO_TYPE, false, 0, -1, 0, false);
		fields[16] = new com.ibm.ObjectQuery.metadata.OSQLExternalColumnDef("jobtb", "Jobtb", com.ibm.ObjectQuery.engine.OSQLConstants.MANY, "q_msatlib_jobtb_queueid_00001", com.ibm.ObjectQuery.engine.OSQLConstants.SINGLE, true, com.ibm.ObjectQuery.engine.OSQLSymbols.OOSQL_TABLE, 0, com.ibm.ObjectQuery.engine.OSQLSymbols.LISTOF, false, 0, -1, com.ibm.ObjectQuery.engine.OSQLSymbols.SQL_BO, false);
		primarykey = new String[1];
		primarykey[0] = "queueid";
		cat[2] = new com.ibm.ObjectQuery.metadata.OSQLExternalCatalogType("Queuetb", "Queuetb_BO", "th.co.msat.motor.queuemanager.database.QueuetbBean", fields, primarykey);


		//--------------------------------------View--------------------------------------
		composedObjs = null;
		composedObjImpls = null;
		subhomes = null;
		cat[3] = new com.ibm.ObjectQuery.metadata.OSQLExternalCatalogView("Queuetb", "Queuetb_Queuetb_BO", "Queuetb_Queuetb1_Alias", composedObjs, composedObjImpls, 
		"select t1.QUEUEID,t1.QUEUENAME,t1.SIZE,t1.AVAILABLE,t1.ISTERMINATE,t1.WORKINGTIME,t1.MESSAGECLASS,t1.ACTIVATECLASS,t1.CREATEDATE,t1.CREATETIME,t1.CREATEUSER,t1.UPDATEDATE,t1.UPDATETIME,t1.UPDATEUSER,t1.UPDATEPROGRAM,t1.RECORDSTATUS,(select  nest  (t2) from Jobtb_Jobtb1_Alias t2 where t2.QUEUEID = t1.QUEUEID) from _this t1", 
		null, subhomes, 
		0, null);



		cat[4] = new com.ibm.ObjectQuery.metadata.OSQLExternalSysApplOptions(14, false);

		return cat;
	}
	/**
	 * createDataAccessSpecs
	 */
	public java.util.Collection createDataAccessSpecs() throws javax.resource.ResourceException {
		com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec daSpec;
		com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl iSpec;
		java.util.Collection result = new java.util.ArrayList(14);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("Create");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("Create");
		daSpec.setInputRecordName("Create");
		daSpec.setOptimistic(false);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.CREATE_BEAN);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("Remove");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("Remove");
		daSpec.setInputRecordName("Remove");
		daSpec.setOptimistic(false);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.REMOVE_BEAN);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("Store");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("Store");
		daSpec.setInputRecordName("Store");
		daSpec.setOptimistic(false);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.STORE_BEAN);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("StoreUsingOCC");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("Store");
		daSpec.setInputRecordName("Store");
		daSpec.setOptimistic(true);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.STORE_BEAN);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("FindByQueueName");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("FindByQueueName");
		daSpec.setInputRecordName("FindByQueueName");
		daSpec.setOptimistic(false);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.FIND_BEAN);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		daSpec.setReadAccess(true);
		daSpec.setAllowDuplicates(false);
		daSpec.setContainsDuplicates(false);
		daSpec.setSingleResult(true);
		daSpec.setExtractor(new com.ibm.ws.ejbpersistence.dataaccess.WholeRowExtractor(getExtractor()));
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("FindByQueueName444Update");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("FindByQueueName");
		daSpec.setInputRecordName("FindByQueueName");
		daSpec.setOptimistic(false);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.FIND_BEAN);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		daSpec.setReadAccess(false);
		daSpec.setAllowDuplicates(false);
		daSpec.setContainsDuplicates(false);
		daSpec.setSingleResult(true);
		daSpec.setExtractor(new com.ibm.ws.ejbpersistence.dataaccess.WholeRowExtractor(getExtractor()));
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("FindAllQueue");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("FindAllQueue");
		daSpec.setInputRecordName("FindAllQueue");
		daSpec.setOptimistic(false);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.FIND_BEAN);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		daSpec.setReadAccess(true);
		daSpec.setAllowDuplicates(true);
		daSpec.setContainsDuplicates(true);
		daSpec.setSingleResult(false);
		daSpec.setExtractor(new com.ibm.ws.ejbpersistence.dataaccess.WholeRowExtractor(getExtractor()));
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("FindAllQueue444Update");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("FindAllQueue");
		daSpec.setInputRecordName("FindAllQueue");
		daSpec.setOptimistic(false);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.FIND_BEAN);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		daSpec.setReadAccess(false);
		daSpec.setAllowDuplicates(true);
		daSpec.setContainsDuplicates(true);
		daSpec.setSingleResult(false);
		daSpec.setExtractor(new com.ibm.ws.ejbpersistence.dataaccess.WholeRowExtractor(getExtractor()));
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("FindByPrimaryKey");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("FindByPrimaryKey");
		daSpec.setInputRecordName("FindByPrimaryKey");
		daSpec.setOptimistic(false);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.FIND_PK);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		daSpec.setReadAccess(true);
		daSpec.setAllowDuplicates(false);
		daSpec.setContainsDuplicates(false);
		daSpec.setSingleResult(true);
		daSpec.setExtractor(new com.ibm.ws.ejbpersistence.dataaccess.WholeRowExtractor(getExtractor()));
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("FindByPrimaryKey444Update");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("FindByPrimaryKey");
		daSpec.setInputRecordName("FindByPrimaryKey");
		daSpec.setOptimistic(false);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.FIND_PK);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		daSpec.setReadAccess(false);
		daSpec.setAllowDuplicates(false);
		daSpec.setContainsDuplicates(false);
		daSpec.setSingleResult(true);
		daSpec.setExtractor(new com.ibm.ws.ejbpersistence.dataaccess.WholeRowExtractor(getExtractor()));
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("ReadReadChecking");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("ReadReadChecking");
		daSpec.setInputRecordName("ReadReadChecking");
		daSpec.setOptimistic(true);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.READ_READ);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("ReadReadCheckingForUpdate");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("ReadReadChecking");
		daSpec.setInputRecordName("ReadReadChecking");
		daSpec.setOptimistic(false);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.READ_READ);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("PartialStore");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("Store");
		daSpec.setInputRecordName("Store");
		daSpec.setOptimistic(false);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.OTHER);
		daSpec.setProperty("IS_PARTIAL_UPDATE", Boolean.TRUE);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		result.add(daSpec);

		daSpec=com.ibm.ws.ejbpersistence.beanextensions.DataAccessSpecFactory.getDataAccessSpec();
		iSpec=new com.ibm.ws.rsadapter.cci.WSInteractionSpecImpl();
		iSpec.setFunctionSetName("th.co.msat.motor.queuemanager.database.websphere_deploy.DB2UDBAS400_V53_1.QueuetbBeanFunctionSet_6b8e8e23");
		iSpec.setFunctionName("PartialStoreUsingOCC");
		daSpec.setInteractionSpec(iSpec);
		daSpec.setSpecName("Store");
		daSpec.setInputRecordName("Store");
		daSpec.setOptimistic(true);
		daSpec.setType(com.ibm.ws.ejbpersistence.beanextensions.EJBDataAccessSpec.OTHER);
		daSpec.setProperty("IS_PARTIAL_UPDATE", Boolean.TRUE);
		daSpec.setQueryScope(new String[]{"Queuetb"});
		result.add(daSpec);

		return result;

	}
	/**
	 * getProperties
	 */
	public java.util.HashMap getProperties() {
		properties.put("USING_DB_AUTO_OCC_COLLISION_DETECTION", Boolean.FALSE);
		properties.put("NEED_REFRESH_AFTER_PERSISTENCE", Boolean.FALSE);
		return properties;
	}
	/**
	 * QueuetbBeanAdaptorBinding_6b8e8e23
	 */
	public QueuetbBeanAdaptorBinding_6b8e8e23() {
		properties = new java.util.HashMap();
		 properties.put("VERSION", "6.0.2.0");
	}
	private java.util.HashMap properties;
}
