package th.co.msat.motor.queuemanager.database.websphere_deploy.CLOUDSCAPE_V51_1;

import java.util.ArrayList;
import java.util.List;
import com.ibm.ws.ejbdeploy.JQueueManagerEJB.CLOUDSCAPE_V51_1.RdbRuntimeUpdateTemplate;

/**
 * QueuetbBeanPartialUpdateQueryHelper
 */
public class QueuetbBeanPartialUpdateQueryHelper {
	/**
	 * getUpdateTemplates
	 */
	static java.util.List getUpdateTemplates() {
		java.util.List result = new java.util.ArrayList(1);
		{
			String[] assignmentColumns = {"QUEUENAME = ?, ","SIZE1 = ?, ","AVAILABLE = ?, ","ISTERMINATE = ?, ","WORKINGTIME = ?, ","MESSAGECLASS = ?, ","ACTIVATECLASS = ?, ","CREATEDATE = ?, ","CREATETIME = ?, ","CREATEUSER = ?, ","UPDATEDATE = ?, ","UPDATETIME = ?, ","UPDATEUSER = ?, ","UPDATEPROGRAM = ?, ","RECORDSTATUS = ?, "};
			boolean isNullablePredicates = false;
			RdbRuntimeUpdateTemplate aTemplate0 = new RdbRuntimeUpdateTemplate(" UPDATE QUEUETB SET ", " WHERE QUEUEID = ? ", assignmentColumns,314, isNullablePredicates);
			int[] dirtyMap = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
			aTemplate0.cmpFieldMap(dirtyMap);
			result.add(aTemplate0);
		}
		 return result;
	}
}
