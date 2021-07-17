package dev.gunlog.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTests {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void createUser() {
        memberRepository.save(Member.builder()
                .username("gunkim")
                .password("gunkim")
                .age(22)
                .build());
    }

    @Test
    @DisplayName("게시글 입력 테스트")
    @WithMockUser(username = "gunkim")
    public void saveTest() {
        postsRepository.save(Posts.builder()
                .title("첫 게시글입니다.")
                .contents("게시글 내용입니다.")
                .build());

        System.out.println(postsRepository.findAll().get(0));
    }
}