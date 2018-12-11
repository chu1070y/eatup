package ga.eatup.user.domain.kakaopay;

import lombok.Data;

@Data
public class AmountVO {

	private Integer total, tax_free, vat, point, discount;
}
