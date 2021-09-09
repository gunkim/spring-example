package dev.gunlog.app.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("이름으로 유저 정보 조회 테스트")
    public void findByNameTest() {
        String name = "gunkim";
        int age = 22;
        userRepository.save(User.builder()
                .name(name)
                .age(age)
                .build());

        User user = userRepository.findByName(name).get();

        assertThat(user.getName(), is(equalTo(name)));
        assertThat(user.getAge(), is(equalTo(age)));
    }
}