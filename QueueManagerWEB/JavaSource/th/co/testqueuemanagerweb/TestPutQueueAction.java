/*
 * Created on 26 ¡.Â. 2551
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

import com.ibm.etools.service.locator.ServiceLocatorManager;

import th.co.msat.motor.database.vo.Notification;
import th.co.msat.motor.queuemanager.QueueSBLocalHome;
import th.co.msat.motor.queuemanager.QueueSBLocal;
import th.co.msat.motor.queuemanager.database.NotificationDAO;
import th.co.msat.motor.queuemanager.database.NotificationDAOImpl;
import th.co.msat.motor.scrapexecutor.ScrapMessage;

/**
 * @author ituser3
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TestPutQueueAction extends Action {

	private final static String STATIC_QueueSBLocalHome_REF_NAME = "ejb/QueueSB";

	private final static Class STATIC_QueueSBLocalHome_CLASS = QueueSBLocalHome.class;

	public ActionForward execute(ActionMapping mapping, ActionForm fform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm form = (DynaActionForm) fform;
		QueueSBLocal qsb = createQueueSBLocal();
		request.setAttribute("allQueueName", qsb.getAllQueueName());
		if ("submit".equals(form.get("action"))) {
			String type = (String)form.get("scrapType");
			if("one".equals(type)){
				 return putSingle(mapping,form,request,response);
			
			}else {
				 return putPeriod(mapping,form,request,response);
			}
		}
		return mapping.findForward("success");
		
	}
	
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	


	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward putPeriod(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String queueName = (String) form.get("queueName");
		String messageFrom = (String) form.get("messageFrom");
		String messageTo = (String) form.get("messageTo");
		String status = (String) form.get("status");
		NotificationDAO ndao = new NotificationDAOImpl();
		Collection c = ndao.getNotificationForScrap(messageFrom, messageTo);
		System.out.println("c size:"+c.size());
		if(c.size()==0)
			return  mapping.findForward("success");
		Collection cs = new ArrayList();
		for (Iterator iter = c.iterator(); iter.hasNext();) {
			Notification item = (Notification) iter.next();
			ScrapMessage tm = new ScrapMessage();
			tm.setNotificationno(item.getNotificationNo());
			cs.add(tm);
		}

		QueueSBLocal qsb = createQueueSBLocal();
		int piority = ((Integer)form.get("piority")).intValue();
		long jobgroup = System.currentTimeMillis();
		qsb.putCollection(queueName,cs,piority,"Hacker","Hacker",status,jobgroup);
		//Start Start Split queue host Veerasak Boonchen 2009/09/07[yyyy/mm/dd]
		qsb.callQueueRunningHost(queueName);
		//End Start Split queue host Veerasak Boonchen 2009/09/07[yyyy/mm/dd]
		System.out.println("job:"+jobgroup);
		form.set("jobgroup",new Long(jobgroup));
		return mapping.findForward("success");
	}


	public ActionForward putSingle(ActionMapping mapping, DynaActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String queueName = (String) form.get("queueName");
		String message = (String) form.get("message");
		String status = (String) form.get("status");
		
		ScrapMessage tm = new ScrapMessage();
		tm.setNotificationno(message);
		QueueSBLocal qsb = createQueueSBLocal();
		int piority = ((Integer)form.get("piority")).intValue();
		long jobgroup = System.currentTimeMillis();
		System.out.println("=========");
		
		qsb.put(queueName,tm,piority,"Hacker","Hacker",status,jobgroup);
		form.set("jobgroup",new Long(jobgroup));
		
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
