package dev.gunlog.app.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TestDao {
    private final static String NAMESPACE = "test";

    private final SqlSession sqlSession;

    public String selectTest(){
        return sqlSession.selectOne(NAMESPACE+".selectTest");
    }
}