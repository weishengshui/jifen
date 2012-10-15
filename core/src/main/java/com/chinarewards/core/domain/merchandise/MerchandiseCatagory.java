package com.chinarewards.core.domain.merchandise;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * 商品分类
 * @author qingminzou
 *
 */
@Entity
public class MerchandiseCatagory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8147708279487398072L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToOne
	private MerchandiseCatagory parent ;
	
	private long lft ;
	
	private long rgt ;
	
	private String name;
	
	private String description ;
	
	@Column(nullable = false)
	private Date createdAt;

	private String createdBy;

	@Column(nullable = false)
	private Date lastModifiedAt;

	private String lastModifiedBy;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MerchandiseCatagory getParent() {
		return parent;
	}

	public void setParent(MerchandiseCatagory parent) {
		this.parent = parent;
	}

	public long getLft() {
		return lft;
	}

	public void setLft(long lft) {
		this.lft = lft;
	}

	public long getRgt() {
		return rgt;
	}

	public void setRgt(long rgt) {
		this.rgt = rgt;
	}
}
