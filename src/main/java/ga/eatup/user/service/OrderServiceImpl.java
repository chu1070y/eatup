package ga.eatup.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ga.eatup.user.domain.CartDTO;
import ga.eatup.user.domain.OrderInfoVO;
import ga.eatup.user.domain.OrderVO;
import ga.eatup.user.mapper.UserOrderMapper;
import lombok.Setter;

@Service
public class OrderServiceImpl implements OrderService {

	@Setter(onMethod_=@Autowired)
	private UserOrderMapper mapper;
	
	@Override
	@Transactional
	public void insertOrder(OrderVO vo, List<CartDTO> list) {
		// TODO Auto-generated method stub
		mapper.insertOrder(vo);
		
		list.forEach(cart->{
			OrderInfoVO order=new OrderInfoVO();
			order.setMno(cart.getMno());
			order.setQuantity(cart.getQuantity());
			order.setTid(vo.getTid());
			mapper.insertOrderInfo(order);
		});
		
	}

}
