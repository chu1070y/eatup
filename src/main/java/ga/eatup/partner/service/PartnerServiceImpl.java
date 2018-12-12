package ga.eatup.partner.service;

import java.util.List;

import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.partner.mapper.PartnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Service
public class PartnerServiceImpl implements PartnerService {

	@Setter(onMethod_=@Autowired)
	private PartnerMapper mapper;
	
	public List<PartnerVO> getPartnerList(){
		log.info("partner 리스트를 가져옵니다.");
		return mapper.getPartnerList();
	}

	@Override
	public PartnerVO getPartner(String pid) {
		log.info("id로 partner 정보를 가져옵니다.");
		return mapper.getPartner(pid);
	}
	
	public int registerPartner(PartnerVO vo) {
		log.info("partner를 새로 추가합니다.");
		
		return mapper.registerPartner(vo);
	}

	@Override
	public int registerAuth(PartnerVO vo) {

		return mapper.registerAuth(vo);
	}

}
