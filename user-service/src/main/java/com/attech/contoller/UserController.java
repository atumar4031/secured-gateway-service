package com.attech.contoller;

import com.attech.dto.request.UserRequest;
import com.attech.dto.response.UserResponse;
import com.attech.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final HttpServletRequest httpRequest;

    @GetMapping("/secured")
    public ResponseEntity<String> secured(){
        List<String> rolesAllowed = List.of("USER", "ADMIN");
        List<String> userRoles = convertToArray(httpRequest.getHeader("role"));

        if (!hasAnyRoles(rolesAllowed, userRoles)){
            System.out.println("ACCESS DENIED");

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("ACCESS DENIED");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Secured");
    }
    @PostMapping
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest){
        UserResponse userResponse =  userService.register(userRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponse);
    }

    private boolean hasAnyRoles(List<String> defineRoles, List<String> userRoles){
        return !CollectionUtils.intersection(defineRoles, userRoles).isEmpty();
    }

    private List<String> convertToArray(String input) {
        String[] rolesArray = input.replaceAll("[\\[\\]]", "").split(", ");
        return new ArrayList<>(Arrays.asList(rolesArray));
    }

}
