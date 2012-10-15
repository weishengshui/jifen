package com.chinarewards.core.logic.merchandise;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.chinarewards.core.common.DaoSupport;
import com.chinarewards.core.common.SystemTimeProvider;
import com.chinarewards.core.context.UserContext;
import com.chinarewards.core.domain.file.FileItem;
import com.chinarewards.core.domain.merchandise.Merchandise;
import com.chinarewards.core.domain.merchandise.MerchandiseCatagory;
import com.chinarewards.core.domain.merchandise.MerchandiseFile;
import com.chinarewards.core.domain.merchandise.OrgMerchandiseCatalog;
import com.chinarewards.core.domain.orgnization.Orgnization;
import com.chinarewards.model.file.FileStore;
import com.chinarewards.model.merchandise.MerchandiseFileStore;

public class MerchandiseLifecycleLogic implements IMerchandiseLifecycleLogic {

	private SystemTimeProvider systemTimeProvider;

	private DaoSupport daoSupport;

	public Merchandise createMerchandise(UserContext userContext,
			List<String> orgIds, String catagoryId, Merchandise merchandise,
			List<MerchandiseFileStore> merchandiseFileStores) {

		MerchandiseCatagory catagory = daoSupport.findTById(
				MerchandiseCatagory.class, catagoryId);
		if (catagory == null) {
			throw new IllegalArgumentException(
					"Creating merchandise that obtained catagory is null !");
		}

		// save catagory with merchandise relation
		merchandise.setCatagory(catagory);
		merchandise.setCreatedAt(systemTimeProvider.getCurrentTime());
		merchandise.setCreatedBy(userContext.getUserId());
		merchandise.setLastModifiedAt(systemTimeProvider.getCurrentTime());
		merchandise.setLastModifiedBy(userContext.getUserId());
		merchandise = createMerchandise(userContext.getUserId(), merchandise);

		// save orgnization with merchandise relation
		if (orgIds != null) {
			for (String orgId : orgIds) {
				Orgnization org = daoSupport
						.findTById(Orgnization.class, orgId);
				if (org == null) {
					throw new IllegalArgumentException(
							"Creating merchandise that obtained orgnization is null !");
				}
				OrgMerchandiseCatalog catalogItem = new OrgMerchandiseCatalog();
				catalogItem.setOrgnization(org);
				catalogItem.setMerchandise(merchandise);
				catalogItem.setCreatedAt(systemTimeProvider.getCurrentTime());
				catalogItem.setLastModifiedAt(systemTimeProvider
						.getCurrentTime());
				catalogItem.setCreatedBy(userContext.getUserId());
				catalogItem.setLastModifiedBy(userContext.getUserId());
				daoSupport.save(catalogItem);
			}
		}

		// sava temp file
		if (merchandiseFileStores != null) {
			for (MerchandiseFileStore fileStore : merchandiseFileStores) {
				FileStore fs = fileStore.getFileStore();
				FileItem item = new FileItem();
				item.setFilesize(fs.getFilesize());
				item.setCreatedAt(systemTimeProvider.getCurrentTime());
				item.setCreatedBy(userContext.getUserId());
				item.setLastModifiedAt(systemTimeProvider.getCurrentTime());
				item.setLastModifiedBy(userContext.getUserId());

				MerchandiseFile merchandiseFile = new MerchandiseFile();
				merchandiseFile.setCreatedAt(systemTimeProvider
						.getCurrentTime());
				merchandiseFile.setCreatedBy(userContext.getUserId());
				merchandiseFile.setFileEnum(fileStore.getFileEnum());
				merchandiseFile.setLastModifiedAt(systemTimeProvider
						.getCurrentTime());
				merchandiseFile.setMerchandise(merchandise);
				merchandiseFile.setFileItem(item);

				daoSupport.save(merchandiseFile);
			}
		}
		return merchandise;
	}

	public Merchandise createMerchandise(String operatorId,
			Merchandise merchandise) {

		if (null == merchandise) {
			throw new IllegalArgumentException("merchandise is required!");
		}
		if (StringUtils.isEmpty(merchandise.getName())) {
			throw new IllegalArgumentException(
					"merchandise name could't be empty!");
		}

		merchandise.setCreatedBy(operatorId);
		merchandise.setCreatedAt(systemTimeProvider.getCurrentTime());

		daoSupport.getHibernateTemplate().save(merchandise);

		return merchandise;
	}

	public Merchandise updateMerchandiseById(String operatorId, String id,
			Merchandise merchandise) {
		// TODO Auto-generated method stub
		return null;
	}

	public SystemTimeProvider getSystemTimeProvider() {
		return systemTimeProvider;
	}

	public void setSystemTimeProvider(SystemTimeProvider systemTimeProvider) {
		this.systemTimeProvider = systemTimeProvider;
	}

	public DaoSupport getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(DaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

}
