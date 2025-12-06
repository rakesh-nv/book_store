package com.example.book_store.service;

import com.example.book_store.dto.UserInfoDto;
import com.example.book_store.entity.UserInfo;

public interface UserInfoService {

    public UserInfoDto createUser(UserInfoDto userInfoDto);

}
