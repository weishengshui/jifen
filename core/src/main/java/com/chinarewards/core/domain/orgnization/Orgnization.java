package com.chinarewards.core.domain.orgnization;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 大客户
 * 
 * @author weishengshui
 * 
 */
@Entity
@Table(name = "ORG_Orgnization")
public class Orgnization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8877961721788963035L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String name; // 名称
	private String forShort;// 简称
	private String businessLicenseNumber;// 营业执照序号

	public Orgnization() {
	}

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

	public String getForShort() {
		return forShort;
	}

	public void setForShort(String forShort) {
		this.forShort = forShort;
	}

	public String getBusinessLicenseNumber() {
		return businessLicenseNumber;
	}

	public void setBusinessLicenseNumber(String businessLicenseNumber) {
		this.businessLicenseNumber = businessLicenseNumber;
	}

}
