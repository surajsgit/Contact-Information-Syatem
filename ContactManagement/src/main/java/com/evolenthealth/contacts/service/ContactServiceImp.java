package com.evolenthealth.contacts.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evolenthealth.contacts.entity.Contact;
import com.evolenthealth.contacts.errorhandler.ContactNotFoundException;
import com.evolenthealth.contacts.repository.IContactRepository;
@Service
public class ContactServiceImp implements IContactService {

	private static Logger LOGGER=Logger.getLogger(ContactServiceImp.class.getCanonicalName());
	
	
	@Autowired
	private IContactRepository repository;
	
	//fetch all contacts details
	@Override
	public List<Contact> getAllContacts() {
		 return repository.findAll();
	}
	//add new contact details
	@Override
	public Contact addContact(Contact contact) {
		
		try{
			contact = repository.save(contact);
		}catch (RuntimeException exception) {
			LOGGER.log(Level.SEVERE, exception.getMessage()+" - "+exception.getStackTrace());
			
		}
		return contact;
	}
	//fetch single record
	@Override
	public Contact getContactById(Integer id) {
		Contact contact = null;
		try {
			contact = repository.findById(id).get();			
		}catch (RuntimeException exception) {
			LOGGER.log(Level.SEVERE, exception.getMessage()+" - "+exception.getStackTrace());
			throw new ContactNotFoundException("contact not found");
		}
		return contact;
	}
	//update existing contact details
	@Override
	public Contact updateContactById(String inputId, Contact contact) {
		int id;
		try {
			id = Integer.parseInt(inputId);			
		}catch (Exception exception) {
			LOGGER.log(Level.WARNING, "INVALID_INPUT_ERROR "+exception.getMessage()+" - "+exception.getStackTrace());
			return null;
		}
		Contact contactUpdated = null;
		int updated = repository.updateContactById(contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getEmail(), contact.getStatus(), id);
		if(updated == 1) {
			contactUpdated = getContactById(id);
		}
		return contactUpdated;
	}
	//delete contact details
	@Override
	public Boolean deleteContact(Integer id) {
		try {
			repository.deleteById(id);			
		}catch (IllegalArgumentException exception) {
			LOGGER.log(Level.WARNING, "INVALID_INPUT_OR_RESOURCE_NOT_FOUND "+exception.getMessage()+" - "+exception.getStackTrace());
			return false;
		}
		return true;
	}

	
}
