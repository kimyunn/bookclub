package com.example.bookclub.user.controller;

import com.example.bookclub.common.security.JwtTokenProvider;
import com.example.bookclub.user.dto.request.UserJoinRequest;
import com.example.bookclub.user.dto.request.UserLoginRequest;
import com.example.bookclub.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest request) {
        userService.join(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/join/check-email")
    public ResponseEntity checkEmail(@RequestParam String email) {
        if (userService.isEmailDuplication(email)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("중복된 이메일입니다.");
        }
    }

    @GetMapping("/join/check-nickname")
    public ResponseEntity checkNickname(@RequestParam String nickname) {
        if (userService.isNicknameDuplication(nickname)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("중복된 닉네임입니다.");
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }


}
