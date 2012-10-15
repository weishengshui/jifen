package com.chinarewards.core.domain.orgnization;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 卡级别
 * @author weishengshui
 *
 */
@Entity
@Table(name="ORG_CardLevel")
public class CardLevel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7084709879695911544L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String levelName;//级别名称
	private int effectiveCycle;//有效期(单位是月)：-1表示"永久有效"
	private int upgradeStandard;//升级标准：积分数量
	private int levelNumber;//级别序号
	
	@ManyToOne(optional=false)
	private Card card;
	
	
	public CardLevel() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public int getEffectiveCycle() {
		return effectiveCycle;
	}
	public void setEffectiveCycle(int effectiveCycle) {
		this.effectiveCycle = effectiveCycle;
	}
	public int getUpgradeStandard() {
		return upgradeStandard;
	}
	public void setUpgradeStandard(int upgradeStandard) {
		this.upgradeStandard = upgradeStandard;
	}
	public int getLevelNumber() {
		return levelNumber;
	}
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	
}
