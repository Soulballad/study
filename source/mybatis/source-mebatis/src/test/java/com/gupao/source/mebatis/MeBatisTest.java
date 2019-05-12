package com.gupao.source.mebatis;

import org.junit.Test;

/**
 * @author Soulballad
 * @date 2019/5/7 21:13
 * @email soda931vzr@163.com
 * @description
 */
public class MeBatisTest {

    @Test
    public void test() {

        SqlSession sqlSession = new SqlSession(new Configuration(), new Executor());
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlogById(1);
        System.out.println(blog);
    }
}
