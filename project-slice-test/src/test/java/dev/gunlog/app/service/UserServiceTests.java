package dev.gunlog.app.service;

import dev.gunlog.app.domain.User;
import dev.gunlog.app.domain.UserRepository;
import dev.gunlog.app.dto.UserInfoResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        this.userService = new UserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("이름으로 유저 정보 조회 테스트, entity -> dto 변환")
    public void selectUserInfo() {
        String name = "gunkim";
        int age = 22;
        when(userRepository.findByName(any(String.class))).thenReturn(Optional.of(User.builder()
                .name(name)
                .age(age)
                .build()));

        UserInfoResponseDto dto = userService.selectUserInfo(name);
        assertThat(dto.getName(), is(equalTo(name)));
        assertThat(dto.getAge(), is(equalTo(age)));
    }
}