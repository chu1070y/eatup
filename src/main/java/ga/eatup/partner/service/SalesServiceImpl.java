package ga.eatup.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ga.eatup.partner.domain.SalesVO;
import ga.eatup.partner.mapper.PartnerMenuMapper;
import ga.eatup.partner.mapper.SalesMapper;
import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class SalesServiceImpl implements SalesService {

	@Setter(onMethod_=@Autowired)
	private SalesMapper mapper;
	
	@Setter(onMethod_=@Autowired)
	private PartnerMenuMapper menuMapper;
	
	@Override
	public int getSno(String pid) {
		log.info("sno를 가져옵니다.");
		return menuMapper.getSno(pid);
	}
	
	@Override
	public List<SalesVO> getDailySales(int sno) {
		log.info("daily chart load..");
		return mapper.getDailySales(sno);
	}
	

	@Override
	public List<SalesVO> getWeeklySales(int sno) {
		
		return mapper.getWeeklySales(sno);
	}

	@Override
	public List<SalesVO> getMonthlySales(int sno) {
		
		return mapper.getMonthlySales(sno);
	}


	@Override
	public List<SalesVO> getDailytableData(int month, int sno) {
		log.info(month + " daily data menu.ver load..");
		return mapper.getDailytableData(month, sno);
	}


	@Override
	public List<SalesVO> getDailytableData_date(int month, int sno) {
		log.info(month + " daily data date.ver load..");
		return mapper.getDailytableData_date(month, sno);
	}


	@Override
	public List<SalesVO> getWeeklytableData(int month, int sno) {
		log.info(month + " weekly data menu.ver load..");
		return mapper.getWeeklytableData(month, sno);
	}


	@Override
	public List<SalesVO> getWeeklytableData_date(int month, int sno) {
		log.info(month + " weekly data date.ver load..");
		return mapper.getWeeklytableData_date(month, sno);
	}


	@Override
	public List<SalesVO> getMonthlytableData(int sno) {
		
		return mapper.getMonthlytableData(sno);
	}


	@Override
	public List<SalesVO> getMonthlytableData_date(int sno) {
		
		return mapper.getMonthlytableData_date(sno);
	}
	
	
	
	
	
	

}
