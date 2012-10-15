package cc.jifen.portal.web.merchandise;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.jifen.portal.web.merchandise.validator.CreateMerchandiseValidator;

import com.chinarewards.common.models.PageSorting.PaginationDetail;
import com.chinarewards.core.common.Constants;
import com.chinarewards.core.context.UserContext;
import com.chinarewards.core.domain.merchandise.Merchandise;
import com.chinarewards.core.domain.merchandise.MerchandiseCatagory;
import com.chinarewards.core.service.merchandise.IMerchandiseEnquireService;
import com.chinarewards.core.service.merchandise.IMerchandiseLifecycleService;
import com.chinarewards.model.common.SelectVO;
import com.chinarewards.model.merchandise.MerchandiseCriteria;

@Controller
public class MerchandiseControler {

	@Autowired
	IMerchandiseLifecycleService merchandiseLifecycleService;

	@Autowired
	IMerchandiseEnquireService merchandiseEnquireService;

	// TODO  should obtained from session .
	UserContext userContext = new UserContext() {
		@Override
		public String getUserId() {
			// TODO Auto-generated method stub
			return "1";
		}

		@Override
		public String getSessionId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getIp() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public UserContext copyNewInstance() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	/**
	 * 获得商品所有类别
	 * @param userContext
	 * @return
	 */
	public List<SelectVO> getMerchandiseCatagories(UserContext userContext) {

		List<SelectVO> list = new ArrayList<SelectVO>();

		List<MerchandiseCatagory> catagories = merchandiseEnquireService
				.searchMerchandiseCatagories(userContext);
		for (MerchandiseCatagory ct : catagories) {
			list.add(new SelectVO(ct.getName(), ct.getId()));
		}

		return list;
	}

	/**
	 * 创建商品
	 * @param merchandise
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/merchandise/create")
	public String createMerchandise(@ModelAttribute Merchandise merchandise,
			BindingResult result, Model model) {
		// validate
		new CreateMerchandiseValidator().validate(merchandise, result);
		if (result.hasErrors()) {
			// prepare data
			List<SelectVO> catagories = getMerchandiseCatagories(null);
			model.addAttribute("catagories", catagories);
			return "merchandise.show";
		}
		// call service create a merchandise
		merchandiseLifecycleService.createMerchandise(userContext, null,
				merchandise.getCatagory().getId(), merchandise, null);
		
		//　TODO 
		return "success";
	}

	/**
	 * 展示商品
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/merchandise/show")
	public String showCreateMerchandise(Model model) {
		// prepare data
		List<SelectVO> catagories = getMerchandiseCatagories(null);

		List<SelectVO> orgs = new ArrayList<SelectVO>();
		SelectVO org1 = new SelectVO();
		org1.setName("中国人寿");
		org1.setValue("1");
		orgs.add(org1);

		SelectVO org2 = new SelectVO();
		org2.setName("深圳航空");
		org2.setValue("2");
		orgs.add(org2);

		model.addAttribute("merchandise", new Merchandise());
		model.addAttribute("catagories", catagories);
		model.addAttribute("orgs", orgs);

		return "merchandise.show";
	}

	/**
	 * 分页展示商品列表
	 * @param criteria
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/merchandise/list")
	public String listMerchandise(@ModelAttribute MerchandiseCriteria criteria,
			Integer currentPage, Model model) {

		currentPage = currentPage == null ? 1 : currentPage;
		PaginationDetail paginationDetail = new PaginationDetail();
		paginationDetail.setStart((currentPage - 1) * Constants.PERPAGE_SIZE);
		paginationDetail.setLimit(Constants.PERPAGE_SIZE);

		criteria.setPaginationDetail(paginationDetail);

		List<Merchandise> merchandise = merchandiseEnquireService
				.searchMerchandises(userContext, criteria);
		Long count = merchandiseEnquireService.countMerchandises(userContext,
				criteria);

		model.addAttribute("totalCount", count);
		model.addAttribute("perpage", Constants.PERPAGE_SIZE);
		model.addAttribute("currentPage", currentPage);

		model.addAttribute("merchandiseList", merchandise);

		if (criteria != null) {
			model.addAttribute("criteria", criteria);
		}
		return "merchandise.list";
	}

	public IMerchandiseLifecycleService getMerchandiseLifecycleService() {
		return merchandiseLifecycleService;
	}

	public void setMerchandiseLifecycleService(
			IMerchandiseLifecycleService merchandiseLifecycleService) {
		this.merchandiseLifecycleService = merchandiseLifecycleService;
	}

	public IMerchandiseEnquireService getMerchandiseEnquireService() {
		return merchandiseEnquireService;
	}

	public void setMerchandiseEnquireService(
			IMerchandiseEnquireService merchandiseEnquireService) {
		this.merchandiseEnquireService = merchandiseEnquireService;
	}
}
