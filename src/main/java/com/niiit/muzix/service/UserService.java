package com.niiit.muzix.service;

import com.niiit.muzix.exception.RegistrationValidationException;
import com.niiit.muzix.exception.UserAlreadyExistsException;
import com.niiit.muzix.exception.UserNotFoundException;
import com.niiit.muzix.model.UserModel;
import com.niiit.muzix.serializer.ChangePasswordSerializer;
import com.niiit.muzix.serializer.ForgotPasswordSerializer;

public interface UserService {
	public UserModel saveUser(UserModel user) throws UserAlreadyExistsException, RegistrationValidationException;
	public UserModel findByUsernameAndPassword(String username , String password) throws UserNotFoundException,RegistrationValidationException;
	public UserModel changePassword(ChangePasswordSerializer user) throws RegistrationValidationException;
//	public UserModel findByUsernameAndSecretanswer(String username,String secretanswer) throws UserNotFoundException,RegistrationValidationException;
	public UserModel forgotPassword(ForgotPasswordSerializer user) throws RegistrationValidationException,UserNotFoundException;
}
