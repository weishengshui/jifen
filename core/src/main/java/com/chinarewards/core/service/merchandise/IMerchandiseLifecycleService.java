package com.chinarewards.core.service.merchandise;

import java.util.List;

import com.chinarewards.core.context.UserContext;
import com.chinarewards.core.domain.merchandise.Merchandise;
import com.chinarewards.model.merchandise.MerchandiseFileStore;

public interface IMerchandiseLifecycleService {

	/**
	 * 创建一个商品，保存商品基本信息，把临时文件移动到正式目录，保存商品的图片信息
	 * 
	 * @param merchandise
	 *            商品基本信息
	 * @param operatorId
	 *            操作人
	 * @param orgIds
	 *            大客戶ID
	 * @param merchandiseFileStores
	 *            商品附件信息
	 * @return
	 */
	public Merchandise createMerchandise(UserContext userContext,
			List<String> orgIds, String catagoryId, Merchandise merchantdise,
			List<MerchandiseFileStore> merchandiseFileStores);

	/**
	 * 根据Id更新商品基本信息
	 * 
	 * @param operatorId
	 * @param id
	 * @param merchandise
	 * @return
	 */
	public Merchandise updateMerchandiseById(UserContext userContext,
			String id, Merchandise merchandise);

}
