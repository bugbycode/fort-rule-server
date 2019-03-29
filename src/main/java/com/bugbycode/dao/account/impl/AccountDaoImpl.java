package com.bugbycode.dao.account.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bugbycode.dao.account.AccountDao;
import com.bugbycode.dao.base.BaseDao;
import com.bugbycode.module.account.Account;

@Repository("accountDao")
public class AccountDaoImpl extends BaseDao implements AccountDao {

	@Override
	public List<Account> queryByRuleId(int ruleId) {
		return getSqlSession().selectList("account.queryByRuleId", ruleId);
	}

	@Override
	public Account queryByAccount(Map<String, Object> param) {
		return getSqlSession().selectOne("account.queryByAccount", param);
	}

	@Override
	public int insert(Account acc) {
		return getSqlSession().insert("account.insert", acc);
	}

	@Override
	public int delete(Map<String, Object> param) {
		return getSqlSession().delete("account.delete", param);
	}

}
