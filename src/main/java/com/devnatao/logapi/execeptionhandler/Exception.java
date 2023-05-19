package com.devnatao.logapi.execeptionhandler;

import java.time.LocalDateTime;
import java.util.List;

public class Exception {

	private Integer status;
	private LocalDateTime moment;
	private String title;
	private List<Field> fields;

	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public LocalDateTime getMoment() {
		return moment;
	}
	
	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	/**
	 * This static class represent the field of the
	 * exception, containing the name, and error message
	 */
	static class Field{
		
		private String fieldName;
		private String message;
		
		public Field(String fieldName, String message) {
			super();
			this.fieldName = fieldName;
			this.message = message;
		}

		public String getFieldName() {
			return fieldName;
		}

		public String getMessage() {
			return message;
		}	
	}
}
