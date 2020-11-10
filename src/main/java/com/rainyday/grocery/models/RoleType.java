package com.rainyday.grocery.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum RoleType {
    ROLE_EMPLOYEE("employee"),
    ROLE_ADMIN("admin"),
    ROLE_GUEST("guest");

    private String type;

    RoleType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public static RoleType getRoleByType(String type) {
        return Arrays.stream(values()).filter(roleType -> roleType.getType().equals(type))
                .findFirst().orElse(RoleType.ROLE_GUEST);
    }
}
