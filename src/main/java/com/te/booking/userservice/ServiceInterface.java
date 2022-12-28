package com.te.booking.userservice;

import java.util.Optional;


import com.te.booking.dto.AboutDto;
import com.te.booking.entity.AboutUser;
import com.te.booking.entity.DefaultUser;

public interface ServiceInterface {
	
	 Integer saveUser(DefaultUser defaultUser);

	Optional<DefaultUser> findByUsername(String username);

	AboutUser addabout(AboutDto aboutDto);

	AboutUser updateabout(AboutDto aboutDto);

	
	
}
