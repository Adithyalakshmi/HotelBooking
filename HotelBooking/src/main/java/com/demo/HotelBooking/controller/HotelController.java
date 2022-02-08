package com.demo.HotelBooking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.HotelBooking.model.Hotel;
import com.demo.HotelBooking.service.HotelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	private static final Logger log =LoggerFactory.getLogger(HotelController.class);
	
	@Operation(summary = "Add Hotel",description = "To Add the details of a new hotel")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Added Hotel Sucessfully",
	        		content = {@Content(mediaType = "application/json")}),
	        @ApiResponse(responseCode = "404", description = "Not able to add a hotel",
	        		content = {@Content()})
	})
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
	
	@Operation(summary = "Fetch all Hotels",description = "To Fetch the details of all hotels")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "fetched the hotel details",
	        		content = {@Content(mediaType = "application/json")}),
	        @ApiResponse(responseCode = "404", description = "Not able fetch hoteldetails",
	        		content = {@Content()})
	})
	@GetMapping("/fetchAllHotels")
	public ResponseEntity<?> listAllHotels() {
		log.info("to list all hotels ");
		ResponseEntity<?> responseEntity = null;
		List<Hotel> hotelList =null;
		try {
			hotelList = hotelService.listAllHotels();
			if(CollectionUtils.isEmpty(hotelList)) {
				responseEntity = ResponseEntity.noContent().build();
			}
			else {
				responseEntity = ResponseEntity.ok().body(hotelList);
			}
		}
		catch(Exception e ) {
			log.error("Exception while fetching all hotels: {}",e.getMessage());
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
		return responseEntity;
	}
	
	@Operation(summary = "Fetch Hotel By Id",description = "To fetch the details of a particular hotel ")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Fetched a particular hotel",
	        		content = {@Content(mediaType = "application/json")}),
	        @ApiResponse(responseCode = "404", description = "Not able to fetch the specific hotel",
	        		content = {@Content()})
	})
	@GetMapping("/fetchHotelById/{hotelId}")
	public ResponseEntity<?> fetchHotelById(@PathVariable("hotelId") int id) {
		log.info("Inside fetchHotelById {}",id);
		ResponseEntity<?> responseEntity = null;
		Hotel hotel = null;
		try {
			hotel = hotelService.fetchHotelById(id);
			if(ObjectUtils.isEmpty(hotel)) {
				responseEntity = ResponseEntity.noContent().build();
			}
			else {
				responseEntity = ResponseEntity.ok().body(hotel);
			}
		}
		catch(Exception e) {
			log.error("Exception while fetching hotel by id {}",e.getMessage());
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return responseEntity;
	}
	
	@Operation(summary = "Delete Hotel By Id",description = "To delete a particular hotel")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Deleted a hotel",
	        		content = {@Content(mediaType = "application/json")}),
	        @ApiResponse(responseCode = "404", description = "Not able to delete the hotel",
	        		content = {@Content()})
	})
	@DeleteMapping("/deleteHotelById/{hotelId}")
	public String deleteHotelById(@PathVariable("hotelId") int id) {
		String response = null;
		try {
			response = hotelService.deleteHotelById(id);
		}
		catch(Exception e) {
			log.error("Exception while trying to delete hotel");
			response = "Not able to delete hotel";
		}
		
		return response;
	}

}
