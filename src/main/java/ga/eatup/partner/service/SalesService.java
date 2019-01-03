package ga.eatup.partner.service;

import java.util.List;

import ga.eatup.partner.domain.SalesVO;

public interface SalesService {
	
	public int getSno(String pid);

	public List<SalesVO> getDailySales(int sno);
	
	public List<SalesVO> getWeeklySales(int sno);
	
	public List<SalesVO> getMonthlySales(int sno);
	
	public List<SalesVO> getDailytableData(int year, int month, int sno);
	
	public List<SalesVO> getDailytableData_date(int year, int month, int sno);
	
	public List<SalesVO> getWeeklytableData(int year, int month, int sno);
	
	public List<SalesVO> getWeeklytableData_date(int year, int month, int sno);
	
	public List<SalesVO> getMonthlytableData(int sno);
	
	public List<SalesVO> getMonthlytableData_date(int sno);
}
