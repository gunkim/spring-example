package dev.gunlog.app.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static dev.gunlog.app.domain.QPerson.person;

@RequiredArgsConstructor
@Repository
public class PersonQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Person findByName(String name) {
        return jpaQueryFactory
                .selectFrom(person)
                .where(person.name.eq(name))
                .fetchOne();
    }
}