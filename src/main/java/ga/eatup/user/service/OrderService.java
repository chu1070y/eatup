package ga.eatup.user.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import ga.eatup.user.domain.CartDTO;
import ga.eatup.user.domain.OrderVO;

public interface OrderService {

	public void insertOrder(OrderVO vo,List<CartDTO> list);
	
	public int getUno(String uid);
	
	public List<OrderVO> getOrderHistory(int uno);
	
}
