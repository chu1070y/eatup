package ga.eatup.partner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ga.eatup.partner.domain.SalesVO;

public interface SalesMapper {

	public List<SalesVO> getDailySales(int sno);
	
	public List<SalesVO> getWeeklySales(int sno);
	
	public List<SalesVO> getMonthlySales(int sno);
	
	public List<SalesVO> getDailytableData(@Param("month")int month, int sno);
	
	public List<SalesVO> getDailytableData_date(@Param("month")int month, int sno);
	
	public List<SalesVO> getWeeklytableData(@Param("month")int month, int sno);
	
	public List<SalesVO> getWeeklytableData_date(@Param("month")int month, int sno);
	
	public List<SalesVO> getMonthlytableData(int sno);
	
	public List<SalesVO> getMonthlytableData_date(int sno);
}
