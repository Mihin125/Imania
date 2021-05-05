package com.demo.dto.security;


import lombok.Getter;

import java.io.Serializable;

@Getter
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private  String jwtToken;
    private  String username;
    private  LoginStatus loginStatus;
    private long personId;


    public AuthenticationResponse(String jwtToken, String username, LoginStatus loginStatus,long personId) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.loginStatus = loginStatus;
        this.personId=personId;
    }

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String username, LoginStatus loginStatus) {
        this.username = username;
        this.loginStatus = loginStatus;
    }
}
