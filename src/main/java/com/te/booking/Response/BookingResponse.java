package com.te.booking.Response;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BookingResponse {
	private String message;
	private Object data;
	private int status;
	private boolean error;
	private String token;
}
