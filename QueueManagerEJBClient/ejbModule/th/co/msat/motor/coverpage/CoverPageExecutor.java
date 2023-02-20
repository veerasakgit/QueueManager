/*
 * Created on 14 µ.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.coverpage;



import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;


import th.co.msat.motor.config.SystemEnvironment;
import th.co.msat.motor.coverpage.generator.CoverpagePrinting;
import th.co.msat.motor.database.facade.CoverpagetbFacade;
import th.co.msat.motor.database.facade.CoverpagetbFacadeHome;
import th.co.msat.motor.database.vo.CoverPageVO;
import th.co.msat.motor.queuemanager.executor.ExecutorImpl;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CoverPageExecutor extends ExecutorImpl {
	private boolean timeout;
	private boolean alive;
	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#execute()
	 */
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		alive = true;
		try{
			CoverPageMessage msg = (CoverPageMessage)getMessage();
			CoverPageVO vo = null;
			CoverpagetbFacade coverpagetbFacade = createCoverpagetbFacade();
			vo  = coverpagetbFacade.getPrintCoverPage(msg.getReferenceId());
			System.out.println("----message reference coverpageExecutor---");
			System.out.println(vo.getClaim_no());
			System.out.println(vo.getNotificationno());
			System.out.println("----message reference coverpageExecutor-End---");
			vo = getCoverPage(vo.getNotificationno(),coverpagetbFacade,msg.getUsername());	
			if(null!=vo){
				System.out.println("==@==@==@==@ Starting generate facing sheet");
				//CoverpagePrinter printer = createCoverpagePrinter();
				//printer.print(vo.getNotificationno(),msg.getUsername());
				CoverpagePrinting printing = new CoverpagePrinting();
				printing.print(vo.getNotificationno(),msg.getUsername());
			
			}
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
		}catch(FinderException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		alive = false;
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#onTimeout()
	 */
	public void onTimeout() {
		// TODO Auto-generated method stub
		timeout = true;
	}

	/* (non-Javadoc)
	 * @see th.co.msat.motor.queuemanager.executor.Executor#isAlive()
	 */
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return alive;
	}
	
	
	public CoverpagePrinter createCoverpagePrinter() throws Exception{
		Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
        //env.put(Context.PROVIDER_URL, SystemEnvironment.getInstance().getBootStrapAddressServerUNIX());
        env.put(Context.PROVIDER_URL, SystemEnvironment.getInstance().getBootStrapAddress());
        try {
			InitialContext ctx = new InitialContext(env);
//			System.out.println(" ============================= hrere is an lookup for remote test ===================================");
			Object s = ctx.lookup("ejb/CoverpagePrinterHome");
//			System.out.println(s);
			CoverpagePrinterHome frHome = (CoverpagePrinterHome) PortableRemoteObject.narrow(s,CoverpagePrinterHome.class);
//			System.out.println(" ============================= hrere is an lookup for remote test ===================================");
			return (CoverpagePrinter) frHome.create();
			
//			System.out.println(ctx.lookup("java:comp/env/ejb/test/remotetest/ForRemoteTestHome"));
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public CoverpagetbFacade createCoverpagetbFacade() throws Exception{
		Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
        //env.put(Context.PROVIDER_URL, SystemEnvironment.getInstance().getBootStrapAddressServerUNIX());
        env.put(Context.PROVIDER_URL, SystemEnvironment.getInstance().getBootStrapAddress());
        try {
			InitialContext ctx = new InitialContext(env);
//			System.out.println(" ============================= hrere is an lookup for remote test ===================================");
			Object s = ctx.lookup("ejb/CoverpagetbFacadeHome");
//			System.out.println(s);
			CoverpagetbFacadeHome frHome = (CoverpagetbFacadeHome) PortableRemoteObject.narrow(s,CoverpagetbFacadeHome.class);
			return (CoverpagetbFacade) frHome.create();
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public CoverPageVO getCoverPage(String notificationno,CoverpagetbFacade cFacade,String username) throws Exception{
		CoverPageVO cvo = null;
		try{
			cvo = cFacade.buildCoverPage(notificationno);
			cvo.setCreate_user(username);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", java.util.Locale.US);
			SimpleDateFormat sdfa = new SimpleDateFormat("HHmmss", java.util.Locale.US);
			int date =  Integer.parseInt(sdf.format(new Date()));
			int time =Integer.parseInt(sdfa.format(new Date()));
			
			cvo.setCreate_date(new Integer(date).toString());
			cvo.setCreate_time(new Integer(time).toString());
			cvo.setUpdate_program("Notifi");
			cFacade.saveCoverPage(cvo);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return cvo;
	}


}
