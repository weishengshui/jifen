package com.chinarewards.core.logic.merchandise;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.chinarewards.IndexAndSearchTest;
import com.chinarewards.core.domain.merchandise.Merchandise;

/**
 * Example testcase for Hibernate Search
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext_core_test.xml" })
@TransactionConfiguration
@Transactional
public class MerchandiseLifecycleLogicTest {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	IMerchandiseLifecycleLogic merchandiseLifecycleLogic;

	private static Logger log = LoggerFactory
			.getLogger(IndexAndSearchTest.class);

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
	}

	@Test
	public void testCreateMerchant() throws Exception {

		String operatorId = "M001";

		Merchandise merchandise = new Merchandise();
		merchandise.setName("ZIP");
		merchandise.setDescription("good merchandise");
		merchandise = merchandiseLifecycleLogic.createMerchandise(operatorId, merchandise);
		
		Assert.assertNotNull(merchandise.getId());
		
		Assert.assertEquals("ZIP", merchandise.getName());
	
		Assert.assertNotNull(merchandise.getCreatedAt());	
	}

}
