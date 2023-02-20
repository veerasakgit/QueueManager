select * from queuetb q;

insert into queuetb
(queueid, queuename, size, available, isterminate, workingtime, messageclass, activateclass, createdate, createtime, createuser, updatedate, updatetime, updateuser, updateprogram, recordstatus, hostname, bootstrap)
values
(41, 'Renew Policy Queue', 1, 1, 'R', 180000, '', '', 20091208, 1416, 'admin', 20091208, 1416, 'admin', 'admin', 'Y', 'http://localhost:9080', 'iiop://localhost:2809/');

update queuetb q
set q.MESSAGECLASS = 'th.co.msat.paymentsystem.PaymentSystemMessage'
where q.QUEUEID = 40;