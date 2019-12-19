package com.organizr.web.payload;



import org.junit.Before;
import org.junit.Test;

import org.apache.commons.lang3.RandomStringUtils;

import javax.validation.*;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RegistrationPayloadTests {

    private Validator validator;

    @Before
    public void setup(){
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validate_blankPayload_shoudlFail(){
        RegistrationPayload payload = new RegistrationPayload();
        Set<ConstraintViolation<RegistrationPayload>> violations =
                validator.validate(payload);
        assertEquals(3, violations.size());
    }

    @Test
    public void validate_payloadWithInvalidEmail_shouldFail(){
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("CorecctUsername");
        payload.setEmailAddress("Invalid email Address");
        payload.setPassword("CorrectPassword");

        Set<ConstraintViolation<RegistrationPayload>> violations =
                validator.validate(payload);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithEmailAddressLongerThan100_shouldFail(){
        String randomString = RandomStringUtils.randomAlphanumeric(55);

        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("CorrectUsername");
        payload.setEmailAddress(randomString + "@" + randomString);
        payload.setPassword("CorrectPassword");

        Set<ConstraintViolation<RegistrationPayload>> violations =
                validator.validate(payload);
        assertEquals(1, violations.size());
    }

    // TODO Validate all other data
    @Test
    public void validate_payloadWithUsernameShorterThan2_shouldFail(){
        RegistrationPayload payload = new RegistrationPayload();

    }

}
