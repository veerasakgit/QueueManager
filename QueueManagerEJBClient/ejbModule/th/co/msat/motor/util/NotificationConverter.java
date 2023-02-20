/*
 * Created on 10 ¡.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import th.co.msat.motor.database.vo.Notification;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotificationConverter {
	public static Notification convert(Map m,Collection header){
		Iterator iter = header.iterator();
		Notification n = new Notification();
		Object o[] = header.toArray(new String[header.size()]);
		n.setNotificationNo   ((String) m.get(o[0]));
		n.setAccidentDate     (((String)m.get(o[1]      )).trim().equals("")? 0
				:Integer.parseInt((String)m.get(o[1]       )));
		n.setAccidentTime     (((String)m.get(o[2]      )).trim().equals("")? 0
				:Integer.parseInt(((String)m.get(o[2]      )).replaceAll("\\.","")));
		n.setNotificationDate (((String)m.get(o[3]   )).trim().equals("")? 0
				:Integer.parseInt((String)m.get(o[3]   )));
		n.setNotificationTime (((String)m.get(o[4] )).trim().equals("")? 0
				:Integer.parseInt(((String)m.get(o[4]   )).replaceAll("\\.","")));
		n.setNotificationName ((String)m.get(o[5] ));
		n.setTelephone        ((String)m.get(o[6] ));
		n.setAccidentPlace    ((String)m.get(o[7]));
		n.setDescriptionLine1 ((String)m.get(o[8]));
		n.setDescriptionLine2 ((String)m.get(o[9]));
		n.setDescriptionLine3 ((String)m.get(o[10]));
		n.setDescriptionLine4 ((String)m.get(o[11] ));
		n.setDescriptionLine5 ((String)m.get(o[12]));
		n.setBranch           ((String)m.get(o[13] ));
		n.setReferencePolicy  ((String)m.get(o[14]));
		n.setRiskNo           (((String)m.get(o[15] )).trim().equals("")? 0
				:Integer.parseInt((String)m.get(o[15])));
		n.setPoicyNo          ((String)m.get(o[16] ));
		n.setCarRegisterNo    ((String)m.get(o[17]));
		n.setInsurename       ((String)m.get(o[18]));
		n.setCarBrand         ((String)m.get(o[19]  ));
		n.setInceptionDate    (((String)m.get(o[20])).trim().equals("")? 0
				:Integer.parseInt((String)m.get(o[20])));
		n.setExpireDate       (((String)m.get(o[21])).trim().equals("")? 0
				:Integer.parseInt((String)m.get(o[21])));
		n.setDriverName       ((String)m.get(o[22] ));
		n.setSurveyorName     ((String)m.get(o[23] ));
		n.setSurveyorOffice   ((String)m.get(o[24] ));
		n.setAssignDate       (((String)m.get(o[25] )).trim().equals("")? 0
				:Integer.parseInt((String)m.get(o[25])));
		n.setEstimateDate     (((String)m.get(o[26])).trim().equals("")? 0
				:Integer.parseInt((String)m.get(o[26] )));
		n.setTransactionStatus((String)m.get(o[27]  ));
		n.setCompletedDate    (((String)m.get(o[28]  )).trim().equals("")? 0
				:Integer.parseInt((String)m.get(o[28])));
		return n;
	}
	
	public static Collection convert(Collection c,Collection header){
		Collection n = new ArrayList();
		for (Iterator iter = c.iterator(); iter.hasNext();) {
			Map element = (Map) iter.next();
			n.add(convert(element,header));
		}
		return n;
	}
	
	public static void main(String a[]){
		
	}
}
