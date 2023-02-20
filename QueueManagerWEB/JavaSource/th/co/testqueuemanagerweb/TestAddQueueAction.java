/*
 * Created on 25 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.testqueuemanagerweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import th.co.msat.motor.queuemanager.vo.QueueVO;

import java.math.BigDecimal;
import java.util.Date;


import com.ibm.etools.service.locator.ServiceLocatorManager;
import th.co.msat.motor.queuemanager.QueueSBLocalHome;
import th.co.msat.motor.queuemanager.QueueSBLocal;
/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestAddQueueAction extends Action {

	private final static String STATIC_QueueSBLocalHome_REF_NAME = "ejb/QueueSB";
	private final static Class STATIC_QueueSBLocalHome_CLASS = QueueSBLocalHome.class;
	public ActionForward execute(ActionMapping mapping, ActionForm fform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DynaActionForm form = (DynaActionForm) fform;
		String actionType = (String)form.get("actiontype");
		if("Y".equals(actionType)){
			String queueName = (String) form.get("queueName");
			String isterminate = (String) form.get("isterminate");
			BigDecimal workingtime = (BigDecimal) form.get("workingtime");
			BigDecimal size = (BigDecimal) form.get("size");
			String activationclass = (String) form.get("activationclass");
	
			QueueVO vo = new QueueVO();
			vo.setAcitvateClass(activationclass);
			vo.setAvailable(size);
			vo.setCreateDate(new Date());
			vo.setCreateUser("Hacker");
			vo.setJobTb(null);
			vo.setMessageClass(null);
			vo.setQueueName(queueName);
			vo.setSize(size);
			vo.setTerminate(isterminate.equals("Y"));
			vo.setUpdateDate(new Date());
			vo.setUpdateProgram("Test ");
			vo.setUpdateUser("Hacker");
			vo.setWorkingTime(workingtime);
	
			QueueSBLocal qsb = createQueueSBLocal();
			qsb.createQueue(vo);
		}
		return mapping.findForward("success");
	}
	protected QueueSBLocal createQueueSBLocal() {
		QueueSBLocalHome aQueueSBLocalHome = (QueueSBLocalHome) ServiceLocatorManager
				.getLocalHome(STATIC_QueueSBLocalHome_REF_NAME,
						STATIC_QueueSBLocalHome_CLASS);
		try {
			if (aQueueSBLocalHome != null)
				return aQueueSBLocalHome.create();
		} catch (javax.ejb.CreateException ce) {
			// TODO Auto-generated catch block
			ce.printStackTrace();
		}
		return null;
	}
}
