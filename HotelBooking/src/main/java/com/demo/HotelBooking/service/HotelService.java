package com.demo.HotelBooking.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.HotelBooking.model.Hotel;
import com.demo.HotelBooking.repository.HotelRepository;


@Service
public class HotelService {
	
	@Autowired
	HotelRepository hotelRepository;
	
	private static final Logger log =LoggerFactory.getLogger(HotelService.class);
	
	public void addHotel(Hotel hotel) {
		log.info("To insert hotel details");
		hotelRepository.save(hotel);
	}
	
	public List<Hotel> listAllHotels() {
		log.info("To List all hotels");
		List<Hotel> hotelList = null;
		hotelList = hotelRepository.findAll();
		return hotelList;
	}

}