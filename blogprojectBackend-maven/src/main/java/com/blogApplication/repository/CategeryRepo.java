package com.blogApplication.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.entity.Categery;

public interface CategeryRepo extends JpaRepository<Categery, Integer>{
	

}
