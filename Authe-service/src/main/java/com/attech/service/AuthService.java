package com.attech.service;

import com.attech.dto.request.UserRequest;
import com.attech.dto.response.AuthResponse;
import com.attech.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

     private final RestClient restClient;
     private final JwtUtils jwtUtils;

    public AuthResponse register(UserRequest userRequest){
        userRequest.setPassword(BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt()));

        System.out.println("userRequest " +  userRequest);

        UserResponse registeredUser = restClient
                .post()
                .uri("http://localhost:9094/users")
                .accept(APPLICATION_JSON)
                .body(userRequest)
                .retrieve()
                .body(UserResponse.class);


        String accessToken = jwtUtils.generate(String.valueOf(registeredUser.getId()), registeredUser.getRole(), "ACCESS");
        String refreshToken = jwtUtils.generate(String.valueOf(registeredUser.getId()), registeredUser.getRole(), "REFRESH");

        return AuthResponse.builder()
                .access(accessToken)
                .refresh(refreshToken)
                .build();
    }
}
