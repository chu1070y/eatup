package ga.eatup.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ga.eatup.user.domain.OrderVO;
import ga.eatup.user.mapper.UserOrderMapper;
import lombok.Setter;

public class OrderServiceImpl implements OrderService {
	
	@Setter(onMethod_=@Autowired)
	private UserOrderMapper orderMapper;

	@Override
	public List<OrderVO> orderList(Map<String, Object> map) {
		
		return orderMapper.orderList(map);
	}

}
