package com.lmz.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 分页结果类
 * @param <T>
 *
 */
public class PageResult<T> implements Serializable{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** 当前页 */
	private Integer pageNo;
	/** 每页大小 */
	private Integer pageSize;
	/** 总数 */
	private Long count;
	/**总页数*/
	private Integer totalPage;
	/** list集合数据 */
	private List<T> list;
	/** map集合数据 */
	private Map<Object, Object> map;
	
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
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
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
	
	public PageResult(Integer pageNo, Integer pageSize, Long count, Integer totalPage, List<T> list,
			Map<Object, Object> map) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.count = count;
		this.totalPage = totalPage;
		this.list = list;
		this.map = map;
	}
	
	@Override
	public String toString() {
		return "PageResult [pageNo=" + pageNo + ", pageSize=" + pageSize + ", count=" + count + ", totalPage="
				+ totalPage + ", list=" + list + ", map=" + map + "]";
	}

}
