package concept1.web.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import concept1.web.domain.User;
import concept1.web.security.domain.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUser();   //Get User using rest
		CustomUserDetails customUser = new CustomUserDetails(user);
		
		return customUser;
	}
	
	private User getUser() {
		User user = new User();   //Get User using rest
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setEmail("email@gmail.com");
		user.setPassword(encryptPasswordUsingBcrypt("password"));
		
		return user;
	}
	
	private String encryptPasswordUsingBcrypt(String rawPassword) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		System.out.println(encodedPassword);
		
		if (isValidPassword(rawPassword, encodedPassword)) {
			System.out.println("Is valid");
		}

		return encodedPassword;
	}
	
	private boolean isValidPassword(String rawPassword, String encodedPassword) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
		

}
