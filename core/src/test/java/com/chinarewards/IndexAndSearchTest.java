package com.chinarewards;

import org.hibernate.SessionFactory;
import org.junit.After;
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
import org.springframework.util.Assert;

import com.chinarewards.core.common.DaoSupport;

/**
 * Example testcase for Hibernate Search
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext_core_test.xml" })
@TransactionConfiguration
@Transactional
public class IndexAndSearchTest {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DaoSupport daoSupport;
	
	private static Logger log = LoggerFactory
			.getLogger(IndexAndSearchTest.class);

	@Before
	public void setUp() {
		initHibernate();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testStemming() throws Exception {
		Assert.notNull(sessionFactory);
		sessionFactory.openSession().find("SELECT b FROM Book b WHERE 1=1");
	
		Assert.notNull(daoSupport);
	}

	private void initHibernate() {

	}
}
