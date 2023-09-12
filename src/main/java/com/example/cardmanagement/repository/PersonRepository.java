package com.example.cardmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository <Person, Long>, JpaRepository <Person, Long> {

    Optional<Person> findAllById (Long id);

    Optional<Person> findAllByNationalId (String nationalId);

    List<Person> findAllByPhoneNumber (String phoneNumber);

    List<Person> findAllByNameAndFamily (String name, String family);
}
