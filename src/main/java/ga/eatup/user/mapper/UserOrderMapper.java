package ga.eatup.user.mapper;

import java.util.List;
import java.util.Map;

import ga.eatup.user.domain.OrderVO;

public interface UserOrderMapper {

	public List<OrderVO> orderList(Map<String, Object> map);
	
	public OrderVO getOrder(int tid);
}
