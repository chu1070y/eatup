package ga.eatup.partner.mapper;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import ga.eatup.partner.domain.PartnerMenuVO;

public interface PartnerMenuMapper {
	
	public List<PartnerMenuVO> getMenu(@RequestParam("sno") int sno);

}
