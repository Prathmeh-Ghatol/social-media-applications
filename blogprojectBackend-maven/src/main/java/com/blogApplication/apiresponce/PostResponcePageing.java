package com.blogApplication.apiresponce;

import java.util.List;
import com.blogApplication.playloads.PostDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponcePageing {
	
	private List<PostDto>content;
	private int page_number;
	private int page_size;
	private Long totalElements;
	private int totalPages;
	boolean isLastPage;

}
