/*
 * Created on 1 µ.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/****************************************************/
/*----------------- Modify History -----------------*/
/****************************************************/
/* --------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/07[yyyy/mm/dd]
 * @description Split queue host follow queue.host.running
 * 				in systemenv.properties
 * 				if QUEUETB.hostname = queue.host.running
 * 					run thread
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/08[yyyy/mm/dd]
 * @description Queue real-time follow database
 * 				don't use server instance
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/11/04[yyyy/mm/dd]
 * @description Session Bean freeze cause
 * -------------------------------------------------*/


package th.co.testqueuemanagerweb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.queuemanager.QueueSBLocal;
import th.co.msat.motor.queuemanager.QueueSBLocalHome;
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.msat.motor.queuemanager.vo.QueueConfigurationVO;
import th.co.msat.motor.queuemanager.vo.QueueVO;
import th.co.queuemanager.QueueBean;

import com.ibm.etools.service.locator.ServiceLocatorManager;

/**
 * Strut action for QueueConsole
 * 
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QueueConsoleAction extends Action {
	private final static String STATIC_QueueSBLocalHome_REF_NAME = "ejb/QueueSB";

	private final static Class STATIC_QueueSBLocalHome_CLASS = QueueSBLocalHome.class;

	public ActionForward execute(ActionMapping mapping, ActionForm fform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm form = (DynaActionForm) fform;
		//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
		//QueueSBLocal qlocal = createQueueSBLocal();
		//form.set("allQueueName",qlocal.getAllQueueName());
		JobQueueDAO jqDAO = new JobQueueDAO();
		form.set("allQueueName", jqDAO.getAllQueueName());
		//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
		if (form.get("action").equals("init")) {
			return init(mapping, form, request, response);
		}else if(form.get("action").equals("save")){
			return save(mapping, form, request, response);
		}else if("resetQueue".equals(form.get("action"))){
			return resetQueue(mapping, form, request, response);
		} else {
			return search(mapping, form, request, response);
		}

	}

	/**
	 * Reset queue
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	private ActionForward resetQueue(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String qname = (String)form.get("queueName");
		//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
		//QueueSBLocal qlocal = createQueueSBLocal();
		try {
			//qlocal.resetQueue(qname);
			QueueBean qBean = new QueueBean();
			qBean.resetQueue(qname);
			//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}

	/**
	 * Update queue status
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	private ActionForward save(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String qname = (String)form.get("queueName");
		String terminate = (String)form.get("switchs");
		//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
		//QueueSBLocal qlocal = createQueueSBLocal();
		QueueBean qBean = new QueueBean();
		
		try {
			//qlocal.updateQueueTerminate(qname,terminate);
			qBean.updateQueueTerminate(qname,terminate);
			
			//Start Start Split queue host Veerasak Boonchen 2009/09/07[yyyy/mm/dd]
			if(terminate.equals(DefaultQueueConstants.QUEUE_READY))
			{
				//qlocal.callQueueRunningHost(qname);
				qBean.callQueueRunningHost(qname);
				//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
			}
			//End Start Split queue host Veerasak Boonchen 2009/09/07[yyyy/mm/dd]
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}

	/**
	 * Searching data
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	private ActionForward search(ActionMapping mapping, DynaActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String qname = (String) form.get("queueName");
		//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
		//QueueSBLocal qlocal = createQueueSBLocal();
		//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
		try {
			//Start Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]
			/*QueueConfigurationVO qcvo = qlocal.getQueueConfiguration(qname);
			form.set("switchs",qcvo.getSwitchStatus());
			form.set("processingJob",new Integer(qcvo.getProcessingJob()));
			form.set("availabelThread",new Integer(qcvo.getAvailabelThread()));
			form.set("holdJob",new Integer(qcvo.getHoldingJob()));
			form.set("size",new Integer(qcvo.getSize()));
			form.set("releaseJob",new Integer(qcvo.getReleaseJob()));*/
			
			JobQueueDAO jobqDAO = new JobQueueDAO();
			QueueVO qVO = jobqDAO.getQueue(qname);
			
			form.set("switchs", jobqDAO.getQueueTerminateStatus(qname));
			form.set("processingJob", new Integer(jobqDAO.getProcessingJob(qname)));
			form.set("availabelThread", new Integer(qVO.getAvailable().intValue()));
			form.set("holdJob", new Integer(jobqDAO.getHoldJob(qname)));
			form.set("size", new Integer(jobqDAO.getQueueSize(qname)));
			form.set("releaseJob", new Integer(jobqDAO.getReleaseJob(qname)));
			//End Queue real-time Veerasak Boonchern Aey 2009/09/08[yyyy/mm/dd]
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}

	/**
	 * Initail queue name
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	private ActionForward init(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
//		QueueSBLocal qlocal = createQueueSBLocal();
//		form.set("allQueueName",qlocal.getAllQueueName());
		return mapping.findForward("success");
		
	}
	
	/**
	 * Connect to QueueSBBean
	 * 
	 * @author Den
	 * @since before 2009/08[yyyy/mm]
	 * @return QueueSBLocal
	 */
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
