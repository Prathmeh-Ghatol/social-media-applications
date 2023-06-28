package com.blogApplication.services;

import java.util.List;

import com.blogApplication.apiresponce.PostResponcePageing;
import com.blogApplication.entity.Post;
import com.blogApplication.playloads.PostDto;

public interface PostService {

	PostDto updatepPost(int post_id, PostDto post);

	PostDto createPost(PostDto post, int user_Id, int categery_Id);

	PostDto getByPostById(int post_id);

	PostResponcePageing getAllPost(int page_no,int page_size,String sort,String SortDiretion);

	void postDeleteById(int post_id);

	void deleteAllPost();

	PostResponcePageing getPostByCategery(int categery_Id ,int Page_size,int Page_No);

	PostResponcePageing getPostByUser(int user_Id,int Page_size,int Page_No);
	
	 PostResponcePageing Search(String KeyWord,int pageNo,int spageSize,String sort, String diretion);
}
	