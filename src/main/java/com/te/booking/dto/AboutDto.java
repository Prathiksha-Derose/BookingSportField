package com.te.booking.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AboutDto {
	
    private Integer useraboutId;
	private LocalDate dob;
	private String gender;
	private int active;
	private LocalDateTime createdOn;
	@JsonFormat(pattern = "HH",shape=JsonFormat.Shape.STRING)
	private LocalTime lastLogin;
	private LocalDateTime requestOn;
//	private Integer userId;
//	private int groupId;
//    private String ipAddress;
//	private LocalTime lastLogin;
//	private String activationCode;
    
	//private String forgottenPassword;
   // private String rememberCode;
    
    
//    private Integer sportFieldId;
//	private String name;
//	private String text;
//	private double price;
//	private int startingHour;
//	private int endingHour;
	
	
	


}
