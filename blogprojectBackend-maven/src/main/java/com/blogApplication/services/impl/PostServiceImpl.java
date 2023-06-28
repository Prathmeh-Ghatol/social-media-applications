package com.blogApplication.services.impl;

import 	org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import com.blogApplication.apiresponce.PostResponcePageing;
import com.blogApplication.entity.Categery;
import com.blogApplication.entity.Post;
import com.blogApplication.entity.User;
import com.blogApplication.exception.ResourseNotFoundException;
import com.blogApplication.playloads.PostDto;
import com.blogApplication.repository.CategeryRepo;
import com.blogApplication.repository.PostRepo;
import com.blogApplication.repository.UserRepo;
import com.blogApplication.services.PostService;
@Component
public class PostServiceImpl implements PostService {
	@Autowired
	PostRepo postRepo;
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CategeryRepo categeryRepo;
	
	@Autowired
	UserRepo userRepo;
	@Override
	public PostDto updatepPost(int post_id, PostDto post) {
		// validator
		Post post_update =this.postRepo.findById(post_id).orElseThrow(()->new  ResourseNotFoundException("post","not foud for update", post_id));
		User user=post_update.getUser();			
		Categery categery=post_update.getCategery();
		// Request Post
		
		Post post2=this.PostDto_to_Post(post);
		post2.setPostId(post.getPostId());
		post2.setAddData(new Date());
		post2.setContent(post.getContent());
		post2.setCategery(categery);
		post2.setImageName(post.getImageName());
		post2.setTitle(post.getTitle());
		post2.setUser(user);
		
		this.postRepo.save(post2);
		return this.post_to_PostDto(post2);
	}
	@Override
public	PostDto createPost(PostDto post, int user_Id, int categery_Id) {
		//get user from user-id
		User user=this.userRepo.findById(user_Id).orElseThrow(()->new ResourseNotFoundException("user", "of id", user_Id));
		Categery categery=this.categeryRepo.findById(categery_Id).orElseThrow(()->new ResourseNotFoundException("categery", "of id", categery_Id));
		Post post2=this.PostDto_to_Post(post);
		post2.setUser(user);
		post2.setCategery(categery);
		post2.setAddData(new Date());
		post2.setImageName(null);
		this.postRepo.save(post2);
		return  post_to_PostDto(post2);		
}
	@Override
	public PostDto getByPostById(int post_id) {
		Post post=this.postRepo.findById(post_id).orElseThrow(() ->new ResourseNotFoundException("Post", "ofId", post_id));
		return this.post_to_PostDto(post);
	}
	
