package com.rainyday.grocery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/welcome/employee")
    public ResponseEntity<String> welcomeEmployee() {
        return new ResponseEntity<String>("Hi Employee, You can start your Work", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/welcome/admin")
    public ResponseEntity<String> welcomeAdmin() {
        return new ResponseEntity<String>("Hi ADMIN, You can control everything", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    @GetMapping("/welcome/employeeOrAdmin")
    public ResponseEntity<String> welcomeEmployeeOrAdmin() {
        return new ResponseEntity<String>("API for Employee and ADMIN role", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ANONYMOUS')")
    @GetMapping("/welcome/anonymous")
    public ResponseEntity<String> greetingAnonymous() {
        return new ResponseEntity<String>("API for Employee and ADMIN role", HttpStatus.OK);
    }
}
