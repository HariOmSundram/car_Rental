package com.carrental.user;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.carrental.user.entity.User;
import com.carrental.user.service.UserService;
import com.carrental.user.utility.Constants.ActiveStatus;
import com.carrental.user.utility.Constants.UserRole;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class CarRentalUserServiceApplication implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger(CarRentalUserServiceApplication.class);

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(CarRentalUserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User admin = this.userService.getUserByEmailIdAndRoleAndStatus("demo.admin@demo.com",
				UserRole.ROLE_ADMIN.value(), ActiveStatus.ACTIVE.value());

		if (admin == null) {

			LOG.info("Admin not found in system, so adding default admin");

			User user = new User();
			user.setEmailId("demo.admin@demo.com");
			user.setPassword(Base64.getEncoder().encodeToString("123456".getBytes()));
			user.setRole(UserRole.ROLE_ADMIN.value());
			user.setStatus(ActiveStatus.ACTIVE.value());

			this.userService.addUser(user);

		}

	}

}
