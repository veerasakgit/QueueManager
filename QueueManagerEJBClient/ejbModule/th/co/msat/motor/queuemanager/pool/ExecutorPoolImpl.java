/*
 * Created on 23 ก.ย. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager.pool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import th.co.msat.motor.queuemanager.executor.Executor;
import th.co.msat.motor.queuemanager.executor.ExecutorFactory;

/**
 * Class that contain executor
 * Terminated(Update 2009/09/08[yyyy/mm/dd])
 * 
 * @author ituser3
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ExecutorPoolImpl implements ExecutorPool {

	private int size;

	private String qname;

	private String executorClass;

	//	private int used;

	private List runningThread;

	private List availableThread;

	//	private int index;

	public ExecutorPoolImpl(String executorClass, int size, String qname)
			throws Exception {
		this.executorClass = executorClass;
		this.size = size;
		this.qname = qname;
		initial();
	}

	public boolean isRunning(BigDecimal jobid) {
		for (Iterator iter = runningThread.iterator(); iter.hasNext();) {
			Executor extor = (Executor) iter.next();
			//			System.out.println("extor:"+extor);
			if (jobid.compareTo(extor.getJobid()) == 0) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see th.co.msat.motor.queuemanager.pool.Pool#getPoolSize()
	 */
	public int getPoolSize() {
		// TODO Auto-generated method stub
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see th.co.msat.motor.queuemanager.pool.Pool#getNext()
	 */
	public Executor getNext() {
		System.out.println("#####################################");
		System.out.println(" on get next availabel size:"+availableThread.size());
		//		try{
		if (availableThread.size() <= 0)
			return null;
		Executor e = (Executor) availableThread.get(availableThread.size() - 1);
				System.out.println("executer:"+e);
		e.setPool(this);
		availableThread.remove(e);
		//		System.out.println(" availabel size:"+availableThread.size());
		runningThread.add(e);
		//			edao.decreaseAvailabel(qname,1);
		System.out.println(" on get next availabel size:"+availableThread.size());
				System.out.println(" running size:"+runningThread.size());
				System.out.println("#####################################");
		return e;
		//		}catch(Exception e){
		//			e.printStackTrace();
		//		}
		//		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see th.co.msat.motor.queuemanager.pool.Pool#getAvailable()
	 */
	public int getAvailable() {
		// TODO Auto-generated method stub
		return availableThread.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see th.co.msat.motor.queuemanager.pool.Pool#setPoolSize(int)
	 */
	public void setPoolSize(int size) {
		// TODO Auto-generated method stub
		//		ตอนนี้ยัง set size ตอน runtime ไม่ได้นะ
		this.size = size;
		//		throw new UnsupportedOperationException(" not yet implements.");
	}

	/**
	 * Set class that is executor
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @throws Exception
	 *  
	 */
	public void setExecutorClass(String executorClass) {
		this.executorClass = executorClass;
	}

	/**
	 * Create executor until completely
	 * 
	 * @author Den
	 * @since before 2009/08
	 * @throws Exception
	 */
	public void initial() throws Exception {
		runningThread = new ArrayList();
		availableThread = new ArrayList();
		System.out.println(" availablethread:"+availableThread.size());
		System.out.println(" size :"+size);
		while (availableThread.size() < size) {
			
			Executor e = null;
			try{
				e = ExecutorFactory.getInstance().getExecutor(
					executorClass.trim());
			}catch(Exception ex){
//				ex.printStackTrace();
				size -= 1 ;
				continue;
			}
			e.setQueueName(qname);
			availableThread.add(e);
			System.out.println("add available thared, total size is "+availableThread.size());
			System.out.println("checking has next running thread:"+runningThread.size());
			
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see th.co.msat.motor.queuemanager.pool.Pool#returnPool(java.lang.Object)
	 */
	public void returnPool(Object o) {
		// TODO Auto-generated method stub
				System.out.println(" *********************************************");
				System.out.println(" return pool"+o.getClass()+"=====");
				System.out.println(" *********************************************");
		runningThread.remove(o);
		availableThread.add(o);
		
		System.out.println(" total availabel size is:"+availableThread.size());
		System.out.println("checking has next running thread:"+runningThread.size());
		//		try {
		//			edao.addAvailabel(qname,1);
		//		} catch (Exception e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

	}

	//	ExecutorDAO edao = new ExecutorDAO();

	/*
	 * (non-Javadoc)
	 * 
	 * @see th.co.msat.motor.queuemanager.pool.Pool#hasNext()
	 */
	public boolean hasNext() {
		//		try {
		//			return edao.hasNext(qname);
				System.out.println("#####################################");
		System.out.println("checking has next "+availableThread.size());
		System.out.println("checking has next running thread:"+runningThread.size());
		return availableThread.size() > 0;
		//		} catch (Exception e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		return false;
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.pool.ExecutorPool#reset()
	 */
	public void reset() throws Exception {
//		 TODO Auto-generated method stub
//		int rsize = runningThread.size();
////		for(int i=0;i<rsize;i++){
//			runningThread.remove(i);
//		}
//		int ssize = availableThread.size();
//		for(int i=0;i<ssize;i++){
//			availableThread.remove(i);
//		}
//		runningThread = new ArrayList();
//		availableThread = new ArrayList();
		initial();
	}

}
