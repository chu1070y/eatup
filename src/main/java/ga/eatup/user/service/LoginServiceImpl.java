package ga.eatup.user.service;

import java.util.List;

import ga.eatup.user.domain.UserVO;
import ga.eatup.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Service
public class LoginServiceImpl implements LoginService {

	@Setter(onMethod_=@Autowired)
	private UserMapper mapper;
	
	public List<UserVO> getUserList(){
		log.info("user 리스트를 가져옵니다.");
		return mapper.getUserList();
	}
	
	public int registerUser(UserVO vo) {
		log.info("user를 새로 추가합니다.");
		
		return mapper.registerUser(vo);
	}

	@Override
	public UserVO getUser(String uid) {
		return mapper.getUser(uid);
	}

	@Override
	public int setDefaultkey(String sns_id) {
		return mapper.setDefaultkey(sns_id);
	}

	@Override
	public int encodeDefaultkey(UserVO vo) {
		return mapper.encodeDefaultkey(vo);
	}

	@Override
	public int nullDefaultkey(String uid) {
		return mapper.nullDefaultkey(uid);
	}

	@Override
	public int registerAuth(UserVO vo) {
		return mapper.registerUser(vo);
	}
}
