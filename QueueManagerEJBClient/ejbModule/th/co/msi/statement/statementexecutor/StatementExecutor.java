package th.co.msi.statement.statementexecutor;

import th.co.msat.motor.queuemanager.executor.ExecutorImpl;

public class StatementExecutor extends ExecutorImpl
{
	
	private boolean isAlive = false;
	
	public void execute() throws Exception
	{
		StatementMessage message = (StatementMessage)getMessage();
		
		int i = 0;
		while (i < 120)
		{
			System.out.println("------------------- [" + this.getThreadName() + "] " + i);
			Thread.sleep(1000);
			i++;
		}
	}

	public boolean isAlive()
	{
		return isAlive;
	}

	public void onTimeout()
	{
		isAlive = false;
	}
}
