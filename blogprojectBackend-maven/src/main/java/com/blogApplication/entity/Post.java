package com.blogApplication.entity;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@Column(name ="post_title" ,nullable = false)
	private String title;
	
	@Column(name="post_Content")
	private String content;
	
	@Column(name="image")
	private String imageName;
	 
	
	 java.util.Date addData;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch =FetchType.EAGER )
	@JoinColumn(name="user_id", referencedColumnName = "userId")
	User user;


	@ManyToOne(cascade = CascadeType.ALL,fetch =FetchType.EAGER )
	@JoinColumn(name="categery_Id", referencedColumnName = "cat_id")
	private Categery categery;
	
	@OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
 	@JsonBackReference
	Set<Comment>comments=new HashSet<>();
	
	
	}

