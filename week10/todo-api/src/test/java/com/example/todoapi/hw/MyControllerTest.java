package com.example.todoapi.hw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootTest
public class MyControllerTest {

    @Autowired
    MyController myController;

    @Autowired
    MyService myService;

    @Autowired
    MyRepository myRepository;

    @Test
    public void controllerTest() {
        myController.controllerMethod();
    }
}
