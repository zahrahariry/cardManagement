package com.example.cardmanagement.mapstruct;

import com.example.cardmanagement.model.CardModel;
import com.example.cardmanagement.repository.Card;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardModel convertCardEntityToModel (Card card);

    Card convertCardModelToEntity (CardModel cardModel);

    List<CardModel> convertCardEntityListToModelList (List<Card> cardList);

    List<Card> convertCardModelListToEntityList (List<CardModel> cardModelList);
}
