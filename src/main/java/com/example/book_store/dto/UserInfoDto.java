package com.example.book_store.dto;

public record UserInfoDto(
        String userId,
        String userName,
        String password,
        String roles
) {
}
