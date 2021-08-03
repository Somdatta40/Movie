package com.cg.mts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Seat;
import com.cg.mts.entity.Ticket;
import com.cg.mts.exception.SeatNotFoundException;
import com.cg.mts.repository.ISeatRepository;
import com.cg.mts.service.SeatService;

public class SeatServiceTest 
{
	private SeatService seatService;
    private ISeatRepository seatRepository;
   
    static Ticket ticket = new Ticket();
    @BeforeEach
    void setUp() {
    	seatRepository = mock(ISeatRepository.class);
    	seatService = new SeatService();
    }

    @Test
    void shouldSavedUserSuccessfully() throws SeatNotFoundException {
    	Seat seat = new Seat("45",true,ticket);
        given(seatService.bookSeat(seat, ticket)).willReturn(null);
        given(seatRepository.save(seat)).willAnswer(invocation -> invocation.getArgument(0));

        Seat savedSeat = seatService.bookSeat(seat, ticket);

        assertThat(savedSeat).isNotNull();

        verify(seatRepository).save(any(Seat.class));
    }

    @Test
    void shouldThrowErrorWhenSaveSeatWithExistingTicket() {
    	Seat seat = new Seat("45",true,ticket);
        given(seatService.findByTicket(ticket)).willReturn(null);

        assertThrows(SeatNotFoundException.class, () -> {
        	seatService.bookSeat(seat, ticket);
        });

        verify(seatRepository, never()).save(any(Seat.class));
    }
}
