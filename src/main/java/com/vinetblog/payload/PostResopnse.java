package com.vinetblog.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostResopnse {
	
	private List<PostDto>content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalpages;
	private boolean lastPage;
	

}
