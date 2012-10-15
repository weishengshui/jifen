package com.chinarewards.core.logic.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.chinarewards.core.common.DaoSupport;
import com.chinarewards.core.common.Page;
import com.chinarewards.core.common.StringUtils;
import com.chinarewards.core.domain.member.Member;

public class RegisterMemberLogic implements IRegisterMemberLogic {

	private DaoSupport daoSupport;
	
	@Override
	public Member createMember(String operatorId,Member member) {
		daoSupport.saveOrUpdate(member);
		return member;
	}
	
	@Override
	public Member cancelMember(Member member) {
		return null;
	}
	
	@Override
	public List<Member> findMember(Member member,Page page) {
		DetachedCriteria criteria = DetachedCriteria.forClass(member.getClass());
		criteria.add(Example.create(member).excludeProperty("name"));
		if(member.getName() != null && !"".equals(member.getName())){
			criteria.add(Restrictions.like("name", member.getName(),MatchMode.ANYWHERE));
		}
		return daoSupport.findPageByCriteria(page, criteria);
	}
	
	@Override
	public Member updateMember(String operatorId, Member member) {
		return null;
	}

	@Override
	public Member findMemberById(Integer id) {
		return daoSupport.findTById(Member.class, id);
	}
	
	@Override
	public Integer updateMemberStatus(String ids, Integer status) {
		String hql = "update Member set status = :status where id in (:ids)";
		Map<String,Object> map =  new HashMap<String,Object>();
		map.put("status", status);
		map.put("ids", StringUtils.getIntegers(ids));
		return daoSupport.executeHQL(hql,map);
	}
	
	public DaoSupport getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(DaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
	
}
