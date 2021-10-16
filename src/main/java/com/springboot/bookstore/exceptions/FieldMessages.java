package com.springboot.bookstore.exceptions;

import java.io.Serializable;

public class FieldMessages implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String filedName;
	private String message;
	
	public FieldMessages(String filedName, String message) {
		super();
		this.filedName = filedName;
		this.message = message;
	}

	public FieldMessages() {
		super();
	}

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
		

}
