/*
 * Created on 22 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager;


import th.co.msat.motor.queuemanager.constants.DefaultQueueConstants;
import th.co.msat.motor.queuemanager.database.JobQueueDAO;
import th.co.msat.motor.queuemanager.message.Message;
import th.co.msat.motor.queuemanager.vo.JobVO;
/**
 * Class that execute jobtb
 * Terminated(Update 2009/09/08[yyyy/mm/dd])
 * 
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DefaultQueueManager implements QueueManager {
	private String queueName;
	public DefaultQueueManager(String queueName){
		this.queueName = queueName;
	}

	public boolean isEmpty() throws Exception {
		JobQueueDAO jqdao = new JobQueueDAO();
		return (jqdao.findNextJob(queueName)==null);
	}
	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.QueueManager#getNextMessage()
	 */
	public Message getNextMessage() throws Exception {
		JobQueueDAO jqdao = new JobQueueDAO();
		JobVO jobvo = jqdao.findNextJob(queueName);
		return jobvo == null? null:jobvo.getMessage();
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.QueueManager#getNextJob()
	 */
	public JobVO getNextJob() throws Exception {
		System.out.println("DefaultQueueManager:GET NEXT JOB for Queue"+queueName);
		JobQueueDAO jqdao = new JobQueueDAO();
		JobQueueDAO jqdaoUpdate = new JobQueueDAO();
		JobVO jobvo = jqdao.findNextJob(queueName);
		try{
			if(jobvo != null && jobvo.getJobid() != null ){
				jqdaoUpdate.updateJobStatusForceProcess(jobvo.getJobid(),DefaultQueueConstants.JOB_STATUS_PROCESSING);
				System.out.println("Processing job id updated"+ jobvo.getJobid());
			}
		
			System.out.println("Staring process");
			if(null!=jobvo){
				System.out.println("DefaultQueueManager:QueueName " + queueName);
				System.out.println("DefaultQueueManager:Jobgroup " + jobvo.getJobgroup());
				System.out.println("DefaultQueueManager:Jobid " + jobvo.getJobid());
				System.out.println("DefaultQueueManager:Jobstatus " + jobvo.getStatus());
				System.out.println("DefaultQueueManager:JobMessage " + jobvo.getMessage().getReferenceId());
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
			
			return jobvo;
	}
	
	
	
	
}
