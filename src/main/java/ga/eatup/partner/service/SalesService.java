package ga.eatup.partner.service;

import java.util.List;

import ga.eatup.partner.domain.SalesVO;

public interface SalesService {

	public List<SalesVO> getDailySales();
	
	public List<SalesVO> getWeeklySales();
	
	public List<SalesVO> getMonthlySales();
}
