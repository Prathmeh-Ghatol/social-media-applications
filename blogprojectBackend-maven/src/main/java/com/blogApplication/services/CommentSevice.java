package com.blogApplication.services;

import java.util.List;

import com.blogApplication.playloads.CommentDto;

public interface CommentSevice {



		 CommentDto createComment( CommentDto commentDto ,int user_Id,int post_Id);
		 void deleteCommentDto(int comment_Id); 
		 List<CommentDto> getAllcommentByUser(int user_Id);
		 List<CommentDto> getAllCommentsOfPost(int post_Id);
		 CommentDto updateComment(CommentDto commentDto, int commentId);
}
