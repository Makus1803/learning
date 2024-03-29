package com.organizr.web.apis;

import com.organizr.domain.application.UserService;
import com.organizr.domain.model.user.EmailAddressExistsException;
import com.organizr.domain.model.user.RegistrationException;
import com.organizr.domain.model.user.UsernameExistsException;
import com.organizr.web.payload.RegistrationPayload;
import com.organizr.web.results.ApiResult;
import com.organizr.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.Registration;
import javax.validation.Valid;

@Controller
public class RegistrationApiController {

    private UserService service;

    public RegistrationApiController(UserService service) {
        this.service = service;
    }

    @PostMapping("/api/registrations")
    public ResponseEntity<ApiResult> register(
            @Valid @RequestBody RegistrationPayload payload) {
        try {
            service.register(payload.toCommand());
            return Result.created();
        } catch (RegistrationException e){
            String errorMessage = "Registration failed";
            if(e instanceof UsernameExistsException){
                errorMessage = "Username already exists";
            }
            if(e instanceof EmailAddressExistsException){
                errorMessage = "Email Address already exists";
            }
            return Result.failure(errorMessage);
        }

    }
}
