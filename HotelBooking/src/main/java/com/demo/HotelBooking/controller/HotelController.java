package com.demo.HotelBooking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.HotelBooking.model.Hotel;
import com.demo.HotelBooking.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	private static final Logger log =LoggerFactory.getLogger(HotelController.class);
	
	@PostMapping("/addHotel") 
	public ResponseEntity<?> addHotel(@RequestBody Hotel hotel) {
		log.info("To save hotel details");
		ResponseEntity<?> responseEntity = null;
		try {
			log.info("Inside try in addHotel");
			if(hotel.getId()==0) {
				responseEntity= ResponseEntity.status(HttpStatus.NO_CONTENT).body("Give Proper Hostel Details");
			}
			else {
			    hotelService.addHotel(hotel);
			    responseEntity= ResponseEntity.status(HttpStatus.OK).body("Added hotel to the list with id "+hotel.getId());
			}
		}
		catch(Exception e) {
			log.error("Exception while trying to add a hotel: {}",e.getMessage());
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return responseEntity;
	}

}
