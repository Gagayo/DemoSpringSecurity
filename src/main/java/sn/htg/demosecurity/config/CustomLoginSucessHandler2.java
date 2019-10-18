package sn.htg.demosecurity.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginSucessHandler2 implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// Url by Role
		boolean hasUserRole = false;
		boolean hasManagerRole = false;
		boolean hasAdminRole = false;

		Collection<? extends GrantedAuthority> authorites = authentication.getAuthorities();

		// Parcours des roles
		for (GrantedAuthority grantedAuthority : authorites) {

			if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
				hasUserRole = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_MANAGER")) {
				hasManagerRole = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				hasAdminRole = true;
				break;
			}
		}

		// Redirection
		if (hasUserRole) {
			redirectStrategy.sendRedirect(request, response, "/user");
		} else if (hasManagerRole) {
			redirectStrategy.sendRedirect(request, response, "/manager");
		} else if (hasAdminRole) {
			redirectStrategy.sendRedirect(request, response, "/admin");
		}

	}

}
