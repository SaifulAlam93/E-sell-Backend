package com.my_ecommerce.my_ecommerce.model;

import com.my_ecommerce.my_ecommerce.domain.User01;

public class JwtResponse {

    private User01 user;
    private String jwtToken;

    public JwtResponse(User01 user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public User01 getUser() {
        return user;
    }

    public void setUser(User01 user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
