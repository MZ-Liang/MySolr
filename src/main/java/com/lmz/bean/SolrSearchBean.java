package com.lmz.bean;

import java.io.Serializable;
import java.util.List;

public class SolrSearchBean implements Serializable {
	/** SerialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 关键字列表 */
	private List<String> keywordList;

	/** 排序类型[0:倒序;1:正序] */
	private Integer orderType;

	/** 过滤条件：状态[0:下架;1:上架] */
	private Integer status;
	
	/** 是否高亮 */
	private Boolean ifHL;

	/** 当前页数 */
	private Integer pageNo;

	/** 页面大小 */
	private Integer pageSize;

	public List<String> getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(List<String> keywordList) {
		this.keywordList = keywordList;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIfHL() {
		return ifHL;
	}

	public void setIfHL(Boolean ifHL) {
		this.ifHL = ifHL;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public SolrSearchBean() {
		super();
	}

	public SolrSearchBean(List<String> keywordList, Integer orderType, Integer status, Boolean ifHL,
			Integer pageNo, Integer pageSize) {
		super();
		this.keywordList = keywordList;
		this.orderType = orderType;
		this.status = status;
		this.ifHL = ifHL;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "SolrSearchBean [keywordList=" + keywordList + ", orderType=" + orderType + ", status=" + status
				+ ", ifHL=" + ifHL + ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ "]";
	}

}
