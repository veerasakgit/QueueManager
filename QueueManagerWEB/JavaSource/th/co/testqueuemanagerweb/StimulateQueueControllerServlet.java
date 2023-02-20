package th.co.testqueuemanagerweb;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.etools.service.locator.ServiceLocatorManager;
import th.co.msat.motor.queuemanager.QueueSBLocalHome;
import th.co.msat.motor.queuemanager.QueueSBLocal;
public class StimulateQueueControllerServlet extends HttpServlet implements
		Servlet {
	private final static String STATIC_QueueSBLocalHome_REF_NAME = "ejb/QueueSB";
	private final static Class STATIC_QueueSBLocalHome_CLASS = QueueSBLocalHome.class;
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public StimulateQueueControllerServlet() {
		super();
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		QueueSBLocal qsb = createQueueSBLocal();
		try {
			qsb.stimulateQueue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(arg0,arg1);
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