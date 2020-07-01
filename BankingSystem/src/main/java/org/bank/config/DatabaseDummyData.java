package org.bank.config;

import org.bank.model.Role;
import org.bank.model.User;
import org.bank.repository.RoleRepository;
import org.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@SpringBootConfiguration
public class DatabaseDummyData {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    @EventListener
    void handleApplicationReadyEvent(ApplicationReadyEvent event) {
        HashMap<String, Role> roles;
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

    private ArrayList<Role> getRolesArrayList(Role... roles) {
        ArrayList<Role> temp = new ArrayList<Role>();
        temp.addAll(Arrays.asList(roles));
        return temp;
    }
}
