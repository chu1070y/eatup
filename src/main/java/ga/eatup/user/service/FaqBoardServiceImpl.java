package ga.eatup.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ga.eatup.user.domain.FaqPageDTO;
import ga.eatup.user.domain.FaqVO;
import ga.eatup.user.mapper.FaqBoardMapper;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Service
public class FaqBoardServiceImpl implements FaqBoardService {
	
	@Setter(onMethod_ = @Autowired)
	private FaqBoardMapper mapper;
	
	@Override
	public int faqAdd(FaqVO vo) {
		
		return mapper.faqAdd(vo);
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

	@Override
	public int faqModify(FaqVO vo) {
		// TODO Auto-generated method stub
		return mapper.faqModify(vo);
	}

	@Override
	public int faqRemove(FaqVO vo) {
		// TODO Auto-generated method stub
		return mapper.faqRemove(vo);
	}

	
	
}
