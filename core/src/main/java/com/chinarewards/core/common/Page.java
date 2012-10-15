package com.chinarewards.core.common;


public class Page {

	//当前页
	private Integer currentPage;
	//总行数
	private Integer totalRows;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public int getStart(){
		if(currentPage == null){
			currentPage = 1;
		}
		return (currentPage - 1) * Constants.PERPAGE_SIZE;
	}
	/**
	 * 每页显示 PERPAGE_SIZE 条记录
	 * @return
	 */
	public int getPerpage(){
		return Constants.PERPAGE_SIZE;
	}
	
}
