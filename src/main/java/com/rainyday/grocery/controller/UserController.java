package com.rainyday.grocery.controller;


import com.rainyday.grocery.requestresponse.LoginRequest;
import com.rainyday.grocery.requestresponse.LoginResponse;
import com.rainyday.grocery.service.UserAuthService;
import com.rainyday.grocery.service.UserLoginService;
import com.rainyday.grocery.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(new LoginResponse(
                userLoginService.getJwtToken(
                        userLoginService.authenticateUser(
                                loginRequest))), HttpStatus.OK);

    }

}
