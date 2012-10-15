package com.chinarewards.core.logic.orgnization;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.core.common.DaoSupport;
import com.chinarewards.core.domain.orgnization.Card;
import com.chinarewards.core.domain.orgnization.CardLevel;
import com.chinarewards.core.domain.orgnization.Orgnization;
import com.chinarewards.core.exception.LogicLevelException;
import com.chinarewards.utils.StringUtil;

/**
 * 
 * @author weishengshui
 * 
 */
public class OrgnizationLogicImpl implements OrgnizationLogic {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private DaoSupport daoSupport;

	@Override
	public <T> List<T> findAll(Class<T> c) throws LogicLevelException {
		return daoSupport.findAll(c);
	}

	@Override
	public Orgnization findOrgnizationById(String id)
			throws LogicLevelException {

		logger.info("findOrgnizationById() id={}", id);
		try {
			return daoSupport.findTById(Orgnization.class, id);
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

	@Override
	public List<Card> findCardsByOrgnization(Orgnization orgnization)
			throws LogicLevelException {

		logger.info("findCardsByOrgnization() called orgnization={}",
				orgnization);
		try {
			if(orgnization == null){
				logger.info("orgnization is required");
				return null;
			}
			DetachedCriteria criteria = DetachedCriteria.forClass(Card.class);
			criteria.add(Restrictions.eq("orgnization", orgnization));
			return daoSupport.findTsByCriteria(criteria);
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

	@Override
	public List<CardLevel> findCardLevelsByCard(Card card)
			throws LogicLevelException {

		logger.info("findCardLevelsByCard() card={}", card);
		try {
			if(card == null){
				logger.info("card is required");
				return null;
			}
			DetachedCriteria criteria = DetachedCriteria
					.forClass(CardLevel.class);
			criteria.add(Restrictions.eq("card", card));
			return daoSupport.findTsByCriteria(criteria);
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

	@Override
	public Orgnization findOrgnizationByName(String name)
			throws LogicLevelException {

		logger.info("findOrgnizationByName() name={}", name);
		try {
			if(name==null || name.trim().length()==0){
				logger.info("orgnization name is required!");
				return null;
			}
			DetachedCriteria criteria = DetachedCriteria
					.forClass(Orgnization.class);
			criteria.add(Restrictions.eq("name", name));
			return daoSupport.findTByCriteria(criteria);
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

	@Override
	public void createCard(Card card) throws LogicLevelException {

		try {
			if(card == null){
				logger.info("card is required!");
				return;
			}
			daoSupport.save(card);
			logger.info("createCard succeed");
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

	@Override
	public void createCardLevel(CardLevel cardLevel) throws LogicLevelException {

		try {
			if(cardLevel == null){
				logger.info("cardLevel is required!");
				return ;
			}
			daoSupport.save(cardLevel);
			logger.info("createCardLevel succeed");
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

	@Override
	public Orgnization createOrgnization(Orgnization orgnization)
			throws LogicLevelException {

		try {
			if(orgnization == null){
				logger.info("orgnization is required!");
				return null;
			}
			daoSupport.save(orgnization);
			logger.info("createOrgnization succeed");
			return orgnization;
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

	@Override
	public void deleteCardById(String id) throws LogicLevelException {

		try {
			if(StringUtil.isEmptyString(id)){
				logger.info("card id is requried!");
				return;
			}
			Card card = daoSupport.findTById(Card.class, id);
			if(card!=null){
				deleteCardLevelsByCard(card);
				daoSupport.delete(card);
				logger.info("deleteCardById succeed");
			}
			
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

	@Override
	public Card findCardById(String id) throws LogicLevelException {

		try {
			return daoSupport.findTById(Card.class, id);
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

	@Override
	public CardLevel findCardLevelById(String id) throws LogicLevelException {

		try {
			if(StringUtil.isEmptyString(id)){
				logger.info("card id is requried!");
				return null;
			}
			return daoSupport.findTById(CardLevel.class, id);
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

	@Override
	public void deleteCardLevelById(String id) throws LogicLevelException {

		try {
			if(StringUtil.isEmptyString(id)){
				logger.info("cardLevel id is requried!");
				return;
			}
			CardLevel cardLevel = daoSupport.findTById(CardLevel.class, id);
			if(cardLevel!=null){
				daoSupport.delete(cardLevel);
				logger.info("deleteCardLevelById succeed");
			}else{
				logger.info("deleteCardLevelById cardLevel id is required");
			}
			
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

	@Override
	public void updateCard(Card card) throws LogicLevelException {

		try {
			if(card==null){
				logger.info("card is required!");
				return;
			}
			daoSupport.update(card);
			logger.info("updateCard succeed");
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

	@Override
	public void updateCardLevel(CardLevel cardLevel) throws LogicLevelException {

		try {
			if(cardLevel==null){
				logger.info("cardLevel is required!");
				return;
			}
			daoSupport.update(cardLevel);
			logger.info("updateCardLevel succeed");
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}
	
	@Override
	public void deleteOrgnizationById(String id)
			throws LogicLevelException {

		try {
			if(StringUtil.isEmptyString(id)){
				logger.info("orgnization id is required!");
				return;
			}
			Orgnization orgnization = daoSupport.findTById(Orgnization.class, id);
			if(orgnization!=null){
				deleteCardsByOrgnization(orgnization);
				daoSupport.delete(orgnization);
				logger.info("deleteOrgnizationById succeed");
			}else{
				logger.info("deleteOrgnizationById orgnization id is required");
			}
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}
	
	@Override
	public void updateOrgnization(Orgnization orgnization)
			throws LogicLevelException {

		try {
			if(orgnization == null){
				logger.info("orgnization is required!");
				return;
			}
			daoSupport.update(orgnization);
			logger.info("updateOrgnization succeed");
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}
	
	public void setDaoSupport(DaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
	
	private void deleteCardsByOrgnization(Orgnization orgnization)throws LogicLevelException{
		
		try {
			if(orgnization == null){
				logger.info("orgnization is required!");
				return;
			}
			List<Card> cards = findCardsByOrgnization(orgnization);
			for(Card card: cards){
				deleteCardLevelsByCard(card);
			}
			daoSupport.deleteAll(cards);
			logger.info("deleteCardByOrgnization succeed");
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}
	
	private void deleteCardLevelsByCard(Card card)throws LogicLevelException{
		
		try {
			if(card == null){
				logger.info("card is required!");
				return;
			}
			List<CardLevel> cardLevels = findCardLevelsByCard(card);
			daoSupport.deleteAll(cardLevels);
			logger.info("deleteCardLevelsByCard succeed");
		} catch (Throwable e) {
			throw new LogicLevelException(e);
		}
	}

}
