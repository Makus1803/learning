package com.organizr.web.payload;

import com.organizr.domain.application.commands.RegistrationCommand;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationPayload {

    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    @NotNull
    private String username;

    @Email(message = "Email address must be valid")
    @Size(max = 100, message = "EmailAddress must be less than 100 characters")
    @NotNull
    private String emailAddress;

    @Size(min = 6, max = 40, message = "Password must be between 6 and 40 characters")
    @NotNull
    private String password;

    public RegistrationCommand toCommand() {
        return new RegistrationCommand(this.username, this.emailAddress, this.password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
