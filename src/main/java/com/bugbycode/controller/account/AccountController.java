package com.bugbycode.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bugbycode.module.account.Account;
import com.bugbycode.service.account.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping("/query")
	@ResponseBody
	public String query(int ruleId) {
		JSONObject json = new JSONObject();
		json.put("data", accountService.queryByRuleId(ruleId));
		return json.toString();
	}
	
	@RequestMapping("/queryByAccount")
	@ResponseBody
	public String queryByAccount(int ruleId,String account) {
		JSONObject json = new JSONObject();
		json.put("data", accountService.queryByAccount(ruleId,account));
		return json.toString();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(String jsonStr) {
		Account acc = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr),Account.class);
		int code = 0;
		String msg = "新建成功";
		try {
			Account ac = accountService.queryByAccount(acc.getRuleId(), acc.getAccount());
			if(ac == null) {
				accountService.insert(acc);
			}else {
				acc.setId(ac.getId());
			}
		}catch (Exception e) {
			code = 1;
			msg = e.getMessage();
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("id", acc.getId());
		json.put("code", code);
		return json.toString();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(int ruleId,String account) {
		int code = 0;
		String msg = "删除成功";
		try {
			accountService.delete(ruleId, account);
		}catch (Exception e) {
			code = 1;
			msg = e.getMessage();
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("code", code);
		return json.toString();
	}
}
