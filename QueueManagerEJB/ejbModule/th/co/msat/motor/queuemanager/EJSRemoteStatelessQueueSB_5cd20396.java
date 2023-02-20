package th.co.msat.motor.queuemanager;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessQueueSB_5cd20396
 */
public class EJSRemoteStatelessQueueSB_5cd20396 extends EJSWrapper implements QueueSB {
	/**
	 * EJSRemoteStatelessQueueSB_5cd20396
	 */
	public EJSRemoteStatelessQueueSB_5cd20396() throws java.rmi.RemoteException {
		super();	}
	/**
	 * isQueueTerminate
	 */
	public boolean isQueueTerminate(java.lang.String queueName) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		boolean _EJS_result = false;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = queueName;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.isQueueTerminate(queueName);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 0, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * deleteJobByReferenceNo
	 */
	public int deleteJobByReferenceNo(java.lang.String queueName, java.lang.String refNo) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		int _EJS_result = 0;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = queueName;
				_jacc_parms[1] = refNo;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 1, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.deleteJobByReferenceNo(queueName, refNo);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 1, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getAllQueueName
	 */
	public java.util.Collection getAllQueueName() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 2, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getAllQueueName();
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 2, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * searchJob
	 */
	public java.util.Collection searchJob(java.lang.String qname, java.lang.String jobGroup, java.lang.String submitUser, java.util.Date submitDateFrom, java.util.Date submitDateTo, java.lang.String[] status, java.lang.String refNo) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[7];
				_jacc_parms[0] = qname;
				_jacc_parms[1] = jobGroup;
				_jacc_parms[2] = submitUser;
				_jacc_parms[3] = submitDateFrom;
				_jacc_parms[4] = submitDateTo;
				_jacc_parms[5] = status;
				_jacc_parms[6] = refNo;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 3, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.searchJob(qname, jobGroup, submitUser, submitDateFrom, submitDateTo, status, refNo);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 3, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getQueueConfiguration
	 */
	public th.co.msat.motor.queuemanager.vo.QueueConfigurationVO getQueueConfiguration(java.lang.String qname) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		th.co.msat.motor.queuemanager.vo.QueueConfigurationVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = qname;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 4, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getQueueConfiguration(qname);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 4, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * callQueueRunningHost
	 */
	public void callQueueRunningHost(java.lang.String queueName) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = queueName;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 5, _EJS_s, _jacc_parms);
			beanRef.callQueueRunningHost(queueName);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 5, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * callQueueRunningHost
	 */
	public void callQueueRunningHost(java.lang.String queueName, java.lang.String process) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = queueName;
				_jacc_parms[1] = process;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 6, _EJS_s, _jacc_parms);
			beanRef.callQueueRunningHost(queueName, process);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 6, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * createQueue
	 */
	public void createQueue(th.co.msat.motor.queuemanager.vo.QueueVO q) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = q;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 7, _EJS_s, _jacc_parms);
			beanRef.createQueue(q);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 7, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * deleteJob
	 */
	public void deleteJob(java.math.BigDecimal jobid) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = jobid;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 8, _EJS_s, _jacc_parms);
			beanRef.deleteJob(jobid);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 8, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * put
	 */
	public void put(java.lang.String queueName, th.co.msat.motor.queuemanager.message.Message message, int piority, java.lang.String username, java.lang.String programId, java.lang.String status, long jobgroup) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[7];
				_jacc_parms[0] = queueName;
				_jacc_parms[1] = message;
				_jacc_parms[2] = new java.lang.Integer(piority);
				_jacc_parms[3] = username;
				_jacc_parms[4] = programId;
				_jacc_parms[5] = status;
				_jacc_parms[6] = new java.lang.Long(jobgroup);
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 9, _EJS_s, _jacc_parms);
			beanRef.put(queueName, message, piority, username, programId, status, jobgroup);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 9, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * put
	 */
	public void put(java.lang.String queueName, th.co.msat.motor.queuemanager.vo.JobVO jobvo) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = queueName;
				_jacc_parms[1] = jobvo;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 10, _EJS_s, _jacc_parms);
			beanRef.put(queueName, jobvo);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 10, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * putCollection
	 */
	public void putCollection(java.lang.String queueName, java.util.Collection message, int piority, java.lang.String username, java.lang.String programId, java.lang.String status, long jobgroup) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[7];
				_jacc_parms[0] = queueName;
				_jacc_parms[1] = message;
				_jacc_parms[2] = new java.lang.Integer(piority);
				_jacc_parms[3] = username;
				_jacc_parms[4] = programId;
				_jacc_parms[5] = status;
				_jacc_parms[6] = new java.lang.Long(jobgroup);
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 11, _EJS_s, _jacc_parms);
			beanRef.putCollection(queueName, message, piority, username, programId, status, jobgroup);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 11, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * resetQueue
	 */
	public void resetQueue(java.lang.String qname) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = qname;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 12, _EJS_s, _jacc_parms);
			beanRef.resetQueue(qname);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 12, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * stimulateQueue
	 */
	public void stimulateQueue() throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 13, _EJS_s, _jacc_parms);
			beanRef.stimulateQueue();
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 13, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * updateJobStatus
	 */
	public void updateJobStatus(java.math.BigDecimal jobId, java.lang.String jobstatus) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = jobId;
				_jacc_parms[1] = jobstatus;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 14, _EJS_s, _jacc_parms);
			beanRef.updateJobStatus(jobId, jobstatus);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 14, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * updateJobStatus
	 */
	public void updateJobStatus(java.util.Collection c, java.lang.String status) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = c;
				_jacc_parms[1] = status;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 15, _EJS_s, _jacc_parms);
			beanRef.updateJobStatus(c, status);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 15, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * updateQueueTerminate
	 */
	public void updateQueueTerminate(java.lang.String qname, java.lang.String status) throws java.lang.Exception, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = qname;
				_jacc_parms[1] = status;
			}
	th.co.msat.motor.queuemanager.QueueSBBean beanRef = (th.co.msat.motor.queuemanager.QueueSBBean)container.preInvoke(this, 16, _EJS_s, _jacc_parms);
			beanRef.updateQueueTerminate(qname, status);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 16, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
}
