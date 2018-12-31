package ga.eatup.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ga.eatup.user.domain.CartDTO;
import ga.eatup.user.domain.OrderInfoVO;
import ga.eatup.user.domain.OrderVO;
import ga.eatup.user.mapper.UserOrderMapper;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
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

	@Override
	public int getUno(String uid) {
		log.info("get uno..");
		return mapper.getUno(uid);
	}

	@Override
	public List<OrderVO> getOrderHistory(Map<String, Object> map) {
		log.info("=====OrderServiceImpl=======");
		return mapper.getOrderHistory(map);
	}

	@Override

	public List<OrderVO> getQuickMenu(int uno) {
		
		return mapper.getQuickMenu(uno);
	}
	
	public int tokenUpdate(OrderVO vo){
		
		return mapper.tokenUpdate(vo);
	}


}
