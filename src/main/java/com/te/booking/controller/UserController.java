package com.te.booking.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.booking.Response.BookingResponse;
import com.te.booking.Response.TokenResponse;
import com.te.booking.dto.AboutDto;
import com.te.booking.dto.LoginDto;
import com.te.booking.entity.AboutUser;
import com.te.booking.entity.DefaultUser;
import com.te.booking.userservice.ServiceInterface;
import com.te.booking.util.JwtUtil;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ServiceInterface serviceInterface;
	
	@Autowired
	private JwtUtil util;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private BookingResponse bookingResponse;
	
	
	@PostMapping("/save")
	public ResponseEntity<String>  saveuser(@RequestBody DefaultUser defaultUser){
		
		Integer id=serviceInterface.saveUser(defaultUser);
		String body="user '"+id+"' saved";
		
		return ResponseEntity.ok(body);
		
	}
	
	//2.validate username and pwd
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> loginUser(@RequestBody LoginDto loginDto){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		
		String token =util.generateToken(loginDto.getUsername());
		return ResponseEntity.ok(new TokenResponse(token,"success"));
		
	}
	
	//3.after login
	@PostMapping("/welcome")
	public ResponseEntity<String> accessdata(Principal p){
		return ResponseEntity.ok("Hello User"+ p.getName());
	}
	
	@PostMapping("/about")
	public ResponseEntity<BookingResponse>  addabout(@RequestBody AboutDto aboutDto){
		AboutUser aboutUser =serviceInterface.addabout(aboutDto);
		if(aboutUser!=null) 
	//		bookingResponse.setData(Arrays.asList(aboutUser));
		bookingResponse.setError(false);
		bookingResponse.setMessage("You have Successfully registered the about details");
		bookingResponse.setStatus(200);
		return new ResponseEntity<BookingResponse>(bookingResponse,HttpStatus.OK);
	}
	
	
	@PostMapping("/update")
	public ResponseEntity<BookingResponse>  updateabout(@RequestBody AboutDto aboutDto){
		AboutUser aboutUser =serviceInterface.updateabout(aboutDto);
		if(aboutUser!=null) 
	//		bookingResponse.setData(Arrays.asList(aboutUser));
		bookingResponse.setError(false);
		bookingResponse.setMessage("You have Successfully updated");
		bookingResponse.setStatus(200);
		return new ResponseEntity<BookingResponse>(bookingResponse,HttpStatus.OK);
	}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

