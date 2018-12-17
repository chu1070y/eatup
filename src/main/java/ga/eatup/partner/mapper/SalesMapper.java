package ga.eatup.partner.mapper;

import java.util.List;

import ga.eatup.partner.domain.SalesVO;

public interface SalesMapper {

	public List<SalesVO> getDailySales();
	
	public List<SalesVO> getWeeklySales();
	
	public List<SalesVO> getMonthlySales();
	
	public List<SalesVO> getDailytableData();
	
}
