package com.chinarewards.core.support.enquire.merchandise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinarewards.core.common.DaoSupport;
import com.chinarewards.core.context.UserContext;
import com.chinarewards.core.domain.merchandise.Merchandise;
import com.chinarewards.core.domain.merchandise.MerchandiseCatagory;
import com.chinarewards.model.merchandise.MerchandiseCriteria;

public class MerchandiseEnquireSupport {

	private DaoSupport daoSupport;

	public List<MerchandiseCatagory> searchMerchandiseCatagories(
			UserContext userContext) {

		List<MerchandiseCatagory> list = daoSupport.getHibernateTemplate()
				.find("FROM MerchandiseCatagory m WHERE 1=1 ");

		return list;
	}

	protected String buildSearchMerchandiseHQL(
			MerchandiseCriteria merchandiseCriteria,
			Map<String, Object> params, boolean isCount) {

		StringBuffer strBuffer = new StringBuffer();
		if (isCount) {
			strBuffer.append("SELECT COUNT(m) ");
		} else {
			strBuffer.append("SELECT m ");
		}

		strBuffer.append("FROM Merchandise m WHERE 1=1 ");

		if (merchandiseCriteria != null) {
			if (null != merchandiseCriteria.getName()
					&& !merchandiseCriteria.getName().isEmpty()) {
				strBuffer.append(" AND m.name like :name");
				params.put("name", "%" + merchandiseCriteria.getName() + "%");
			}
			// TODO
		}
		return strBuffer.toString();
	}

	/**
	 * 根据条件查询总条数
	 * 
	 * @param operatorId
	 * @param merchandiseCriteria
	 * @return
	 */
	public Long countMerchandises(String operatorId,
			MerchandiseCriteria merchandiseCriteria) {

		Map<String, Object> params = new HashMap<String, Object>();
		String hql = buildSearchMerchandiseHQL(merchandiseCriteria, params,
				true);
		List<Long> list = daoSupport.executeQuery(hql, params, null);
		return list.get(0);
	}

	/**
	 * 根据条件分页查询数据
	 * 
	 * @param operatorId
	 * @param merchandiseCriteria
	 * @return
	 */
	public List<Merchandise> searchMerchandises(String operatorId,
			MerchandiseCriteria merchandiseCriteria) {

		Map<String, Object> params = new HashMap<String, Object>();
		String hql = buildSearchMerchandiseHQL(merchandiseCriteria, params,
				false);

		List<Merchandise> list = daoSupport.executeQuery(hql, params,
				merchandiseCriteria.getPaginationDetail());
		return list;
	}

	public DaoSupport getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(DaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
}
