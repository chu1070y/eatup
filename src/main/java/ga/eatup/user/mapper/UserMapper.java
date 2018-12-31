package ga.eatup.user.mapper;

import java.util.List;

import ga.eatup.user.domain.OrderVO;
import ga.eatup.user.domain.UserVO;

public interface UserMapper {

	
	public List<UserVO> getUserList();
	
	public UserVO getUser(String uid);
	
	public int registerUser(UserVO vo);
	
	public int registerAuth(UserVO vo);
	
	public int update(String enPw);

	public int setDefaultkey(String sns_id);
	
	public int encodeDefaultkey(UserVO vo);
	
	public int nullDefaultkey(String uid);
	
	public int checkId(String uid);
	
}
