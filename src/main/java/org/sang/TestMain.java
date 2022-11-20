package org.sang;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.sang.bean.User;
import org.sang.db.UserMapper;

import java.io.IOException;
import java.io.InputStream;

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

        // 获取 Mapper 代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 代理对象调用，查看 MapperProxy.invoke()
        User user = userMapper.getUser(100L);

        // 关闭第一个 sqlSession(二级缓存才能命中)
        sqlSession.close();

        // 获取 sqlSession2 查询
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.getUser(100L);

        System.out.println(user == user2);
        System.out.println(user);

    }

}
