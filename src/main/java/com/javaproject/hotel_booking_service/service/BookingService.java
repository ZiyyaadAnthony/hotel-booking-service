package com.javaproject.hotel_booking_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject.hotel_booking_service.exception.ResourceNotFoundException;
import com.javaproject.hotel_booking_service.model.Booking;
import com.javaproject.hotel_booking_service.repository.BookingRepository;


@Service
public class BookingService {
    
    private BookingRepository bookingRepository;
    
    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    
    
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }
    
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    
    public Booking updateBooking(Long id, Booking bookingDetails) {
        Optional<Booking> booking = Optional.of(bookingRepository.findById(id).orElseThrow());
        booking.get().setCustomerName(bookingDetails.getCustomerName());
        booking.get().setCheckInDate(bookingDetails.getCheckInDate());
        booking.get().setCheckOutDate(bookingDetails.getCheckOutDate());
        booking.get().setNumberOfGuests(bookingDetails.getNumberOfGuests());
        
        return bookingRepository.save(booking.get());
    }
    
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking with id " + id + " not found"));
        bookingRepository.delete(booking);
    }
}
