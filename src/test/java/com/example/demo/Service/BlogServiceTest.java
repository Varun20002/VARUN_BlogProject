package com.example.demo.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.Repository.BlogRepository;
import com.example.demo.Repository.Blogs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {

	@Mock
	BlogRepository blogRepository;
	
	@InjectMocks
	BlogsService blogsService;
	
	Blogs blog1;
	Blogs blog2;
	
	@BeforeEach
	public void setup()
	{
		blog1  = new Blogs();
		blog1.setId(1L);
		blog1.setName("tester");
		blog1.setTopic("testing");
		blog1.setContent("tester is testing the service");
		blog1.setDate(LocalDateTime.parse("2025-09-14T16:44:07"));
		
		
		blog2  = new Blogs();
		blog2.setId(1L);
		blog2.setName("tester2 ");
		blog2.setTopic("testing2");
		blog2.setContent("tester is testing the service2");
		blog2.setDate(LocalDateTime.parse("2025-09-14T16:44:07"));
		
	}
	
	@Test
	@DisplayName("Test getting all blogs")
	public void getBlogs()
	{
		when(blogRepository.findAll()).thenReturn(new ArrayList<>(List.of(blog1,blog2)));
		List<Blogs> blogList = blogsService.getAllBlogs();
		
		LocalDateTime expectedtime = LocalDateTime.parse("2025-09-14T16:44:07");
		
		assertThat(blogList).isNotNull();
		assertThat(blogList.size()).isEqualTo(2);
		assertThat(blogList.get(0).getName()).isEqualTo("tester");
		assertThat(blogList.get(0).getTopic()).isEqualTo("testing");
		assertThat(blogList.get(0).getContent()).isEqualTo("tester is testing the service");
		assertThat(blogList.get(0).getDate()).isEqualTo(expectedtime);
		
	}
	
	@Test
	@DisplayName("testing adding the post operation")
	public void addBlog()
	{
		
		Blogs blog1 = new Blogs();
		blog1.setId(1L);
		blog1.setName("Addition");
		blog1.setTopic("Adding");
		blog1.setContent("Adding operation Test");
		blog1.setDate(LocalDateTime.parse("2025-09-14T16:44:07"));
		 LocalDateTime expected = LocalDateTime.parse("2025-09-14T16:44:07");
		when(blogRepository.save(blog1)).thenReturn(blog1);
		
		var saved = blogsService.addBlog(blog1);
		
		assertThat(saved).isNotNull();
		assertThat(saved.getId()).isEqualTo(1L);
		assertThat(saved.getName()).isEqualTo("Addition");
		assertThat(saved.getTopic()).isEqualTo("Adding");
		assertThat(saved.getContent()).isEqualTo("Adding operation Test");
		assertThat(saved.getDate()).isEqualTo(expected);
		
	}
	
	@Test
	@DisplayName("testing get blog by ID operation")
	public void getByID()
	{
		when(blogRepository.findById(1l)).thenReturn(Optional.of(blog1));
		
		var foundBlog = blogsService.getBlogById(1l);
		assertThat(foundBlog).isPresent();
		assertThat(foundBlog.get().getName()).isEqualTo("tester");
		assertThat(foundBlog.get().getTopic()).isEqualTo("testing");
	}
	
	@Test 
	@DisplayName("testing the delete project")
	public void deleteBlog()
	{
		Long blogId = 2L;
		
		blogsService.deleteBlogsById(blogId);
		
		verify(blogRepository,times(1)).deleteById(blogId);
	}
}
