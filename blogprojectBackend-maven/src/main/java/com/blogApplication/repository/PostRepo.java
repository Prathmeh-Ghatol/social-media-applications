package com.blogApplication.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.entity.Categery;
import com.blogApplication.entity.Post;
import com.blogApplication.entity.User;
import com.blogApplication.playloads.PostDto;

public interface PostRepo extends JpaRepository<Post, Integer> {

	
	Page<Post> findAllByUser(Pageable pageable,User user);
	Page<Post>findByCategery(Pageable pageable, Categery categery );
	
	Page<Post> findByTitleContaining(Pageable pageableS,String title); 
}
