package com.sample.app.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConnectionTests {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void 커넥션_테스트(){
        try(Connection con = sqlSessionFactory.openSession().getConnection()){
            log.info("커넥션 성공!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void 매퍼_테스트(){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            String sysdate = sqlSession.selectOne("test.selectTest");
            log.info(sysdate);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}