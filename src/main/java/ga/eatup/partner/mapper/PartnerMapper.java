package ga.eatup.partner.mapper;

import java.util.List;

import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.partner.domain.StoreVO;

public interface PartnerMapper {

	
	public List<PartnerVO> getPartnerList();
		
	public PartnerVO getPartner(String pid);
	
	public int registerPartner(PartnerVO vo);
	
	public int registerAuth(PartnerVO vo);
	
	public int checkId(String pid);
	
	public int insertPartner(PartnerVO vo);
	
	public int insertStore(StoreVO vo);
		
}
