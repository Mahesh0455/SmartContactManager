package com.smart.util;

public class Message {
	
	
	private String content;
	
	private String mtype;

	public Message(String content, String mtype) {
		super();
		this.content = content;
		this.mtype = mtype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	
	
	
	

}
