package sn.htg.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping(value = "/admin")
	public String admin() {
		return "admin/index";
	}

	@GetMapping(value = "/manager")
	public String manager() {
		return "manager/index";
	}

	@GetMapping(value = "/user")
	public String user() {
		return "user/index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/403")
	public String denyAccess() {
		return "403";
	}

	@GetMapping("/test")
	public String test() {
		return "test/index";
	}
}
