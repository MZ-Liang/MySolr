package com.lmz.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lmz.bean.PageResult;
import com.lmz.bean.SolrSearchBean;
import com.lmz.entity.BlogArticle;
import com.lmz.entity.User;
import com.lmz.exception.SolrCheckException;
import com.lmz.utils.SolrUtilsImpl;

import net.sf.json.JSONObject;

@Controller
public class SolrTestController {
	private SolrUtilsImpl solrUtils=new SolrUtilsImpl();

	@RequestMapping("/addDocument")
	@ResponseBody
	public String goIndex(Model model) {
		Random random = new Random();
		long[] num = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int index = Math.abs(random.nextInt());
		Timestamp time = new Timestamp(new Date().getTime());
		BlogArticle blogArticle = new BlogArticle(num[index % 8], "糖送八大家", num[index % 8] + "号",
				num[index % 8] + "号：和报纸相机，不是个冷互助",
				"浙江是吴越文化、江南文化的发源地，是中国古代文明的发祥地之一。早在5万年前的旧石器时代，就有原始人类“建德人”活动，境内有距今7000年的河姆渡文化、距今6000年的马家浜文化和距今5000年的良渚文化，是典型的山水江南、鱼米之乡，被称为“丝绸之府”、“鱼米之乡”。",
				"浙江是吴越文化、江南文化的发源地", true, new User(num[index % 8], "头像" + num[index % 8], "昵称" + num[index % 8]), "资源",
				"111", new BigDecimal("400"), true, "糖加三傻", time, time, "123", "456", "789", "000");
		try {
			solrUtils.fixAdd(blogArticle);
		} catch (SolrCheckException e) {
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}

	@RequestMapping("/home")
	@ResponseBody
	public String goHome(Model model) {
		SolrSearchBean solrSearchBean = new SolrSearchBean();
		solrSearchBean.setQ("文化");
		solrSearchBean.setCurrentPage(1);
		solrSearchBean.setPageSize(5);
		solrSearchBean.setIfHl(true);
		PageResult page=new PageResult();
		try {
			page = solrSearchBean.search(solrSearchBean);
		} catch (SolrCheckException e) {
			e.printStackTrace();
		}
		JSONObject pageJson=JSONObject.fromObject(page);
		return pageJson.toString();
	}
}
