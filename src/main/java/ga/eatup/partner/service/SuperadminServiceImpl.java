package ga.eatup.partner.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ga.eatup.partner.domain.NoticePageDTO;
import ga.eatup.partner.domain.NoticeUploadVO;
import ga.eatup.partner.domain.NoticeVO;
import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.partner.domain.StoreVO;
import ga.eatup.partner.mapper.SuperadminMapper;
import ga.eatup.user.domain.MenuVO;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Service
public class SuperadminServiceImpl implements SuperadminService {

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

		if (vo.getUploadList() == null || vo.getUploadList().size() <= 0) {
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

	@Transactional
	@Override
	public int noticeModify(NoticeVO vo) {
		log.info("notice modify service.....");
		
		mapper.removeNoticeUpload(vo);

		if (vo.getUploadList() == null || vo.getUploadList().size() <= 0) {
			return mapper.modifyNotice(vo);
		}

		int nno = vo.getNno();
		

		vo.getUploadList().forEach((upload) -> {
			upload.setNno(nno);
			mapper.uploadAdd(upload);
		});

		return mapper.modifyNotice(vo);
	}

	@Transactional
	@Override
	public int noticeRemove(NoticeVO vo) {
		log.info("notice remove service.....");

		mapper.removeNoticeUpload(vo);
		
		deleteFiles(vo.getUploadList());

		return mapper.removeNotice(vo);
	}

	private void deleteFiles(List<NoticeUploadVO> uploadList) {

		if (uploadList == null || uploadList.size() == 0) {
			return;
		}

		log.info("delete attach files..............");
		log.info(uploadList+"");

		uploadList.forEach(upload -> {

			try {
				Path file = Paths.get(
						"C:\\upload\\" + upload.getUpload_path() + "\\" + upload.getUuid() + "_" + upload.getFname());

				Files.deleteIfExists(file);

				if (Files.probeContentType(file).startsWith("image")) {

					Path thumbNail = Paths.get("C:\\upload\\" + upload.getUpload_path() + "\\s_" + upload.getUuid() + "_"
							+ upload.getFname());

					Files.delete(thumbNail);

				}

			} catch (Exception e) {
				log.info("delete file error");
				e.printStackTrace();
			} // end catch

		});// end foreach

	}

	@Override
	public int checkPid(String pid) {
		// TODO Auto-generated method stub
		return mapper.checkPid(pid);
	}

	@Transactional
	@Override
	public int storeAdd(StoreVO vo, String pid) {
		
		log.info("storeAdd service in superadminServiceImpl......");
		
		if (vo.getImageList() == null || vo.getImageList().size() <= 0) {
			mapper.givePartnerAuth(pid);
			return mapper.storeAdd(vo,pid);
		}
		
		mapper.givePartnerAuth(pid);
		int result = mapper.storeAdd(vo,pid);
		
		vo.getImageList().forEach((image) -> {
			mapper.storeImageAdd(pid, image);
		});
		
		return result;
	}

	@Override
	public int storeModify(StoreVO vo, String pid) {
		log.info("storeModify service in superadminServiceImpl......");
		log.info(""+pid);
		
		mapper.storeImageRemove(pid);
		
		if (vo.getImageList() == null || vo.getImageList().size() <= 0) {
			return mapper.storeAdd(vo,pid);
		}
		
		int result = mapper.storeAdd(vo,pid);
		
		vo.getImageList().forEach((image) -> {
			mapper.storeImageAdd(pid, image);
		});
		
		return result;
	}

	@Override
	public int storeImageRemove(String pid) {
		log.info("storeRemove service in superadminServiceImpl......");
		
		return mapper.storeImageRemove(pid);
	}

}
