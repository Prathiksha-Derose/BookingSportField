package com.te.booking.userservice;

import com.te.booking.dto.DeleteGroundDto;
import com.te.booking.dto.GroundTimingsDto;
import com.te.booking.entity.GroundTimings;

public interface AdminService {

	GroundTimings create(GroundTimingsDto groundTimingsdto);

	GroundTimings update(GroundTimingsDto groundTimingsdto);

	boolean delete(DeleteGroundDto deleteGroundDto);

}
