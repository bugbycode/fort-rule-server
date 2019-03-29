package com.bugbycode.service.account.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbycode.dao.account.AccountDao;
import com.bugbycode.module.account.Account;
import com.bugbycode.service.account.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public List<Account> queryByRuleId(int ruleId) {
		return accountDao.queryByRuleId(ruleId);
	}

	@Override
	public Account queryByAccount(int ruleId, String account) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("ruleId", ruleId);
		param.put("account", account);
		return accountDao.queryByAccount(param);
	}

	@Override
	public int insert(Account acc) {
		int row = accountDao.insert(acc);
		if(row > 0) {
			return acc.getId();
		}
		return 0;
	}

	@Override
	public int delete(int ruleId, String account) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("ruleId", ruleId);
		param.put("account", account);
		return accountDao.delete(param);
	}

}
