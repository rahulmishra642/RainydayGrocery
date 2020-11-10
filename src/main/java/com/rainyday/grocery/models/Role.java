package com.rainyday.grocery.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Role {

    private Integer id;

    private RoleType role;

    private String description;

}