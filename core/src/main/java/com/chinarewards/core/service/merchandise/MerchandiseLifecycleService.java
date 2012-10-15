package com.chinarewards.core.service.merchandise;

import java.util.List;

import com.chinarewards.core.context.UserContext;
import com.chinarewards.core.domain.merchandise.Merchandise;
import com.chinarewards.core.logic.merchandise.IMerchandiseLifecycleLogic;
import com.chinarewards.model.merchandise.MerchandiseFileStore;

public class MerchandiseLifecycleService implements
		IMerchandiseLifecycleService {

	private IMerchandiseLifecycleLogic merchandiseLifecycleLogic;

	@Override
	public Merchandise createMerchandise(UserContext userContext,
			List<String> orgIds, String catagoryId, Merchandise merchantdise,
			List<MerchandiseFileStore> merchandiseFileStores) {
		// XXX save temp file
		return merchandiseLifecycleLogic.createMerchandise(userContext, orgIds,
				catagoryId, merchantdise, merchandiseFileStores);
	}

	public Merchandise updateMerchandiseById(UserContext userContext,
			String id, Merchandise merchandise) {
		throw new UnsupportedOperationException();
	}

	public IMerchandiseLifecycleLogic getMerchandiseLifecycleLogic() {
		return merchandiseLifecycleLogic;
	}

	public void setMerchandiseLifecycleLogic(
			IMerchandiseLifecycleLogic merchandiseLifecycleLogic) {
		this.merchandiseLifecycleLogic = merchandiseLifecycleLogic;
	}
}
