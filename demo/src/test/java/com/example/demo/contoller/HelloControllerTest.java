package com.example.demo.contoller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.logging.Logger;


@WebMvcTest(HelloController.class)
public class HelloControllerTest {
    @Autowired
    MockMvc mockMvc;

    @DisplayName("hello test")
    @Test
    @WithMockUser(username = "user",roles = {"USER"})
    void helloTest() throws Exception {
        // when & then
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello test"));

                //json 방식
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(jsonPath("$.message").value("hello test"));

                //model 검증
                //andExpect(model().attribute("userVO",userVO));

                //redirect url 검증
                //andExpect(redirectedUrl("/where"));

    }

}
