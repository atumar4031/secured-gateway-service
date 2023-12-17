package com.attech.service;


import com.attech.dto.request.UserRequest;
import com.attech.dto.response.UserResponse;
import com.attech.entity.User;
import com.attech.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public UserResponse register(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .role(userRequest.getRole())
                .build();
        User save = userRepo.save(user);

        return UserResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .email(save.getEmail())
                .role(save.getRole())
                .build();
    }
}
