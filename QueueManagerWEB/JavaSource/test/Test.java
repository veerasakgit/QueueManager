package test;

import java.util.*;

public class Test 
{
	public Test()
	{
		Map map = new HashMap();
		map.put("001", "test");
		System.out.println(map.size());
		map.clear();
		System.out.println(map.size());
		System.out.println("----------------------");
		
		ThreadManager thdManager = ThreadManager.getInstance();
		thdManager.shootThread();
		
		try
		{
			Thread.currentThread().sleep(10000);
			thdManager.killThread();
		} catch (Exception e) {}
	}

	public static void main(String[] args) 
	{
		new Test();
	}
}

class ThreadManager
{
	private static ThreadManager instance = null;
	private Thread thread = null;
	
	public static ThreadManager getInstance()
	{
		if (instance == null)
			instance = new ThreadManager();
		return instance;
	}

	public void shootThread()
	{
		ThreadMaster chd = new ThreadChild();
		thread = new Thread(chd);
		thread.start();
	}

	public void killThread()
	{
		if (thread != null)
		{
			System.out.println("kill thread: " + thread.getName());
			thread.interrupt();
			System.out.println("kill thread: ok");
		}
	}
}

interface ThreadMaster extends Runnable
{
	public void execute() throws Exception;
}

class ThreadMother implements ThreadMaster, Runnable
{
	public void run()
	{
		try
		{
			execute();
		} catch (Exception e)
		{
			onException();
		}
	}

	public void execute() throws Exception
	{
	}

	private void onException()
	{
		ThreadManager.getInstance().killThread();
	}
}

class ThreadChild extends ThreadMother
{
	public void execute() throws Exception
	{
		try
		{
			int i = 0;
			while (i > -1)
			{
				i++;
				System.out.println(Thread.currentThread().getName() + ": " + i);
				/*if (i == 10)
				{
					ThreadManager.getInstance().killThread();
				}*/

				Thread.currentThread().sleep(1000);
			}
			//int r = 1/0;
		} catch (Exception e)
		{
			System.out.println("exception");
			e.printStackTrace();
			throw e;
		} finally
		{
			System.out.println("finally");
		}
	}
}