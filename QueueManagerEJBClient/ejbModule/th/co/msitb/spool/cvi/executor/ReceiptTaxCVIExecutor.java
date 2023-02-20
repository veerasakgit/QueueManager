/**
 * 
 */
package th.co.msitb.spool.cvi.executor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import th.co.msat.motor.queuemanager.executor.ExecutorImpl;
import th.co.msitb.spool.core.report.generate.GenerateDocumentImplement;
import th.co.msitb.spool.core.report.generate.MergePDFs;
import th.co.msitb.spool.message.SpoolMessage;
import th.co.msitb.spool.model.DetailVO;
import th.co.msitb.spool.model.DocTypeVO;

/**
 * @author jutamas 
 *
 */
public class ReceiptTaxCVIExecutor extends ExecutorImpl {
	boolean isAlive = false;
	/**
	 * Execute to generate PDF of Doctype
	 */
	public void execute() throws Exception {
		isAlive = true;
		final SpoolMessage spoolMessage = (SpoolMessage) getMessage();
		List streamPdfList = new ArrayList();
		GenerateDocumentImplement generateDocumentImplement =new GenerateDocumentImplement();
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
			
			try {
				System.out.println("============  ReceiptTax CVI Executor ==============");
				generateDocumentImplement.generatePDFDocument(detailVO,false,spoolMessage.getUsername());
			} catch (Exception e) {
			
				System.err.println("============ ERROR ReceiptTax CVI Executor ==============");
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

