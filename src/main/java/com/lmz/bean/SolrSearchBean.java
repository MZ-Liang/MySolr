package com.lmz.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import com.lmz.exception.ChecksException;
import com.lmz.exception.SolrCheckException;
import com.lmz.utils.SolrUtilsImpl;

public class SolrSearchBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private SolrUtilsImpl solrArticleUtils=new SolrUtilsImpl();

	// 检索条件
	private String q;
	// 当前页
	private Integer currentPage;
	// 每页大小
	private Integer pageSize;
	// 是否高亮
	private Boolean ifHl;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Boolean getIfHl() {
		return ifHl;
	}

	public void setIfHl(Boolean ifHl) {
		this.ifHl = ifHl;
	}

	/**
	 * 获取查询对象
	 * 
	 * @return SolrQuery 查询对象
	 * @throws ChecksException
	 */
	public SolrQuery getSolrQuery() {
		SolrQuery query = new SolrQuery();
		// 如果前台未添加检索条件，添加一个默认排序字段
			// 设置排序
			query.set("sort", "creatTime desc");
			// 全局搜索
			query.set("q", "content:文化");
		// 添加过滤条件
		query.set("fq", "status:ture");
		if (currentPage < 1) {
			currentPage = 1;
		}

		query.setStart((currentPage - 1) * pageSize);
		query.setRows(pageSize);

		// 设置需要展示的字段
		query.set("fl", "id,title,sketch,labelNamesCache,columnIdsCache,labelIdsCache,creatTime,main_photo,"
				+ "author_head_img,author_nickname,author_id,content");

		// 高亮
		if (ifHl) {
			query.setHighlight(true);
			query.addHighlightField("title");
			query.addHighlightField("sketch");
			query.addHighlightField("content");
			query.setHighlightSimplePre("<font color='green'>");
			query.setHighlightSimplePost("</font>");
		}
		return query;
	}

	/**
	 * solr全文检索
	 * 
	 * @param searchBean
	 *            检索bean
	 * @return 查询分页结果
	 * @throws SolrCheckException 
	 */
	public PageResult search(SolrSearchBean searchBean) throws SolrCheckException {
		PageResult pageResult = null;
		try {
			// 执行检索
			QueryResponse response = solrArticleUtils.getSolrClient().query(searchBean.getSolrQuery());
			// 获取结果
			SolrDocumentList solrDocumentList = response.getResults();
			// 获取所有高亮的字段
			Map<String, Map<String, List<String>>> highlightMap = response.getHighlighting();
			Iterator<SolrDocument> iterator = solrDocumentList.iterator();
			
			pageResult = new PageResult();
			// 封装分页结果对象
			pageResult.setCurrent(searchBean.getCurrentPage());
			pageResult.setPageSize(searchBean.getPageSize());
			pageResult.setCount(solrDocumentList.getNumFound());
			pageResult.setTotalPage(solrDocumentList.getNumFound() / searchBean.getPageSize()
					+ solrDocumentList.getNumFound() % searchBean.getPageSize() == 0 ? 0 : 1);
			
			List<Object> voList = new ArrayList<>();
            Date date = new Date();
            while (iterator.hasNext()) {
                SolrDocument doc = iterator.next();
                Map<String, Object> map = new HashMap<>();
                map.put("id", doc.getFieldValue("id"));
                map.put("name", doc.getFieldValue("title"));
                map.put("sketch", doc.getFieldValue("sketch"));
                map.put("labels", doc.getFieldValue("labelNamesCache"));
                map.put("labelIds", doc.getFieldValue("labelIdsCache"));
                date.setTime(Long.valueOf(doc.getFieldValue("creatTime").toString()));
                map.put("creatTime", date);
                map.put("column", doc.getFieldValue("columnIdsCache").toString().split(";")[0]);
                map.put("nickname", doc.getFieldValue("author_nickname"));
                map.put("uId", doc.getFieldValue("author_id"));
                map.put("headImg", doc.getFieldValue("author_head_img"));
                map.put("mainPhoto", doc.getFieldValue("main_photo"));
                map.put("content", doc.getFieldValue("content"));
                if (searchBean.getIfHl()) {
                    String id = doc.getFieldValue("id").toString();
                    List<String> titleList = highlightMap.get(id).get("title");
                    List<String> sketchList = highlightMap.get(id).get("sketch");
                    List<String> contentList = highlightMap.get(id).get("content");
                    //获取并设置高亮的字段
                    if (titleList != null && titleList.size() > 0) {
                        map.put("name", titleList.get(0));
                    }
                    if (sketchList != null && sketchList.size() > 0) {
                        map.put("sketch", sketchList.get(0));
	                    }
                    if (contentList != null && contentList.size() > 0) {
                        //统一前端摘要展示
                        map.put("content", contentList.get(0));
                    }
                }
                voList.add(map);
            }
            pageResult.setList(voList);
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
			throw new SolrCheckException("solr article search error");
		}
		return pageResult;
	}

}
