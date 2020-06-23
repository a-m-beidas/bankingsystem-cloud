package com.example.testsecurity;

import com.example.testsecurity.model.Roles;
import com.example.testsecurity.model.Users;
import com.example.testsecurity.repository.RoleRepository;
import com.example.testsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

@SpringBootApplication
public class TestSecurityApplication {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	private int counter;

	private HashMap<String, Roles> roles;

	public static void main(String[] args) {
		SpringApplication.run(TestSecurityApplication.class, args);
	}

	@EventListener
	void handleApplicationReadyEvent(ApplicationReadyEvent event) {
		roles = new HashMap<String, Roles>();
		Roles admin = new Roles("admin");
		Roles user = new Roles("user");
		roles.put("admin", admin);
		roles.put("user", user);
		roleRepository.saveAll(roles.values());
		ArrayList<Users> users = new ArrayList<Users>();
		users.add(new Users("John", bcryptEncoder.encode("123"), 30000.0f, getRolesArrayList(user)));
		users.add(new Users("Matt", bcryptEncoder.encode("124"), 500.0f, getRolesArrayList(user)));
		users.add(new Users("Blake", bcryptEncoder.encode("125"), 5050.f, getRolesArrayList(user)));
		userRepository.saveAll(users);
	}

	private void addRole(String roleName) {
		Roles role = new Roles(roleName);
		roles.put(roleName, role);
		counter++;
	}

	private ArrayList<Roles> getRolesArrayList(Roles... roles) {
		ArrayList<Roles> temp = new ArrayList<Roles>(counter);
		temp.addAll(Arrays.asList(roles));
		return temp;
	}

}
