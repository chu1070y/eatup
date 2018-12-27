package ga.eatup.user.mapper;

import java.util.List;

import ga.eatup.user.domain.FaqPageDTO;
import ga.eatup.user.domain.FaqVO;

public interface FaqBoardMapper {

	public int faqAdd(FaqVO vo);
	
	public List<FaqVO> faqList(FaqPageDTO dto);
	
	public FaqVO faqRead(int fno);
	
	public int faqCount();
	
	public int faqModify(FaqVO vo);
	
	public int faqRemove(FaqVO vo);
}
