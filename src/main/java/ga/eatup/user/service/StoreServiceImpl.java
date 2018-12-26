package ga.eatup.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		log.info("스토어 목록을 가져옵니다.");
		return storeMapper.getStore();
	}

	@Override
	public List<StoreVO> getStoreNear(double lat, double lng) {
		log.info("근처의 스토어 목록을 가져옵니다.");
		return storeMapper.getStoreNear(lat, lng);
	}

}
