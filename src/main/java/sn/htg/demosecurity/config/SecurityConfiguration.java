package sn.htg.demosecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private CustomUserDetailsService customUserDetailsService;

	//@Autowired
	//private CustomLoginSucessHandler successHandler;

	@Autowired
	private CustomLoginSucessHandler2 successHandler;
	
	// IoC by Constructor
	public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
		super();
		this.customUserDetailsService = customUserDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Authentification
		auth.authenticationProvider(authenticationProvider());

	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		//Ignore toutes les ressources js,cs,img ...
//		web.ignoring().antMatchers("webjars/**","js/**","js/**","images/*");
//	}
	
	/* Public URLS */
	private static final String[] PUBLIC_MATCHERS = { "/webjars/**", "/i18n/**", "/css/**", "js/**", "/images/**", "/","/test"};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Secure Resources
		http.authorizeRequests()
					.antMatchers(PUBLIC_MATCHERS).permitAll()
					//.antMatchers("/test").permitAll()
					.antMatchers("/login").permitAll()
					.antMatchers("/user/**")
					.authenticated().antMatchers("/admin/**").hasRole("ADMIN")
					.antMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
					.antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
					.antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2").antMatchers("/api/public/users")
					.hasRole("ADMIN")
	
					.and()
					
					.csrf().disable()
					.formLogin().loginPage("/login")
					.failureUrl("/login?error=true")
					.successHandler(successHandler)
					 //.defaultSuccessUrl("/")
					.and()
					.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
					.logoutSuccessUrl("/")
					
					.and()
					.exceptionHandling().accessDeniedPage("/403");
				

	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

		// Set PasswordEncoder
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		// Userdetails Object
		daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);

		return daoAuthenticationProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
