package dev.gunlog.app.domain

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class PersonQueryRepositoryTests {
    @Autowired
    private lateinit var personQueryRepository: PersonQueryRepository
    @Autowired
    private lateinit var personRepository: PersonRepository

    @Test
    @DisplayName("조회 테스트")
    fun findAllTest() {
        val name = "gunkim"
        val age = 22
        personRepository.save(Person(
            name = name,
            age = age
        ))
        val person: Person = personQueryRepository.findByName(name)
        assertThat(person.name, `is`(equalTo(name)))
        assertThat(person.age, `is`(equalTo(age)))
    }
}