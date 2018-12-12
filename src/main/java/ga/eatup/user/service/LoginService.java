package ga.eatup.user.service;

import java.util.List;

import ga.eatup.user.domain.UserVO;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

	public List<UserVO> getUserList();
	
	public int registerUser(UserVO vo);
	
	public UserVO getUser(String uid);
}
