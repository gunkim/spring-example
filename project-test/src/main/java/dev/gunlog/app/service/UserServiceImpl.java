package dev.gunlog.app.service;

import dev.gunlog.app.domain.User;
import dev.gunlog.app.domain.UserRepository;
import dev.gunlog.app.dto.UserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserInfoResponseDto selectUserInfo(String name) throws IllegalArgumentException {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException(name+": 유저를 찾지 못했습니다."));
        return new UserInfoResponseDto(user);
    }
}