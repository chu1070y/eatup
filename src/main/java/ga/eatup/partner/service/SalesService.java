package ga.eatup.partner.service;

import java.util.List;

import ga.eatup.partner.domain.SalesVO;

public interface SalesService {

	public List<SalesVO> getDailySales();
	
	public List<SalesVO> getWeeklySales();
	
	public List<SalesVO> getMonthlySales();
	
	public List<SalesVO> getDailytableData();
	
	public List<SalesVO> getDailytableData_date();
	
	public List<SalesVO> getWeeklytableData();
	
	public List<SalesVO> getWeeklytableData_date();
	
	public List<SalesVO> getMonthlytableData();
	
	public List<SalesVO> getMonthlytableData_date();
}
