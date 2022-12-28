package com.te.booking.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.booking.Response.BookingResponse;
import com.te.booking.dto.DeleteGroundDto;
import com.te.booking.dto.GroundTimingsDto;
import com.te.booking.entity.GroundTimings;
import com.te.booking.exception.ExceptionHandler;
import com.te.booking.userservice.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private BookingResponse bookingResponse;
	
	
	@PostMapping("create")
	public ResponseEntity<BookingResponse> create(@RequestBody GroundTimingsDto groundTimingsdto){
		GroundTimings timings=adminService.create(groundTimingsdto);
		if(timings!=null) {
			bookingResponse.setData(Arrays.asList(timings));
			bookingResponse.setError(false);
			bookingResponse.setMessage("You have Successfully registered the about details");
			bookingResponse.setStatus(200);
			return new ResponseEntity<BookingResponse>(bookingResponse,HttpStatus.OK);
		}
		throw new ExceptionHandler("You have not created");
		
	}
	@PostMapping("update")
	public ResponseEntity<BookingResponse> update(@RequestBody GroundTimingsDto groundTimingsdto){
		GroundTimings timings=adminService.update(groundTimingsdto);
		if(timings!=null) {
			bookingResponse.setData(Arrays.asList(timings));
			bookingResponse.setError(false);
			bookingResponse.setMessage("You have Successfully updated");
			bookingResponse.setStatus(200);
			return new ResponseEntity<BookingResponse>(bookingResponse,HttpStatus.OK);
		}
		throw new ExceptionHandler("You have not Updated");
		
	}
	
	@PostMapping("delete")
	public ResponseEntity<BookingResponse> delete(@RequestBody DeleteGroundDto deleteGroundDto){
		boolean timings=adminService.delete(deleteGroundDto);
		if(timings) {
			
			bookingResponse.setError(false);
			bookingResponse.setMessage("You have Successfully deleted");
			bookingResponse.setStatus(200);
			return new ResponseEntity<BookingResponse>(bookingResponse,HttpStatus.OK);
		}
		throw new ExceptionHandler("You have not deleted");
		
	}

}
