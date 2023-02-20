/*
 * Created on 21 ¡.¤. 2551
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package th.co.msat.motor.scrap.database;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import com.ibm.wsspi.sib.exitpoint.ra.HashMap;

/**
 * @author ituser3
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Cache extends HashMap{
	public static final String ALL_PROVINCE = "ALL_PROVINCE";
	public static final String NATURE_OF_LOSS = "NATURE_OF_LOSS";
	public static final String NATURE_OF_LOSS_MAP = "NATURE_OF_LOSS_MAP";
	public static final String N999_SURVERY_FEE="N999_SURVERY_FEE";
	public static final String N201_SURVERY_FEE="N201_SURVERY_FEE";
	public static final String V100_OD = "V100_OD";
	private static Cache instance;
	public static Cache getInstance(){
		if(null==instance)
			instance = new Cache();
		return instance;
	}
	public String getN999() throws SQLException{
		if(null==get(N999_SURVERY_FEE)){
			ReserveDAOImpl revDAO = new ReserveDAOImpl();
			put(N999_SURVERY_FEE,revDAO.getN999Amount());
		}
		return (String) get(N999_SURVERY_FEE);
	}
	public String getN201() throws SQLException{
		if(null==get(N201_SURVERY_FEE)){
			ReserveDAOImpl revDAO = new ReserveDAOImpl();
			put(N201_SURVERY_FEE,revDAO.getN201Amount());
		}
		return (String) get(N201_SURVERY_FEE);
	}
	
	public String getV100() throws SQLException{
		if(null==get(V100_OD)){
			ReserveDAOImpl revDAO = new ReserveDAOImpl();
			put(V100_OD,revDAO.getV100Amount());
		}
		return (String) get(V100_OD);
	}
	
	public List getNatureOfLost() throws Exception{
		if(null==get(NATURE_OF_LOSS)){
			NatureOfLossCodeDAOImpl nDao = new NatureOfLossCodeDAOImpl();
			put(NATURE_OF_LOSS,nDao.getAll());
		}
		return (List) get(NATURE_OF_LOSS);
	}
	
	public String getNatureOfLostDesc(String code) throws Exception{
		if(null==get(NATURE_OF_LOSS_MAP)){
			NatureOfLossCodeDAOImpl nDao = new NatureOfLossCodeDAOImpl();
			put(NATURE_OF_LOSS_MAP,nDao.getAllMap());
		}
		return (String) ((Map)get(NATURE_OF_LOSS_MAP)).get(code);
			
	}
	
	
	public List getAllProvince() throws SQLException{
		if(null==get(ALL_PROVINCE)){
			ProvinceDAOImpl pDao = new ProvinceDAOImpl();
			put(ALL_PROVINCE,pDao.getAll());
		}
		return (List) get(ALL_PROVINCE);
	}
}
