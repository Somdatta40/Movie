package com.cg.mts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Booking;
import com.cg.mts.exception.BookingNotFoundException;
import com.cg.mts.repository.IBookingRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class BookingServiceTest {
	private BookingService bookingService;
    private IBookingRepository IBookingRepository;

    @BeforeEach
    void setUp() {
    	IBookingRepository = mock(IBookingRepository.class);
        bookingService = new BookingService();
    }

    @Test
    void shouldSavedUserSuccessfully() throws BookingNotFoundException {
    	Booking booking = new Booking();
        given(bookingService.addBooking(booking).getBookingId()).willReturn(null);
        given(IBookingRepository.save(booking)).willAnswer(invocation -> invocation.getArgument(0));

        Booking savedBooking = bookingService.addBooking(booking);

        assertThat(savedBooking).isNotNull();

        verify(IBookingRepository).save(any(Booking.class));
    }

    @Test
    void shouldThrowErrorWhenSaveUserWithExistingTicket() {
    	Booking booking = new Booking();
    	
    	given(bookingService.addBooking(booking)).willReturn(booking);

        assertThrows(BookingNotFoundException.class, () -> {
            bookingService.addBooking(booking);
        });

        verify(IBookingRepository, never()).save(any(Booking.class));
    }
}
