package com.blogApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.entity.Comment;
import com.blogApplication.entity.Post;
import com.blogApplication.entity.User;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
	List<Comment> findAllByUser(User user);
	List<Comment> findAllBypost(Post post);
	
}
