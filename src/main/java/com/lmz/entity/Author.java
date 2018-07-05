package com.lmz.entity;

import java.io.Serializable;

/**
 * 作者实体
 *
 */
public class Author implements Serializable{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 名字 */
	private String name;
	/** 昵称 */
	private String nickname;
	
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Author() {
		super();
	}
	
	public Author(Long id, String name, String nickname) {
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", nickname=" + nickname + "]";
	}
	
}
