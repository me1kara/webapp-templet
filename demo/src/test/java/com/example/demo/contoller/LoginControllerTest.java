package com.example.demo.contoller;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest(LoginController.class)
public class LoginControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    @Test
    void loginPage() throws Exception {
        // when & then
        MvcResult result = mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andReturn();
        // 이후에 result를 사용하여 추가적인 검증을 수행할 수 있음
        // 예를 들어, 응답 본문의 내용을 검사하는 코드를 추가할 수 있음
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("login")); // 예시로 추가한 검증 코드

    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void users() throws Exception {
        //기대값 설정
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("user");
        user.setPassword("1234");
        userList.add(user);

        //when
        when(userService.findAllUser()).thenReturn(userList);

        //
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("user")); // 이 부분이 수정된 부분입니다.
    }
}
