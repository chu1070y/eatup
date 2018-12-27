package ga.eatup.user.domain;


import java.util.List;

import lombok.Data;


@Data
public class CartDTO {

	private int sno;
	private int uno;
	private int mno;
	private int mprice;
	private int quantity;
	private int totalPrice;
	private String tid;
	private String sname;
	private String mname;
	
	private boolean compare(MenuVO vo) {
		
		if(this.sno== vo.getSno() && this.mno==vo.getMno()) {
			
				return true;
		}
		return false;
	}
	
	public static void classify(List<CartDTO> cartList, List<MenuVO> menu){
		
	
		for (MenuVO menuVO : menu) {
			
			for (CartDTO cart : cartList) {
				if(cart.compare(menuVO)) {
					cart.setMprice(menuVO.getMprice());
					cart.setSname(menuVO.getSname());
					cart.setMname(menuVO.getMname());
					cart.setTotalPrice(menuVO.getMprice() * cart.getQuantity());

				}
			}
		
		}
		
	}



}
