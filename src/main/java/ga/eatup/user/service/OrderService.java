package ga.eatup.user.service;

import java.util.List;
import java.util.Map;

import ga.eatup.user.domain.OrderVO;

public interface OrderService {
	
	public List<OrderVO> orderList(Map<String, Object> map);

}
