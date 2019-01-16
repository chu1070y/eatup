package ga.eatup.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import ga.eatup.user.domain.StoreVO;
import ga.eatup.user.mapper.StoreMapper;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Service
public class StoreServiceImpl implements StoreService {

	@Setter(onMethod_=@Autowired)
	private StoreMapper storeMapper;
	
	@Override
	public List<StoreVO> getStore() {
		return storeMapper.getStore();
	}

	@Override
	public List<StoreVO> getStoreNear(double lat, double lng) {
		return storeMapper.getStoreNear(lat, lng);
	}

	@Override
	public List<StoreVO> getStoreImg(@RequestParam("sno") int sno) {
		return storeMapper.getStoreImg(sno);
	}
	
	

}
