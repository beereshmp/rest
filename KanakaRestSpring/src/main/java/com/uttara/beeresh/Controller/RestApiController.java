package com.uttara.beeresh.Controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.uttara.beeresh.Model.User;
import com.uttara.beeresh.Service.UserService;

@RestController
@RequestMapping("/api")
public class RestApiController {

	UserService us = new UserService();

	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findUserById(@PathVariable("id") Long id) {
		System.out.println(id);
		User user = us.fingUserById(id);
		if (user != null)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		else

			return new ResponseEntity<>("Sorry user id " + id + " not found ", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/getallusers", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUsers() {
		List<User> l = us.getAllUsers();
		if (l == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(l, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User user,UriComponentsBuilder uriComponentsBuilder)
	{
		if(us.fingUserById(user.getId())!=null)
		{
			return new ResponseEntity<>("user already exist try diffrent ",HttpStatus.CONFLICT);
		}
		us.addUser(user);
		
		HttpHeaders head=new HttpHeaders();
		head.setLocation(uriComponentsBuilder.path("/api/getUser/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<>(head,HttpStatus.CREATED);
	}
	
	
	

}
