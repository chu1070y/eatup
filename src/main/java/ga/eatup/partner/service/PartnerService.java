package ga.eatup.partner.service;

import java.util.List;

import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.user.domain.MenuVO;

import org.springframework.stereotype.Service;

@Service
public interface PartnerService {

	public List<PartnerVO> getPartnerList();
	
	public PartnerVO getPartner(String pid);
	
	public int registerPartner(PartnerVO vo);

	public int registerAuth(PartnerVO vo);

}
