package com.example.testsecurity;

import com.example.testsecurity.model.Role;
import com.example.testsecurity.model.User;
import com.example.testsecurity.repository.RoleRepository;
import com.example.testsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@EnableSwagger2
@SpringBootApplication
public class BankApplication {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	private int counter;

	private HashMap<String, Role> roles;

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@EventListener
	void handleApplicationReadyEvent(ApplicationReadyEvent event) {
		roles = new HashMap<String, Role>();
		Role admin = new Role("admin");
		Role user = new Role("user");
		roles.put("admin", admin);
		roles.put("user", user);
		roleRepository.saveAll(roles.values());
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John", bcryptEncoder.encode("123"), 30000.0f, getRolesArrayList(user)));
		users.add(new User("Matt", bcryptEncoder.encode("124"), 500.0f, getRolesArrayList(user)));
		users.add(new User("Blake", bcryptEncoder.encode("125"), 5050.f, getRolesArrayList(user)));
		userRepository.saveAll(users);
	}

	private void addRole(String roleName) {
		Role role = new Role(roleName);
		roles.put(roleName, role);
		counter++;
	}

	private ArrayList<Role> getRolesArrayList(Role... roles) {
		ArrayList<Role> temp = new ArrayList<Role>(counter);
		temp.addAll(Arrays.asList(roles));
		return temp;
	}

}
