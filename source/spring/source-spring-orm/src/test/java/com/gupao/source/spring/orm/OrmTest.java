package com.gupao.source.spring.orm;

import com.gupao.source.spring.orm.demo.dao.MemberDao;
import com.gupao.source.spring.orm.demo.entity.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Soulballad
 * @date 2019/5/3 17:03
 * @email soda931vzr@163.com
 * @description
 */
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrmTest {

    @Autowired
    private MemberDao memberDao;

    @Test
    public void test() throws Exception {
        List<Member> memberList = this.memberDao.selectList();
        System.out.println(Arrays.toString(memberList.toArray()));
    }
}
