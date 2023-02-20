/*
 * Created on 22 ¡.Â. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.queuemanager;

import java.lang.Exception;
/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Queue {

	public Object getNext(int size) throws Exception;
	public int countRemain() throws Exception;
}
