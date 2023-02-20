/*
 * Created on 25 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/****************************************************/
/*----------------- Modify History -----------------*/
/****************************************************/
/* ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/09/07[yyyy/mm/dd]
 * @description Split queue host follow queue.host.running
 * 				in systemenv.properties
 * 				if QUEUETB.hostname = queue.host.running
 * 					run thread
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/10/19[yyyy/mm/dd]
 * @description Reset queue on Queue host
 * ---------------------------------------------------
 * @modifier Veerasak Boonchern Aey
 * @since 2009/12/08[yyyy/mm/dd]
 * @description Add Payment System into queue
 * --------------------------------------------------*/


package th.co.msat.motor.queuemanager.constants;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DefaultQueueConstants {

	public static final String SCRAP_QUEUE_NAME = "SCRAP_QUEUE_NAME";
//	queue piority
	public static final int PIORITY_HIGH=10;
	public static final int PIORITY_LOW=1;
	public static final int PIORITY_NORMAL=5;
//	queue status
	public static final String JOB_STATUS_RELEASE = "R";
	public static final String JOB_STATUS_HOLD = "H";
	public static final String JOB_STATUS_PROCESSING = "P";
	public static final String JOB_STATUS_ERROR = "E";
	public static final String JOB_STATUS_COMPLETE = "C";
	
	public static final String RECORD_STATUS_AVAILABLE = "Y";
	public static final String RECORD_STATUS_CANCEL = "N";
	
	public static final String QUEUE_TERMINATE = "T";
	public static final String QUEUE_READY = "R";
	
	public static final String QUEUE_NAME_NAT_SYNCHRONIZE = "";
	public static final String QUEUE_NAME_SYNCHRONIZE="Paid Synchronize                                  ";
	public static final String QUEUE_NAME_PRINTING =  "Coverpage Printing Queue                          ";
	public static final String QUEUE_NAME_RESERVE_ADJUST = "Reserve Adjust Queue                              ";
	public static final String QUEUE_NAME_CHANGE_GENERAL_INFO = "Change general infomation                         ";
	
	//Start Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
	public static final String QUEUE_SERVLET_PATH = "/QueueManagerWEB/QueueManagerServlet";
	//End Split queue host Veerasak Boonchern Aey 2009/09/07[yyyy/mm/dd]
	
	//Start Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
	public static final String PROCESS_RESET_QUEUE = "reset queue";
	public static final String PROCESS_EXECUTE = "execute queue";
	//End Reset queue on Queue host Veerasak Boonchern Aey 2009/10/19[yyyy/mm/dd]
	
	//Start Add PaymentSystem Veerasak Boonchern Aey 2009/12/08[yyyy/mm/dd]
	public static final String PRINT_REQUISITION_SERVLET_PATH = "/AuthorizationWeb/PaymentScrapCallbackServlet";
	
	public static final String PAYMENT_SYSTEM_QUEUE = "Payment System Queue";
	public static final String REQUISITION_PRINTING_QUEUE = "Requisition Printing Queue";
	//End Add PaymentSystem Veerasak Boonchern Aey 2009/12/08[yyyy/mm/dd]
}
