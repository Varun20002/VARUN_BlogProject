package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.BadException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repository.*;

@Service
public class BlogsService {

	@Autowired
	private BlogRepository blogrepository;
	
	public List<Blogs> getAllBlogs()
	{
		return blogrepository.findAll();
	}
	
	public Blogs addBlog(Blogs blog)
	{
		 if (blog.getName() == null || blog.getName().isEmpty()) {
		        throw new BadException("Creator name cannot be null or empty.");
		    }
		return blogrepository.save(blog);
	}
	
	public Optional<Blogs> getBlogById(Long Id)
	{
		Optional<Blogs> blog = blogrepository.findById(Id);
		if(blog.isEmpty())
		{
			throw new ResourceNotFoundException("Blog not found with ID: " + Id);
		}
		return blog;
	}
	
	public void deleteBlogsById(Long Id) {
		Optional<Blogs> blog = blogrepository.findById(Id);
		if(blog.isEmpty())
		{
			throw new ResourceNotFoundException("Blog not found with ID: " + Id);
		}
	  blogrepository.deleteById(Id);
	}
	
	
	
	
}
