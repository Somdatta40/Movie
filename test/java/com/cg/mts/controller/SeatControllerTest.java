package com.cg.mts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.mts.entity.Admin;
import com.cg.mts.entity.Seat;
import com.cg.mts.entity.Show;
import com.cg.mts.entity.Ticket;
import com.cg.mts.service.SeatService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = SeatController.class)
@ActiveProfiles("test")
public class SeatControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatService seatService;

    @Autowired
    private ObjectMapper objectMapper;
    
    private List<Seat> seatList;
    static Ticket ticket=new Ticket(); 
    @BeforeEach
    void setUp() {
        this.seatList = new ArrayList<>();
        this.seatList.add(new Seat("A12",true,ticket));
        this.seatList.add(new Seat("A12",true,ticket));
        this.seatList.add(new Seat("A12",true,ticket));

        //objectMapper.registerModule(new ProblemModule());
        //objectMapper.registerModule(new ConstraintViolationProblemModule());
    }
    @Test
    void shouldFetchAllUsers() throws Exception {
        given(seatService.viewSeats()).willReturn(this.seatList);

        this.mockMvc.perform(get("seat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(seatList.size())));
    }
    
    @Test
    void shouldFindSeatByTId() throws Exception {
       
        Seat seat =new Seat("A12",true,ticket);
        given(seatService.findByTicket(ticket)).willReturn((List<Seat>) seat);

        this.mockMvc.perform(get("/seat/getseatbytid/{tId}", ticket))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seatNumber", is(seat.getSeatNumber())))
                .andExpect(jsonPath("$.seatStatus", is(seat.isSeatStatus())))
                .andExpect(jsonPath("$.ticket", is(seat.getTicket())))
        ;
    }

    @Test
    void shouldReturn404WhenFetchingNonExistingSeat() throws Exception {
        
        given(seatService.findByTicket(ticket)).willReturn(null);

        this.mockMvc.perform(get("/seat/getseatbytid/{tId}", ticket))
                .andExpect(status().isNotFound());

    }
    
    @Test
    void shouldFindSeatBySeatNumber() throws Exception {
       String seatNumber="A12";
        Seat seat =new Seat("A12",true,ticket);
        given(seatService.findSeatBySeatNumber(seatNumber)).willReturn( seat);

        this.mockMvc.perform(get("/seat/getseatbyseatnumber/{seatNumber}", seatNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seatNumber", is(seat.getSeatNumber())))
                .andExpect(jsonPath("$.seatStatus", is(seat.isSeatStatus())))
                .andExpect(jsonPath("$.ticket", is(seat.getTicket())))
        ;
    }

    @Test
    void shouldReturn404WhenFetchingNonExistingSeatBySeatNumber() throws Exception {
    	String seatNumber="A12";
        given(seatService.findSeatBySeatNumber(seatNumber)).willReturn(null);

        this.mockMvc.perform(get("/seat/getseatbyseatnumber/{seatNumber}", seatNumber))
                .andExpect(status().isNotFound());

    }
   

    @Test
    void shouldCreateNewSeat() throws Exception {
        given(seatService.bookSeat(any(Seat.class), ticket)).willAnswer((invocation) -> invocation.getArgument(0));

        Seat seat =new Seat("A12",true,ticket);
        this.mockMvc.perform(post("/seat/addseatbytid/{tId}",ticket)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(seat)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.seatNumber", is(seat.getSeatNumber())))
                .andExpect(jsonPath("$.seatStatus", is(seat.isSeatStatus())))
                .andExpect(jsonPath("$.ticket", is(seat.getTicket())))
                ;

    }

    @Test
    void shouldReturn400WhenCreateNewUserWithoutSeatNumber() throws Exception {
    	 Seat seat =new Seat(null,true,ticket);

        this.mockMvc.perform(post("/seat/addseatbytid/{tId}",ticket)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(seat)))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", is("application/problem+json")))
                .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
                .andExpect(jsonPath("$.title", is("Constraint Violation")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.violations", hasSize(1)))
                .andExpect(jsonPath("$.violations[0].field", is("name")))
                .andExpect(jsonPath("$.violations[0].message", is("name should not be empty")))
                .andReturn()
        ;
    }

    @Test
    void shouldUpdateSeat() throws Exception {
    	long seatId=6001;
    	Seat seat =new Seat("A12",true,ticket);
        given(seatService.bookSeat(seat, ticket)).willReturn(seat);
        given(seatService.blockSeat(any(Seat.class), seatId)).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/seat/updateseatbyid/{sId}", seat.getSeatId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(seat)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seatNumber", is(seat.getSeatNumber())))
                .andExpect(jsonPath("$.seatStatus", is(seat.isSeatStatus())))
                .andExpect(jsonPath("$.ticket", is(seat.getTicket())));

    }

    @Test
    void shouldReturn404WhenUpdatingNonExistingSeat() throws Exception {
    	long seatId=6001;
    	Seat seat =new Seat("A12",true,ticket);
        given(seatService.bookSeat(seat, ticket)).willReturn(null);
       

        this.mockMvc.perform(put("/seat/updateseatbyid/{sId}", seatId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(seat)))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldDeleteSeat() throws Exception {
    	long seatId=6001;
    	Seat seat =new Seat("A12",true,ticket);
        given(seatService.bookSeat(seat, ticket)).willReturn(seat);
        doNothing().when(seatService).cancelSeatBooking(seatId);

        this.mockMvc.perform(delete("/seat/deleteseatbyid/{sId}", seat.getSeatId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seatNumber", is(seat.getSeatNumber())))
                .andExpect(jsonPath("$.seatStatus", is(seat.isSeatStatus())))
                .andExpect(jsonPath("$.ticket", is(seat.getTicket())));

    }

    @Test
    void shouldReturn404WhenDeletingNonExistingAdmin() throws Exception {
    	long seatId=6001;
    	Seat seat =new Seat("A12",true,ticket);
    	given(seatService.bookSeat(seat, ticket)).willReturn(null);

        this.mockMvc.perform(delete("/seat/deleteseatbyid/{sId}", seatId))
                .andExpect(status().isNotFound());

    }
}
