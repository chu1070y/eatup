package ga.eatup.partner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ga.eatup.partner.domain.NoticePageDTO;
import ga.eatup.partner.domain.NoticeUploadVO;
import ga.eatup.partner.domain.NoticeVO;
import ga.eatup.partner.domain.StoreImageVO;
import ga.eatup.partner.domain.StoreVO;
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
	
	public int modifyNotice(NoticeVO vo);
	
	public int uploadCount(NoticeVO vo);
	
	public int removeNotice(NoticeVO vo);
	
	public int removeNoticeUpload(NoticeVO vo);
	
	public int checkPid(String pid);
	
	public int storeAdd(@Param("vo") StoreVO vo, @Param("pid") String pid);
	
	public int givePartnerAuth(String pid);
	
	public int storeImageAdd(@Param("pid") String pid, @Param("vo") StoreImageVO vo);
	
	public int storeImageRemove(String pid);
	
	public int findAuth(String pid);
	
	public int deleteAuth(String pid);
	
	public int removePartnerStore(String pid);
	
	public int removeMenuFromStore(String pid);
	
	public int openMenuFromStore(String pid);

}
