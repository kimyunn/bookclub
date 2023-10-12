package com.example.bookclub.user.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    ROLE_USER("사용자"),
    ROLE_ADMIN("관리자");

    private final String role;

}
