package com.my.experiments.springbootdockerpost.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.experiments.springbootdockerpost.domain.User;
import com.my.experiments.springbootdockerpost.persistence.UserRepository;

@RestController
public class AppRestController {
	
	@Autowired
	UserRepository userRepository;
	
	
	@RequestMapping(value="/getUser")
	public User getUserById(@RequestParam(name="id")String id)
	{
		
		return userRepository.findById(Long.parseLong(id)).orElse(new User(0,"This user does not exist",0,null));
		//return null;

	}
	
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST,
			consumes="application/json")
	public ResponseEntity<?> createUser(@RequestBody User user)
	{
		
		
		userRepository.save(user);
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value="/updateUser",method=RequestMethod.PUT,
			consumes="application/json")
	
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user)
	{
		
		if (!userRepository.existsById((long)user.getId())) {
		
			return ResponseEntity.badRequest().body("No such User");
		}
		User userToUpdate = userRepository.findById((long)user.getId()).orElse(new User(0,"No such user",0,null));
		userToUpdate.setAge(user.getAge() == 0 ? userToUpdate.getAge():user.getAge());
		userToUpdate.setDOB(user.getDOB() == null ? userToUpdate.getDOB():user.getDOB());
		userToUpdate.setName(user.getName() == null ? userToUpdate.getName():user.getName());
		userRepository.save(userToUpdate);
		
		
		return new ResponseEntity<>(userToUpdate,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/deleteUser", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@RequestParam(value="id") String id)
	{
		if (!userRepository.existsById(Long.parseLong(id)))
		{
					
			return ResponseEntity.badRequest().body("No such User");
		}
		
		
		return ResponseEntity.ok("User deleted");
	}

}
