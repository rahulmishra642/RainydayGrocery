package com.rainyday.grocery.service;

import com.rainyday.grocery.exception.DisableUserException;
import com.rainyday.grocery.exception.InvalidUserCredentialsException;
import com.rainyday.grocery.controller.LoginRequest;
import com.rainyday.grocery.models.UserInfo;
import com.rainyday.grocery.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {
    private AuthenticationManager authenticationManager;
    private UserAuthService userAuthService;
    private JwtUtil jwtUtil;

    @Override
    public UserInfo authenticateUser(LoginRequest loginRequest) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        } catch (DisabledException e) {
            throw new DisableUserException("User Inactive");
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException("Invalid Credentials");
        }

        UserDetails userDetails = userAuthService.loadUserByUsername(loginRequest.getUserName());
        Set<String> roles = userDetails.getAuthorities().stream().map(r -> r.getAuthority())
                .collect(Collectors.toSet());
        UserInfo user = new UserInfo();
        user.setUserName(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setRoles(roles);
        return user;
    }

    @Override
    public String getJwtToken(UserInfo userInfo){
        return jwtUtil.generateToken(userInfo);
    }
}
