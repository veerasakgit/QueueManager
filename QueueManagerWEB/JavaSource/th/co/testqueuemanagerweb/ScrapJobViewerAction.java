/*
 * Created on 9 µ.¤. 2551
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
 * @since 2009/11/04[yyyy/mm/dd]
 * @description Session Bean freeze cause
 * -------------------------------------------------*/

package th.co.testqueuemanagerweb;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.ibm.etools.service.locator.ServiceLocatorManager;
import th.co.msat.motor.queuemanager.QueueSBLocalHome;
import th.co.msat.motor.queuemanager.QueueSBLocal;
import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.queuemanager.QueueBean;
/**
 * Strut action for ScrapJobViewer
 * 
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ScrapJobViewerAction extends Action {
//	private static final String QUEUE_NAME = "Coverpage printing queue";
	private static final String SEARCH_RESULT = "result";

	private final static String STATIC_QueueSBLocalHome_REF_NAME = "ejb/QueueSB";
	private final static Class STATIC_QueueSBLocalHome_CLASS = QueueSBLocalHome.class;
	
	//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
	QueueBean qBean = new QueueBean();
	//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		DynaActionForm fm = (DynaActionForm) form;
		//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
		//request.setAttribute("allQueueName", qsb.getAllQueueName());
		JobQueueDAO jqDAO = new JobQueueDAO();
		request.setAttribute("allQueueName", jqDAO.getAllQueueName());
		//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
//		String refNoLIST [] = (STRING [])FM.GET("REFNOLIST");
//		SYSTEM.OUT.PRINTLN("===============");
//		FOR (INT I = 0; I < REFNOLIST.LENGTH; I++) {
//			SYSTEM.OUT.PRINTLN("NOTI:"+REFNOLIST[I]);
//		}
		
		Collection jobidC = new ArrayList();
		String jobidList [] = (String[])fm.get("jobidList");
		for (int i = 0; i < jobidList.length; i++) {
			jobidC.add(new BigDecimal(jobidList[i]));
		}
		
		if ("search".equals(fm.get("action"))) {
			return search(mapping, fm, request, response);
		}else if("rel".equals(fm.get("action"))){
			//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
			//qsb.updateJobStatus(jobidC,DefaultQueueConstants.JOB_STATUS_RELEASE);
			qBean.updateJobStatus(jobidC,DefaultQueueConstants.JOB_STATUS_RELEASE);
			//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
			//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
			JobQueueDAO jqdao = new JobQueueDAO();
			Map jobMap = jqdao.getJobStatus(jobidC);
			
			for (Iterator iter = jobidC.iterator(); iter.hasNext();)
			{
				BigDecimal jobid = (BigDecimal) iter.next();
				if(DefaultQueueConstants.JOB_STATUS_RELEASE.equals(DefaultQueueConstants.JOB_STATUS_RELEASE)
						&& jobMap.get(new Integer(jobid.intValue())).equals(DefaultQueueConstants.JOB_STATUS_RELEASE))
				{
					 //Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
					/*QueueSBLocal qsb = createQueueSBLocal();
					qsb.callQueueRunningHost(jqdao.getQueueName(jobid));*/
					qBean.callQueueRunningHost(jqdao.getQueueName(jobid));
					//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
					break;
				}
			}
			//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
			return search(mapping, fm, request, response);
		}else if("hol".equals(fm.get("action"))){
			//Start  Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
			//qsb.updateJobStatus(jobidC,DefaultQueueConstants.JOB_STATUS_HOLD);
			qBean.updateJobStatus(jobidC,DefaultQueueConstants.JOB_STATUS_HOLD);
			//End  Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
			//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
			JobQueueDAO jqdao = new JobQueueDAO();
			Map jobMap = jqdao.getJobStatus(jobidC);
			
			for (Iterator iter = jobidC.iterator(); iter.hasNext();)
			{
				BigDecimal jobid = (BigDecimal) iter.next();
				if(DefaultQueueConstants.JOB_STATUS_RELEASE.equals(DefaultQueueConstants.JOB_STATUS_RELEASE)
						&& jobMap.get(new Integer(jobid.intValue())).equals(DefaultQueueConstants.JOB_STATUS_RELEASE))
				{
					//Start  Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
					/*QueueSBLocal qsb = createQueueSBLocal();
					qsb.callQueueRunningHost(jqdao.getQueueName(jobid));*/
					qBean.callQueueRunningHost(jqdao.getQueueName(jobid));
					//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
					break;
				}
			}
			//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
			return search(mapping, fm, request, response);
		}else if("del".equals(fm.get("action"))){
			for (int i = 0; i < jobidList.length; i++) {
				//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
				//qsb.deleteJob(new BigDecimal(jobidList[i].trim()));
				qBean.deleteJob(new BigDecimal(jobidList[i].trim()));
				//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
			}
			return search(mapping, fm, request, response);
		}
		/*if("addRef".equals(request.getParameter("act"))){
			 addRef(mapping,fm,request,response);
		}*/
		//String s[] = {"R","H"};
		//fm.set("status", s);
		
		/*
		Collection refNoFinal  = (Collection)request.getSession().getAttribute("refNoFinal");
		if(null==refNoFinal)
			refNoFinal = new ArrayList();
		String refNoList [] = new String[refNoFinal.size()]; 
		int i = 0;
		for (Iterator iter = refNoFinal.iterator(); iter.hasNext();) {
			System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
			
			String item = (String) iter.next();
			System.out.println(item);
			refNoList[i] = item;
			i=i+1;
		}
		fm.set("refNoList",refNoList);
		request.getSession().setAttribute("refNoFinal",refNoFinal);*/
		return mapping.findForward("success");
		
	}
	
	/*private ActionForward addRef(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String sel = request.getParameter("sel");
		String refNo = request.getParameter("refNo");
		Collection refNoFinal  = (Collection)request.getSession().getAttribute("refNoFinal");
		if(null==refNoFinal)
			refNoFinal = new ArrayList();
		System.out.println("===========--------------------------==========================");
		System.out.println(sel);
		System.out.println(refNo);
		if(sel.equals("deselect")){
			refNoFinal.remove(refNo.trim());
		}else{
			refNoFinal.add(refNo.trim());
		}
		
		request.getSession().setAttribute("refNoFinal",refNoFinal);
		return mapping.findForward("success");
	}*/

	/**
	 * Search data from data base
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
		try {
			String[] status = (String[]) form.get("status");
			String jobGroup = (String) form.get("jobGroup");
			String submitUser = (String) form.get("submitUser");
			String submitTimeFrom = (String) form.get("submitTimeFrom");
			String submitTimeTo = (String) form.get("submitTimeTo");
			String refNo = (String)form.get("referenceNo");
			String qname = (String)form.get("qname");
			
			Date submitDateTimeFrom =null;
			if(null!=submitTimeFrom&&!"".equals(submitTimeFrom))
				submitDateTimeFrom = new SimpleDateFormat(
					"dd/MM/yyyy hh:mm:ss").parse(submitTimeFrom);
			Date submitDateTimeTo = null;
			if(null!=submitTimeTo&&!"".equals(submitTimeTo))
				submitDateTimeTo = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
					.parse(submitTimeTo);

			//Start Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
			/*QueueSBLocal qsb = createQueueSBLocal();
			Collection c = qsb.searchJob(qname,jobGroup,submitUser,submitDateTimeFrom,submitDateTimeTo,status,refNo);*/
			JobQueueDAO jqDAO = new JobQueueDAO();
			Collection c = jqDAO.searchJob(qname,jobGroup,submitUser,submitDateTimeFrom,submitDateTimeTo,status,refNo);
			//End Session Freeze Veerasak Boonchen Aey 2009/11/04[yyyy/mm/dd]
			request.setAttribute(SEARCH_RESULT,c);
			//String s[] = {"R","H"};
			//form.set("status", s);
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("error");
		}
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
