package com.example.book_store.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userInfo")
public class UserInfo {
    @Id
    private String userId;
    private String userName;
    private String password;
    private String roles;
}
