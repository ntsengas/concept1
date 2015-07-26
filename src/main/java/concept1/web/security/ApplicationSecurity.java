package concept1.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties security;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/resources/css/**").permitAll()
				.anyRequest().fullyAuthenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
				.permitAll()	
				.and()
			.logout()
				.permitAll();
	}
	
	/**
	 * th:with="currentUser=${#httpServletRequest.userPrincipal?.principal}" 
	 * (Authentication)Principal getUserPrincipal()...so
	 * ((Authentication)getUserPrincipal()).getPrincipal() returns UserDetails...so
	 * in this case UserDetails is CustomUserDetails so currentUser is CustomerUserDetails
	 */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
        auth
            .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

	/**
	 * th:with="currentUser=${#httpServletRequest.userPrincipal?.name}" 
	 * 
	 * <form  method="POST">
	 * 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	 * </form>
	 * 
	 * Spring Security Tag
	 * <form  method="POST">
	 * 	<sec:csrfInput/>
	 * </form>
	 * 
	 * Spring JSP Tag
	 * <form:form  method="POST">
	 * 	
	 * </form:form>
	 * 
	 * Thymeleaf form automatically add csrf token
	 * 
	 */
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//				.withUser("admin")
//					.password("admin")
//					.roles("ADMIN", "USER")
//					.and()
//				.withUser("user")
//					.password("user")
//					.roles("USER");
//	}
}
