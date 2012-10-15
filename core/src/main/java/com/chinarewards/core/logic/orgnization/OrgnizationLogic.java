package com.chinarewards.core.logic.orgnization;

import java.util.List;

import com.chinarewards.core.domain.orgnization.Card;
import com.chinarewards.core.domain.orgnization.CardLevel;
import com.chinarewards.core.domain.orgnization.Orgnization;
import com.chinarewards.core.exception.LogicLevelException;
/**
 * 
 * @author weishengshui
 *
 */
public interface OrgnizationLogic {

	public <T> List<T> findAll(Class<T> c) throws LogicLevelException;

	public Orgnization findOrgnizationByName(String name)
			throws LogicLevelException;

	public Orgnization createOrgnization(Orgnization orgnization)
			throws LogicLevelException;

	public Orgnization findOrgnizationById(String id)
			throws LogicLevelException;

	public Card findCardById(String id) throws LogicLevelException;

	public void createCard(Card card) throws LogicLevelException;

	public void updateCard(Card card) throws LogicLevelException;

	public void deleteCardById(String id) throws LogicLevelException;

	public List<Card> findCardsByOrgnization(Orgnization orgnization)
			throws LogicLevelException;

	public void createCardLevel(CardLevel cardLevel) throws LogicLevelException;

	public void updateCardLevel(CardLevel cardLevel) throws LogicLevelException;

	public CardLevel findCardLevelById(String id) throws LogicLevelException;

	public void deleteCardLevelById(String id) throws LogicLevelException;

	public List<CardLevel> findCardLevelsByCard(Card card)
			throws LogicLevelException;
	
	public void updateOrgnization(Orgnization orgnization) throws LogicLevelException;
	
	public void deleteOrgnizationById(String id) throws LogicLevelException;
	

}
