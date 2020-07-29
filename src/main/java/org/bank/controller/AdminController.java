package org.bank.controller;

import org.bank.model.User;
import org.bank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping(path = "/users")
    public ResponseEntity<Page<User>> listUsers(@RequestParam(required = false) Integer start) {
        start = (start == null) ? 0 : start;
        return new ResponseEntity<Page<User>>(adminService.listUsers(PageRequest.of(start, start + 5)), HttpStatus.OK);
    }
}
