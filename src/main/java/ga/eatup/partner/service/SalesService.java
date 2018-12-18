package ga.eatup.partner.service;

import java.util.List;

import ga.eatup.partner.domain.SalesVO;

public interface SalesService {

	public List<SalesVO> getDailySales();
	
	public List<SalesVO> getWeeklySales();
	
	public List<SalesVO> getMonthlySales();
	
	public List<SalesVO> getDailytableData(int month);
	
	public List<SalesVO> getDailytableData_date(int month);
	
	public List<SalesVO> getWeeklytableData(int month);
	
	public List<SalesVO> getWeeklytableData_date(int month);
	
	public List<SalesVO> getMonthlytableData();
	
	public List<SalesVO> getMonthlytableData_date();
}
