package com.organizr.web.apis;

import com.organizr.domain.application.UserService;
import com.organizr.domain.model.user.EmailAddressExistsException;
import com.organizr.domain.model.user.UsernameExistsException;
import com.organizr.utils.JsonUtils;
import com.organizr.web.apis.RegistrationApiController;
import com.organizr.web.payload.RegistrationPayload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationApiController.class)
public class RegistrationApiControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService serviceMock;

    @Test
    public void register_blankPayload_shouldFailAndReturn400() throws Exception {
        mvc.perform(post("/api/registrations"))
                .andExpect(status().is(400));
    }

    @Test
    public void register_existedUsername_shouldFailAndReturn400() throws Exception {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("exist");
        payload.setEmailAddress("Correct@email.com");
        payload.setPassword("CorrectPassword123");

        doThrow(UsernameExistsException.class)
                .when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(post("/api/registrations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toJson(payload)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Username already exists"));
    }

    @Test
    public void register_existedEmailAddress_shouldFailAndReturn400() throws Exception {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("CorrectUsername");
        payload.setEmailAddress("exist@email.com");
        payload.setPassword("CorrectPassword123");

        doThrow(EmailAddressExistsException.class)
                .when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(post("/api/registrations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toJson(payload)))
        .andExpect(status().is(400))
        .andExpect(jsonPath("$.message").value("Email Address already exists"));
    }

    @Test
    public void register_validPayload_shouldSucceedAndReturn201() throws Exception {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("Correct Username");
        payload.setEmailAddress("Correct@email.com");
        payload.setPassword("CorrectPassword123");

        doNothing().when(serviceMock).register(payload.toCommand());

        mvc.perform(post("/api/registrations")
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtils.toJson(payload)))
        .andExpect(status().is(201));

    }

}
