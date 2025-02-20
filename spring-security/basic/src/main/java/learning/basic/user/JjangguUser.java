package learning.basic.user;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JjangguUser implements UserDetails {

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(() -> "READ");
	}

	@Override
	public String getPassword() {
		return "12345";
	}

	@Override
	public String getUsername() {
		return "jjanggu.shin";
	}
}
