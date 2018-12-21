package ga.eatup.partner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ga.eatup.partner.domain.StoreVO;
import ga.eatup.partner.mapper.OpenMapper;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Service
public class OpenServiceImpl implements OpenService {
	
	@Setter(onMethod_=@Autowired)
	private OpenMapper mapper;

	@Override
	public int updateX(StoreVO vo) {
		
		
		return mapper.updateOpenX(vo);
	}

	@Override
	public int updateO(StoreVO vo) {
		
		
		return mapper.updateOpenO(vo);
	}

	
}

