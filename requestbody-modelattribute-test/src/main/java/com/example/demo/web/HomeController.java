package com.example.demo.web;

import com.example.demo.dto.PersonRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class HomeController {
    @PostMapping("/api/rb")
    public void rb(@RequestBody PersonRequestDto dto){
        System.out.println("TEST :: "+dto.toString());
    }
    @PostMapping("/api/ma")
    public void ma(@ModelAttribute PersonRequestDto dto){
        System.out.println("TEST :: "+dto.toString());
    }
}