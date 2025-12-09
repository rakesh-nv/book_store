package com.example.book_store.contorller;

import com.example.book_store.dto.UserInfoDto;
import com.example.book_store.entity.UserInfo;
import com.example.book_store.response.ResponseHandler;
import com.example.book_store.service.UserInfoService;
import com.example.book_store.service.impl.UserInfoUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserInfoUserDetailsServiceImpl userInfoUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity<String> createUserInfo(@RequestBody UserInfoDto userInfoDto) {
        UserInfoDto userInfoDto1 = userInfoService.createUser(userInfoDto);
        return new ResponseEntity<>("User " + userInfoDto1.userName() + " is Created ", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> getUserInfo(@RequestBody UserInfoDto userInfoDto) {
        UserDetails userInfo = userInfoUserDetailsService.loadUserByUsername(userInfoDto.userName());
        return ResponseHandler.userResponseBuilder("SUCCESS", HttpStatus.OK, userInfoService.getUserInfo(userInfoDto), userInfo);
//     return new ResponseEntity<>(userInfoService.getUserInfo(userInfoDto),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<UserInfoDto> userInfoDtoList = userInfoService.getAllUsers();
        return ResponseHandler.responseBuilder("SUCCESS", HttpStatus.OK, userInfoDtoList);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable String userId) {
        userInfoService.deleteUserByUserId(userId);
        return "userDeleted Successfully";
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteCurrentUser(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Missing or invalid Authorization header", HttpStatus.BAD_REQUEST);
        }
        String token = authHeader.substring(7);
        userInfoService.deleteCurrentUser(token);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

}
