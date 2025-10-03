package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Service.BlogsService;
import com.example.demo.Repository.Blogs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/blogs")
public class BlogsController {

    @Autowired
    BlogsService blogsService;

    @Operation(summary = "Get all blog posts")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Blogs.class)))
    })
    @GetMapping
    public List<Blogs> getblogs() {
        return blogsService.getAllBlogs();
    }

    @Operation(summary = "Create a new blog post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Blog post created",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Blogs.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Blogs> addBlogs(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Blog post to be created",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Blogs.class))
            ) Blogs blog) {
        Blogs blogs = blogsService.addBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(blogs);
    }

    @Operation(summary = "Get blog post by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog post found",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Blogs.class))),
        @ApiResponse(responseCode = "404", description = "Blog post not found", content = @Content)
    })
    @GetMapping("/{blogId}")
    public Optional<Blogs> getBlogById(
            @Parameter(description = "ID of the blog to be retrieved") @PathVariable("blogId") Long id) {
        return blogsService.getBlogById(id);
    }

    @Operation(summary = "Delete blog post by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog post deleted"),
        @ApiResponse(responseCode = "404", description = "Blog post not found", content = @Content)
    })
    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(
            @Parameter(description = "ID of the blog to be deleted") @PathVariable("blogId") Long id) {
        blogsService.deleteBlogsById(id);
        return ResponseEntity.ok().build();
    }
}
