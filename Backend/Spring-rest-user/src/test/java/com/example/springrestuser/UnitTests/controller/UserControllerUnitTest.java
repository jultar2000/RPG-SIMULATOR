package com.example.springrestuser.UnitTests.controller;

import com.example.springrestuser.user.controller.UserController;
import com.example.springrestuser.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerUnitTest {


    private MockMvc mvc;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.standaloneSetup(userService)
                .build();
    }

    @Test
    void canGetUserTest() {



    }


}
