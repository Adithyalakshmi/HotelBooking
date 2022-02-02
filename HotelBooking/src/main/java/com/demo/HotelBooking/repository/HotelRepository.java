package com.demo.HotelBooking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.HotelBooking.model.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, Integer> {

}
