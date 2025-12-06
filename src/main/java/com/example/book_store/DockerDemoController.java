package com.example.book_store;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DockerDemoController {

    @GetMapping("/dockerDemo")
    public String getDemo(){
        return "Spring Boot App is Dockerized";
    }
}
