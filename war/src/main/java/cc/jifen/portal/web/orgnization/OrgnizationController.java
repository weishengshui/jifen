package cc.jifen.portal.web.orgnization;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.jifen.portal.web.orgnization.validator.OrgnizationValidator;

import com.chinarewards.core.domain.orgnization.Card;
import com.chinarewards.core.domain.orgnization.Orgnization;
import com.chinarewards.core.logic.orgnization.OrgnizationLogic;

/**
 * 
 * @author weishengshui
 *
 */
@Controller
@RequestMapping(value = "/orgnization")
public class OrgnizationController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private OrgnizationLogic orgnizationLogic;
	private List<Orgnization> orgnizations;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView orgnizationRegister(ModelMap model) throws Exception {
		orgnizations = orgnizationLogic.findAll(Orgnization.class);
		if (orgnizations != null) {
			System.out.println("orgnizationRegister() orgnizations.size()="
					+ orgnizations.size());
		} else {
			System.out.println("orgnizationRegister() orgnizations is null");
		}
		model.addAttribute("orgnizations", orgnizations);
		return new ModelAndView("orgnization.register").addObject(new Orgnization()); //"orgnization.register";
	}

	@RequestMapping(value = "/create")
	public @ResponseBody List<ObjectError> addOrgnization(@ModelAttribute Orgnization orgnization,
			BindingResult result, Model model) throws Exception {
		System.out.println("addOrgnization() orgnizationName="
				+ orgnization.getName() + ", forShort=" + orgnization.getForShort()
				+ ", businessLicenseNumber=" + orgnization.getBusinessLicenseNumber());
		
		logger.info("addOrgnization() orgnizationName="
				+ orgnization.getName() + ", forShort=" + orgnization.getForShort()
				+ ", businessLicenseNumber=" + orgnization.getBusinessLicenseNumber());
		
		new OrgnizationValidator(orgnizationLogic).validate(orgnization, result);
		orgnizations = orgnizationLogic.findAll(Orgnization.class);
		model.addAttribute("orgnizations", orgnizations);
		if(result.hasErrors()){
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error:errors){
				System.out.println("code="+error.getCode()+"\tdefaultMessage="+error.getDefaultMessage());
			}
			return errors;
		}
		orgnizationLogic.createOrgnization(orgnization);
		orgnization = new Orgnization();
		model.addAttribute("orgnization", orgnization);
		return null;
	}

	@RequestMapping(value = "/addCard", method = RequestMethod.POST)
	public @ResponseBody
	void addCard(ModelMap modelMap, String cardName, String orgnizationId)
			throws Exception {
		Orgnization orgnization = orgnizationLogic
				.findOrgnizationById(orgnizationId);
		Card card = new Card();
		card.setName(cardName);
		card.setOrgnization(orgnization);
		orgnizationLogic.createCard(card);
	}

	@RequestMapping(value = "/getCardsByOrgnizationId", method = RequestMethod.POST)
	public @ResponseBody
	List<Card> getCardsByOrgnizationId(String orgnizationId) throws Exception {

		Orgnization orgnization = orgnizationLogic
				.findOrgnizationById(orgnizationId);
		List<Card> cards = orgnizationLogic.findCardsByOrgnization(orgnization);
		System.out.println("getCardsByOrgnizationId()  orgnizationId="
				+ orgnizationId);
		System.out.println("getCardsByOrgnizationId()  cards.size()="
				+ cards.size());
		return cards;
	}

	@RequestMapping(value = "/deleteCardByCardId")
	public @ResponseBody
	void deleteCardByCardId(String cardId) throws Exception {
		System.out.println("deleteCardByCardId()  cardId=" + cardId);
		orgnizationLogic.deleteCardById(cardId);
	}

	@RequestMapping(value = "/updateCard")
	public @ResponseBody
	void updateCard(String cardName, String cardId) throws Exception {
		System.out.println("updateCard()  cardName=" + cardName + " cardId="
				+ cardId);
		logger.info("updateCard() cardName={}, cardId={}", new Object[] {
				cardName, cardId });
		Card card = orgnizationLogic.findCardById(cardId);

		if (card != null) {
			card.setName(cardName);
			orgnizationLogic.updateCard(card);
		}
	}

	@RequestMapping(value = "/updateOrgnization")
	public @ResponseBody
	void updateOrgnization(String orgnizationId, String orgnizationName,
			String forShort, String businessLicenseNumber) throws Exception {
		String errorMessage = "";
		if (StringUtils.isBlank(orgnizationName)) {
			errorMessage += "大客户名称不能为空！<br>";
		}
		if (StringUtils.isBlank(forShort)) {
			errorMessage += "大客户简称不能为空！<br>";
		}
		if (StringUtils.isBlank(businessLicenseNumber)) {
			errorMessage += "营业执照不能为空！";
		}
	}
	
	@RequestMapping(value="/deleteOrgnizationById")
	public @ResponseBody void deleteOrgnizationById(String orgnizationId) throws Exception{
		
	}
	
	@RequestMapping(value="/getAllOrgnization")
	public @ResponseBody List<Orgnization> getAllOrgnization() throws Exception{
		return null;
	}

	public void setOrgnizationLogic(OrgnizationLogic orgnizationLogic) {
		this.orgnizationLogic = orgnizationLogic;
	}
	
	
}
