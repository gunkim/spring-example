package com.sample.app.dao;

import dev.gunlog.app.dao.TestDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestDaoTest {
    @Autowired
    private TestDao dao;

    @Test
    public void dao_테스트(){
        log.info(dao.selectTest());
    }
}