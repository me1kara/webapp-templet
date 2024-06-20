package com.example.demo.service;

import com.example.demo.repository.UserRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class) //spring 설정을 사용할 수 있게 해줌
@DataJpaTest
public class UserServiceTest {
    @Autowired
    UserRepo userRepo;

    @Test
    @DisplayName("find all user")
    void usersTest(){
        userRepo.findAll();

    }

    @BeforeAll
    static void init(){
        System.out.println("UserServiceTest init");
    }

    @AfterAll
    static void done(){
        System.out.println("UserServiceTest done");
    }
}
