package com.te.booking.userservice;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.booking.dto.DeleteGroundDto;
import com.te.booking.dto.GroundTimingsDto;
import com.te.booking.entity.GroundTimings;
import com.te.booking.repository.GroundTimingsRepo;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private GroundTimingsRepo groundTimingsRepo;
	
//create ground timings details
	@Override
	public GroundTimings create(GroundTimingsDto groundTimingsdto) {
		GroundTimings groundTimings = new GroundTimings();
		BeanUtils.copyProperties(groundTimingsdto, groundTimings);
		return groundTimingsRepo.save(groundTimings);
	}
// update ground timings
	
	@Override
	public GroundTimings update(GroundTimingsDto groundTimingsdto) {
		Optional<GroundTimings> optional=groundTimingsRepo.findById(groundTimingsdto.getGroundName());
		if (optional.isPresent()) {
			GroundTimings groundTimings =optional.get();
			BeanUtils.copyProperties(groundTimingsdto, groundTimings);
			return groundTimingsRepo.save(groundTimings);
	}
		return null;
	}
	
	//delete ground timings
	
	@Override
	public boolean delete(DeleteGroundDto deleteGroundDto) {
		Optional<GroundTimings> optional=groundTimingsRepo.findById(deleteGroundDto.getGroundName());
		if (optional.isPresent()) {
			GroundTimings groundTimings =optional.get();
			 
				groundTimingsRepo.delete(groundTimings);
				return true;
	}
		return false;
}
}
