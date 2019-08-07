package com.aibany;

import com.aibany.inter.UserMapper;
import com.aibany.mapper.MyMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

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

    @Test
    public void testGetList() throws Exception{
        SqlSession session = null;
        session = sqlSessionFactory.openSession();
        List<User> user = session.selectList("test.findUserByUsername", "明");
        System.out.println(user);
        session.close();
    }

    @Test
    public void testInsert() throws Exception{

        User user = new User();
        user.setUsername("我是谁");
        user.setAddress("北京");
        user.setSex("1");
        user.setBirthday(new Date());

        SqlSession session = sqlSessionFactory.openSession();
        session.insert("test.insertUser", user);
        System.out.println(user);
        session.commit();
        session.close();
    }

    @Test
    public void testQueryByInterface() {

        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println(mapper.findUserByUsername("我"));

        MyMapper myMapper = session.getMapper(MyMapper.class);
        System.out.println(myMapper.getUser());

        session.close();
    }


}
