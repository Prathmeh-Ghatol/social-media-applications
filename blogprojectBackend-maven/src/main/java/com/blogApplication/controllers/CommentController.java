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
import com.blogApplication.playloads.CommentDto;
import com.blogApplication.services.CommentSevice;
import com.blogApplication.services.impl.CommentServiceImpl;

@RestController
@RequestMapping("api/comment/")
public class CommentController {
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	
	@PostMapping("create/postId/{postId}/UserId/{userId}")
	public ResponseEntity<CommentDto> CreatesComment(@PathVariable("postId")  int postId, 
												@PathVariable("userId") int UserId,
												@RequestBody CommentDto commentDto) {
				CommentDto commentDto2 =this.commentServiceImpl.createComment(commentDto, UserId,postId);
				return new ResponseEntity<CommentDto>(commentDto,HttpStatus.CREATED);
			}
	
	@GetMapping("post/userId/{user_Id}")
	public ResponseEntity<List<CommentDto>>getPostByUser(@PathVariable("user_Id") int userId){
		List<CommentDto> commentDto=this.commentServiceImpl.getAllcommentByUser(userId);
		return new ResponseEntity<List <CommentDto>> (commentDto,HttpStatus.OK);
		
	}
	
	@GetMapping("post/{post_Id}")
	public ResponseEntity<List <CommentDto>>getAllCommentByPost(@PathVariable ("post_Id") int post_id){
		List<CommentDto>allPost=this.commentServiceImpl.getAllCommentsOfPost(post_id);
		return new ResponseEntity<List<CommentDto>>(allPost,HttpStatus.OK);
	}
	
	@PutMapping("update/comment/{comment_Id}")
	public ResponseEntity<CommentDto>updateComment(@RequestBody CommentDto commentDto ,@PathVariable int comment_Id){
	
				CommentDto commentDto2=this.commentServiceImpl.updateComment(commentDto,comment_Id );
				return new ResponseEntity<CommentDto> (commentDto2,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("delete/comment/{comment_Id}")
	public ResponseEntity<ApiResponce>deleteComment(@PathVariable("comment_Id") int commentid ){
		this.commentServiceImpl.deleteCommentDto(commentid);
		return new ResponseEntity<ApiResponce>(new ApiResponce("comment deleted" ,true), HttpStatus.OK);
	}

}
