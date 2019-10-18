package sn.htg.demosecurity.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sn.htg.demosecurity.dao.UtilisateursRepository;
import sn.htg.demosecurity.entities.Utilisateurs;

@RestController
@RequestMapping("api/public")
public class PublicRestAPIController {

	private UtilisateursRepository userRepo;
	
	public PublicRestAPIController(UtilisateursRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}


	@GetMapping("test1")
	public String test1() {
		
		return "API Test 1";
	}


	@GetMapping("test2")
	public String test2() {
		
		return "API Test 2";
	}

	@GetMapping("users")
	public List<Utilisateurs> users(){
		
		return userRepo.findAll();
	}
}
