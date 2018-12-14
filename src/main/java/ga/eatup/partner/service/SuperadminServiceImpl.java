package ga.eatup.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ga.eatup.partner.domain.NoticePageDTO;
import ga.eatup.partner.domain.NoticeUploadVO;
import ga.eatup.partner.domain.NoticeVO;
import ga.eatup.partner.mapper.SuperadminMapper;
import ga.eatup.user.domain.MenuVO;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Service
public class SuperadminServiceImpl implements SuperadminService{
	
	@Setter(onMethod_ = @Autowired)
	private SuperadminMapper mapper;
	
	@Override
	public int menuAdd(MenuVO vo) {
		// TODO Auto-generated method stub
		return mapper.menuAdd(vo);
	}

	@Override
	public MenuVO searchSno(String sname, String mname) {
		// TODO Auto-generated method stub
		return mapper.searchSno(sname, mname);
	}

	@Override
	public MenuVO searchSnoMno(String sname, String mname) {
		// TODO Auto-generated method stub
		return mapper.searchSnoMno(sname, mname);
	}

	@Override
	public int menuUpdate(MenuVO vo) {
		// TODO Auto-generated method stub
		return mapper.menuUpdate(vo);
	}

	@Override
	public int menuRemove(int mno) {
		// TODO Auto-generated method stub
		return mapper.menuRemove(mno);
	}

	@Transactional
	@Override
	public int noticeAdd(NoticeVO vo) {
		log.info("noticeAdd......... service");
		
		if(vo.getUploadList() == null || vo.getUploadList().size() <= 0) {
			return mapper.noticeAdd(vo);
		}
		
		int result = mapper.noticeAdd(vo);
		
		vo.getUploadList().forEach(upload -> {
			upload.setNno(vo.getNno());
			mapper.uploadAdd(upload);
		});
		
		return result;
	}

	@Override
	public List<NoticeVO> noticeList(NoticePageDTO dto) {
		// TODO Auto-generated method stub
		return mapper.noticeList(dto);
	}

	@Override
	public NoticeVO noticeRead(int nno) {
		// TODO Auto-generated method stub
		return mapper.noticeRead(nno);
	}

	@Override
	public List<NoticeUploadVO> uploadRead(int nno) {
		// TODO Auto-generated method stub
		return mapper.uploadRead(nno);
	}

	@Override
	public int noticeCount() {
		// TODO Auto-generated method stub
		return mapper.noticeCount();
	}

	
	
}
