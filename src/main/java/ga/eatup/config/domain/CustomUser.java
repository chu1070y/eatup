package ga.eatup.config.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.user.domain.UserVO;
import lombok.Data;

@Data
public class CustomUser extends User{

	private PartnerVO partnerVO;
	private UserVO userVO;
	
	public CustomUser(PartnerVO vo) {
		this(vo.getPid(), vo.getPpw(), vo.getPmail(), vo.getOwner_name(), vo.getOwner_num(), null, null, true, true, true, true,
				vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList())
				);
		this.partnerVO = vo;
	}
	
	public CustomUser(UserVO vo) {
		this(vo.getUid(), vo.getUpw(), vo.getEmail(), vo.getNickname(), null, vo.getSns_id(), vo.getDefaultkey(), true, true, true, true,
				vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList())
				);
		this.userVO = vo;
	}
	
	public CustomUser(String username, String password, String email, String owner_name, String owner_num, String snsId, String defaultKey,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}


	
	
}
