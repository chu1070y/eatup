package ga.eatup.partner.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ga.eatup.partner.domain.SalesVO;
import ga.eatup.partner.service.SalesService;
import lombok.Setter;

@RequestMapping("/partner/*")
@Controller
public class ExcelController {

	@Setter(onMethod_=@Autowired)
	private SalesService service;
	
	@RequestMapping(value="exceldown/dailydata/menu/{month}")
	public void excelDownload(HttpServletResponse response, @PathVariable("month") int month)throws Exception{
		
		List<SalesVO> list = service.getDailytableData(month);
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("dailydata_menu_month");
		Row row = null;
		Cell cell = null;
		int rowNo = 0;
		
		//헤더 테두리
		CellStyle headStyle = wb.createCellStyle();
		headStyle.setBorderTop(BorderStyle.THIN);
	    headStyle.setBorderBottom(BorderStyle.THIN);
	    headStyle.setBorderLeft(BorderStyle.THIN);
	    headStyle.setBorderRight(BorderStyle.THIN);

	    //배경색
	    headStyle.setFillForegroundColor(HSSFColorPredefined.GREY_25_PERCENT.getIndex());
	    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    //가운데정렬
	    headStyle.setAlignment(HorizontalAlignment.CENTER);
	    
	    //데이터 테두리
	    CellStyle bodyStyle = wb.createCellStyle();
	    bodyStyle.setBorderTop(BorderStyle.THIN);
	    bodyStyle.setBorderBottom(BorderStyle.THIN);
	    bodyStyle.setBorderLeft(BorderStyle.THIN);
	    bodyStyle.setBorderRight(BorderStyle.THIN);

	    //헤더 생성
	    row = sheet.createRow(rowNo++);
	    cell = row.createCell(0);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("날짜");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("메뉴명");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("수량");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("소계");
	    
	    //데이터 생성
	    for(SalesVO vo:list) {
	    	row = sheet.createRow(rowNo++);
	    	cell = row.createCell(0);
	    	cell.setCellStyle(bodyStyle);
	    	cell.setCellValue(vo.getOrderdate());
	    	cell = row.createCell(1);
	    	cell.setCellStyle(bodyStyle);
	    	cell.setCellValue(vo.getMname());
	    	cell = row.createCell(2);
	    	cell.setCellStyle(bodyStyle);
	    	cell.setCellValue(vo.getQuantity());
	    	cell = row.createCell(3);
	    	cell.setCellStyle(bodyStyle);
	    	cell.setCellValue(vo.getTotal());
	    	
	    }
	    
	    //컨텐츠 타입, 파일명
	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=.dailydata_menu.xls");
	    
	    //엑셀 출력
	    wb.write(response.getOutputStream());
	    wb.close();
	    
	    

	}
}
