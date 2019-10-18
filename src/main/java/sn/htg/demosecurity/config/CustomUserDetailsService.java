package sn.htg.demosecurity.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sn.htg.demosecurity.dao.UtilisateursRepository;
import sn.htg.demosecurity.entities.Utilisateurs;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UtilisateursRepository userRepository;

	// IoC by Constructor
	public CustomUserDetailsService(UtilisateursRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Get Utilisateur
		Utilisateurs utilisateur = this.userRepository.findByUsername(username);

		if (utilisateur == null) {
			throw new UsernameNotFoundException("Not user found for " + username);
		}
		// Object UserPrincipal
		UserPrincipal userPrincipal = new UserPrincipal(utilisateur);

		return userPrincipal;
	}

}
