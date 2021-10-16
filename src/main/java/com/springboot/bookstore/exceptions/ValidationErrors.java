package com.springboot.bookstore.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors extends StandardError {

	private List<FieldMessages> errors = new ArrayList<>();
	
	public ValidationErrors() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidationErrors(Long timeStamp, Integer status, String error) {
		super(timeStamp, status, error);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessages> getErrors() {
		return errors;
	}

	public void addErrors(String fieldName, String message) {
		this.errors.add(new FieldMessages(fieldName, message));
	}

	
}
