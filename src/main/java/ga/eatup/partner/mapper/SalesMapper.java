package ga.eatup.partner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ga.eatup.partner.domain.SalesVO;

public interface SalesMapper {

	public List<SalesVO> getDailySales();
	
	public List<SalesVO> getWeeklySales();
	
	public List<SalesVO> getMonthlySales();
	
	public List<SalesVO> getDailytableData(@Param("month")int month);
	
	public List<SalesVO> getDailytableData_date(@Param("month")int month);
	
	public List<SalesVO> getWeeklytableData(@Param("month")int month);
	
	public List<SalesVO> getWeeklytableData_date(@Param("month")int month);
	
	public List<SalesVO> getMonthlytableData();
	
	public List<SalesVO> getMonthlytableData_date();
}
