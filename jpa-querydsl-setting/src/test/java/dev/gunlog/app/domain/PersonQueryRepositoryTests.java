package dev.gunlog.app.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Querydsl를 테스트하기 위한 클래스
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonQueryRepositoryTests {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonQueryRepository personQueryRepository;

    @BeforeEach
    public void setup() {
        Person person = Person.builder()
                .name("GunKim")
                .age(22)
                .build();
        personRepository.save(person);
    }
    @Test
    @DisplayName("Querydsl 조회 테스트")
    public void queryFindAllTest() {
        String name = "GunKim";
        int age = 22;

        Person person = personQueryRepository.findByName(name);

        assertThat(person.getName(), is(equalTo(name)));
        assertThat(person.getAge(), is(equalTo(age)));
    }
}