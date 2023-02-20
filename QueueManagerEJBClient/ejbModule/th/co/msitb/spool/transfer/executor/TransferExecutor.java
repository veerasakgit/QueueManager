package th.co.msitb.spool.transfer.executor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import th.co.msat.motor.queuemanager.executor.ExecutorImpl;
import th.co.msitb.spool.core.database.DetailTBDAO;
import th.co.msitb.spool.core.database.DetailTBDAOImplement;
import th.co.msitb.spool.core.database.DocTypeItemDAOImplement;
import th.co.msitb.spool.core.report.TransferDFS;
import th.co.msitb.spool.core.report.TransferDFSImplement;
import th.co.msitb.spool.core.report.generate.GenerateDocumentImplement;
import th.co.msitb.spool.core.report.generate.MergePDFs;
import th.co.msitb.spool.message.SpoolMessage;
import th.co.msitb.spool.model.DetailVO;
import th.co.msitb.spool.model.DocTypeVO;

public class TransferExecutor extends ExecutorImpl {

	boolean isAlive = false;

	/**
	 * Execute to generate PDF of Doctype 
	 */

	public void execute() throws Exception {
		isAlive = true; 
		final SpoolMessage spoolMessage = (SpoolMessage) getMessage();
		List streamPdfList = new ArrayList();
		// PolicyImplement policyImplement = new PolicyImplement();
		GenerateDocumentImplement generateDocumentImplement = new GenerateDocumentImplement();
		MergePDFs mergePDFs = new MergePDFs();
		DocTypeItemDAOImplement docTypeItemDAOImplement = new DocTypeItemDAOImplement();
		DetailTBDAO detailTBDAOImplement = new DetailTBDAOImplement();
		DetailVO	detailVO =new DetailVO();
		DocTypeVO docTypeVO = new DocTypeVO();
		byte[] byteArr = null;
		String batchNo = null;
		String sequence = null;
		String type = null;

		String referId = spoolMessage.getReferDFS();
		if (null != referId && !"".equals(referId)) {
			String[] text = referId.split("#");
			// for(int i=0; i < text.length; i++ ){}
			batchNo = text[0];
			sequence = text[1];
			type = text[2];

			TransferDFS transferDFS = new TransferDFSImplement();
			DetailVO detailVO2 = new DetailVO();
			try {
				
				detailVO= transferDFS.getDataRequireDFS(batchNo,
						new BigDecimal(sequence), new BigDecimal(type));
			} catch (Exception e1) {
				e1.printStackTrace();
				System.err.println("Error generate : getDataRequireDFS  : " +e1.getMessage());
				throw e1;
			}
			detailVO2.setBatchNo(batchNo);
			detailVO2.setSequenceNo(new BigDecimal(sequence));
			
            
			docTypeVO.setDocTypeId(new BigDecimal(type));
			detailVO2.setBroker(detailVO.getBroker());
			detailVO2.setClient(detailVO.getClient());
			detailVO2.setMak(detailVO.getMak());
			detailVO2.setModel(detailVO.getModel());
			detailVO2.setLicense(detailVO.getLicense());
			detailVO2.setChassis(detailVO.getChassis());
			detailVO2.setReferenceId(detailVO.getReferenceId());
			detailVO2.setInsuredName(detailVO.getInsuredName());
			detailVO2.setDocType(docTypeVO);

			try {
		//	transfer to DFS
				System.out.println("Ex getBatchNo      :"+detailVO2.getBatchNo());
				System.out.println("Ex getSequenceNo   :"+detailVO2.getSequenceNo());
			    System.out.println("Ex getDocTypeId    :"+detailVO2.getDocType().getDocTypeId());
				System.out.println("Ex getBroker       :"+detailVO2.getBroker());
				System.out.println("Ex getClient       :"+detailVO2.getClient());
				System.out.println("Ex getMak          :"+detailVO2.getMak());
				System.out.println("Ex getModel        :"+detailVO2.getModel());
				System.out.println("Ex getLicense      :"+detailVO2.getLicense());
				System.out.println("Ex getChassis      :"+detailVO2.getChassis());
				System.out.println("Ex getReferenceId  :"+detailVO2.getReferenceId());
				System.out.println("Ex getInsuredName  :"+detailVO2.getInsuredName());
			
				transferDFS.exportPDFDocument(detailVO2 ,spoolMessage.getUsername());
				
				
				

			} catch (Exception e) {
				
			   
				e.printStackTrace();
				System.err.println("Error generate : exportPDFDocument  : " +e.getMessage());
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
