package com.bugbycode.service.rule;

import java.util.List;
import java.util.Map;

import com.bugbycode.module.rule.Rule;
import com.util.page.SearchResult;

public interface RuleService {
	
	public SearchResult<Rule> query(Map<String,Object> param,int startIndex,int pageSize);
	
	public List<Rule> query(Map<String,Object> param);
	
	public Rule queryById(int id);
	
	public int insert(Rule r);
	
	public int update(Rule r);
	
	public int delete(int id);
}
