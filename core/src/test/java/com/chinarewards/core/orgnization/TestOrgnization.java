package com.chinarewards.core.orgnization;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.chinarewards.core.domain.orgnization.Card;
import com.chinarewards.core.domain.orgnization.CardLevel;
import com.chinarewards.core.domain.orgnization.Orgnization;
import com.chinarewards.core.logic.orgnization.OrgnizationLogic;

/**
 * 
 * @author weishengshui
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext_core_test.xml" })
@TransactionConfiguration
@Transactional
public class TestOrgnization {
	
	
	@Autowired
	private OrgnizationLogic orgnizationLogic;
	
	
	@Test
	public void testOrgnizationLogic() throws Exception{
		System.out.println("TestOrgnization.testOrgnizationLogic() start");
		//大客户增删查改测试
		Orgnization orgnization = new Orgnization();
		orgnization.setName("深圳积享通信息技术有限公司");
		orgnization.setForShort("JXT");
		orgnization.setBusinessLicenseNumber("122222jxt2323");
		orgnizationLogic.createOrgnization(orgnization);
		orgnization = new Orgnization();
		orgnization.setName("深圳航空");
		orgnization.setForShort("SHENZHEN_AIR");
		orgnization.setBusinessLicenseNumber("2255333114222");
		orgnizationLogic.createOrgnization(orgnization);
		orgnization = new Orgnization();
		orgnization.setName("中国人寿");
		orgnization.setBusinessLicenseNumber("212332445344life");
		orgnization.setForShort("CHINA_LIFE");
		orgnizationLogic.createOrgnization(orgnization);
		
		Orgnization jxt = orgnizationLogic.findOrgnizationByName("深圳积享通信息技术有限公司");
		Orgnization shenzhen_air = orgnizationLogic.findOrgnizationByName("深圳航空"); 
		Orgnization china_life = orgnizationLogic.findOrgnizationByName("中国人寿");
		Assert.assertNotNull(jxt);
		Assert.assertNotNull(shenzhen_air);
		Assert.assertNotNull(china_life);
		Assert.assertEquals(3, orgnizationLogic.findAll(Orgnization.class).size());
		china_life.setForShort("zhongguorenshou");
		orgnizationLogic.updateOrgnization(china_life);
		china_life = orgnizationLogic.findOrgnizationByName("中国人寿");
		Assert.assertEquals("zhongguorenshou", china_life.getForShort());
		orgnizationLogic.deleteOrgnizationById(china_life.getId());
		Assert.assertEquals(2, orgnizationLogic.findAll(Orgnization.class).size());
		china_life = orgnizationLogic.findOrgnizationByName("中国人寿");
		Assert.assertNull(china_life);
		
		
		//为大客户创建卡
		Card binfenCard = new Card();
		binfenCard.setId("binfen");
		binfenCard.setName("缤纷联盟卡");
		binfenCard.setOrgnization(jxt);
		Assert.assertEquals(0, orgnizationLogic.findCardsByOrgnization(jxt).size());
		orgnizationLogic.createCard(binfenCard);
		Assert.assertEquals(1, orgnizationLogic.findCardsByOrgnization(jxt).size());
		
		Card lichengCard = new Card();
		lichengCard.setName("深航里程卡");
		lichengCard.setOrgnization(shenzhen_air);
		Assert.assertEquals(0, orgnizationLogic.findCardsByOrgnization(shenzhen_air).size());
		orgnizationLogic.createCard(lichengCard);
		Assert.assertEquals(1, orgnizationLogic.findCardsByOrgnization(shenzhen_air).size());
		

		// 为大客户的卡设定卡级别
		CardLevel lichengCardLevel = new CardLevel();
		lichengCardLevel.setCard(lichengCard);
		lichengCardLevel.setEffectiveCycle(-1);//-1表示永久有效
		lichengCardLevel.setLevelName("普卡");
		lichengCardLevel.setLevelNumber(0);
		lichengCardLevel.setUpgradeStandard(0);
		Assert.assertEquals(0, orgnizationLogic.findCardLevelsByCard(lichengCard).size());
		orgnizationLogic.createCardLevel(lichengCardLevel);
		Assert.assertEquals(1, orgnizationLogic.findCardLevelsByCard(lichengCard).size());
		lichengCardLevel = new CardLevel();
		lichengCardLevel.setCard(lichengCard);
		lichengCardLevel.setEffectiveCycle(6);
		lichengCardLevel.setLevelName("银卡");
		lichengCardLevel.setLevelNumber(1);
		lichengCardLevel.setUpgradeStandard(5000);
		orgnizationLogic.createCardLevel(lichengCardLevel);
		Assert.assertEquals(2, orgnizationLogic.findCardLevelsByCard(lichengCard).size());
		
		CardLevel binfenCardLevel = new CardLevel();
		binfenCardLevel.setCard(binfenCard);
		binfenCardLevel.setEffectiveCycle(-1);
		binfenCardLevel.setLevelName("普卡");
		binfenCardLevel.setLevelNumber(0);
		binfenCardLevel.setUpgradeStandard(0);
		Assert.assertEquals(0, orgnizationLogic.findCardLevelsByCard(binfenCard).size());
		orgnizationLogic.createCardLevel(binfenCardLevel);
		Assert.assertEquals(1, orgnizationLogic.findCardLevelsByCard(binfenCard).size());
		binfenCardLevel = new CardLevel();
		binfenCardLevel.setCard(binfenCard);
		binfenCardLevel.setEffectiveCycle(12);
		binfenCardLevel.setLevelName("金卡");
		binfenCardLevel.setLevelNumber(2);
		binfenCardLevel.setUpgradeStandard(10000);
		orgnizationLogic.createCardLevel(binfenCardLevel);
		Assert.assertEquals(2, orgnizationLogic.findCardLevelsByCard(binfenCard).size());
		
		//删除大客户的同时，也会删除其下所属的卡以及卡级别
		orgnizationLogic.deleteOrgnizationById(shenzhen_air.getId());
		Assert.assertNull(orgnizationLogic.findOrgnizationById(shenzhen_air.getId()));
		Assert.assertEquals(0, orgnizationLogic.findCardsByOrgnization(shenzhen_air).size());
		Assert.assertEquals(0, orgnizationLogic.findCardLevelsByCard(lichengCard).size());
		
		//删除卡级别
		Assert.assertEquals(2, orgnizationLogic.findCardLevelsByCard(binfenCard).size());
		orgnizationLogic.deleteCardLevelById(binfenCardLevel.getId());
		Assert.assertEquals(1, orgnizationLogic.findCardLevelsByCard(binfenCard).size());
		
		//删除卡的同时，也会删除该卡对应的卡级别
		orgnizationLogic.deleteCardById(binfenCard.getId());
		Assert.assertEquals(0, orgnizationLogic.findCardsByOrgnization(jxt).size());
		Assert.assertEquals(0, orgnizationLogic.findCardLevelsByCard(binfenCard).size());
		
		
	}
	
}
