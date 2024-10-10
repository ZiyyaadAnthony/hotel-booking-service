package com.javaproject.hotel_booking_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javaproject.hotel_booking_service.exception.ResourceNotFoundException;
import com.javaproject.hotel_booking_service.model.Booking;
import com.javaproject.hotel_booking_service.repository.BookingRepository;
import com.javaproject.hotel_booking_service.service.BookingService;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    
    @Mock
    private BookingRepository bookingRepository ;
    
    @InjectMocks
    private BookingService bookingService;
    
    
    
    LocalDate checkInDate = LocalDate.of(2024, 10, 2);
    LocalDate checkOutDate = LocalDate.of(2024, 10, 12);
    String customerName = "MZ Anthony";
    
    @Test
    public void testCreateBooking() {
        
        Booking booking = new Booking();
        booking.setBookingId(1L);
        booking.setCustomerName(customerName);
        booking.setNumberOfGuests(2);
        booking.setCheckInDate(checkInDate);
        booking.setCheckInDate(checkOutDate);
        
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        
        Booking createdBooking = bookingService.createBooking(booking);
        
        assertNotNull(createdBooking);
        assertEquals(customerName, createdBooking.getCustomerName());
    }
    
    @Test
    public void testUpdateBooking() {
        Booking existingBooking = new Booking();
        existingBooking.setBookingId(1L);
        existingBooking.setCustomerName(customerName);
        existingBooking.setNumberOfGuests(2);
        existingBooking.setCheckInDate(checkInDate);
        existingBooking.setCheckInDate(checkOutDate);
        
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(existingBooking));
        
        Booking updatedBookingDetails = new Booking();
        
        LocalDate updatedCheckInDate = LocalDate.of(2024, 11, 2);
        LocalDate updatedcheckOutDate = LocalDate.of(2024, 11, 12);
        
        updatedBookingDetails.setCustomerName("A Vally");
        updatedBookingDetails.setNumberOfGuests(3);
        updatedBookingDetails.setCheckInDate(updatedCheckInDate);
        updatedBookingDetails.setCheckInDate(updatedcheckOutDate);
        
        when(bookingRepository.save(any(Booking.class))).thenReturn(updatedBookingDetails);
        
        Booking updatedBooking = bookingService.updateBooking(1L, updatedBookingDetails);
        
        assertNotNull(updatedBooking);
        assertEquals("A Vally", updatedBooking.getCustomerName());
        assertEquals(3, updatedBooking.getNumberOfGuests());
    }
    
    @Test
    public void testDeleteBooking() {
        Booking existingBooking = new Booking();
        existingBooking.setBookingId(1L);
        existingBooking.setCustomerName(customerName);
        
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(existingBooking));
        
        bookingService.deleteBooking(1L);
        
        verify(bookingRepository).delete(existingBooking);
    }
    
    @Test
    public void testDeleteBookingNotFound() {
        Long bookingId = 99L;
        
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {bookingService.deleteBooking(bookingId);});
        
        String expectedMessage = "Booking with id " + bookingId + " not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
