/**
 * 
 */
package th.co.msitb.spool.motor.executor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import th.co.msat.motor.queuemanager.executor.ExecutorImpl;
import th.co.msitb.spool.core.report.generate.GenerateDocumentImplement;
import th.co.msitb.spool.core.report.generate.GenerateDocumentSpool;
import th.co.msitb.spool.core.report.generate.GenerateDocumentSpoolImplement;
import th.co.msitb.spool.core.report.generate.MergePDFs;
import th.co.msitb.spool.message.SpoolMessage;
import th.co.msitb.spool.model.DetailVO;
import th.co.msitb.spool.model.DocTypeVO;

/**
 * @author jutamas
 *
 */
public class SpoolAtt2MotorExecutor extends ExecutorImpl{
	boolean isAlive = false;
	/**
	 * Execute to generate PDF of Doctype
	 */
	public void execute() throws Exception {
		isAlive = true;
		final SpoolMessage spoolMessage = (SpoolMessage) getMessage();
		List streamPdfList = new ArrayList();
		GenerateDocumentSpool spool = new GenerateDocumentSpoolImplement();
	  	MergePDFs mergePDFs = new MergePDFs();
		DetailVO detailVO=new DetailVO();
		DocTypeVO docTypeVO=new DocTypeVO();
		byte[] byteArr = null;
		String batchNo = null;
		String sequence = null;
		String fileJRXML=null;

		String referId = spoolMessage.getReferenceId();
		if (null != referId && !"".equals(referId)) {
			String[] text = referId.split("#");
			// for(int i=0; i < text.length; i++ ){}
			batchNo = text[0];
			sequence = text[1];
			detailVO.setBatchNo(batchNo);
			detailVO.setSequenceNo(new BigDecimal(sequence));
			docTypeVO.setDocTypeId(new BigDecimal (spoolMessage.getTypeId()));
			docTypeVO.setDocTypeName(spoolMessage.getTypeName());
		    detailVO.setBroker(spoolMessage.getBroker());
		    detailVO.setClient(spoolMessage.getClient());
		    detailVO.setMak(spoolMessage.getMak());
		    detailVO.setModel(spoolMessage.getModel());
		    detailVO.setLicense(spoolMessage.getLicense());
		    detailVO.setChassis(spoolMessage.getChassis());
		    detailVO.setReferenceId(spoolMessage.getPolicyNo());
		    detailVO.setInsuredName(spoolMessage.getInsureName());
		    detailVO.setDocType(docTypeVO);

		    System.out.println("Att2 executer get Type"+spoolMessage.getTypeId());
			 	
			try {
				System.out.println("============  Att2 Motor Executor ==============");
				spool.generatePDFDocument(detailVO,true,spoolMessage.getUsername());
			} catch (Exception e) {
				
				System.err.println("============  Error Att2 Motor Executor ==============");
				
				  throw e;
			}
		   
			
		}
		isAlive = false;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void onTimeout() {
		
		isAlive = false;
		
		
	}


}
