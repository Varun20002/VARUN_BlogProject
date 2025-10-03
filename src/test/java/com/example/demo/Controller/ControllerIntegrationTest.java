package com.example.demo.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import com.example.demo.Repository.Blogs;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test 
	@DisplayName("Integration Test: POST /blogs and GET /blogs/{id}") 
	public void testCreateAndGetBlogById()
	{ 
		Blogs blog = new Blogs(); 
		blog.setName("testing"); 
		blog.setTopic("testing topic"); 
		blog.setDate(LocalDateTime.parse("2025-09-22T00:00:00")); 
		blog.setContent("hey this is the complete empty content"); 
		
		ResponseEntity<Blogs> postResponse = restTemplate.postForEntity("/blogs", blog, Blogs.class); 
		
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED); 
		Blogs created = postResponse.getBody(); 
		
		assertThat(created).isNotNull(); 
		assertThat(created.getId()).isNotNull(); 
		
		Long createdId = created.getId(); 
		
		ResponseEntity<Blogs> getResponse = restTemplate.getForEntity("/blogs/" + createdId, Blogs.class); 
		
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK); 
		
		Blogs fetched = getResponse.getBody(); 
		
		assertThat(fetched).isNotNull(); 
		assertThat(fetched.getId()).isEqualTo(createdId); 
		assertThat(fetched.getName()).isEqualTo("testing"); 
		assertThat(fetched.getTopic()).isEqualTo("testing topic"); 
	}

	@Test 
	@DisplayName("Integration Test: DELETE /blogs/{id}") 
	public void testDeleteBlogById() { Blogs blog = new Blogs(); 
		blog.setName("ToDelete"); 
		blog.setTopic("Delete topic"); 
		blog.setDate(LocalDateTime.parse("2025-09-22T00:00:00")); 
		blog.setContent("This will be deleted"); 
	ResponseEntity<Blogs> postResponse = restTemplate.postForEntity("/blogs", blog, Blogs.class); 
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED); 
	Blogs created = postResponse.getBody(); 
		assertThat(created).isNotNull(); 
	Long blogId = created.getId(); // DELETE request 
		restTemplate.delete("/blogs/" + blogId); 
	ResponseEntity<Blogs> getAfterDelete = restTemplate.getForEntity("/blogs/" + blogId, Blogs.class); 
		assertThat(getAfterDelete.getStatusCode()).isEqualTo(HttpStatus.OK); 
	}

}
