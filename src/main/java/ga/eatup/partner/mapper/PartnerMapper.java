package ga.eatup.partner.mapper;

import java.util.List;

import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.user.domain.MenuVO;

public interface PartnerMapper {

	
	public List<PartnerVO> getPartnerList();
		
	public PartnerVO getPartner(String pid);
	
	public int registerPartner(PartnerVO vo);
	
	public int registerAuth(PartnerVO vo);
		
}
