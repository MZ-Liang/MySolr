package com.lmz.bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 分页结果类
 *
 */
public class PageResult {
	/** 当前页 */
	private Integer currentPage;
	/** 每页大小 */
	private Integer pageSize;
	/** 总数 */
	private Long count;
	/**总页数*/
	private Integer totalPage;
	/** list集合数据 */
	private List<Object> list;
	/** map集合数据 */
	private Map<Object, Object> map;
	
	public Integer getCurrent() {
		return currentPage;
	}
	public void setCurrent(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long l) {
		this.count = l;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public Map<Object, Object> getMap() {
		return map;
	}
	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}
	
	public PageResult() {
		super();
	}
	
	public PageResult(Integer currentPage, Integer pageSize, Long count, Integer totalPage, List<Object> list,
			Map<Object, Object> map) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.count = count;
		this.totalPage = totalPage;
		this.list = list;
		this.map = map;
	}

}
