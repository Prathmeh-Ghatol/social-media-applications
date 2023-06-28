package com.blogApplication.playloads;

import java.util.Date;

import com.blogApplication.entity.Post;
import com.blogApplication.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	private int CommentId;
	private String comment;

	//private User user;
//	private Post post;
//		
//		private Date date;

}
