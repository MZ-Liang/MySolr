package com.lmz.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import com.lmz.bean.PageResult;
import com.lmz.bean.SolrSearchBean;
import com.lmz.entity.Author;
import com.lmz.entity.Book;
import com.lmz.exception.SolrCheckException;
import com.lmz.utils.SolrUtils;

/**
 * solr服务
 *
 */
@Component
public class BookSolrService {
	/** solr核心url */
	private static final String URL = "http://localhost:8080/solr/solr_core";
	/** solr工具类 */
	private SolrUtils solrUtils = new SolrUtils(URL);

	/**
	 * 删除全部索引
	 * 
	 * @throws SolrServerException
	 * @throws IOException
	 * @throws SolrCheckException
	 */
	public void delAll() throws SolrServerException, IOException, SolrCheckException {
		solrUtils.deleteAll();
	}

	/**
	 * 添加索引
	 * 
	 * @param book
	 * @throws SolrCheckException
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public void addSolrServer(Book book) throws SolrServerException, IOException, SolrCheckException {
		solrUtils.add(book.getSolrInputDocument(book));
	}

	/**
	 * 搜索
	 * 
	 * @param bean
	 *            搜索实体
	 * @return 搜索结果集
	 * @throws SolrCheckException
	 */
	public PageResult<Book> search(SolrSearchBean bean) throws SolrCheckException {
		// 获取SolrQuery
		SolrQuery query = getSolrQuery(bean);
		// 搜索响应
		QueryResponse response = null;
		// 结果分页
		PageResult<Book> page = new PageResult<Book>();

		// 设置结果分页
		page.setPageNo(bean.getPageNo());
		page.setPageSize(bean.getPageSize());
		page.setCount(0L);
		page.setTotalPage(0);

		try {
			// 执行搜索
			response = solrUtils.getSolrClient().query(query);
			// 获取搜索结果文档
			SolrDocumentList documentList = response.getResults();
			// 获取所有高亮的字段
			Map<String, Map<String, List<String>>> highlightMap = response.getHighlighting();
			// 设置搜索总数
			page.setCount(documentList.getNumFound());
			// 设置总页数
			page.setTotalPage((int) (page.getCount() % page.getPageSize() == 0 ? page.getCount() / page.getPageSize()
					: page.getCount() / page.getPageSize() + 1));
			// 图书集合
			List<Book> books = new ArrayList<Book>();

			Book book = null;
			Author author = null;

			// 从结果文档中获取数据，并封装实体
			for (SolrDocument solrDocument : documentList) {
				book = new Book();
				book.setId(new Long(solrDocument.getFieldValue("id").toString()));
				book.setBookName(rmBracket((String) solrDocument.getFieldValue("bookName").toString()));
				book.setContent(rmBracket((String) solrDocument.getFieldValue("content").toString()));
				book.setStatus(new Integer(solrDocument.getFieldValue("status").toString()));
				book.setPublishDate(
						new Timestamp(Long.parseLong(solrDocument.getFieldValue("publishDate").toString())));
				author = new Author();
				author.setId(new Long(solrDocument.getFirstValue("author_id").toString()));
				author.setName(rmBracket(solrDocument.getFieldValue("author_name").toString()));
				author.setNickname(rmBracket(solrDocument.getFirstValue("author_nickname").toString()));

				// 获取并设置高亮字段
				if (bean.getIfHL()) {
					String id = solrDocument.getFieldValue("id").toString();
					List<String> bookNameList = highlightMap.get(id).get("bookName");
					List<String> contentList = highlightMap.get(id).get("content");
					List<String> authorNameList = highlightMap.get(id).get("author_name");
					List<String> authorNicknameList = highlightMap.get(id).get("author_nickname");
					if (bookNameList != null && bookNameList.size() > 0) {
						book.setBookName(rmBracket(bookNameList.get(0)));
					}
					if (contentList != null && contentList.size() > 0) {
						book.setContent(rmBracket(contentList.get(0)));
					}
					if (authorNameList != null && authorNameList.size() > 0) {
						author.setName(rmBracket(authorNameList.get(0)));
					}
					if (authorNicknameList != null && authorNicknameList.size() > 0) {
						author.setNickname(rmBracket(authorNicknameList.get(0)));
					}
				}

				book.setAuthor(author);
				books.add(book);
			}

			page.setList(books);
		} catch (SolrServerException | IOException e) {
			throw new SolrCheckException("solr search error");
		}
		return page;
	}

	/**
	 * 通过搜索实体获取SolrQuery
	 * 
	 * @param bean
	 *            搜索实体
	 * @return SolrQuery
	 */
	private SolrQuery getSolrQuery(SolrSearchBean bean) {
		SolrQuery query = new SolrQuery();

		StringBuffer sb_q = new StringBuffer();
		if (bean.getKeywordList() == null || bean.getKeywordList().size() == 0) {
			// 没有关键字则全查
			sb_q.append("*");
		} else {
			for (int i = 0; i < bean.getKeywordList().size(); i++) {
				if (i == 0) {
					sb_q.append(bean.getKeywordList().get(i));
				} else {
					sb_q.append(" AND ").append(bean.getKeywordList().get(i));
				}
			}
		}

		// 设置检索关键字,从"_text_"域中检索,也就是复制域中设置了该域的域
		query.set("q", "_text_:" + sb_q.toString());

		StringBuffer sb_fq = new StringBuffer();
		// 过滤状态
		if (bean.getStatus() != null && bean.getStatus() >= 0) {
			addStr(sb_fq);
			sb_fq.append("status:" + bean.getStatus());
		}

		// 设置过滤条件
		if (sb_fq.length() > 0) {
			query.set("fq", sb_fq.toString());
		}

		// 设置分页
		// 开始页,solr第一页从0开始
		query.setStart(bean.getPageNo() - 1);
		// 每页大小
		query.setRows(bean.getPageSize());

		// 排序
		if (bean.getOrderType() != null && bean.getOrderType() >= 0) {
			if (bean.getOrderType() == 0) {
				query.addSort("publishDate", SolrQuery.ORDER.desc);
			} else {
				query.addSort("publishDate", SolrQuery.ORDER.asc);
			}
		}

		// 若是全查，取消高亮
		if ("*".equals(sb_q.toString())) {
			bean.setIfHL(false);
		}
		
		// 设置高亮
		if (bean.getIfHL()) {
			// 开启高亮
			query.setHighlight(true);
			// 添加高亮字段
			query.addHighlightField("bookName");
			query.addHighlightField("content");
			query.addHighlightField("author_name");
			query.addHighlightField("author_nickname");
			// 高亮的头部分
			query.setHighlightSimplePre("<font color='red'>");
			// 高亮的尾部分
			query.setHighlightSimplePost("</font>");
		}
		return query;
	}

	/**
	 * 在字符串末尾追加" and "
	 * 
	 * @param sb_fq
	 */
	private void addStr(StringBuffer sb_fq) {
		if (sb_fq.length() > 0) {
			sb_fq.append(" and ");
		}
	}
	
	/**
	 * 移除字符串的首"[",尾"]"
	 * @param str
	 * @return
	 */
	private String rmBracket(String str) {
		if (str.length()>0) {
			if ('['==str.charAt(0)) {
				str=str.substring(1);
			}
			if (str.length()>0&&']'==str.charAt(str.length()-1)) {
				str=str.substring(0, str.length()-1);
			}
		}
		return str;
	}
}
