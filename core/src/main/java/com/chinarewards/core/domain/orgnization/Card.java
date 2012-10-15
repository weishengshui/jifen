package com.chinarewards.core.domain.orgnization;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ORG_Card")
public class Card implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1478531396235663931L;
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id;
	private String name;
	@ManyToOne(optional=false)
	private Orgnization orgnization;
	
	public Card() {
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

	public Orgnization getOrgnization() {
		return orgnization;
	}

	public void setOrgnization(Orgnization orgnization) {
		this.orgnization = orgnization;
	}
	
	
	
	
}
