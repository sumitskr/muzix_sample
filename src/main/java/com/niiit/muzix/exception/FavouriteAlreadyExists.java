package com.niiit.muzix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "Favourite Already Exists")
public class FavouriteAlreadyExists extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
