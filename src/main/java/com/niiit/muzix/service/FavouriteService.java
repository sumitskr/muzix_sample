package com.niiit.muzix.service;

import java.util.List;

import com.niiit.muzix.exception.FavouriteAlreadyExists;
import com.niiit.muzix.model.FavouriteModel;
import com.niiit.muzix.serializer.DeleteFavSerializer;

public interface FavouriteService {
	public FavouriteModel saveFav(FavouriteModel track) throws FavouriteAlreadyExists;
	public List<FavouriteModel> findByUsername(String username);
	public String deleteFav(DeleteFavSerializer track);
	
}
