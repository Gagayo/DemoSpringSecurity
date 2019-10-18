package sn.htg.demosecurity.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import sn.htg.demosecurity.entities.Utilisateurs;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Utilisateurs utilisateur;

	public UserPrincipal(Utilisateurs utilisateur) {
		super();
		this.utilisateur = utilisateur;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		// Get All authorities
		List<GrantedAuthority> authorities = new ArrayList<>();

		// Extract List of permissions(name)
		this.utilisateur.getPermissionsList().forEach(permission -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(permission);
			// Add
			authorities.add(authority);

		});
		
		// Extract List of role(roles)
		this.utilisateur.getRoleList().forEach(roles -> {
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roles);
			// Add
			authorities.add(authority);

		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.utilisateur.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.utilisateur.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.utilisateur.getActive() == 1;
	}

}
