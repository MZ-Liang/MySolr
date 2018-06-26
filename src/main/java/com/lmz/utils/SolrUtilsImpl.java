package com.lmz.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.transaction.annotation.Transactional;

import com.lmz.entity.BlogArticle;
import com.lmz.exception.SolrCheckException;

public class SolrUtilsImpl extends SolrUtils {

	/**
	 * 公共操作类，根据传入的参数进行相应操作
	 * 
	 * @param ifDelete
	 *            是否执行删除操作
	 * @param obj
	 *            操作对象，这里可以是文档对象(BlogArticle) 文档ID(String) 文档ID数组(List<String>)
	 * @throws SolrCheckException
	 */
	@SuppressWarnings("unchecked")
	private void solrOption(boolean ifDelete, Object obj) throws SolrCheckException {
		if (obj != null) {
			try {
				if (ifDelete) {
					if (obj instanceof List) {
						this.delete((List<String>) obj);
					} else {
						this.delete((String) obj);
					}
				} else {
					if (obj instanceof BlogArticle) {
						BlogArticle blogArticle = (BlogArticle) obj;
						this.add(blogArticle.getSolrInputDocument(blogArticle));
					}
				}
			} catch (IOException | SolrServerException e) {
				e.printStackTrace();
				throw new SolrCheckException("solr article option error");
			}
		}
	}

	/**
	 * 添加方法 
	 * @param tempVo 需要添加的文档对象
	 */
	@Transactional(rollbackFor = Exception.class)
	public void fixAdd(BlogArticle tempVo) throws SolrCheckException {
		// 省略添加逻辑
		solrOption(false, tempVo);
	}

	/**
	 * 修改方法 
	 * @param tempVo 需要修改的文档对象
	 */
	@Transactional(rollbackFor = Exception.class)
	public void fixEditSave(BlogArticle tempVo) throws SolrCheckException {
		// 省略修改逻辑
		solrOption(false, tempVo);
	}

	/**
	 * 单项删除
	 * @param id 数据id
	 */
	@Transactional(rollbackFor = Exception.class)
	public void fixDel(Object id) throws SolrCheckException {
		// 省略删除逻辑
		solrOption(true, id.toString());
	}

	/**
	 * 多项删除
	 * @param id 数据id
	 */
	@Transactional(rollbackFor = Exception.class)
	public void fixDelMore(List<Object> ids) throws SolrCheckException {
		// 省略删除逻辑
		List<String> list = new ArrayList<>(ids.size());
		ids.forEach(x -> list.add(ids.toString()));
		solrOption(true, list);
	}
}
