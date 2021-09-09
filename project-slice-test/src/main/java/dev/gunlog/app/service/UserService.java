package dev.gunlog.app.service;

import dev.gunlog.app.dto.UserInfoResponseDto;

public interface UserService {
    UserInfoResponseDto selectUserInfo(String name) throws IllegalArgumentException;
}
