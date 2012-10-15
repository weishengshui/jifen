package com.chinarewards.core.common;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.chinarewards.common.models.PageSorting.PaginationDetail;

@SuppressWarnings("unchecked")
public class DaoSupport extends HibernateDaoSupport {

	public <T> List<T> findAll(Class<T> c) {
		return getHibernateTemplate().find("from " + c.getSimpleName());
	}

	public <T> T findTByCriteria(DetachedCriteria criteria) {
		List<T> list = getHibernateTemplate().findByCriteria(criteria);
		if (list.size() != 0)
			return list.get(0);
		return null;
	}

	public <T> List<T> findTsByCriteria(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public Double findSumByCriteria(final DetachedCriteria criteria,
			final String property) {
		return getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<Double>() {
					public Double doInHibernate(Session session)
							throws HibernateException {
						Criteria executableCriteria = criteria
								.getExecutableCriteria(session);
						prepareCriteria(executableCriteria);
						Double sum = (Double) executableCriteria.setProjection(
								Projections.sum(property)).uniqueResult();
						return sum;
					}
				});
	}

	public Integer findCountByCriteria(final DetachedCriteria criteria) {
		return getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session)
							throws HibernateException {
						Criteria executableCriteria = criteria
								.getExecutableCriteria(session);
						prepareCriteria(executableCriteria);
						Object totalRows = executableCriteria.setProjection(
								Projections.rowCount()).uniqueResult();
						return Integer.valueOf(totalRows.toString());
					}
				});
	}

	public <T> List<T> findTs(T t) {
		return getHibernateTemplate().findByExample(t);
	}

	// public <T> List<T> findTs(T t, String property, Date start, Date end) {
	// return findPage(null, t, property, start, end);
	// }

	public <T> List<T> findTsByHQL(String hql, Object... args) {
		return getHibernateTemplate().find(hql, args);
	}

	public <T> T findTByHQL(String hql, Object... args) {
		List<T> list = getHibernateTemplate().find(hql, args);
		if (list.size() != 0)
			return list.get(0);
		return null;
	}

	public <T> T findTById(Class<T> c, Integer id) {
		List<T> list = getHibernateTemplate().find(
				"from " + c.getSimpleName() + " where id=?", id);
		if (list.size() != 0)
			return list.get(0);
		return null;
	}

	public <T> T findTById(Class<T> c, String id) {
		List<T> list = getHibernateTemplate().find(
				"from " + c.getSimpleName() + " where id=?", id);
		if (list.size() != 0)
			return list.get(0);
		return null;
	}

	// public <T> List<T> findPage(Page page, T t) {
	// Assert.notNull(t);
	// DetachedCriteria criteria = DetachedCriteria.forClass(t.getClass());
	// criteria.add(Example.create(t));
	// return findPageByCriteria(page, criteria);
	// }

	// public <T> List<T> findPage(Page page, T t, String property, Date start,
	// Date end) {
	// Assert.notNull(t);
	// DetachedCriteria criteria = DetachedCriteria.forClass(t.getClass());
	// criteria.add(Example.create(t));
	// return findPage(page, criteria, property, start, end);
	// }

	/*
	 * public <T> List<T> findPage(Page page, DetachedCriteria criteria, String
	 * property, Date start, Date end) { if (start != null) { Calendar cal =
	 * Calendar.getInstance(); cal.setTime(start); cal.set(Calendar.HOUR_OF_DAY,
	 * 0); cal.set(Calendar.MINUTE, 0); cal.set(Calendar.SECOND, 0); Date date;
	 * if (start instanceof Timestamp) { date = new
	 * Timestamp(cal.getTimeInMillis()); } else if (start instanceof Time) {
	 * date = new Time(cal.getTimeInMillis()); } else { date = new
	 * java.sql.Date(cal.getTimeInMillis()); }
	 * criteria.add(Restrictions.ge(property, date)); } if (end != null) {
	 * Calendar cal = Calendar.getInstance(); cal.setTime(end);
	 * cal.set(Calendar.HOUR_OF_DAY, 23); cal.set(Calendar.MINUTE, 59);
	 * cal.set(Calendar.SECOND, 59); Date date; if (end instanceof Timestamp) {
	 * date = new Timestamp(cal.getTimeInMillis()); } else if (end instanceof
	 * Time) { date = new Time(cal.getTimeInMillis()); } else { date = new
	 * java.sql.Date(cal.getTimeInMillis()); }
	 * criteria.add(Restrictions.le(property, date)); } if (page != null) return
	 * findPageByCriteria(page, criteria); return
	 * getHibernateTemplate().findByCriteria(criteria); }
	 */
	
	public <T> List<T> findPageByCriteria(final Page page, final DetachedCriteria criteria) {
		Assert.notNull(page);
		return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				Criteria executableCriteria = criteria.getExecutableCriteria(session);
				prepareCriteria(executableCriteria);
				Object totalRows = executableCriteria.setProjection(Projections.rowCount()).uniqueResult();
				executableCriteria.setProjection(null);
				page.setTotalRows(Integer.valueOf(totalRows.toString()));
				executableCriteria.setFirstResult(page.getStart());
				executableCriteria.setMaxResults(page.getPerpage());
				return executableCriteria.list();
			}
		});
	}
	
	public <T> T save(T t) {
		getHibernateTemplate().save(t);
		return t;
	}

	public <T> void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
	}

	public <T> void saveOrUpdateAll(List<T> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
	}

	public <T> void update(T t) {
		getHibernateTemplate().update(t);
	}

	public <T> void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	public <T> void deleteAll(List<T> list) {
		getHibernateTemplate().deleteAll(list);
	}

	public Integer executeHQL(final String hql, final Map<String,Object> map) {
		return getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						Set<String> set = map.keySet();
						for(String str : set){
							Object obj = map.get(str);
							if(obj instanceof Object[]){
								query.setParameterList(str, (Object[])obj);
							}else if(obj instanceof Collection<?>){
			                    query.setParameterList(str, (Collection<?>)obj);  
			                }else{
								query.setParameter(str, obj);
							}
						}
						return query.executeUpdate();
					}
				});
	}

	/**
	 * 执行查询 hql ，提供HQL参数以名称的方式绑定例如
	 * <code> SELECT m FROM Merchandise WHERE m.name=:name
	 * AND m.description=:description </code>, 参数params 以Map
	 * (key=name,value=xxx;key=description,value=xxx)形式传递进来 , 分页参数如果不为空将会进行分页查询
	 * 
	 * @param <T>
	 * @param hql
	 * @param params
	 * @param ctrl
	 * @return
	 */
	public <T> T executeQuery(final String hql,
			final Map<String, Object> params, final PaginationDetail ctrl) {
		return getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<T>() {
					@Override
					public T doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						setQueryParameters(query, params, ctrl);
						return (T) query.list();
					}
				});
	}

	protected void setQueryParameters(Query q, Map<String, Object> params,
			PaginationDetail ctrl) {

		if (params != null) {
			for (Map.Entry<String, Object> param : params.entrySet()) {
				q.setParameter(param.getKey(), param.getValue());
			}
		}

		if (ctrl != null) {
			if (ctrl.getStart() > 0) {
				q.setFirstResult(ctrl.getStart());
			}

			if (ctrl.getLimit() >= 0) {
				q.setMaxResults(ctrl.getLimit());
			}
		}
	}

	public <T> List<T> findTsBySQL(final Class<T> c, final String sql,
			final Object... args) {
		return getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql).addEntity(
								c);
						for (int i = 0; i < args.length; i++)
							query.setParameter(i, args[i]);
						return query.list();
					}
				});
	}

	public List<Map<String, Object>> findMapBySQL(final String sql,
			final Object... args) {
		return getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<List<Map<String, Object>>>() {
					public List<Map<String, Object>> doInHibernate(
							Session session) throws HibernateException,
							SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
						for (int i = 0; i < args.length; i++)
							query.setParameter(i, args[i]);
						return query.list();
					}
				});
	}

	public List<List<Object>> findListBySQL(final String sql,
			final Object... args) {
		return getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<List<List<Object>>>() {
					public List<List<Object>> doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						query.setResultTransformer(Transformers.TO_LIST);
						for (int i = 0; i < args.length; i++)
							query.setParameter(i, args[i]);
						return query.list();
					}
				});
	}

	public Object findObjectBySQL(final String sql, final Object... args) {
		return getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						for (int i = 0; i < args.length; i++)
							query.setParameter(i, args[i]);
						return query.uniqueResult();
					}
				});
	}

	protected void prepareCriteria(Criteria criteria) {
		if (getHibernateTemplate().isCacheQueries()) {
			criteria.setCacheable(true);
			if (getHibernateTemplate().getQueryCacheRegion() != null)
				criteria.setCacheRegion(getHibernateTemplate()
						.getQueryCacheRegion());
		}
		if (getHibernateTemplate().getFetchSize() > 0)
			criteria.setFetchSize(getHibernateTemplate().getFetchSize());
		if (getHibernateTemplate().getMaxResults() > 0)
			criteria.setMaxResults(getHibernateTemplate().getMaxResults());
		SessionFactoryUtils.applyTransactionTimeout(criteria,
				getSessionFactory());
	}
}
