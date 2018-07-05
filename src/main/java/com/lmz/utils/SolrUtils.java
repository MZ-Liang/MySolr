package com.lmz.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import com.lmz.exception.SolrCheckException;

/**
 * solr工具类
 *
 */
public class SolrUtils {
	private static HttpSolrClient solrClient;
	
	public SolrUtils(String url){
	    //初始化solrClient
		solrClient=new HttpSolrClient.Builder(url).build();
		solrClient.setConnectionTimeout(1000);
		solrClient.setSoTimeout(1000);
	}

	/**
	 * 添加对象到全文检索
	 * 
	 * @param document
	 * @throws IOException
	 * @throws SolrServerException
	 * @throws SolrCheckException
	 */
	public void add(SolrInputDocument document) throws SolrServerException, IOException, SolrCheckException {
		UpdateResponse response = solrClient.add(document);
		if (response.getStatus() == 0) {
			solrClient.commit();
		} else {
			throw new SolrCheckException("solr add error");
		}
	}

	/**
	 * 添加对象到全文检索
	 * 
	 * @param document
	 * @throws IOException
	 * @throws SolrServerException
	 * @throws SolrCheckException
	 */
	public void add(Collection<SolrInputDocument> docs) throws IOException, SolrServerException, SolrCheckException {
		if (docs != null && docs.size() > 0) {
			UpdateResponse response = solrClient.add(docs);
			if (response.getStatus() == 0) {
				solrClient.commit();
			} else {
				throw new SolrCheckException("solr add error");
			}
		}
	}
	
	/**
	 * 添加bean对象到全文检索
	 * @param object
	 * @throws IOException
	 * @throws SolrServerException
	 * @throws SolrCheckException
	 */
	public void addByBean(Object object) throws IOException, SolrServerException, SolrCheckException {
		UpdateResponse response=solrClient.addBean(object);
		if (response.getStatus() == 0) {
			solrClient.commit();
		} else {
			throw new SolrCheckException("solr add error");
		}
	}
	
	/**
	 * 添加bean对象到全文检索
	 * 
	 * @param document
	 * @throws IOException
	 * @throws SolrServerException
	 * @throws SolrCheckException
	 */
	public void addByBean(Collection<Object> objects) throws IOException, SolrServerException, SolrCheckException {
		if (objects != null && objects.size() > 0) {
			UpdateResponse response = solrClient.addBeans(objects);
			if (response.getStatus() == 0) {
				solrClient.commit();
			} else {
				throw new SolrCheckException("solr add error");
			}
		}
	}
	
	/**
	 * 在全文检索中移除对象
	 * 
	 * @param id
	 * @throws SolrServerException
	 * @throws IOException
	 * @throws SolrCheckException
	 */
	public void delete(String id) throws SolrServerException, IOException, SolrCheckException {
		UpdateResponse response=solrClient.deleteById(id);
		if (response.getStatus()==0) {
			solrClient.commit();
		} else {
			throw new SolrCheckException("solr delete error");
		}
	}
	
	/**
	 * 在全文检索中移除对象
	 * 
	 * @param id
	 * @throws SolrServerException
	 * @throws IOException
	 * @throws SolrCheckException
	 */
	public void delete(List<String> ids) throws SolrServerException, IOException, SolrCheckException {
		UpdateResponse response=solrClient.deleteById(ids);
		if (response.getStatus()==0) {
			solrClient.commit();
		} else{
			throw new SolrCheckException("solr delete error");
		}
	}
	
	/**
	 * 删除全文检索的所有对象
	 * 
	 * @throws SolrServerException
	 * @throws IOException
	 * @throws SolrCheckException
	 */
	public void deleteAll() throws SolrServerException, IOException, SolrCheckException {
		UpdateResponse response=solrClient.deleteByQuery("*");
		if (response.getStatus()==0) {
			solrClient.commit();
		} else {
			throw new SolrCheckException("solr delete all error");
		}
	}
	
	/**
	 * 获取solrClient
	 * @return
	 */
	public SolrClient getSolrClient() {
		return solrClient;
	}
	
}
