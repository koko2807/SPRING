package com.aloha.security_method.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Users {
    private int no;
    private String username;
    private String password;
    private String name;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private int enabled;

    private List<UserAuth> authList;

}