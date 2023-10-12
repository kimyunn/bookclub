package com.example.bookclub.user.service;

import com.example.bookclub.common.security.JwtTokenProvider;
import com.example.bookclub.user.dto.request.UserJoinRequest;
import com.example.bookclub.user.dto.request.UserLoginRequest;
import com.example.bookclub.user.entity.User;
import com.example.bookclub.user.exception.EmailNotFoundException;
import com.example.bookclub.user.exception.PasswordInvalidValueException;
import com.example.bookclub.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void join(UserJoinRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(request.toEntity());
    }

    public String login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new EmailNotFoundException(String.format("가입되지 않은 email입니다. email: %s", request.getEmail())));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new PasswordInvalidValueException("잘못된 비밀번호 입니다.");
        }
        return jwtTokenProvider.createToken(user.getUsername(), user.getAuthorities());
    }

    public boolean isEmailDuplication(String email) {
        return !userRepository.existsByEmail(email);
    }

    public boolean isNicknameDuplication(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }
}
