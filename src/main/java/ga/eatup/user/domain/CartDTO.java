package ga.eatup.user.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class CartDTO {

	private int sno;
	private int mno;
	private int mprice;
	private int quantity;
	private int totalPrice;
	private String mname;
	



	private boolean compare(MenuVO vo) {
		
		if(this.sno== vo.getSno() && this.mno==vo.getMno()) {
			
				return true;
		}
		return false;
	}
	
	public static void classify(List<CartDTO> cartList,List<MenuVO> menu){
		
	
		
		for (MenuVO menuVO : menu) {
			
			for (CartDTO cart : cartList) {
				if(cart.compare(menuVO)) {
					cart.setMprice(menuVO.getMprice());
					cart.setMname(menuVO.getMname());
					cart.setTotalPrice(menuVO.getMprice() * cart.getQuantity());
				}
			}
		
		}
		
	}



}
