package ga.eatup.user.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;


public class OrderNumDTO {
	
	private static Map<Integer, Integer[]> order_num = new HashMap<>();

	public static Map<Integer, Integer[]> getOrder_num() {
		return order_num;
	}

	public static void putOrder_num(Integer int1, Integer[] int2) {
		OrderNumDTO.order_num.put(int1, int2) ;
	}


}
