package ga.eatup.user.mapper;

import java.util.List;

import ga.eatup.user.domain.UserVO;

public interface UserMapper {

	
	public List<UserVO> getUserList();
	
	public UserVO getUser(String uid);
	
	public int registerUser(UserVO vo);
	
	public int update(String enPw);

}
