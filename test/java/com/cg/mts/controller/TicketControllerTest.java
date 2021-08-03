package com.cg.mts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.mts.entity.Theatre;
import com.cg.mts.entity.Ticket;
import com.cg.mts.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TicketControllerTest 
{
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private ObjectMapper objectMapper;
    
    private List<Ticket> ticketList;
    static Theatre theatre=new Theatre();
	
    @BeforeEach
    void setUp() 
    {
        this.ticketList = new ArrayList<>();
        this.ticketList.add(new Ticket(true,"SN21",theatre,2,500));
        this.ticketList.add(new Ticket(false,"SN21",theatre,5,1000));
        this.ticketList.add(new Ticket(true,"SN22",theatre,3,700));
    }
    
    @Test
    void shouldFetchAllTicket() throws Exception {
        given(ticketService.viewAllTicket()).willReturn(this.ticketList);

        this.mockMvc.perform(get("/ticket/getalltickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(ticketList.size())));
    }

    @Test
    void shouldFindTicketByTheatreId() throws Exception {
        long walletId=1301;
        ticketList=new ArrayList<Ticket>();
        Ticket ticket = new Ticket(true,"SN21",theatre,2,500);
        given(ticketService.findByTheatre(theatre)).willReturn(ticketList);

        this.mockMvc.perform(get("/ticket/getticketbythid/{thId}",walletId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.screenName", is(ticket.getScreenName())))
                .andExpect(jsonPath("$.ticketStatus", is(ticket.isTicketStatus())))
                .andExpect(jsonPath("$.noOfSeats", is(ticket.getNoOfSeats())))
                .andExpect(jsonPath("$.totalCost", is(ticket.getTotalCost())))
                .andExpect(jsonPath("$.theatre", is(ticket.getTheatre())));
    }
    @Test
    void shouldReturn404WhenFetchingNonExistingTicketByWalletId() throws Exception {
        long theatreId=8001;
        given(ticketService.findByTheatre(theatre)).willReturn(null);

        this.mockMvc.perform(get("/ticket/getticketbythid/{thId}",theatreId))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldFindTicketByScreenName() throws Exception {
        String screenName="SN21";
        ticketList=new ArrayList<Ticket>();
        Ticket ticket = new Ticket(true,"SN21",theatre,2,500);
        given(ticketService.findByScreenName(screenName)).willReturn(ticketList);

        this.mockMvc.perform(get("/ticket/getticketbyscname/{scName}",screenName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.screenName", is(ticket.getScreenName())))
                .andExpect(jsonPath("$.ticketStatus", is(ticket.isTicketStatus())))
                .andExpect(jsonPath("$.noOfSeats", is(ticket.getNoOfSeats())))
                .andExpect(jsonPath("$.totalCost", is(ticket.getTotalCost())))
                .andExpect(jsonPath("$.theatre", is(ticket.getTheatre())));
    }
    @Test
    void shouldReturn404WhenFetchingNonExistingTicketByScreenName() throws Exception {
    	String screenName="SN21";
        given(ticketService.findByScreenName(screenName)).willReturn(null);

        this.mockMvc.perform(get("/ticket/getticketbyscname/{scName}",screenName))
                .andExpect(status().isNotFound());

    }
    
    @Test
    void shouldDeleteTicket() throws Exception {
    	long ticketId = 9001;
    	Ticket ticket = new Ticket(true,"SN21",theatre,2,500);
        given(ticketService.viewTicketById(ticketId)).willReturn(ticket);
        doNothing().when(ticketService).deleteTicket(ticket.getTicketId());

        this.mockMvc.perform(delete("/ticket/deleteticketbyid/{tId}", ticket.getTicketId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.screenName", is(ticket.getScreenName())))
                .andExpect(jsonPath("$.ticketStatus", is(ticket.isTicketStatus())))
                .andExpect(jsonPath("$.noOfSeats", is(ticket.getNoOfSeats())))
                .andExpect(jsonPath("$.totalCost", is(ticket.getTotalCost())))
                .andExpect(jsonPath("$.theatre", is(ticket.getTheatre())));

    }
    @Test
    void shouldReturn404WhenDeletingNonExistingTicket() throws Exception {
        long ticketId = 9001;
        given(ticketService.viewTicketById(ticketId)).willReturn(null);

        this.mockMvc.perform(delete("/ticket/deleteticketbyid/{tId}", ticketId))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldUpdateTicket() throws Exception {
    	long ticketId = 9001;
    	Ticket ticket = new Ticket(true,"SN21",theatre,2,500);
        given(ticketService.viewTicketById(ticketId)).willReturn(ticket);
        given(ticketService.updateTicket(any(Ticket.class),ticketId)).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/updateticketbyid/{tId}", ticket.getTicketId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.screenName", is(ticket.getScreenName())))
                .andExpect(jsonPath("$.ticketStatus", is(ticket.isTicketStatus())))
                .andExpect(jsonPath("$.noOfSeats", is(ticket.getNoOfSeats())))
                .andExpect(jsonPath("$.totalCost", is(ticket.getTotalCost())))
                .andExpect(jsonPath("$.theatre", is(ticket.getTheatre())));

    }
    @Test
    void shouldReturn404WhenUpdatingNonExistingTicket() throws Exception {
        long ticketId = 1101;
        given(ticketService.viewTicketById(ticketId)).willReturn(null);
        Ticket ticket = new Ticket(true,"SN21",theatre,2,500);

        this.mockMvc.perform(put("/updateticketbyid/{tId}", ticketId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isNotFound());

    }
    
    @Test
    void shouldAddNewTicket() throws Exception {
    	long ticketId = 9001;
        given(ticketService.saveTicket(any(Ticket.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Ticket ticket = new Ticket(true,"SN21",theatre,2,500);
        this.mockMvc.perform(post("/ticket/addticketbythid/{thId}",ticketId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.screenName", is(ticket.getScreenName())))
                .andExpect(jsonPath("$.ticketStatus", is(ticket.isTicketStatus())))
                .andExpect(jsonPath("$.noOfSeats", is(ticket.getNoOfSeats())))
                .andExpect(jsonPath("$.totalCost", is(ticket.getTotalCost())))
                .andExpect(jsonPath("$.theatre", is(ticket.getTheatre())));

    }
    @Test
    void shouldReturn400WhenCreateNewUserWithoutTheatre() throws Exception {
    	Ticket ticket = new Ticket(true,"SN21",null,2,500);
    	long bookingId=2001;
        this.mockMvc.perform(post("/ticket/addticketbythid/{thId}",bookingId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", is("application/problem+json")))
                .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
                .andExpect(jsonPath("$.title", is("Constraint Violation")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.violations", hasSize(1)))
                .andExpect(jsonPath("$.violations[0].field", is("theatre")))
                .andExpect(jsonPath("$.violations[0].message", is("Theatre should not be empty")))
                .andReturn();
    }

}
