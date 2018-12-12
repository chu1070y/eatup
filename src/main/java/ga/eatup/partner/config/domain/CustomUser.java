package ga.eatup.partner.config.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import ga.eatup.partner.domain.PartnerVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class CustomUser extends User{

	private PartnerVO vo;
	
	public CustomUser(PartnerVO vo) {
		this(vo.getPid(), vo.getPpw(), vo.getPmail(), vo.getOwner_name(), vo.getOwner_num(), true, true, true, true,
				vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList())
				);
		this.vo = vo;
	}
	
	public CustomUser(String username, String password, String email, String owner_name, String owner_num,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}

}
