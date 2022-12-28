package com.te.booking.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component

public class Booking implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	
	
	//uni directional mapping
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="StatusId")
	private BookStatus bookStatus;
	
	
	//bidirectional mapping
	@OneToOne(cascade=CascadeType.ALL)    
    @JoinColumn(name="SportFieldId")
	private  SportField sportFields;
	
	
	
	
	//bidirectional many-to-many association to Subject
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Booking_user", joinColumns=@JoinColumn(name="bookingId"),
    inverseJoinColumns=@JoinColumn(name="useraboutId"))//@JoinTable is used to map Join table in database
    private List<AboutUser> aboutUser;

}
