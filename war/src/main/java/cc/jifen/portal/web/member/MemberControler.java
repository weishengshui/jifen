package cc.jifen.portal.web.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinarewards.core.common.Page;
import com.chinarewards.core.common.SystemTimeProvider;
import com.chinarewards.core.domain.member.Member;
import com.chinarewards.core.service.member.IMemberEnquireService;

@Controller
@RequestMapping(value = "/member")
public class MemberControler {

	@Autowired
	private IMemberEnquireService memberEnquireService;

	@RequestMapping(value = "/list")
	public void listMember(Model model,Member member,Page page) {
		List<Member> list = memberEnquireService.findMember(member,page);
		model.addAttribute("list", list);
		model.addAttribute("member",member);
		model.addAttribute("page", page);
	}
	
	@RequestMapping(value = "/createPage")
	public void createPage(Model model){
		
	}
	
	@RequestMapping(value = "/create")
	public String create(Member member){
		memberEnquireService.createMember(String.valueOf(SystemTimeProvider.getCurrentTime()), member);
		return "redirect:list" ;
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public String findMemberById(@PathVariable Integer id,Model model){
		Member member = memberEnquireService.findMemberById(id);
		model.addAttribute("member", member);
		return "member/update";
	}
	
	@RequestMapping(value = "/updateStatus")
	public String updateStatus(String ids,Integer status){
		memberEnquireService.updateMemberStatus(ids, status);
		return "redirect:list" ;
	}
}
