package com.example.testsecurity.service;


import com.example.testsecurity.model.Roles;
import com.example.testsecurity.model.Users;
import com.example.testsecurity.repository.UserRepository;
import com.example.testsecurity.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder bcryptEncoder;

    public CustomUserDetailsService(UserRepository repository) {
        super();
    }

    /**
     * Overrides the {@link UserDetailsService#loadUserByUsername(String)} method
     * @return returns {@link CustomUserDetails}
     */
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repository.findByUsername(username);
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(user.getRoles().size());
        for (Roles role: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        }
        AuthorityUtils utils = null;
        utils.createAuthorityList();
        return new CustomUserDetails(user.getUsername(), user.getPassword(), user.getId(), authorities);
    }

    public Users save(Users user) {
        if(user.getRoles().isEmpty())
            throw new IllegalStateException("User must have at least one role");
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}