	@Override
	public PostResponcePageing getAllPost(int page_No,int page_Size, String sort, String SortingDirection) {
		Sort sorting=null;
		if(SortingDirection.equalsIgnoreCase("asc")) {
		           sorting=sorting.by(sort).ascending();	
		}else if(SortingDirection.equalsIgnoreCase("des")) {
	           sorting=sorting.by(sort).descending();	
	}
		 org.springframework.data.domain.Pageable p= PageRequest.of(page_No, page_Size,sorting);
				Page<Post>post_page=this.postRepo.findAll(p);
	 List<Post>posts=post_page.getContent();
	 
	 List<PostDto>postDtos=posts.stream().map(post->this.post_to_PostDto(post)).collect(Collectors.toList());
	 
	 	PostResponcePageing postResponcePageing=new PostResponcePageing();
	 	postResponcePageing.setContent(postDtos);
	 	postResponcePageing.setLastPage(post_page.isLast());
	 	postResponcePageing.setPage_number(post_page.getNumber());
	 	postResponcePageing.setPage_size(post_page.getSize());
	 	postResponcePageing.setTotalElements(post_page.getTotalElements());
	 	postResponcePageing.setTotalPages(post_page.getTotalPages());
	 	
	 	
	 	return postResponcePageing;
	
	}
	@Override
	public void postDeleteById(int post_id) {
			this.postRepo.deleteById(post_id);
		
	}
	@Override
	public void deleteAllPost() {
		this.postRepo.deleteAll();
		
	}
	@Override
	public PostResponcePageing getPostByCategery( int Page_No  , int pageSize,int categery_Id  ) {
		Categery categery=this.categeryRepo.findById(categery_Id).orElseThrow(()->new ResourseNotFoundException("Categery","of id" ,categery_Id));
		
		org.springframework.data.domain.Pageable  pageable  = PageRequest.of(Page_No,pageSize);
		
		Page<Post>post_Page=this.postRepo.findByCategery(pageable, categery);
		List<Post>posts = post_Page.getContent();
		
		List<PostDto>postDtos=posts.stream().map(post->this.post_to_PostDto(post)).collect(Collectors.toList());
		
		PostResponcePageing postResponcePageing=new PostResponcePageing();
	 	postResponcePageing.setContent(postDtos);
	 	postResponcePageing.setLastPage(post_Page.isLast());
	 	postResponcePageing.setPage_number(post_Page.getNumber());
	 	postResponcePageing.setPage_size(post_Page.getSize());
	 	postResponcePageing.setTotalElements(post_Page.getTotalElements());
	 	postResponcePageing.setTotalPages(post_Page.getTotalPages());
	 	
		
		return postResponcePageing;
				
	}
	@Override
	public PostResponcePageing getPostByUser(int user_Id,int Page_No,int Page_size) {
			User user=this.userRepo.findById(user_Id).orElseThrow(()->new ResourseNotFoundException("user","of",user_Id));
			Pageable pageable=PageRequest.of(Page_No, Page_size);
			Page<Post>page_post=this.postRepo.findAllByUser(pageable,user);
			List<Post>posts=page_post.getContent();
			List<PostDto>postDtos=posts.stream().map(post -> this.post_to_PostDto(post)).collect(Collectors.toList());
			
			PostResponcePageing postResponcePageing=new PostResponcePageing();
			postResponcePageing.setContent(postDtos);
			postResponcePageing.setLastPage(page_post.isLast());
			postResponcePageing.setPage_number(page_post.getNumber());
			postResponcePageing.setPage_size(page_post.getSize());
			postResponcePageing.setTotalElements(page_post.getTotalElements());
			postResponcePageing.setTotalPages(page_post.getTotalPages());
			
			return postResponcePageing;

			
	}
	
	
	/////////////////////////////////////////////////////mapper////////////////////////
	private Post PostDto_to_Post(PostDto postDto) {
		Post post=this.modelMapper.map(postDto, Post.class);
		return post;
	}
	
  private	PostDto post_to_PostDto(Post post) {
		PostDto post2=this.modelMapper.map(post, PostDto.class);
		return post2;
	}
  
@Override
public PostResponcePageing Search(String KeyWord,int pageNo,int pageSize,String sort, String diretion) {
	Sort sorting=null;
	if(diretion.equalsIgnoreCase("asc")) {
	           sorting=sorting.by(sort).ascending();	
	}else if(diretion.equalsIgnoreCase("des")) {
           sorting=sorting.by(sort).descending();	
}
	Pageable pageable=PageRequest.of(pageNo, pageSize, sorting);
	Page<Post>page_post=this.postRepo.findByTitleContaining(pageable,KeyWord );
	List<Post>posts=page_post.getContent();
	
	List<PostDto>post=posts.stream().map(p-> post_to_PostDto(p)).collect(Collectors.toList());
	PostResponcePageing pageing=new PostResponcePageing();
	pageing.setContent(post);
	pageing.setLastPage(page_post.isLast());
	pageing.setPage_number(page_post.getNumber());
	pageing.setPage_size(page_post.getSize());
	pageing.setTotalElements(page_post.getTotalElements());
	pageing.setTotalPages(page_post.getTotalPages());
	
	return pageing;
	
	}
}
