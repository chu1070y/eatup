package ga.eatup.user.domain;

import java.util.Date;

import lombok.Data;

@Data
public class UserVO {
	private int uno;
	private String uid;
	private String upw;
	private String name, nickname, sex,age;
	private String email;
	private String sns_id;
	private Date regdate;
	private char status;
	
}