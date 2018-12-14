package ga.eatup.user.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserVO {
	private int uno;
	private String uid;
	private String upw;
	private String nickname;
	private String email;
	private String sns_id;
	private String defaultkey;
	private Date regdate;
	private Date updatedate;
	private char status;
	
	private List<UserAuthVO> authList;

	
}