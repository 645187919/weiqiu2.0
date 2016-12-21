package com.hzy.weiqiu.fragment.news;

public class News {
	private String title;
	private String content;
	private int imageId;
	
	public News(String title, String content, int imageId) {
		
		this.title = title;
		this.content = content;
		this.imageId = imageId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	

}
