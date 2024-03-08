package com.niiit.muzix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niiit.muzix.exception.FavouriteAlreadyExists;
import com.niiit.muzix.model.FavouriteModel;
import com.niiit.muzix.repository.FavouriteRepositiry;
import com.niiit.muzix.serializer.DeleteFavSerializer;

@Service
public class FavouriteServiceImpl implements FavouriteService{

	@Autowired 
	private FavouriteRepositiry favrepo;
	
	@Override
	public FavouriteModel saveFav(FavouriteModel track) throws FavouriteAlreadyExists{
		// TODO Auto-generated method stub
		if(favrepo.findByUsernameAndUrl(track.getUsername(),track.getUrl())!=null){
			throw new FavouriteAlreadyExists();
		}
		return favrepo.save(track);		
	}

	@Override
	public List<FavouriteModel> findByUsername(String username) {
		// TODO Auto-generated method stub
		return favrepo.findByUsername(username);
	}

	@Override
	public String deleteFav(DeleteFavSerializer track) {
		// TODO Auto-generated method stub
		favrepo.deleteByUsernameAndUrl(track.getUsername(), track.getUrl());
		return track.toString()+"Deleted";
	}

	

}
