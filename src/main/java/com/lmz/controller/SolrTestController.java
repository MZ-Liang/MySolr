package com.lmz.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lmz.bean.PageResult;
import com.lmz.bean.SolrSearchBean;
import com.lmz.entity.Book;
import com.lmz.exception.SolrCheckException;
import com.lmz.service.BookSolrService;
import com.lmz.utils.DateUtils;

@Controller
public class SolrTestController {
	@Autowired
	private BookSolrService bookSolrService;
	
	/**
	 * 添加索引
	 * @param model
	 * @param book book对象，包含author对象
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model,Book book) {
		String msg="添加成功";
		Long id=DateUtils.getIDByDate();
		book.setId(id);
		book.getAuthor().setId(id);
		book.setPublishDate(new Timestamp(new Date().getTime()));
		try {
			bookSolrService.addSolrServer(book);
		} catch (SolrServerException | IOException | SolrCheckException e) {
			msg="添加失败";
		}
		model.addAttribute("msg", msg);
		return "index";
	}
	
	/**
	 * 全文检索
	 * @param model
	 * @param bean solr搜索对象
	 * @return
	 */
	@RequestMapping("/search")
	public String search(Model model,SolrSearchBean bean) {
		//去除集合中空数据
		if (bean.getKeywordList()!=null) {
			for (int i = 0; i < bean.getKeywordList().size();) {
				if (StringUtils.isBlank(bean.getKeywordList().get(i))) {
					bean.getKeywordList().remove(i);
					if (i >= bean.getKeywordList().size()) {
						break;
					}
				} else {
					i++;
				}
			} 
		}
		//设置分页
		bean.setPageNo(1);
		bean.setPageSize(10);
		//分页结果
		PageResult<Book> page=null;
		//搜索
		try {
			page = bookSolrService.search(bean);
		} catch (SolrCheckException e) {
			//solr异常，组装空结果
			page=new PageResult<Book>();
			page.setCount(0l);
			page.setTotalPage(0);
			page.setPageNo(0);
			page.setPageSize(bean.getPageSize());
			page.setList(new ArrayList<Book>());
		}
		model.addAttribute("page", page);
		return "search";
	}

	/**
	 * 删除全部索引
	 * @return
	 */
	@RequestMapping("/del")
	@ResponseBody
	public String del() {
		try {
			bookSolrService.delAll();
		} catch (SolrServerException | IOException | SolrCheckException e) {
			return "删除失败";
		}
		return "删除成功";
	}
	
}
