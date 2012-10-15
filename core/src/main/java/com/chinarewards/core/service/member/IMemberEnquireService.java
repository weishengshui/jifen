package com.chinarewards.core.service.member;

import java.util.List;

import com.chinarewards.core.common.Page;
import com.chinarewards.core.domain.member.Member;

public interface IMemberEnquireService {

	/**
	 * 创建一个会员信息
	 * @param member
	 */
	public Member createMember(String operatorId,Member member);
	/**
	 * 修改会员信息
	 * @param operatorId
	 * @param member
	 * @return
	 */
	public Member updateMember(String operatorId,Member member);
	/**
	 * 注销会员信息
	 * @param member
	 */
	public Member cancelMember(Member member);
	/**
	 * 查询会员信息
	 * @param member
	 * @return
	 */
	public List<Member> findMember(Member member,Page page);
	/**
	 * 根据Id查询会员
	 * @param id
	 * @return
	 */
	public Member findMemberById(Integer id);
	/**
	 * 注销/启用
	 * @param id
	 * @param status
	 * @return
	 */
	public Integer updateMemberStatus(String ids,Integer status);
}
