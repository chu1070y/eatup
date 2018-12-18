package ga.eatup.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ga.eatup.partner.domain.SalesVO;
import ga.eatup.partner.mapper.SalesMapper;
import lombok.Setter;

@Service
public class SalesServiceImpl implements SalesService {

	@Setter(onMethod_=@Autowired)
	private SalesMapper mapper;
	
	@Override
	public List<SalesVO> getDailySales() {
		
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
	public List<SalesVO> getDailytableData() {
		
		return mapper.getDailytableData();
	}


	@Override
	public List<SalesVO> getDailytableData_date() {
		
		return mapper.getDailytableData_date();
	}


	@Override
	public List<SalesVO> getWeeklytableData() {
		 
		return mapper.getWeeklytableData();
	}


	@Override
	public List<SalesVO> getWeeklytableData_date() {
		
		return mapper.getWeeklytableData_date();
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
