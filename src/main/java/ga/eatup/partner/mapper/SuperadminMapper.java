package ga.eatup.partner.mapper;

import java.util.List;

import ga.eatup.partner.domain.NoticePageDTO;
import ga.eatup.partner.domain.NoticeUploadVO;
import ga.eatup.partner.domain.NoticeVO;
import ga.eatup.user.domain.MenuVO;

public interface SuperadminMapper {
	
	public MenuVO searchSno(String sname, String mname);
	
	public int menuAdd(MenuVO vo);
	
	public MenuVO searchSnoMno(String sname, String mname);
	
	public int menuUpdate(MenuVO vo);
	
	public int menuRemove(int mno);
	
	public int noticeAdd(NoticeVO vo);
	
	public int uploadAdd(NoticeUploadVO vo);
	
	public List<NoticeVO> noticeList(NoticePageDTO dto);
	
	public NoticeVO noticeRead(int nno);
	
	public List<NoticeUploadVO> uploadRead(int nno);
	
	public int noticeCount();

}
