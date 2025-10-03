package com.example.demo.Repository;



import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class BlogRepositoryTest {

	@Autowired
	
	private BlogRepository blogRepository;
	
	@Autowired 
	private TestEntityManager entityManager;
	
	@Test
	@DisplayName("Integration test save Blog")
	public void saveBlof() {
		Blogs blog1 = new Blogs();
		
//		blog1.setId(1L);
		blog1.setName("tester");
		blog1.setTopic("testing");
		blog1.setContent("tester is testing the service");
		blog1.setDate(LocalDateTime.parse("2025-09-14T16:44:07"));
		
		
		Blogs saved = blogRepository.save(blog1);
		assertThat(saved).isNotNull();
		assertThat(saved.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("Integration test fidn by Id")
	public void testFindById()
	{
		Blogs blog1 = new Blogs();
//		blog1.setId(1L);
		blog1.setName("tester");
		blog1.setTopic("testing");
		blog1.setContent("tester is testing the service");
		blog1.setDate(LocalDateTime.parse("2025-09-14T16:44:07"));
		
		Blogs saved = entityManager.persistAndFlush(blog1);
		
		
		Optional<Blogs> found = blogRepository.findById(saved.getId());
		
		assertThat(found).isPresent();
		
		assertThat(found.get().getName()).isEqualTo("tester");
		
		
	}
	
	
	@Test
	@DisplayName("Delete the product")
	public void deleteBlog()
	{
		Blogs blog1 = new Blogs();
		blog1.setName("tester");
		blog1.setTopic("testing");
		blog1.setContent("tester is testing the service");
		blog1.setDate(LocalDateTime.parse("2025-09-14T16:44:07"));
		
		Blogs saved = entityManager.persistAndFlush(blog1);
		
		blogRepository.delete(saved);
		
		Optional<Blogs> found =blogRepository.findById(saved.getId());
		
		assertThat(found).isEmpty();
		
		
	}
	

	
	
	
	
}
