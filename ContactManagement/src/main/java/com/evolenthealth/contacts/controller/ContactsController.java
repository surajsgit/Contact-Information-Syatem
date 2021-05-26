package com.evolenthealth.contacts.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evolenthealth.contacts.entity.Contact;
import com.evolenthealth.contacts.service.IContactService;

@RestController
@RequestMapping("/api/contacts")
public class ContactsController {
	
	@Autowired
	private IContactService service;
	
	@GetMapping
	public List<Contact> getContactInfo() {
		return service.getAllContacts();
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> addContacts(@RequestBody Contact contact) {
		Contact resContact = service.addContact(contact);
		Map<String, Object> resMap = new LinkedHashMap<>();
		if(contact == null) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}else {
			resMap.put("timestamp", new Date());
			resMap.put("created",resContact);
			return new ResponseEntity<Object>(resMap, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/{id}")
	public Contact getContactById(@PathVariable String id) {
		return service.getContactById(Integer.valueOf(id));		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateContact(@PathVariable String id, @RequestBody Contact contact) {
		Map<String, Object> resMap = new LinkedHashMap<>();
		Contact resContact = service.updateContactById(id, contact);
		if(resContact == null) {
			resMap.put("timestamp", new Date().toString() );
			resMap.put("error", "invalid input");
			return new ResponseEntity<>(resMap,HttpStatus.BAD_REQUEST);
		}
		Contact contactUpdated = service.updateContactById(id, contact);
		resMap.put("status", "updated");
		resMap.put("updatedata", contactUpdated);
		return new ResponseEntity<>(resMap, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteContact(@PathVariable String id) {
		Boolean isDeleted = service.deleteContact(Integer.valueOf(id));
		if(isDeleted) {
			return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
