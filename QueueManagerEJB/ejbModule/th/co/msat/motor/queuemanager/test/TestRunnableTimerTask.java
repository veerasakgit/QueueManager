/*
 * Created on 19 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager.test;

import java.util.Timer;
import java.util.TimerTask;

import com.ibm.ws.scheduler.Runnable;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestRunnableTimerTask extends Runnable{
//	Timer t;
	public TestRunnableTimerTask(){
//		 t = new Timer();
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
//		t.schedule(new TimerTask(){
//
//			public void run() {
//				// TODO Auto-generated method stub
//				throw new RuntimeException(" timeup ! ");
//				
//			}
//			
//		},5000);
		
		while(true){
			System.out.print(".");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	
	
	
	public static void main(String arg[]){
		final Thread t = new Thread(new TestRunnableTimerTask());
		Timer tt = new Timer();
		tt.schedule(new TimerTask(){

			public void run() {
				// TODO Auto-generated method stub
				
				
				t.stop();
				
			}},5000);
		try{
			t.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
