package com.yassine.bookshop.controllers;

import com.yassine.bookshop.dto.UserResponse;
import com.yassine.bookshop.dto.UserRegisterDto;
import com.yassine.bookshop.services.AuthService;
import com.yassine.bookshop.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("sign-up")
    public UserResponse addUser(
            @RequestBody UserRegisterDto userRegisterDto
    ){
        return userService.addUser(userRegisterDto);
    }
}
