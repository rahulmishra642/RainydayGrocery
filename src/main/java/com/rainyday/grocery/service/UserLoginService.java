package com.rainyday.grocery.service;

import com.rainyday.grocery.requestresponse.LoginRequest;
import com.rainyday.grocery.requestresponse.UserInfo;

public interface UserLoginService {
    UserInfo authenticateUser(LoginRequest accountLoginRequest);
    String getJwtToken(UserInfo userInfo);
}
