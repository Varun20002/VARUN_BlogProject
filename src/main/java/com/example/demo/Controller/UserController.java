package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Service.UserService;
import com.example.demo.Repository.Users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Users.class)))
    })
    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Users.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Users> addUser(@RequestBody Users user) {
        Users savedUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Users.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/{userId}")
    public Optional<Users> getUserById(
            @Parameter(description = "ID of the user to be retrieved") @PathVariable("userId") Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Update user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Users.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PutMapping("/{userId}")
    public ResponseEntity<Users> updateUser(
            @Parameter(description = "ID of the user to be updated") @PathVariable("userId") Long id,
            @RequestBody Users user) {
        Users updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User deleted"),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user to be deleted") @PathVariable("userId") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
