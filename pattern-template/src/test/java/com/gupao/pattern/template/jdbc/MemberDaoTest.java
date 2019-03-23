package com.gupao.pattern.template.jdbc;

import com.gupao.pattern.template.jdbc.dao.MemberDao;

import java.util.List;

/**
 * @author Soulballad
 * @date 2019/3/22/0022 21:37
 * @email soda931vzr@163.com
 * @description
 */
public class MemberDaoTest {

    public static void main(String[] args) throws Exception {

        MemberDao memberDao = new MemberDao(null);
        List<?> list = memberDao.selectAll();
        System.out.println(list);
    }
}