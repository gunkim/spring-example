package com.example.demo.web;

import com.example.demo.dto.PersonRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/rq")
@RestController
public class HomeController {
    @PostMapping("")
    public void rb(@RequestBody PersonRequestDto dto){
        log.info("TEST :: "+dto.toString());
    }
    @GetMapping("")
    public void ma(@ModelAttribute PersonRequestDto dto){
        log.info("TEST :: "+dto.toString());
    }
}