package com.blogApplication.exception;


public class ResourseNotFoundException extends RuntimeException {
		String ResourseName;
		String FieldName;
		int id;
	
	
	public ResourseNotFoundException(String ResourseName, String FieldName, int id) {
		super(String.format("%s not found %s : %s", ResourseName,FieldName, id));
				this.ResourseName=ResourseName;
				this.FieldName=FieldName;
				this.id = id;
		

	}
	

}
