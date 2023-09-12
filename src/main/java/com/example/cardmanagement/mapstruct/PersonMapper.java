package com.example.cardmanagement.mapstruct;

import com.example.cardmanagement.model.PersonModel;
import com.example.cardmanagement.repository.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person convertPersonModelToEntity (PersonModel personModel);

    PersonModel convertPersonEntityToModel (Person person);

    List<PersonModel> convertPersonEntityListToModelList (List<Person> personList);

    List<Person> convertPersonModelListToEntityList (List<PersonModel> personModelList);

}
