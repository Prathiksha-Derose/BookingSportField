package com.te.booking.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class SportField implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sportFieldId;
	private String name;
	private String text;
	private double price;
	private int requestOn;
	
	//bidirectional mapping from aboutuser to sportfield

		@ManyToOne(cascade=CascadeType.ALL)    
	    @JoinColumn(name="useraboutId")
		private  AboutUser aboutUser;
		
		
		//bidirectional mapping
		@OneToOne(cascade=CascadeType.ALL )    
	    @JoinColumn(name="bookingId")
		private  Booking booking;


}
