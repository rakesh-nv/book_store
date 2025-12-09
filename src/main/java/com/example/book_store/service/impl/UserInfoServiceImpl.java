package com.example.book_store.service.impl;

import com.example.book_store.dto.UserInfoDto;
import com.example.book_store.entity.UserInfo;
import com.example.book_store.mapper.UserInfoMapper;
import com.example.book_store.repository.UserInfoRepository;
import com.example.book_store.service.JWTService;
import com.example.book_store.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public JWTService jwtService;

    @Override
    public UserInfoDto createUser(UserInfoDto userInfoDto) {
        UserInfo userInfo = UserInfoMapper.toEntity(userInfoDto);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        UserInfo savedUser = userInfoRepository.save(userInfo);
        return UserInfoMapper.toDto(savedUser);
    }

    @Override
    public String getUserInfo(UserInfoDto userInfoDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userInfoDto.userName(),
                        userInfoDto.password()
                )
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userInfoDto.userName());
        }
        return "Failure";
    }

    @Override
    public List<UserInfoDto> getAllUsers() {
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        List<UserInfoDto> userInfoDtoList = new ArrayList<>();
        for(UserInfo userInfo : userInfoList){
            userInfoDtoList.add(UserInfoMapper.toDto(userInfo));
        }
        return userInfoDtoList;
    }

    @Override
    public void deleteUserByUserId(String userId) {
        userInfoRepository.deleteById(userId);
    }

    @Override
    public void deleteCurrentUser(String jwtToken) {
        String userName = jwtService.extractUserName(jwtToken);
        userInfoRepository.findByUserName(userName)
                .ifPresentOrElse(
                        userInfoRepository::delete,
                        () -> { throw new org.springframework.security.core.userdetails.UsernameNotFoundException("User not found"); }
                );
    }
}
