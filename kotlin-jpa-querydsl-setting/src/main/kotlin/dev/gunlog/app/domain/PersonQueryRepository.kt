package dev.gunlog.app.domain

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.gunlog.app.domain.QPerson.person as qPerson
import org.springframework.stereotype.Repository
import org.springframework.util.ObjectUtils

@Repository
class PersonQueryRepository(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun findByName(name: String): Person {
        return jpaQueryFactory
            .selectFrom(qPerson)
            .where(eqName(name))
            .fetchFirst()
    }
    private fun eqName(name: String): BooleanExpression? {
        if(ObjectUtils.isEmpty(name)){
            return null
        }
        return qPerson.name.eq(name)
    }
}