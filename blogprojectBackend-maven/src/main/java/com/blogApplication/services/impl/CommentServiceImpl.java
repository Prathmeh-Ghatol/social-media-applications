package com.blogApplication.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blogApplication.entity.Comment;
import com.blogApplication.entity.Post;
import com.blogApplication.entity.User;
import com.blogApplication.exception.ResourseNotFoundException;
import com.blogApplication.playloads.CommentDto;
import com.blogApplication.repository.CommentRepo;
import com.blogApplication.repository.PostRepo;
import com.blogApplication.repository.UserRepo;
import com.blogApplication.services.CommentSevice;
@Component
public class CommentServiceImpl implements CommentSevice{
	@Autowired
	ModelMapper mapper;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Override
	public CommentDto createComment(CommentDto commentDto, int user_Id, int post_Id) {
			
		Comment comment=this.commentDto_To_Comment(commentDto);
		User user=this.userRepo.findById(user_Id).orElseThrow( ()-> new ResourseNotFoundException("user", "of", user_Id));
		Post post=this.postRepo.findById(post_Id).orElseThrow( ()-> new ResourseNotFoundException("user", "of", post_Id));
		comment.setPost(post);
		comment.setUser(user);
		comment.setDate(new Date());
		System.out.print(comment.getComment());
		
		this.commentRepo.save(comment);
		 
		CommentDto commentDto2=this.comment_To_commentDto(comment);
		return commentDto2;

	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, int commentId) {
		Comment  comment=this.commentRepo.findById(commentId).orElseThrow(() -> new ResourseNotFoundException("comment", "of id", commentId));
			User user=comment.getUser();
			Post post=comment.getPost();
			
			Comment comment2=this.commentDto_To_Comment(commentDto);
			comment2.setComment(commentDto.getComment());
			comment2.setCommentId(commentId);
			comment2.setDate(new Date());
			comment.setPost(post);
			comment.setUser(user);
			return this.comment_To_commentDto(comment2);
		
	}
	

	@Override
	public void deleteCommentDto(int comment_Id) {
			this.commentRepo.deleteById(comment_Id);
		
	}

	@Override
	public List<CommentDto> getAllcommentByUser(int user_Id) {
		User user = this.userRepo.findById(user_Id).orElseThrow(()->new ResourseNotFoundException("user", "of_id", user_Id));
		List<Comment>allComment=this.commentRepo.findAllByUser(user);
		List<CommentDto>allCommentDtos=allComment.stream().map(comment->this.comment_To_commentDto(comment)).collect(Collectors.toList());
		return  allCommentDtos;
		
	}

	@Override
	public List<CommentDto> getAllCommentsOfPost(int PostId) {
		Post post=this.postRepo.findById(PostId).orElseThrow(()->new ResourseNotFoundException("post", "of id", PostId));
		List<Comment>comments=this.commentRepo.findAllBypost(post);
		List<CommentDto>allCommentDtos=comments.stream().map(comment->this.comment_To_commentDto(comment)).collect(Collectors.toList());
		return allCommentDtos;
	
	}
//-----------------------------------Model-Mapper ----------------- //
	
	CommentDto comment_To_commentDto(Comment comment) {
		 CommentDto commentDto = this.mapper.map(comment, CommentDto.class);
		return commentDto;
	}
	Comment commentDto_To_Comment(CommentDto commentDto) {
		Comment comment =this.mapper.map(commentDto, Comment.class);
		return comment;
	}

	
	
	
	
}
