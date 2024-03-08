package com.niiit.muzix.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.niiit.muzix.model.FavouriteModel;

public interface FavouriteRepositiry extends MongoRepository<FavouriteModel, String>{
	public List<FavouriteModel> findByUsername(String username);
	public FavouriteModel findByUsernameAndUrl(String username,String url);
	public FavouriteModel deleteByUsernameAndUrl(String username,String url);
	
	
}
