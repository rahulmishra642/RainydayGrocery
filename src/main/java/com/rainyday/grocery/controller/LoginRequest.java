package com.rainyday.grocery.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String userName;

    private String password;
}