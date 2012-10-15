package com.chinarewards.core.service.merchandise;

import java.util.List;

import com.chinarewards.core.context.UserContext;
import com.chinarewards.core.domain.merchandise.Merchandise;
import com.chinarewards.core.domain.merchandise.MerchandiseCatagory;
import com.chinarewards.model.merchandise.MerchandiseCriteria;

/**
 * 商品查询相关服务接口定义
 * 
 * @author qingminzou
 * 
 */
public interface IMerchandiseEnquireService {

	/**
	 * 根据条件查询商品信息
	 * 
	 * @param merchandise
	 * @param operatorId
	 * @return
	 */
	public List<Merchandise> searchMerchandises(UserContext userContext,
			MerchandiseCriteria merchantdiseCriteria);

	/**
	 * 根据条件获取商品总数
	 * 
	 * @param userContext
	 * @param merchantdiseCriteria
	 * @return
	 */
	public Long countMerchandises(UserContext userContext,
			MerchandiseCriteria merchantdiseCriteria);

	/**
	 * 查询商户类别
	 * 
	 * @param userContext
	 * @return
	 */
	public List<MerchandiseCatagory> searchMerchandiseCatagories(
			UserContext userContext);

}
