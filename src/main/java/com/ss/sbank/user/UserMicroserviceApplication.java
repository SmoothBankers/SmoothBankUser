package com.ss.sbank.user;

import com.ss.sbank.user.dao.UserDao;
import com.ss.sbank.user.entity.User;
import com.ss.sbank.user.service.UserRoleService;
import com.ss.sbank.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UserMicroserviceApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService roleService;

	@Autowired
	private UserDao userDao;


	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}

	// Creating a test user here instead of data.sql because need Bcrypt
	@Override
	public void run(String... args) throws Exception {

		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Smith");
		user1.setUsername("johnsmith");
		user1.setPassword(new BCryptPasswordEncoder().encode("1234"));
		user1.setEmail("jsmith@smoothbank.com");
		user1.setRole(roleService.findById(1).get());

		userDao.save(user1);

		User user3 = new User();
		user3.setFirstName("John");
		user3.setLastName("Doe");
		user3.setUsername("jdoe");
		user3.setPassword(new BCryptPasswordEncoder().encode("1234"));
		user3.setEmail("jdoe@smoothbank.com");
		user3.setRole(roleService.findById(1).get());

		userDao.save(user3);

		User user4 = new User();
		user4.setFirstName("Adam");
		user4.setLastName("Smith");
		user4.setUsername("asmith");
		user4.setPassword(new BCryptPasswordEncoder().encode("1234"));
		user4.setEmail("asmith@smoothbank.com");
		user4.setRole(roleService.findById(1).get());

		userDao.save(user4);

		User user2 = new User();
		user2.setFirstName("Jane");
		user2.setLastName("Smith");
		user2.setUsername("jane");
		user2.setPassword(new BCryptPasswordEncoder().encode("1234"));
		user2.setEmail("jane@example.com");
		user2.setRole(roleService.findById(2).get());

		userDao.save(user2);

		User user5 = new User();
		user5.setFirstName("Saif");
		user5.setLastName("Zahedi");
		user5.setUsername("szahedi");
		user5.setPassword(new BCryptPasswordEncoder().encode("1234"));
		user5.setEmail("saif.zahedi@smoothstack.com");
		user5.setRole(roleService.findById(2).get());

		userDao.save(user5);

		User user6 = new User();
		user6.setFirstName("Novak");
		user6.setLastName("Djokovic");
		user6.setUsername("novak");
		user6.setPassword(new BCryptPasswordEncoder().encode("1234"));
		user6.setEmail("ndojokovic@example.com");
		user6.setRole(roleService.findById(2).get());

		userDao.save(user6);

		User user7 = new User();
		user7.setFirstName("Rafael");
		user7.setLastName("Nadal");
		user7.setUsername("rnadal");
		user7.setPassword(new BCryptPasswordEncoder().encode("1234"));
		user7.setEmail("rnadal@example.com");
		user7.setRole(roleService.findById(2).get());

		userDao.save(user7);
	}
}
