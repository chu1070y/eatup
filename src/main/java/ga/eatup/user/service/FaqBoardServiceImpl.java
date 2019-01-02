package ga.eatup.user.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ga.eatup.partner.domain.NoticeUploadVO;
import ga.eatup.user.domain.FaqPageDTO;
import ga.eatup.user.domain.FaqUploadVO;
import ga.eatup.user.domain.FaqVO;
import ga.eatup.user.mapper.FaqBoardMapper;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Service
public class FaqBoardServiceImpl implements FaqBoardService {
	
	@Setter(onMethod_ = @Autowired)
	private FaqBoardMapper mapper;
	
	@Transactional
	@Override
	public int faqAdd(FaqVO vo) {
		
		log.info("FaqAdd......... service");
		
		if (vo.getUploadList() == null || vo.getUploadList().size() <= 0) {
			return mapper.faqAdd(vo);
		}
		
		int result = mapper.faqAdd(vo);

		vo.getUploadList().forEach(upload -> {
			upload.setFno(vo.getFno());
			mapper.uploadAdd(upload);
		});


		return result;
	}

	@Override
	public List<FaqVO> faqList(FaqPageDTO dto) {
		// TODO Auto-generated method stub
		return mapper.faqList(dto);
	}

	@Override
	public FaqVO faqRead(int fno) {
		// TODO Auto-generated method stub
		return mapper.faqRead(fno);
	}

	@Override
	public int faqCount() {
		// TODO Auto-generated method stub
		return mapper.faqCount();
	}

	@Transactional
	@Override
	public int faqModify(FaqVO vo) {
		log.info("faq modify service.....");
		
		mapper.removeFaqUpload(vo);
		
		if (vo.getUploadList() == null || vo.getUploadList().size() <= 0) {
			return mapper.faqModify(vo);
		}

		int fno = vo.getFno();
		

		vo.getUploadList().forEach((upload) -> {
			upload.setFno(fno);
			mapper.uploadAdd(upload);
		});
		return mapper.faqModify(vo);
	}

	@Transactional
	@Override
	public int faqRemove(FaqVO vo) {
		log.info("faq remove service.....");

		mapper.removeFaqUpload(vo);
		
		deleteFiles(vo.getUploadList());

		
		return mapper.faqRemove(vo);
	}
	
	private void deleteFiles(List<FaqUploadVO> uploadList) {

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
	public List<FaqUploadVO> uploadRead(int fno) {
		// TODO Auto-generated method stub
		return mapper.uploadRead(fno);
	}

	
	
	
}
