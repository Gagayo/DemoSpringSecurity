package sn.htg.demosecurity.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		// URL
		String targetUrl = determineUrl(authentication);

		if (response.isCommitted()) {
			return;
		}
		// Redirect Object
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);

	}

	protected String determineUrl(Authentication authentication) {

		// url
		String url = "/login?error=true";

		// Fetch role in auth object
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority grantedAuthority : authorities) {
			roles.add(grantedAuthority.getAuthority());
		}

		// ADMIN role
		if (roles.contains("ROLE_ADMIN")) {
			
			url = "/admin";

		} 
		// MANAGER role
		else if (roles.contains("ROLE_MANAGER")) {
			url = "/manager";
		}
		// USER role
		else if (roles.contains("ROLE_USER")) {
			url = "/user";
		}
		// Ajouter d'autre role sil existe

		return url;
	}
}
