package com.bugbycode.service.account;

import java.util.List;

import com.bugbycode.module.account.Account;

public interface AccountService {

	public List<Account> queryByRuleId(int ruleId);
	
	public Account queryByAccount(int ruleId,String account);
	
	public int insert(Account acc);
	
	public int delete(int ruleId,String account);
}
