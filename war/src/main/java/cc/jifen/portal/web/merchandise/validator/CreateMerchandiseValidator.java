package cc.jifen.portal.web.merchandise.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chinarewards.core.domain.merchandise.Merchandise;

public class CreateMerchandiseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Merchandise.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Merchandise merchandise = (Merchandise) target;
		if (null == merchandise.getName() || merchandise.getName().isEmpty()) {
			ValidationUtils.rejectIfEmpty(errors, "name", "name",
					"商品名称必须输入");
		}

	}
}
