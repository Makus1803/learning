package com.organizr.domain.application.commands;

import java.util.Objects;

public class RegistrationCommand {
    private String username;
    private String emailAddress;
    private String password;

    public RegistrationCommand(String username, String emailAddress, String password) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationCommand)) return false;
        RegistrationCommand that = (RegistrationCommand) o;
        return username.equals(that.username) &&
                emailAddress.equals(that.emailAddress) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, emailAddress, password);
    }

    @Override
    public String toString() {
        return "RegistrationCommand{" +
                "username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
