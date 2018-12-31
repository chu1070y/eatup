package ga.eatup.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.partner.domain.StoreVO;
import ga.eatup.partner.mapper.PartnerMapper;
import ga.eatup.partner.mapper.PartnerOrderMapper;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Service
public class PartnerServiceImpl implements PartnerService {

	@Setter(onMethod_=@Autowired)
	private PartnerMapper mapper;
	
	@Setter(onMethod_=@Autowired)
	private PartnerOrderMapper ordermapper;
	
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

	@Override
	public int checkId(String pid) {
		// TODO Auto-generated method stub
		return mapper.checkId(pid);
	}

	@Transactional
	@Override
	public int insertPartner(PartnerVO partnerVO, StoreVO storeVO) {
		
		mapper.insertPartner(partnerVO);
		
		log.info("pno입니당: " + partnerVO.getPno());
		
		storeVO.setPno(partnerVO.getPno());
		
		return mapper.insertStore(storeVO);
	}

	@Override
<<<<<<< HEAD
	public List<StoreVO> getOpen(StoreVO storeVO) {
		
		ordermapper.getOpen(storeVO);
		
		return ordermapper.getOpen(storeVO);
		
=======
	public int orderComplete(String tid) {
		
		return mapper.orderComplete(tid);
>>>>>>> master
	}





}
