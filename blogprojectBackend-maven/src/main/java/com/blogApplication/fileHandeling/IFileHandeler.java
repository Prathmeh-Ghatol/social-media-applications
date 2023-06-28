package com.blogApplication.fileHandeling;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface  IFileHandeler {
	String uploadFile(String Path,MultipartFile file)throws IOException;
	 
	
	
	

}
