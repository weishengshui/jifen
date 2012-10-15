package com.chinarewards.model.merchandise;

import com.chinarewards.common.models.PageSorting.PaginationDetail;
import com.chinarewards.common.models.PageSorting.SortingDetail;

/**
 * 查询商品条件VO 
 * @author qingminzou
 *
 */
public class MerchandiseCriteria {

	private String id;

	private String orgId;
	
	private String name;

	private String categoryName;

	private String description;
	
	private PaginationDetail paginationDetail;

	private SortingDetail sortingDetail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PaginationDetail getPaginationDetail() {
		return paginationDetail;
	}

	public void setPaginationDetail(PaginationDetail paginationDetail) {
		this.paginationDetail = paginationDetail;
	}

	public SortingDetail getSortingDetail() {
		return sortingDetail;
	}

	public void setSortingDetail(SortingDetail sortingDetail) {
		this.sortingDetail = sortingDetail;
	}
}
