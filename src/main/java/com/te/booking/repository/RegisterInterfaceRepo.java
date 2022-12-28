package com.te.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.te.booking.entity.DefaultUser;

@Repository
public interface RegisterInterfaceRepo extends JpaRepository<DefaultUser, Integer>{ 
	
	Optional<DefaultUser> findByUsername(String username);
}
