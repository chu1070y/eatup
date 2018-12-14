package ga.eatup.partner.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import ga.eatup.partner.domain.PartnerMenuVO;

@Service
public interface PartnerMenuService {

	public List<PartnerMenuVO> getMenu(@RequestParam("sno") int sno);
}
