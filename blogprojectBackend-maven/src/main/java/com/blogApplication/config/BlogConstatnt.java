package com.blogApplication.config;

import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component
public class BlogConstatnt {
	
	
	public final  static String pageSize="5";
	public final static String pageNumber="0";
	public final static String  driectionAscending="asc";
	public final static String  driectionDescending="asc";

	public final static  String filePath=new PathResource("/static/Image").getPath();
	
	
}
