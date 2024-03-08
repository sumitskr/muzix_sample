package com.niiit.muzix.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.niiit.muzix.exception.RegistrationValidationException;
import com.niiit.muzix.exception.UserNotFoundException;
import com.niiit.muzix.model.UserModel;



public interface UserRepository extends MongoRepository<UserModel, String>{
	public UserModel findByUsernameAndPassword(String username,String password);
	public UserModel findByUsername(String username);
	public UserModel findByUsernameAndSecretanswer(String username,String secretanswer) throws UserNotFoundException,RegistrationValidationException;
}
