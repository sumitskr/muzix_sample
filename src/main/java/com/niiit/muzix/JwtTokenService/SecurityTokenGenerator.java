package com.niiit.muzix.JwtTokenService;

import java.util.Map;

import com.niiit.muzix.model.UserModel;


public interface SecurityTokenGenerator {
	  Map<String,String> generateToken(UserModel user);

}
