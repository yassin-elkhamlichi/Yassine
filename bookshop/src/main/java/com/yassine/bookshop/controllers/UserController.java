package com.yassine.bookshop.controllers;

import com.yassine.bookshop.dto.UserResponse;
import com.yassine.bookshop.dto.UserRegisterDto;
import com.yassine.bookshop.exceptions.UserExistException;
import com.yassine.bookshop.services.AuthService;
import com.yassine.bookshop.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<String> handleUserExistException(UserExistException e) {
        return ResponseEntity.badRequest().body("User already exists");
    }
}
