package com.te.booking.userservice;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.booking.dto.AboutDto;
import com.te.booking.entity.AboutUser;
import com.te.booking.entity.Booking;
import com.te.booking.entity.DefaultUser;
import com.te.booking.entity.SportField;
import com.te.booking.exception.ExceptionHandler;
import com.te.booking.repository.AboutRepo;
import com.te.booking.repository.BookingRepo;
import com.te.booking.repository.RegisterInterfaceRepo;
import com.te.booking.repository.SportFieldRepo;
@Service
public class ServiceImpl implements ServiceInterface,UserDetailsService{
	
	@Autowired
	private RegisterInterfaceRepo registerInterfaceRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private AboutRepo aboutRepo;
	@Autowired
	private SportFieldRepo sportFieldRepo;
	@Autowired
	private BookingRepo bookingRepo;
	
	
	AboutUser aboutUser = new AboutUser();
	
	
	
	UUID uuid = UUID.randomUUID();
	String uuidAsString = uuid.toString();

	@Override
	public Integer saveUser(DefaultUser defaultUser) {
//		defaultUser.setPassword(defaultUser.getUsername()+uuidAsString);
		//Encode Code
		defaultUser.setPassword(bCryptPasswordEncoder.encode(defaultUser.getPassword()));
		
		
		return registerInterfaceRepo.save(defaultUser).getUserId();
	}
	@Override
	public Optional<DefaultUser> findByUsername(String username) {
		// TODO Auto-generated method stub
		return registerInterfaceRepo.findByUsername(username);
	}
//get user by username
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		Optional<DefaultUser> defaultUser=findByUsername(username);
		if(defaultUser.isEmpty()) {
			throw new UsernameNotFoundException("User Not Found");
		}else {
		DefaultUser user =defaultUser.get();
		 return new User(username,user.getPassword(),
			 user.getRoles().stream()
			 .map(role -> new SimpleGrantedAuthority(role))
				 .collect(Collectors.toList()));
		}
 }
	//AboutUser 
	@Override
	public AboutUser addabout(AboutDto aboutDto) {
		AboutUser aboutUser = new AboutUser();
		SportField sportField = new SportField();
		Booking booking = new Booking();
		BeanUtils.copyProperties(aboutDto, aboutUser);
		BeanUtils.copyProperties(aboutDto, sportField);
		BeanUtils.copyProperties(aboutDto, booking);
		aboutUser.getSportFields().add(sportField);	
		aboutUser.getBooking().add(booking);
		
		return aboutRepo.save(aboutUser);
	}
	@Override
	public AboutUser updateabout(AboutDto aboutDto) {
		Optional<AboutUser>optional=aboutRepo.findById(aboutDto.getUseraboutId());
		if(optional!=null) {
		BeanUtils.copyProperties(aboutDto,aboutUser);
		return aboutRepo.save(aboutUser);
		}
		throw new ExceptionHandler("You have not updated");
	}
}
