package com.example.demo.Service;

import java.security.Principal;
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

    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    public List<Blogs> getAllBlogs() {
        return blogrepository.findAll();
    }

    // Modified to associate blog with the logged-in user
    public Blogs addBlog(Blogs blog) {
        if (blog.getName() == null || blog.getName().isEmpty()) {
            throw new BadException("Creator name cannot be null or empty.");
        }
        // For now, we'll create a default user or use the first available user
        // In a real application, you'd get this from the authentication context
        Users defaultUser = userRepository.findAll().stream().findFirst()
                .orElseGet(() -> {
                    Users newUser = new Users();
                    newUser.setUsername("default_user");
                    newUser.setEmail("default@example.com");
                    newUser.setRole("USER");
                    return userRepository.save(newUser);
                });
        
        blog.setAuthor(defaultUser); // Set the author
        return blogrepository.save(blog);
    }

    public Optional<Blogs> getBlogById(Long Id) {
        Optional<Blogs> blog = blogrepository.findById(Id);
        if (blog.isEmpty()) {
            throw new ResourceNotFoundException("Blog not found with ID: " + Id);
        }
        return blog;
    }

    public void deleteBlogsById(Long Id) {
        if (!blogrepository.existsById(Id)) {
            throw new ResourceNotFoundException("Blog not found with ID: " + Id);
        }
        blogrepository.deleteById(Id);
    }
    
    // New method for security check
    public boolean isOwner(String username, Long blogId) {
        Optional<Blogs> blogOpt = blogrepository.findById(blogId);
        if (blogOpt.isEmpty()) {
            return false; // Or throw an exception
        }
        return blogOpt.get().getAuthor().getUsername().equals(username);
    }
}