package ga.eatup.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import ga.eatup.partner.domain.PartnerMenuVO;
import ga.eatup.partner.mapper.PartnerMenuMapper;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Service
public class PartnerMenuServiceImpl implements PartnerMenuService {
	
	@Setter(onMethod_=@Autowired)
	private PartnerMenuMapper mapper;


	@Override
	public List<PartnerMenuVO> getMenu(@RequestParam("sno") int sno){
		log.info("get Menu page..........");
		return mapper.getMenu(sno);
	}

}
