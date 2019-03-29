package com.bugbycode.dao.rule;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.bugbycode.module.rule.Rule;

public interface RuleDao {
	
	public List<Rule> query(Map<String,Object> param,RowBounds rb);
	
	public int count(Map<String,Object> param);
	
	public List<Rule> query(Map<String,Object> param);
	
	public Rule queryById(int id);
	
	public int insert(Rule r);
	
	public int update(Rule r);
	
	public int delete(int id);
}
