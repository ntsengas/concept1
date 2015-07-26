package concept1.web.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import concept1.web.domain.User;

@Controller
public class BaseController {

	@RequestMapping("/")
	public String home(Map<String, Object> model) {
		model.put("message", "Hello World");
		model.put("title", "Hello Home");
		model.put("date", new Date());
		return "home";
	}

	@RequestMapping("/foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}
	
//	@RequestMapping(method=RequestMethod.GET)
//	public ModelAndView list() {
//		//note: Populating the SecurityContextHolder will tell Spring the user has
//		//been authenticated.
//		SecurityContext ctx = SecurityContextHolder.getContext();
//		Authentication authentication = ctx.getAuthentication();
//		User custom = authentication == null ? null: (User)authentication.getPrincipal();
//		
//		return null;
//	}

//	@RequestMapping(method=RequestMethod.GET)
//	public ModelAndView list(Authentication authentication) {
//		User custom = authentication == null ? null: (User)authentication.getPrincipal();
//		
//		return null;
//	}
	
//	@RequestMapping(method=RequestMethod.GET)
//	public ModelAndView list(@AuthenticationPrincipal User currentUser) {
//		
//		return null;
//	}
	
}
