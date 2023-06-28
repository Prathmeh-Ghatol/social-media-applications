package com.blogApplication.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogApplication.apiresponce.ApiResponce;
import com.blogApplication.apiresponce.PostResponcePageing;
import com.blogApplication.config.BlogConstatnt;
import com.blogApplication.entity.Post;
import com.blogApplication.fileHandeling.impl.FileHandeler;
import com.blogApplication.playloads.PostDto;
import com.blogApplication.services.impl.PostServiceImpl;

@RestController
@RequestMapping("api/post/")
public class PostController {
	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@Autowired
	FileHandeler fileHandeler;
	@Autowired
	private BlogConstatnt blogConstatnt;
	@Value("${project.image}")
	private String Path ;
	
	@PostMapping("create/user/{user_id}/categery/{categery_id}/post")
	public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto,
			@PathVariable("user_id") int user_Id 
			,@PathVariable("categery_id")int catagery_Id ){
		PostDto  posDto= this.postServiceImpl.createPost(postDto, user_Id, catagery_Id);
		return new ResponseEntity<PostDto>(posDto, HttpStatus.CREATED);		
	}
	
	@GetMapping("getpost/categery/{cat_id}")
	public ResponseEntity<PostResponcePageing>getPostByCategery(@PathVariable("cat_id") int cat_id,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false)int pageNo,
			@RequestParam(value = "pageSize" ,defaultValue = "5", required = false )int pageSize){
		
			PostResponcePageing postResponcePageing =this.postServiceImpl.getPostByCategery(pageNo,pageSize,cat_id);
		return new ResponseEntity<PostResponcePageing>(postResponcePageing,HttpStatus.OK);
	}
	
	@GetMapping("getuserpost/user/{user_id}")
	public ResponseEntity<PostResponcePageing> getPostByuser(@PathVariable("user_id") int user_Id,
	@RequestParam(value="pageNumber",defaultValue = "0",required = false)int pageNumber,
	@RequestParam(value = "pageSize",defaultValue = "5",required = false)int pageSize){
		PostResponcePageing pageing= this.postServiceImpl.getPostByUser(user_Id, pageNumber, pageSize);
		return new ResponseEntity<PostResponcePageing>(pageing,HttpStatus.OK);	
	}
	
	@GetMapping("getpost/post/{post_id}")
	public ResponseEntity<PostDto>getPostByPost_Id(@PathVariable("post_id") int post_Id){
			PostDto postDto=this.postServiceImpl.getByPostById(post_Id);
			return new ResponseEntity<PostDto>(postDto ,HttpStatus.OK);
	}
	
	@GetMapping("getall/post")
	public ResponseEntity< PostResponcePageing>getAllPOst(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false)int pageNo,
			@RequestParam(value = "pageSize" ,defaultValue = "5", required = false) int pageSize,
			@RequestParam(value="sorting", defaultValue = "postId",required = false)String Sort,
			@RequestParam(value="direction", defaultValue = "asc", required = false)String direction
			){
		PostResponcePageing postList=this.postServiceImpl.getAllPost(pageNo,pageSize,Sort,direction);
		return new ResponseEntity<PostResponcePageing> (postList,HttpStatus.OK);
	}
	
	@PutMapping("update/post/{post_id}")
	public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable("post_id") int post_Id ){
		PostDto pDto=this.postServiceImpl.updatepPost(post_Id, postDto);
		return new ResponseEntity<PostDto>(pDto,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("delete/post/{id}")
	public ResponseEntity<ApiResponce>deleteById( @PathVariable("id") int post_ID){
		this.postServiceImpl.postDeleteById(post_ID);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Deleted" + post_ID ,true),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/search/{search}")
	public ResponseEntity<PostResponcePageing>Serch(@PathVariable("search") String keyword,
			@RequestParam(value = "pageNumber" ,defaultValue =BlogConstatnt.pageNumber,required = false) int page_No,
			@RequestParam(value = "pageSize",defaultValue =BlogConstatnt.pageSize ,required = false )int page_Size,
			@RequestParam(value = "sorting",defaultValue = "title",required = false)String sort,
			@RequestParam(value="direction",defaultValue= BlogConstatnt.driectionAscending, required = false)String direction){
		    PostResponcePageing post_Pagining=this.postServiceImpl.Search(keyword, page_No, page_Size, sort, direction);
		    
		   return new ResponseEntity<PostResponcePageing>(post_Pagining, HttpStatus.OK);
	}
	
	@PostMapping("/uploadFile/postId/{postID}")
	public ResponseEntity<PostDto> uploadFile(@RequestParam(value ="file") MultipartFile file, @PathVariable("postID")
		int postId)throws IOException {
		PostDto postDto=this.postServiceImpl.getByPostById(postId);
			String FileName = this.fileHandeler.uploadFile(Path, file);
				postDto.setImageName(file.getOriginalFilename());
			PostDto postDto2=this.postServiceImpl.updatepPost(postId, postDto);
			return new ResponseEntity<PostDto>(postDto2,HttpStatus.OK);

		
	}
	
	
	
			
		
}
	
	
