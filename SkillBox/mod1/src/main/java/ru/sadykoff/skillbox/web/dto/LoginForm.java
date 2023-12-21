package ru.sadykoff.skillbox.web.dto;

public class LoginForm {

    private String login;
    private String password;

    public LoginForm(String username, String password) {
        this.login = username;
        this.password = password;
    }

    public LoginForm() {
    }

    public String getUsername() {
        return login;
    }

    public void setUsername(String username) {
        this.login = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
