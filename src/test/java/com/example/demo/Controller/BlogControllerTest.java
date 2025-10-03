package com.example.demo.Controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Repository.Blogs;
import com.example.demo.Service.BlogServiceTest;
import com.example.demo.Service.BlogsService;

@WebMvcTest(BlogsController.class)
public class BlogControllerTest {
  @Autowired
  private MockMvc mockMvc;
  private Blogs blog1;
  
  @MockitoBean
  private BlogsService blogsService;
  
  
  @BeforeEach
  void setup()
  {
	  blog1  = new Blogs();
		blog1.setId(1L);
		blog1.setName("tester");
		blog1.setTopic("testing");
		blog1.setContent("tester is testing the service");
		blog1.setDate(LocalDateTime.parse("2025-09-14T16:44:07"));
  }
  
  @Test
  @DisplayName("GET -- /blogs get operation")
  public void getBlogs() throws Exception
  {
	  when(blogsService.getAllBlogs()).thenReturn(new ArrayList<>(List.of(blog1)));
	  
	  mockMvc.perform(get("/blogs"))
	  .andExpect(status().isOk())
	  .andExpect(jsonPath("$[0].name").value("tester"));
	  
  }
  
  @Test
  @DisplayName("Post -- /blogs tetsing")
  public void addBlog() throws Exception
  {
	  var myblog = new Blogs();
	  LocalDateTime datetime = LocalDateTime.parse("2025-09-14T16:44:07");
	  myblog.setId(3l);
	  myblog.setName("Postting");
	  myblog.setTopic("post test");
	  myblog.setDate(datetime);
	  myblog.setContent("hello this is to test the post operation");
  }
  
  @Test
  @DisplayName("GET -- /blogs/Id get the product by the ID")
  public void filterByID() throws Exception
  {
	  when(blogsService.getBlogById(1l)).thenReturn(Optional.of(blog1));
	  
	  mockMvc.perform(get("/blogs/1"))
	  .andExpect(status().isOk())
	  .andExpect(jsonPath("$.name").value("tester"))
	  .andExpect(jsonPath("$.topic").value("testing"));
	  
  }
  
  @Test
  @DisplayName("Delete -- /blogs delete the blog by ID")
  public void deletById() throws Exception
  {
	  Long deleteId = 1L;
//	  doNothing().when(blogsService).deleteBlogsById(deleteId);

	  mockMvc.perform(delete("/blogs/{Id}",deleteId))
	  .andExpect(status().isOk());
	  
	  
	  verify(blogsService).deleteBlogsById(deleteId);
  }
  
  
  
}
