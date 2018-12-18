package ga.eatup.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ga.eatup.partner.domain.SalesVO;
import ga.eatup.partner.mapper.SalesMapper;
import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class SalesServiceImpl implements SalesService {

	@Setter(onMethod_=@Autowired)
	private SalesMapper mapper;
	
	@Override
	public List<SalesVO> getDailySales() {
		log.info("daily chart load..");
		return mapper.getDailySales();
	}
	

	@Override
	public List<SalesVO> getWeeklySales() {
		
		return mapper.getWeeklySales();
	}

	@Override
	public List<SalesVO> getMonthlySales() {
		
		return mapper.getMonthlySales();
	}


	@Override
	public List<SalesVO> getDailytableData(int month) {
		log.info(month + " daily data menu.ver load..");
		return mapper.getDailytableData(month);
	}


	@Override
	public List<SalesVO> getDailytableData_date(int month) {
		log.info(month + " daily data date.ver load..");
		return mapper.getDailytableData_date(month);
	}


	@Override
	public List<SalesVO> getWeeklytableData(int month) {
		log.info(month + " weekly data menu.ver load..");
		return mapper.getWeeklytableData(month);
	}


	@Override
	public List<SalesVO> getWeeklytableData_date(int month) {
		log.info(month + " weekly data date.ver load..");
		return mapper.getWeeklytableData_date(month);
	}


	@Override
	public List<SalesVO> getMonthlytableData() {
		
		return mapper.getMonthlytableData();
	}


	@Override
	public List<SalesVO> getMonthlytableData_date() {
		
		return mapper.getMonthlytableData_date();
	}
	
	
	
	
	
	

}
