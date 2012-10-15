package com.chinarewards.model.merchandise;

import java.io.Serializable;

import com.chinarewards.model.file.FileStore;

public class MerchandiseFileStore implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3362790198522860260L;

	private FileStore fileStore ;
	
	private MerchandiseFileEnum fileEnum;

	private String operator ;
	
	public FileStore getFileStore() {
		return fileStore;
	}

	public void setFileStore(FileStore fileStore) {
		this.fileStore = fileStore;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public MerchandiseFileEnum getFileEnum() {
		return fileEnum;
	}

	public void setFileEnum(MerchandiseFileEnum fileEnum) {
		this.fileEnum = fileEnum;
	}
}
