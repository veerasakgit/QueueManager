package th.co.msat.motor.queuemanager.database.websphere_deploy.CLOUDSCAPE_V51_1;

import java.util.ArrayList;
import java.util.List;
import com.ibm.ws.ejbdeploy.JQueueManagerEJB.CLOUDSCAPE_V51_1.RdbRuntimeUpdateTemplate;

/**
 * JobtbBeanPartialUpdateQueryHelper
 */
public class JobtbBeanPartialUpdateQueryHelper {
	/**
	 * getUpdateTemplates
	 */
	static java.util.List getUpdateTemplates() {
		java.util.List result = new java.util.ArrayList(1);
		{
			String[] assignmentColumns = {"MESSAGE1 = ?, ","STATUS = ?, ","PIORITY = ?, ","SUBMITDATE = ?, ","SUBMITTIME = ?, ","SUBMITUSER = ?, ","UPDATEDATE = ?, ","UPDATETIME = ?, ","UPDATEUSER = ?, ","UPDATEPROGRAM = ?, ","RECORDSTATUS = ?, ","REFERENCENO = ?, ","JOBGROUP = ?, ","Q_MSATLIB_JOBTB_QUEUEID_00001_QUEUEID = ?, "};
			boolean isNullablePredicates = false;
			RdbRuntimeUpdateTemplate aTemplate0 = new RdbRuntimeUpdateTemplate(" UPDATE JOBTB SET ", " WHERE JOBID = ? ", assignmentColumns,309, isNullablePredicates);
			int[] dirtyMap = {1,2,3,4,5,6,7,8,9,10,11,12,13,15};
			aTemplate0.cmpFieldMap(dirtyMap);
			result.add(aTemplate0);
		}
		 return result;
	}
}
