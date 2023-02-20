package th.co.msat.motor.scrapexecutor;

import th.co.msat.motor.queuemanager.executor.ExecutorImpl;

public class TestQueueExecutor extends ExecutorImpl
{
	public void execute() throws Exception
	{
		int i = 0;
		try
		{
			while (true)
			{
				System.out.println(this.getQueueName() + " " + this.getThreadName() + " " + this.getJobRefNo() + " " + Thread.currentThread().getName() + " running");
				i++;
				Thread.sleep(1000);
				
				if (i == 60)
				{
					//int r = 1/0;
					break;
				}
			}
		} catch (ArithmeticException ae)
		{
			throw new Exception("wrong way to divide by 0.");
		} catch (NumberFormatException nfe)
		{
			throw new Exception("wrong number format.");
		} catch (Exception e)
		{
			throw e;
		}
	}
	
	public boolean isAlive()
	{
		return true;
	}
	
	public void onTimeout()
	{
		System.out.println(this.getQueueName() + " " + this.getThreadName() + " " + this.getJobRefNo() + " stop");
	}
}
