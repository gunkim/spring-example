package dev.gunlog.app.service;

import dev.gunlog.app.domain.User;
import dev.gunlog.app.domain.UserRepository;
import dev.gunlog.app.dto.UserInfoResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("이름으로 유저 정보 조회 테스트, entity -> dto 변환")
    public void selectUserInfo() {
        String name = "gunkim";
        int age = 22;
        userRepository.save(User.builder()
                .name(name)
                .age(age)
                .build());

        UserInfoResponseDto dto = userService.selectUserInfo(name);
        assertThat(dto.getName(), is(equalTo(name)));
        assertThat(dto.getAge(), is(equalTo(age)));
    }
}