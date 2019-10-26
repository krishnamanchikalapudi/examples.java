package com.example.apps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.apps.dao.AddressBookDao;
import com.example.apps.model.AddressBook;

@Service
public class AddressBookService {

	@Autowired
	private final AddressBookDao addressbookDao;

	public AddressBookService(final AddressBookDao userRepository) {
		this.addressbookDao = userRepository;
	}

	public CrudRepository<AddressBook, Long> getRepository() {
		return this.addressbookDao;
	}

	public List<AddressBook> findByName(String name) {
		return this.addressbookDao.findByName(name);
	}
}
