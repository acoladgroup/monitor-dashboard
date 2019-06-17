package com.amplexor.itdashboard.model;

import javax.validation.constraints.NotEmpty;

/**
 * Created by marquesh on 13/08/2018.
 */
public class Credentials {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
