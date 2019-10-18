package sn.htg.demosecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sn.htg.demosecurity.dao.UtilisateursRepository;

@SpringBootApplication
public class DemoSecurityApplication implements CommandLineRunner {

	private UtilisateursRepository userRepository;

	//private PasswordEncoder passwordEncoder;

	public DemoSecurityApplication(UtilisateursRepository userRepository) {

		this.userRepository = userRepository;
		//this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
//		Utilisateurs user1 = new Utilisateurs("admin", passwordEncoder.encode("admin"), "ADMIN",
//				"ACCESS_TEST1,ACCESS_TEST2");
//		Utilisateurs user2 = new Utilisateurs("manager", passwordEncoder.encode("manager"), "MANAGER", "ACCESS_TEST1");
//		Utilisateurs user3 = new Utilisateurs("user", passwordEncoder.encode("user"), "USER", "");
//
//		// Add
//		List<Utilisateurs> users = Arrays.asList(user1, user2, user3);
//
//		this.userRepository.save(users);

		userRepository.findAll().forEach(u -> {
			System.out.println("Username :" + u.getUsername());
		});
	}

}
