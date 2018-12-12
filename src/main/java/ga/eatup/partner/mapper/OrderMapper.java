package ga.eatup.partner.mapper;

import java.util.List;

import ga.eatup.partner.domain.OrderVO;

public interface OrderMapper {

//	public List<OrderVO> getOrder();
	public List<OrderVO> getOrder(OrderVO order);
}
