package com.blogApplication.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int CommentId;
	
	
	private String comment;
	
	@ManyToOne()
	@JoinColumn(name="user_id", referencedColumnName = "userId")
	private User user;
	
	@ManyToOne()
	@JoinColumn(name="post_forginKey", referencedColumnName="postId")
	private Post post;
	
	private Date date;
		
	}

