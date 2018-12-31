package ga.eatup.partner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.partner.domain.StoreVO;

@Service
public interface PartnerService {

	public List<PartnerVO> getPartnerList();
	
	public PartnerVO getPartner(String pid);
	
	public int registerPartner(PartnerVO vo);

	public int registerAuth(PartnerVO vo);
	
	public int checkId(String pid);
	
	public int insertPartner(PartnerVO partnerVO, StoreVO storeVO);
	
	public List<StoreVO> getOpen(StoreVO storeVO);

	public int orderComplete(String tid);

}
