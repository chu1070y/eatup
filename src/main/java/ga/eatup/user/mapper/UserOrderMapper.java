package ga.eatup.user.mapper;

import java.util.List;
import java.util.Map;

import ga.eatup.user.domain.OrderInfoVO;
import ga.eatup.user.domain.OrderVO;

public interface UserOrderMapper {

	public List<OrderVO> orderList(Map<String, Object> map);
	
	public OrderVO getOrder(int tid);

	public int insertOrder(OrderVO vo);
	
	public int insertOrderInfo(OrderInfoVO vo);

}
