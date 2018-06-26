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

public class SolrUtils {
	private static HttpSolrClient solrClient;
	private static final String URL="http://localhost:8080/solr/solr_core";
	
	public SolrUtils(){
	    //初始化solrClient
		solrClient=new HttpSolrClient.Builder(URL).build();
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
	 * 获取solrClient
	 * @return
	 */
	public SolrClient getSolrClient() {
		return solrClient;
	}
	
}
