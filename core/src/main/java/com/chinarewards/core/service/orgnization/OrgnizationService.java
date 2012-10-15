package com.chinarewards.core.service.orgnization;


import java.util.List;

import com.chinarewards.core.domain.orgnization.Card;
import com.chinarewards.core.domain.orgnization.CardLevel;
import com.chinarewards.core.domain.orgnization.Orgnization;
import com.chinarewards.core.exception.ServiceLevelException;

public interface OrgnizationService {

	void addOrgnization(Orgnization orgnization) throws ServiceLevelException;

	Orgnization findOrgnizationById(String orgnizationId)throws ServiceLevelException;

	void addCard(Card card)throws ServiceLevelException;

	Card findCardById(String cardId)throws ServiceLevelException;

	void updateCard(Card card)throws ServiceLevelException;

	void deleteCard(Card card)throws ServiceLevelException;

	void addCardLevel(CardLevel cardLevel)throws ServiceLevelException;

	CardLevel findCardLevelById(String cardLevelId)throws ServiceLevelException;

	void updateCardLevel(CardLevel cardLevel)throws ServiceLevelException;

	
	void deleteCardLevel(CardLevel cardLevel)throws ServiceLevelException;

	List<Orgnization> findAllOrgnizations()throws ServiceLevelException;

	List<Card> findAllCards()throws ServiceLevelException;

	List<CardLevel> findAllCardLevels()throws ServiceLevelException;

	List<CardLevel> findCardLevelsByCardId(String cardId)throws ServiceLevelException;

	List<Card> findCardsByOrgnizationId(String orgnizationId)throws ServiceLevelException;
	
}
