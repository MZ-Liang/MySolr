package com.lmz.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.solr.common.SolrInputDocument;

public class BlogArticle implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name; // 标题
	private String mainPhoto; // 封面图片
	private String sketch; // 简述
	private String content; // 详细描述
	private String contentMd; // 详细描述 markdown
	private Boolean ifTop; // 是否置顶
	private User user; // 本文发布者
	private String sources; // 来源
	private String staticCode; // 静态码
	private BigDecimal sorter;
	private Boolean status; // 状态
	private String creater;
	private Timestamp lastUpdateTime;
	private Timestamp creatTime;
	private String columnNamesCache;
	private String columnIdsCache;
	private String labelIdsCache;
	private String labelNamesCache;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMainPhoto() {
		return mainPhoto;
	}

	public void setMainPhoto(String mainPhoto) {
		this.mainPhoto = mainPhoto;
	}

	public String getSketch() {
		return sketch;
	}

	public void setSketch(String sketch) {
		this.sketch = sketch;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentMd() {
		return contentMd;
	}

	public void setContentMd(String contentMd) {
		this.contentMd = contentMd;
	}

	public Boolean getIfTop() {
		return ifTop;
	}

	public void setIfTop(Boolean ifTop) {
		this.ifTop = ifTop;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	public String getStaticCode() {
		return staticCode;
	}

	public void setStaticCode(String staticCode) {
		this.staticCode = staticCode;
	}

	public BigDecimal getSorter() {
		return sorter;
	}

	public void setSorter(BigDecimal sorter) {
		this.sorter = sorter;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Timestamp getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}

	public String getColumnNamesCache() {
		return columnNamesCache;
	}

	public void setColumnNamesCache(String columnNamesCache) {
		this.columnNamesCache = columnNamesCache;
	}

	public String getColumnIdsCache() {
		return columnIdsCache;
	}

	public void setColumnIdsCache(String columnIdsCache) {
		this.columnIdsCache = columnIdsCache;
	}

	public String getLabelIdsCache() {
		return labelIdsCache;
	}

	public void setLabelIdsCache(String labelIdsCache) {
		this.labelIdsCache = labelIdsCache;
	}

	public String getLabelNamesCache() {
		return labelNamesCache;
	}

	public void setLabelNamesCache(String labelNamesCache) {
		this.labelNamesCache = labelNamesCache;
	}

	public BlogArticle() {
		super();
	}

	public BlogArticle(Long id, String name, String mainPhoto, String sketch, String content, String contentMd,
			Boolean ifTop, User user, String sources, String staticCode, BigDecimal sorter, Boolean status,
			String creater, Timestamp lastUpdateTime, Timestamp creatTime, String columnNamesCache,
			String columnIdsCache, String labelIdsCache, String labelNamesCache) {
		super();
		this.id = id;
		this.name = name;
		this.mainPhoto = mainPhoto;
		this.sketch = sketch;
		this.content = content;
		this.contentMd = contentMd;
		this.ifTop = ifTop;
		this.user = user;
		this.sources = sources;
		this.staticCode = staticCode;
		this.sorter = sorter;
		this.status = status;
		this.creater = creater;
		this.lastUpdateTime = lastUpdateTime;
		this.creatTime = creatTime;
		this.columnNamesCache = columnNamesCache;
		this.columnIdsCache = columnIdsCache;
		this.labelIdsCache = labelIdsCache;
		this.labelNamesCache = labelNamesCache;
	}
	
	@Override
	public String toString() {
		return "BlogArticle [id=" + id + ", name=" + name + ", mainPhoto=" + mainPhoto + ", sketch=" + sketch
				+ ", content=" + content + ", contentMd=" + contentMd + ", ifTop=" + ifTop + ", user=" + user
				+ ", sources=" + sources + ", staticCode=" + staticCode + ", sorter=" + sorter + ", status=" + status
				+ ", creater=" + creater + ", lastUpdateTime=" + lastUpdateTime + ", creatTime=" + creatTime
				+ ", columnNamesCache=" + columnNamesCache + ", columnIdsCache=" + columnIdsCache + ", labelIdsCache="
				+ labelIdsCache + ", labelNamesCache=" + labelNamesCache + "]";
	}

	/**
	 * 获取solr全文检索对象
	 *
	 * @param blogArticle 文档
	 *           
	 * @return 全文检索对象
	 */
	public SolrInputDocument getSolrInputDocument(BlogArticle blogArticle) {
		// 添加solr
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", blogArticle.getId());
		document.addField("title", blogArticle.getName());
		document.addField("content", blogArticle.getContent());
		document.addField("sketch", blogArticle.getSketch());
		document.addField("columnNamesCache", blogArticle.getColumnNamesCache());
		document.addField("columnIdsCache", blogArticle.getColumnIdsCache());
		document.addField("labelIdsCache", blogArticle.getColumnIdsCache());
		document.addField("labelNamesCache", blogArticle.getLabelNamesCache());
		document.addField("creatTime", blogArticle.getCreatTime().getTime());
		document.addField("main_photo", blogArticle.getMainPhoto());
		document.addField("author_head_img", blogArticle.getUser().getHeadImg()); // 头像
		document.addField("author_nickname", blogArticle.getUser().getNickName()); // 昵称
		document.addField("author_id", blogArticle.getUser().getId()); // id
		document.addField("status", blogArticle.getStatus());
		return document;
	}

}
