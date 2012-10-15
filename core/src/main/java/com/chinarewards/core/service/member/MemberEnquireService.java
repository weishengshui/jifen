package com.chinarewards.core.service.member;

import java.util.List;

import com.chinarewards.core.common.Page;
import com.chinarewards.core.domain.member.Member;
import com.chinarewards.core.logic.member.IRegisterMemberLogic;

//@Service
public class MemberEnquireService implements IMemberEnquireService {

	private IRegisterMemberLogic registerMemberLogic;
	
	@Override
	public Member createMember(String operatorId,Member member) {
		if(member.getId() == null){
			member.setStatus(0);
		}
		return registerMemberLogic.createMember(operatorId, member);
	}
	
	@Override
	public Member cancelMember(Member member) {
		return registerMemberLogic.cancelMember(member);
	}
	
	@Override
	public List<Member> findMember(Member member,Page page) {
		return registerMemberLogic.findMember(member,page);
	}
	
	@Override
	public Member updateMember(String operatorId, Member member) {
		return registerMemberLogic.updateMember(operatorId, member);
	}
	
	@Override
	public Member findMemberById(Integer id) {
		return registerMemberLogic.findMemberById(id);
	}

	@Override
	public Integer updateMemberStatus(String ids, Integer status) {
		return registerMemberLogic.updateMemberStatus(ids, status);
	}
	
	public IRegisterMemberLogic getRegisterMemberLogic() {
		return registerMemberLogic;
	}

	public void setRegisterMemberLogic(IRegisterMemberLogic registerMemberLogic) {
		this.registerMemberLogic = registerMemberLogic;
	}
	
	
}
