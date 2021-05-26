package com.evolenthealth.contacts.service;

import java.util.List;

import com.evolenthealth.contacts.entity.Contact;

public interface IContactService {

	public List<Contact> getAllContacts();

	public Contact addContact(Contact contact);

	public Contact getContactById(Integer id);

	public Contact updateContactById(String id, Contact contact);

	public Boolean deleteContact(Integer id);

}
