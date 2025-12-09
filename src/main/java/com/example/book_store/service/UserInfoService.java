package com.example.book_store.service;

import com.example.book_store.dto.UserInfoDto;
import com.example.book_store.entity.UserInfo;

import java.util.List;

public interface UserInfoService {

    public UserInfoDto createUser(UserInfoDto userInfoDto);

    public String getUserInfo(UserInfoDto userInfoDto);
    public List<UserInfoDto> getAllUsers();

    public void deleteUserByUserId(String userId);
    public void deleteCurrentUser(String jwtToken);
}
