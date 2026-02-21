package com.yassine.bookshop.services;

import com.yassine.bookshop.dto.UserRegisterDto;
import com.yassine.bookshop.dto.UserResponse;
import com.yassine.bookshop.entities.Role;
import com.yassine.bookshop.entities.User;
import com.yassine.bookshop.mappers.UserMapper;
import com.yassine.bookshop.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse addUser(UserRegisterDto userRegisterDto) {
        User user = userMapper.toEntity(userRegisterDto);
        user.setRole(Role.USER);
        user.setPasswordHash(passwordEncoder.encode(userRegisterDto.getPassword())); // ✅ set manually
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
}
