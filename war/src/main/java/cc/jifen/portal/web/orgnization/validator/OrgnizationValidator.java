package cc.jifen.portal.web.orgnization.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chinarewards.core.domain.orgnization.Orgnization;
import com.chinarewards.core.logic.orgnization.OrgnizationLogic;

/**
 * 
 * @author weishengshui
 *
 */
public class OrgnizationValidator implements Validator{
	

	private OrgnizationLogic orgnizationLogic;
	
	public OrgnizationValidator(OrgnizationLogic orgnizationLogic) {
		this.orgnizationLogic = orgnizationLogic;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Orgnization.class);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Orgnization orgnization = (Orgnization)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name", "大客户名称必须输入");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "forShort", "forShort", "大客户简称必须输入");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "businessLicenseNumber", "businessLicenseNumber", "营业执照必须输入");
		if(!StringUtils.isBlank(orgnization.getName())){
			try {
				orgnization = orgnizationLogic.findOrgnizationByName(orgnization.getName());
				if(orgnization!=null){
					errors.rejectValue("name", "name","该大客户名称已存在");
				}else{
					System.out.println("orgnization is null");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
