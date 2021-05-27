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
		user1.setEmail("jsmith@example.com");
		user1.setRole(roleService.findById(2).get());

		userDao.save(user1);

		User user2 = new User();
		user2.setFirstName("Jane");
		user2.setLastName("Smith");
		user2.setUsername("jane");
		user2.setPassword(new BCryptPasswordEncoder().encode("1234"));
		user2.setEmail("jane@example.com");
		user2.setRole(roleService.findById(2).get());

		userDao.save(user2);
	}
}
