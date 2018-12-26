package ga.eatup.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ga.eatup.user.domain.StoreVO;

public interface StoreMapper {

	public List<StoreVO> getStore();
	
	public List<StoreVO> getStoreNear(
			@Param("plat")double lat, 
			@Param("plng")double lng);
}
