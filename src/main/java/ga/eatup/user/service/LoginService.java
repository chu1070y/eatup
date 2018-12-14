package ga.eatup.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ga.eatup.user.domain.UserVO;

@Service
public interface LoginService {

	public List<UserVO> getUserList();
	
	public int registerUser(UserVO vo);
	
	public UserVO getUser(String uid);
	
	public int setDefaultkey(String sns_id);
	
	public int encodeDefaultkey(UserVO vo);
	
	public int nullDefaultkey(String uid);
}
