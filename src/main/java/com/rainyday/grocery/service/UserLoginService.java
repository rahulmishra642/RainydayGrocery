package com.rainyday.grocery.service;

import com.rainyday.grocery.controller.LoginRequest;
import com.rainyday.grocery.models.UserInfo;

public interface UserLoginService {
    UserInfo authenticateUser(LoginRequest accountLoginRequest);
    String getJwtToken(UserInfo userInfo);
}
