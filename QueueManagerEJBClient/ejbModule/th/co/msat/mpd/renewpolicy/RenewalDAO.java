package th.co.msat.mpd.renewpolicy;

public interface RenewalDAO {
	
	public boolean inserReNewtTB(PolicyStatus policyStatus) throws Exception;	
	public boolean updateReNewTB(PolicyStatus policyStatus) throws Exception;	
	
}
