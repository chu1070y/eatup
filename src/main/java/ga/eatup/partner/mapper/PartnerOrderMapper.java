package ga.eatup.partner.mapper;

import java.util.List;

import ga.eatup.partner.domain.OrderVO;
import ga.eatup.partner.domain.StoreVO;

public interface PartnerOrderMapper {


	public List<OrderVO> getOrder(OrderVO order);
	
	public List<StoreVO> getOpen(StoreVO sno);
}
