package com.bugbycode.service.rule.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbycode.dao.rule.RuleDao;
import com.bugbycode.module.rule.Rule;
import com.bugbycode.service.rule.RuleService;
import com.util.page.Page;
import com.util.page.SearchResult;

@Service("ruleService")
public class RuleServiceImpl implements RuleService {

	@Autowired
	private RuleDao ruleDao;
	
	@Override
	public SearchResult<Rule> query(Map<String, Object> param, int startIndex, int pageSize) {
		SearchResult<Rule> sr = new SearchResult<Rule>();
		Page page = new Page(pageSize, startIndex);
		int totalCount = ruleDao.count(param);
		List<Rule> list = new ArrayList<Rule>();
		if(totalCount > 0) {
			page.setTotalCount(totalCount);
			RowBounds rb = new RowBounds(page.getStartIndex(), page.getPageSize());
			list = ruleDao.query(param, rb);
		}
		sr.setPage(page);
		sr.setList(list);
		return sr;
	}

	@Override
	public List<Rule> query(Map<String, Object> param) {
		return ruleDao.query(param);
	}

	@Override
	public Rule queryById(int id) {
		return ruleDao.queryById(id);
	}

	@Override
	public int insert(Rule r) {
		r.setCreateTime(new Date());
		int row = ruleDao.insert(r);
		if(row > 0) {
			return r.getId();
		}
		return 0;
	}

	@Override
	public int update(Rule r) {
		r.setUpdateTime(new Date());
		return ruleDao.update(r);
	}

	@Override
	public int delete(int id) {
		return ruleDao.delete(id);
	}

}
