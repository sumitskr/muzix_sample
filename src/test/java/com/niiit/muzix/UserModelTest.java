package com.niiit.muzix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.niiit.muzix.model.UserModel;
import com.niiit.muzix.repository.UserRepository;

//@ExtendWith(SpringExtension.class)
@DataMongoTest
class UserModelTest {
	
	@Autowired
	private UserRepository userRepository;
	private UserModel userModel;
	
	@BeforeEach
	public void setUp() {
		userModel = new UserModel("john","john@cena", "john", "cena", "johncena@gmai.com", "2fingers");
	}
	@AfterEach
	public void tearDown() {
		userModel=null;
		userRepository.deleteAll();
	}
	
	@Test
	public void givenUserShouldReturnUser() {
		userRepository.save(userModel);
		UserModel user1 = userRepository.findByUsername(userModel.getUsername());
		assertNotNull(user1);
		assertEquals(userModel.getUsername(), user1.getUsername());
	}

}
