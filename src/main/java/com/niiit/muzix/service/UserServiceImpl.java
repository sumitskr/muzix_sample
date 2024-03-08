package com.niiit.muzix.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niiit.muzix.exception.RegistrationValidationException;
import com.niiit.muzix.exception.UserAlreadyExistsException;
import com.niiit.muzix.exception.UserNotFoundException;
import com.niiit.muzix.model.UserModel;
import com.niiit.muzix.repository.UserRepository;
import com.niiit.muzix.serializer.ChangePasswordSerializer;
import com.niiit.muzix.serializer.ForgotPasswordSerializer;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userrepo;
	
	@Override
	public UserModel saveUser(UserModel user) throws UserAlreadyExistsException,RegistrationValidationException{
		// TODO Auto-generated method stub
		if(userrepo.existsById(user.getUsername())==true) {
			throw new UserAlreadyExistsException();
		
		}
		else if (user.getUsername().length()<8 ||
				 user.getFirstname().length()<3 ||
				 user.getLastname().length()<3 ||
				 user.getPassword().length()<8 ||
				 user.getSecretanswer().length()<8 ||
				 user.getEmail().length()<8) {
			throw new RegistrationValidationException();
		}
		return userrepo.save(user);
	}

	@Override
	public UserModel findByUsernameAndPassword(String username, String password) throws UserNotFoundException{
		// TODO Auto-generated method stub
		UserModel user = userrepo.findByUsernameAndPassword(username, password);
		if(user==null) {
			throw new UserNotFoundException();
		}
		return user;

	}

	@Override
	public UserModel changePassword(ChangePasswordSerializer user1) throws RegistrationValidationException{
		// TODO Auto-generated method stub
		UserModel user = userrepo.findByUsernameAndPassword(user1.getUsername(),user1.getOldpassword());
		user.setPassword(user1.getNewpassword());
		return userrepo.save(user);
	}

	@Override
	public UserModel forgotPassword(ForgotPasswordSerializer user1) throws RegistrationValidationException,UserNotFoundException{
		// TODO Auto-generated method stub
		UserModel user = userrepo.findByUsernameAndSecretanswer(user1.getUsername(), user1.getSecretanswer());
		if(user==null ) {
			throw new UserNotFoundException();
		}
		else if((user1.getPassword().length() <8 || user1.getPassword().length() <8) && user !=null ) {
			throw new RegistrationValidationException();
		}
		user.setPassword(user1.getPassword());
		return userrepo.save(user);
	}



	
}
