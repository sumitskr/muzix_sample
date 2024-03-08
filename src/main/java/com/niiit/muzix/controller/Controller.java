package com.niiit.muzix.controller;


import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.niiit.muzix.JwtTokenService.SecurityTokenGenerator;
import com.niiit.muzix.exception.FavouriteAlreadyExists;
import com.niiit.muzix.exception.RegistrationValidationException;
import com.niiit.muzix.exception.UserAlreadyExistsException;
import com.niiit.muzix.exception.UserNotFoundException;
import com.niiit.muzix.model.FavouriteModel;
import com.niiit.muzix.model.UserModel;
import com.niiit.muzix.recommended.Object;
import com.niiit.muzix.searchbyname.SearchObjectbyname;
import com.niiit.muzix.serializer.ChangePasswordSerializer;
import com.niiit.muzix.serializer.DeleteFavSerializer;
import com.niiit.muzix.serializer.ForgotPasswordSerializer;
import com.niiit.muzix.serializer.UserLoginSerializer;
import com.niiit.muzix.service.FavouriteService;
import com.niiit.muzix.service.UserService;



@RestController
@RequestMapping(value="/muzix/api/v1/")
@CrossOrigin(origins = "*")

public class Controller {
	
	@Autowired
	private UserService userService;
	private SecurityTokenGenerator securityTokenGenerator;
	private FavouriteService favouriteService;
	
	public Controller( UserService userService , SecurityTokenGenerator securityTokenGenerator,FavouriteService favouriteService) {
		this.userService=userService;
		this.securityTokenGenerator=securityTokenGenerator;
		this.favouriteService=favouriteService;
	}
	
	//get songs
	@GetMapping("/")
	public RedirectView root() {
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/swagger-ui/index.html#/");
		return redirectView; 
	}
	@GetMapping("/recommanded")
	public Object recommanded() {
		System.out.println("recommanded called");
		RestTemplate restTemplate = new RestTemplate();
		String url ="http://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag=love&api_key=299085b3e0df9cc7b6dc15f511efd00d&format=json";	
		Object ob = restTemplate.getForObject(url, Object.class);
		return ob;
	}
	
	@GetMapping("/searchtrack")
	public SearchObjectbyname searchtrack(@RequestParam String searchValue ) {
		RestTemplate restTemplate = new RestTemplate();
		String url;
		url ="http://ws.audioscrobbler.com/2.0/?method=track.search&track="+searchValue+"&api_key=299085b3e0df9cc7b6dc15f511efd00d&format=json";	
		SearchObjectbyname ob = restTemplate.getForObject(url, SearchObjectbyname.class);
		return ob;
	}
	
	//add favourite
	@PostMapping("/authenticated/addfavourite")
	public ResponseEntity<String> addfavourite(@RequestBody FavouriteModel mv) throws FavouriteAlreadyExists{
		favouriteService.saveFav(mv);
		return new ResponseEntity<>(mv.toString(), HttpStatus.CREATED);	
	}
	//get all fav	
	@GetMapping("/authenticated/getfavourite")
	public ResponseEntity<List<FavouriteModel>> getfavourite(@RequestParam String username){
		List<FavouriteModel> ob = null;
		if(favouriteService.findByUsername(username).isEmpty()) {
			return new ResponseEntity<List<FavouriteModel>>(ob,HttpStatus.NOT_FOUND);
			
		}
		
		ob = favouriteService.findByUsername(username);
		return new ResponseEntity<>(ob,HttpStatus.OK);
	}
	//delete fav
	@PostMapping("/authenticated/deletefav")
	public ResponseEntity<String> deleteFav(@RequestBody DeleteFavSerializer track){
		return new ResponseEntity<String>(favouriteService.deleteFav(track),HttpStatus.OK);
		
	}
	
	//user creation
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserModel mv) throws UserAlreadyExistsException,RegistrationValidationException{
		userService.saveUser(mv);
		return new ResponseEntity<>(mv.toString(), HttpStatus.CREATED);
	}
	
	//login
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginSerializer userlogin) throws UserNotFoundException{
		Map<String, String> map = null;
		try {
			UserModel userObj = userService.findByUsernameAndPassword(userlogin.getUsername(), userlogin.getPassword());
			if(userObj.getUsername().equals(userlogin.getUsername())) {
				map = securityTokenGenerator.generateToken(userObj);
			}
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
		} catch (UserNotFoundException e) {
			// TODO: handle exception
			throw new UserNotFoundException();
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Map<String,String>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//change password
	@PostMapping("/authenticated/changepassword")
	public ResponseEntity<UserModel> changepassword(@RequestBody ChangePasswordSerializer mv) throws RegistrationValidationException{
		return new ResponseEntity<>(userService.changePassword(mv),HttpStatus.ACCEPTED);
	}
	//forget password
	@PostMapping("/forgotpassword")
	public ResponseEntity<UserModel> forgotPassword(@RequestBody ForgotPasswordSerializer mv) throws RegistrationValidationException,UserNotFoundException{
		return new ResponseEntity<>(userService.forgotPassword(mv),HttpStatus.ACCEPTED);
	}
}







