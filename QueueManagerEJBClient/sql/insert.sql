select * from queuetb q;

insert into queuetb
(queueid, queuename, size, available, isterminate, workingtime, messageclass, activateclass, createdate, createtime, createuser, updatedate, updatetime, updateuser, updateprogram, recordstatus, hostname, bootstrap)
values
(36, 'Statement Queue', 2, 2, 'R', 9000000, '', 'th.co.msi.statement.statementexecutor.StatementExecutor', 20091015, 1556, 'admin', 20091015, 1556, 'admin', 'admin', 'Y', 'http://srv4:9080', 'iiop://srv4:2809/');

update queuetb q
set q.MESSAGECLASS = 'th.co.msat.reqsprinting.RequisitionPrintingMessage'
	, q.ACTIVATECLASS = 'th.co.msat.reqsprinting.RequisitionPrintingExecutor'
	, q.BOOTSTRAP = 'iiop://MSA11:2809/'
	, q.HOSTNAME = 'http://MSA11:9080'
where q.QUEUEID = 42;

insert into jobtb
(jobid, queueid, message, status, piority, submitdate, submittime, submituser, updatedate, updatetime, updateuser, updateprogram, recordstatus, referenceno, jobgroup)
values
(25, 42, null, 'R', 5, 20091217, 1449, 'admin', 20091217, 1449, 'admin', 'admin', 'Y', 'BT08088809', 1247630944149);