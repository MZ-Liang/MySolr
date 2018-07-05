package com.lmz.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.solr.common.SolrInputDocument;

public class Book implements Serializable{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 书名 */
	private String bookName;
	/** 作者 */
	private Author author;
	/** 状态[0:下架;1:上架] */
	private Integer status;
	/** 内容 */
	private String content;
	/** 出版时间 */
	private Timestamp publishDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Timestamp publishDate) {
		this.publishDate = publishDate;
	}
	
	public Book() {
		super();
	}
	
	public Book(Long id, String bookName, Author author, Integer status, String content, Timestamp publishDate) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.author = author;
		this.status = status;
		this.content = content;
		this.publishDate = publishDate;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", author=" + author + ", status=" + status + ", content="
				+ content + ", publishDate=" + publishDate + "]";
	}
	
	/**
	 * 获取solr全文检索对象
	 *
	 * @param book 
	 *           
	 * @return 全文检索对象
	 */
	public SolrInputDocument getSolrInputDocument(Book book) {
		SolrInputDocument document=new SolrInputDocument();
		document.addField("id", book.getId());
		document.addField("bookName", book.getBookName());
		document.addField("status", book.getStatus());
		document.addField("content", book.getContent());
		document.addField("publishDate", book.getPublishDate().getTime());
		if (book.getAuthor()!=null) {
			document.addField("author_id", book.getAuthor().getId());
			document.addField("author_name", book.getAuthor().getName());
			document.addField("author_nickname", book.getAuthor().getNickname());
		}
		return document;
	}
	
}
