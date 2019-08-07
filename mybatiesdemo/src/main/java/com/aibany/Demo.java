package com.aibany;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class Demo {

    SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws Exception{
        String config = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(config);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testGetUser() throws Exception{
        SqlSession session = null;
        session = sqlSessionFactory.openSession();
        User user = session.selectOne("test.queryUser", 1);
        System.out.println(user);
        session.close();
    }

}
