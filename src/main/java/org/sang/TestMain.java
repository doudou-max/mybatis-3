package org.sang;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.sang.bean.User;
import org.sang.db.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doudou
 * @since: 2022-11-03
 */
public class TestMain {

    public static void main(String[] args) throws IOException {

        // 读取mybatis配置文件流
        InputStream inputStream = Resources.getResourceAsStream("mybatis-conf.xml");

        // 构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*// 获取 Mapper 代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 代理对象调用，查看 MapperProxy.invoke()
        User user = userMapper.getUserIdAndUserName(101L, "u-0");
        System.out.println(user);*/

        /*// 关闭第一个 sqlSession(二级缓存才能命中)
        sqlSession.close();

        // 获取 sqlSession2 查询
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.getUser(100L);

        System.out.println(user == user2);
        System.out.println(user);*/


        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.getByUserIds(new ArrayList<>(Arrays.asList(2L, 3L)));
        users.forEach(user -> System.out.println(user));

    }

}
