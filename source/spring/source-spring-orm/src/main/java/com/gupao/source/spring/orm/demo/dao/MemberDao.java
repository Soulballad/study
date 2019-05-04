package com.gupao.source.spring.orm.demo.dao;

import com.gupao.source.spring.orm.demo.entity.Member;
import com.gupao.source.spring.orm.framework.BaseDaoSupport;
import com.gupao.source.spring.orm.framework.QueryRule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * @author Soulballad
 * @date 2019/5/4 13:50
 * @email soda931vzr@163.com
 * @description
 */
@Repository
public class MemberDao extends BaseDaoSupport<Member> {

    @Override
    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource) {
        super.setJdbcTemplate(new JdbcTemplate(dataSource));
    }

    public List<Member> selectList() throws Exception {
        QueryRule queryRule = new QueryRule();
        queryRule.andEqual("name", "tom");
        List list = super.selectList(queryRule);
        return list;
    }
}
