package com.example.SpringWithDocker.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringWithDocker.model.User;
import com.example.SpringWithDocker.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) {

		User savedUser = userService.save(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));

	}

	// Read all users
	@GetMapping
	public ResponseEntity<?> readAll() {

		return ResponseEntity.ok(userService.findAll());
	}

	//Update an user
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@RequestBody User user, @PathVariable(value = "id") Long userId){
		Optional<User> oUser = userService.findById(userId);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		oUser.get().setUsername(user.getUsername());
		oUser.get().setPassword(user.getPassword());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable(value = "id") Long userId) {
		Optional<User> oUser = userService.findById(userId);

		if (!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		userService.deleteById(userId);
		return ResponseEntity.ok().build();
	}

}
