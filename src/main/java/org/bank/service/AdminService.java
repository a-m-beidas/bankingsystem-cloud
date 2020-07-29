package org.bank.service;

import org.bank.model.User;
import org.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    public Page<User> listUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
