package com.te.booking.entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class AboutUser implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer useraboutId;
	private LocalDate dob;
	private String gender;
//	private Integer userId;
//	private int groupId;
//    private String ipAddress;
	private int active;
//	private String activationCode;
    private LocalDateTime createdOn;
	@JsonFormat(pattern = "HH",shape=JsonFormat.Shape.STRING)
	private LocalTime lastLogin;
	private LocalDateTime requestOn;
	//private String forgottenPassword;
   // private String rememberCode;
    
  //bidirectional mapping from aboutUser to  bookstatus
  	@OneToMany(cascade = CascadeType.ALL,mappedBy = "aboutUser")
//  	@JoinColumn(name="userId")
  	private List<BookStatus> bookStatus=Lists.newArrayList();
  	
  	
  	//bidirectional mapping from aboutUser to sportfield
  	@OneToMany(cascade=CascadeType.ALL, mappedBy ="aboutUser" )    
  //    @JoinColumn(name="userId")
  	private  List<SportField> sportFields=new ArrayList<>();
  	
  //bi directional mapping
  	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "aboutUser")
 // 	@JoinColumn(name="bookingId")
  	private List<Booking> booking=new ArrayList<>();
	

}
