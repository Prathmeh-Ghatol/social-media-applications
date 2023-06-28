package com.blogApplication.exception;

public class SecurityException extends RuntimeException {

	private String Details;
	private String fieldName;
	private String id;

	public SecurityException(String Details, String fieldName, String id) {
		super(String.format("%f %f  and %f", Details, fieldName, id));
		this.Details=Details;
		this.fieldName=fieldName;
		this.id=id;
		

	}

}
