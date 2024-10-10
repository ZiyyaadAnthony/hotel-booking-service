package com.javaproject.hotel_booking_service.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
@Getter
@Setter


public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private String customerName;
    private int numberOfGuests;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    
   
    
}


