package com.evolenthealth.contacts.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.evolenthealth.contacts.entity.Contact;
@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer>{

	@Modifying
	@Transactional
	@Query(value = "UPDATE Contact_info c SET c.first_name = (:firstName), c.last_name = (:lastName), c.phone_number = (:phoneNumber), c.email = (:email), c.status = (:status) WHERE c.id = (:id);",
					nativeQuery = true)
	int updateContactById(
			@Param("firstName") String firstName,
			@Param("lastName") String lastName,
			@Param("phoneNumber") Long phoneNumber,
			@Param("email") String email,
			@Param("status") String status,
			@Param("id") Integer id			
			);
}
