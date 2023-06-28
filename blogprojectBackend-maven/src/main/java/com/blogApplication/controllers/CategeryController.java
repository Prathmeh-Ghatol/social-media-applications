package com.blogApplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.apiresponce.ApiResponce;
import com.blogApplication.playloads.CategeryDto;
import com.blogApplication.services.impl.CategeryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categery")
public class CategeryController {
	
	@Autowired
	private CategeryServiceImpl categeryServiceImpl;
	
	@GetMapping("/")
	public    ResponseEntity<List<CategeryDto>> getAllCategery() {
		List<CategeryDto>categeryDtos=categeryServiceImpl.getCategery();
		return new ResponseEntity<List<CategeryDto>>(categeryDtos,HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategeryDto>getCategeryById(@PathVariable("id") int categery_Id){
		CategeryDto categeryDto=this.categeryServiceImpl.getCategeryById(categery_Id);
		return new ResponseEntity<CategeryDto>(categeryDto,HttpStatus.FOUND);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategeryDto> updateCategery(@PathVariable("id") int id ,@Valid @RequestBody CategeryDto categeryDto){
		CategeryDto categeryDto2=this.categeryServiceImpl.updateCategery(categeryDto, id);
		return new ResponseEntity<CategeryDto>(categeryDto,HttpStatus.OK);
		
	}
	@PostMapping("/")
	public ResponseEntity<CategeryDto>createCategery(@Valid @RequestBody CategeryDto categeryDto){
		
		CategeryDto categeryDto2=this.categeryServiceImpl.createCategery(categeryDto);
		return new ResponseEntity<CategeryDto>(categeryDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponce>deleteCategeryByid(@PathVariable("id")  int categery_Id){
		this.categeryServiceImpl.deleteCategeryById(categery_Id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("categery deleted",true), HttpStatus.ACCEPTED);
		
	}

		
}

