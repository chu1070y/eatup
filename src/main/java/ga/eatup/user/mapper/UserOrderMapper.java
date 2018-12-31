package ga.eatup.user.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import ga.eatup.user.domain.OrderInfoVO;
import ga.eatup.user.domain.OrderVO;

public interface UserOrderMapper {

	public int insertOrder(OrderVO vo);
	
	public int insertOrderInfo(OrderInfoVO vo);
	
	public int getUno(String uid);
	
	public List<OrderVO> getOrderHistory(Map<String, Object> map);
	
	public OrderVO getTid(String tid);

}
