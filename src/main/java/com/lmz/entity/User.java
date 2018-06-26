package com.lmz.entity;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String headImg;
	private String nickName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public User() {
		super();
	}
	
	public User(Long id, String headImg, String nickName) {
		super();
		this.id = id;
		this.headImg = headImg;
		this.nickName = nickName;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", headImg=" + headImg + ", nickName=" + nickName + "]";
	}
	
}
