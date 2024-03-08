package com.niiit.muzix.JwtTokenService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.niiit.muzix.model.UserModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator{

	@Override
	public Map<String, String> generateToken(UserModel user) {
		// TODO Auto-generated method stub
		String jwtToken = null;
	    jwtToken = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + 3000000))
	      .signWith(SignatureAlgorithm.HS256,"secretkey").compact();
//	    public String getUserNameFromJwtToken(String token) {
//	        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
//	      }
	    Map<String,String> map = new HashMap<>();
	    map.put("token",jwtToken);
	    map.put("message", "User Successfully logged in");
	    map.put("username",user.getUsername());
	    return map;

	}

}
