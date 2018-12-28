package ga.eatup.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import ga.eatup.user.domain.StoreVO;

@Service
public interface StoreService {
	
	public List<StoreVO> getStore();
	
	public List<StoreVO> getStoreNear(double lat, double lng);
	
	public List<StoreVO> getStoreImg(@RequestParam("sno") int sno);
}
