package com.example.apps.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.apps.model.AddressBook;

@Repository
public interface AddressBookDao extends CrudRepository<AddressBook, Long> {

	List<AddressBook> findByName(String name);
}
