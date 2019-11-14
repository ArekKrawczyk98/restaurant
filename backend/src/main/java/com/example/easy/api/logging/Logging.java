package com.example.easy.api.logging;

import lombok.Value;

@Value
public class Logging {
    private final String login;
    private final String password;

    public boolean canPass(){
        return this.login.equals("bar") || this.login.equals("kitchen") && this.password.equals("pass123");
    }
}
