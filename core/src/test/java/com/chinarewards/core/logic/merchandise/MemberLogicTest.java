package com.chinarewards.core.logic.merchandise;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.chinarewards.core.domain.member.Member;
import com.chinarewards.core.logic.member.IRegisterMemberLogic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext_core_test.xml" })
@TransactionConfiguration
@Transactional
public class MemberLogicTest {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	IRegisterMemberLogic registerMemberLogic; 

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
	}

	@Test
	public void testCreateMerchant() throws Exception {

		String operatorId = "Member001";
		Member member = new Member();
		member.setName("张三");
		member.setAddress("宝安区");
		member.setSex(1);
		member = registerMemberLogic.createMember(operatorId, member);
		
		Assert.assertNotNull(member.getId());
		Assert.assertEquals("张三", member.getName());
		Assert.assertEquals("宝安区", member.getAddress());
		Assert.assertEquals("男", member.getSex());
		
	}

}
