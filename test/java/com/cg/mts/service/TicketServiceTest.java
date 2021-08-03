package com.cg.mts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Theatre;
import com.cg.mts.entity.Ticket;
import com.cg.mts.exception.TicketNotFoundException;
import com.cg.mts.repository.ITicketRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class TicketServiceTest 
{
    private TicketService ticketService;
    private ITicketRepository ticketRepository;
    
    static Theatre theatre=new Theatre();
    
    @BeforeEach
    void setUp() {
    	ticketRepository = mock(ITicketRepository.class);
    	ticketService = new TicketService();
    }

    @Test
    void shouldSavedUserSuccessfully() throws TicketNotFoundException {
    	Ticket ticket = new Ticket(true,"SN21",theatre,2,500);
        given(ticketService.viewTicketById(ticket.getTicketId())).willReturn(null);
        given(ticketRepository.save(ticket)).willAnswer(invocation -> invocation.getArgument(0));

        Ticket savedTicket = ticketService.saveTicket(ticket);

        assertThat(savedTicket).isNotNull();

        verify(ticketRepository).save(any(Ticket.class));
    }

    @Test
    void shouldThrowErrorWhenSaveUserWithExistingTicketId() {
    	Ticket ticket = new Ticket(true,"SN21",theatre,2,500);
        given(ticketRepository.findById(ticket.getTicketId())).willReturn(null);

        assertThrows(TicketNotFoundException.class, () -> {
        	ticketService.saveTicket(ticket);
        });

        verify(ticketRepository, never()).save(any(Ticket.class));
    }
}
