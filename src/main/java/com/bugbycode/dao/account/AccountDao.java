package com.bugbycode.dao.account;

import java.util.List;
import java.util.Map;

import com.bugbycode.module.account.Account;

public interface AccountDao {

	public List<Account> queryByRuleId(int ruleId);
	
	public Account queryByAccount(Map<String,Object> param);
	
	public int insert(Account acc);
	
	public int delete(Map<String,Object> param);
}
