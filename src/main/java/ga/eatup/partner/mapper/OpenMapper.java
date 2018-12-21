package ga.eatup.partner.mapper;

import ga.eatup.partner.domain.StoreVO;

public interface OpenMapper {
	
	public StoreVO readOpen(Long sno);
	
	public int updateOpenX(StoreVO sno);
	
	public int updateOpenO(StoreVO sno);

}
