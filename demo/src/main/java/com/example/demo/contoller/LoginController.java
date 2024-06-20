package com.example.demo.contoller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Controller
@RequiredArgsConstructor
class LoginController {
    private final UserService userService;
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "login";
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<List<User>> findAllUser(){
        return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);
    }
}
