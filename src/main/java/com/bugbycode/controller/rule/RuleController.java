package com.bugbycode.controller.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bugbycode.module.rule.Rule;
import com.bugbycode.service.rule.RuleService;
import com.util.StringUtil;
import com.util.page.SearchResult;

@Controller
@RequestMapping("/rule")
public class RuleController {

	@Autowired
	private RuleService ruleService;
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(
			@RequestParam(name="keyword",defaultValue="")
			String keyWord,
			@RequestParam(name="startIndex",defaultValue="-1")
			int startIndex,
			@RequestParam(name="pageSize",defaultValue="10")
			int pageSize) {
		JSONObject json = new JSONObject();
		Map<String,Object> param = new HashMap<String,Object>();
		if(StringUtil.isNotBlank(keyWord)) {
			param.put("keyword", keyWord);
		}
		if(startIndex > -1) {
			SearchResult<Rule> sr = ruleService.query(param, startIndex, pageSize);
			json.put("data", sr.getList());
			json.put("page", sr.getPage());
		}else {
			json.put("data", ruleService.query(param));
		}
		return json.toString();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(String jsonStr) {
		Rule rule = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr),Rule.class);
		int code = 0;
		String msg = "新建成功";
		int empId = rule.getEmpId();
		int resId = rule.getResId();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("empId", empId);
		param.put("resId", resId);
		try {
			List<Rule> list = ruleService.query(param);
			if(!CollectionUtils.isEmpty(list)) {
				ruleService.delete(list.get(0).getId());
			}
			ruleService.insert(rule);
		}catch (Exception e) {
			code = 1;
			msg = e.getMessage();
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("ruleId", rule.getId());
		json.put("code", code);
		return json.toString();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(String jsonStr) {
		Rule rule = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr),Rule.class);
		int code = 0;
		String msg = "修改成功";
		int empId = rule.getEmpId();
		int resId = rule.getResId();
		int id = 0;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("empId", empId);
		param.put("resId", resId);
		try {
			List<Rule> list = ruleService.query(param);
			if(!CollectionUtils.isEmpty(list)) {
				id = list.get(0).getId();
			}
			if(id > 0) {
				rule.setId(id);
				ruleService.update(rule);
			}
		}catch (Exception e) {
			code = 1;
			msg = e.getMessage();
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("ruleId", rule.getId());
		json.put("code", code);
		return json.toString();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(int ruleId) {
		int code = 0;
		String msg = "删除成功";
		try {
			ruleService.delete(ruleId);
		}catch (Exception e) {
			code = 1;
			msg = e.getMessage();
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("code", code);
		return json.toString();
	}
	
	@RequestMapping("/queryById")
	@ResponseBody
	public String queryById(int ruleId) {
		JSONObject json = new JSONObject();
		json.put("data", ruleService.queryById(ruleId));
		return json.toString();
	}
}
