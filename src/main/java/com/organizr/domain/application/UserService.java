package com.organizr.domain.application;

import com.organizr.domain.application.commands.RegistrationCommand;
import com.organizr.domain.model.user.RegistrationException;


public interface UserService {
    void register(RegistrationCommand command) throws RegistrationException;
}
