package dev.gunlog.app.web;

import dev.gunlog.app.dto.UserInfoResponseDto;
import dev.gunlog.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserInfoResponseDto> getUserInfo(@RequestParam("username") String name) throws IllegalArgumentException {
        return ResponseEntity.ok(userService.selectUserInfo(name));
    }
}