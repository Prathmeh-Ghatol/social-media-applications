package com.blogApplication.services;

import java.util.List;

import com.blogApplication.playloads.CategeryDto;

public interface CategeryService {
	//create--post
	CategeryDto createCategery(CategeryDto categeryDto);
	
	
	// update--put
	CategeryDto updateCategery(CategeryDto categeryDto,int categery_Id);

	
	//get--get

	 List< CategeryDto >getCategery(); 
			
	
	// get --get (id)
	CategeryDto getCategeryById(int  categery_id);
	
	
	//delete-delete(id)
	
	void deleteCategeryById(int categery_id);

	// delete--delete

	
	void deleteAllCategery();
}
