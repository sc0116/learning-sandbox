package learning.basic.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class User implements UserDetails {

	private final String username;
	private final String password;
	private final String authority;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(() -> authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
}
