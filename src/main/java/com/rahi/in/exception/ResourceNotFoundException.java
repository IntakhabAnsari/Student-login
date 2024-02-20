package com.rahi.in.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	Object fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, Object email) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, email));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = email;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Object getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}	
	
	
}