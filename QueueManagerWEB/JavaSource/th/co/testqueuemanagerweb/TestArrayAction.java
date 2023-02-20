/*
 * Created on 26 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.testqueuemanagerweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestArrayAction extends Action {

	
	public ActionForward execute(ActionMapping mapping, ActionForm fform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		DynaActionForm form = (DynaActionForm)fform;
		if("submit".equals(form.get("action"))){
			String [] a = (String[]) form.get("set");
			for (int i = 0; i < a.length; i++) {
				System.out.println(i+":"+a[i]);
			}
		}
		return mapping.findForward("success");
	}
}
