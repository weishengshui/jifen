package com.chinarewards.core.service.merchandise;

import java.util.List;

import com.chinarewards.core.context.UserContext;
import com.chinarewards.core.domain.merchandise.Merchandise;
import com.chinarewards.core.domain.merchandise.MerchandiseCatagory;
import com.chinarewards.core.support.enquire.merchandise.MerchandiseEnquireSupport;
import com.chinarewards.model.merchandise.MerchandiseCriteria;

public class MerchandiseEnquireService implements IMerchandiseEnquireService {

	private MerchandiseEnquireSupport merchandiseEnquireSupport;

	public List<Merchandise> searchMerchandises(UserContext userContext,
			MerchandiseCriteria merchantdiseCriteria) {
		return merchandiseEnquireSupport.searchMerchandises(
				userContext.getUserId(), merchantdiseCriteria);
	}

	public Long countMerchandises(UserContext userContext,
			MerchandiseCriteria merchandiseCriteria) {
		return merchandiseEnquireSupport.countMerchandises(
				userContext.getUserId(), merchandiseCriteria);
	}

	public void setMerchandiseEnquireSupport(
			MerchandiseEnquireSupport merchandiseEnquireSupport) {
		this.merchandiseEnquireSupport = merchandiseEnquireSupport;
	}

	public List<MerchandiseCatagory> searchMerchandiseCatagories(
			UserContext userContext) {

		return merchandiseEnquireSupport
				.searchMerchandiseCatagories(userContext);
	}
}
