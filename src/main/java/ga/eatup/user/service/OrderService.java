package ga.eatup.user.service;

import java.util.List;

import ga.eatup.user.domain.CartDTO;
import ga.eatup.user.domain.OrderVO;

public interface OrderService {

	public void insertOrder(OrderVO vo,List<CartDTO> list);
}
