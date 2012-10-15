package com.chinarewards.core.logic.merchandise;

import java.util.List;

import com.chinarewards.core.context.UserContext;
import com.chinarewards.core.domain.merchandise.Merchandise;
import com.chinarewards.model.merchandise.MerchandiseFileStore;

/**
 * 商品基本信息维护接口定义
 * 
 * @author qingminzou
 *
 */
public interface IMerchandiseLifecycleLogic {

	
	/**
	 * 
	 * @param userContext
	 * @param orgIds
	 * @param catagoryId
	 * @param merchantdise
	 * @param merchandiseFileStores
	 * @return
	 */
	public Merchandise createMerchandise(UserContext userContext,
			List<String> orgIds, String catagoryId, Merchandise merchantdise,
			List<MerchandiseFileStore> merchandiseFileStores);
	
	/**
	 * 创建一个商品
	 * @param merchandise
	 * @param operatorId
	 * @return
	 */
	public Merchandise createMerchandise(String operatorId ,Merchandise merchantdise);
	
	/**
	 * 根据Id更新商品基本信息
	 * @param operatorId
	 * @param id
	 * @param merchandise
	 * @return
	 */
	public Merchandise updateMerchandiseById(String operatorId ,String id,Merchandise merchandise);
	
}
