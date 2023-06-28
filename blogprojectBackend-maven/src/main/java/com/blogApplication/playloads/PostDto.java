package com.blogApplication.playloads;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.blogApplication.entity.Categery;
import com.blogApplication.entity.Comment;
import com.blogApplication.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
private int postId;
	
	private String title;
	
	
	private String imageName;
	 
	//private Date addData;
	
	private User user;
	
	private Categery categery;
	
	private String content;
	
	private Set<Comment>comments=new HashSet<>();
	

		
	}

