/**
 * 
 */
package th.co.msitb.spool.miscellaneous.executor;

import java.math.BigDecimal;

import th.co.msat.motor.queuemanager.executor.ExecutorImpl;
import th.co.msitb.spool.core.report.generate.GenerateDocumentImplement;
import th.co.msitb.spool.core.report.generate.GenerateDocumentSpoolImplement;
import th.co.msitb.spool.message.SpoolMessage;
import th.co.msitb.spool.model.DetailVO;
import th.co.msitb.spool.model.DocTypeVO;

/**
 * @author jutamas
 *
 */
public class SpoolDebitNoteMISCExecutor  extends ExecutorImpl {

	boolean isAlive = false;
	/**
	 * Execute to generate PDF of Doctype
	 */
	public void execute() throws Exception {
		isAlive = true;
		final SpoolMessage spoolMessage = (SpoolMessage) getMessage();
		//PolicyImplement policyImplement = new PolicyImplement();
		GenerateDocumentSpoolImplement generateDocumentImplement =new GenerateDocumentSpoolImplement();
	  
		DetailVO detailVO=new DetailVO();
		DocTypeVO docTypeVO=new DocTypeVO();
		
		String batchNo = null;
		String sequence = null;
		

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
		    detailVO.setReferenceId(spoolMessage.getPolicyNo());
		    detailVO.setInsuredName(spoolMessage.getInsureName());
		    detailVO.setDocType(docTypeVO);
			
		
			
				
			
		  
			try {
				System.out.println("============  DebitNote MISC Executor ==============");
			    
				generateDocumentImplement.generatePDFDocument(detailVO,false,spoolMessage.getUsername());
					
			} catch (Exception e) {
				System.err.println("============  Error DebitNote MISC Executor ==============");
			    
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
