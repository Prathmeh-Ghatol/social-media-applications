package com.blogApplication.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blogApplication.entity.Categery;
import com.blogApplication.entity.Post;
import com.blogApplication.exception.ResourseNotFoundException;
import com.blogApplication.playloads.CategeryDto;
import com.blogApplication.repository.CategeryRepo;
import com.blogApplication.services.CategeryService;

@Component
public class CategeryServiceImpl implements CategeryService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	CategeryRepo categeryRepo;
	
	public CategeryDto createCategery(CategeryDto categeryDto) {
		Categery categery_create=CategeryDto_To_Categery(categeryDto);
		this.categeryRepo.save(categery_create);
		return this.categery_To_CategeryDto(categery_create);
	}

	@Override
	public CategeryDto updateCategery(CategeryDto categeryDto, int categery_Id) {
		
		Categery categery_update=this.categeryRepo.findById(categery_Id).orElseThrow(() -> 
											new ResourseNotFoundException("categery","of",categery_Id));
			categery_update.setCategeryId(categeryDto.getCategeryId());
			categery_update.setCategeryDesc(categeryDto.getCategeryDesc());
			categery_update.setCategeryTitle(categeryDto.getCategeryTitle());
			this.categeryRepo.save(categery_update);
		
			CategeryDto catDto=categery_To_CategeryDto(categery_update);
			return catDto;
			
	}

	@Override
	public List< CategeryDto >getCategery() {
		List<Categery>categery_getAll=this.categeryRepo.findAll();
		 List<CategeryDto>categeryDtos=categery_getAll.stream().map(c -> categery_To_CategeryDto(c)).collect(Collectors.toList());
		 return categeryDtos;
		
		}

	@Override
	public CategeryDto getCategeryById(int categery_id){
		Categery categery=this.categeryRepo.findById(categery_id).orElseThrow(()->new ResourseNotFoundException("categery", "of", categery_id));
		CategeryDto categeryDto=categery_To_CategeryDto(categery);
		return categeryDto;
	}

	@Override

	public void deleteCategeryById(int categery_id) {
	this.categeryRepo.deleteById(categery_id);
		
	}

	@Override
	public void deleteAllCategery() {
		this.categeryRepo.deleteAll();
	}
	
	
	private Categery CategeryDto_To_Categery(CategeryDto categeryDto) {
		Categery categery=this.modelMapper.map(categeryDto,Categery.class);
		return categery;
	}
	
	private CategeryDto categery_To_CategeryDto(Categery categery) {
		CategeryDto categeryDto=this.modelMapper.map(categery, CategeryDto.class);
				return categeryDto;
						
	}
	Post post=new Post();
	

}
