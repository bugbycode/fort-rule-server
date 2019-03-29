package com.bugbycode.dao.rule.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.bugbycode.dao.base.BaseDao;
import com.bugbycode.dao.rule.RuleDao;
import com.bugbycode.module.rule.Rule;

@Repository("ruleDao")
public class RuleDaoImpl extends BaseDao implements RuleDao {

	@Override
	public List<Rule> query(Map<String, Object> param, RowBounds rb) {
		return getSqlSession().selectList("rule.query", param, rb);
	}

	@Override
	public int count(Map<String, Object> param) {
		return getSqlSession().selectOne("rule.count", param);
	}

	@Override
	public List<Rule> query(Map<String, Object> param) {
		return getSqlSession().selectList("rule.query", param);
	}

	@Override
	public Rule queryById(int id) {
		return getSqlSession().selectOne("rule.queryById", id);
	}

	@Override
	public int insert(Rule r) {
		return getSqlSession().insert("rule.insert", r);
	}

	@Override
	public int update(Rule r) {
		return getSqlSession().update("rule.update", r);
	}

	@Override
	public int delete(int id) {
		return getSqlSession().delete("rule.delete", id);
	}

}
