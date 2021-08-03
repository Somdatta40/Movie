package com.cg.mts.controller;

import com.cg.mts.entity.Admin;
import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Customer;
import com.cg.mts.entity.Ticket;
import com.cg.mts.service.AdminService;
import com.cg.mts.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BookingController.class)
@ActiveProfiles("test")
public class BookingControllerTest {
	
	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private BookingService bookingService;

	    @Autowired
	    private ObjectMapper objectMapper;

	    private List<Booking> bookingList;

	    static LocalDate localdate = LocalDate.parse("2021-07-10");
		static Ticket ticket=new Ticket();
		static Customer customer=new Customer();
	    @BeforeEach
	    void setUp() {
	        this.bookingList = new ArrayList<>();
	        this.bookingList.add(new  Booking(4001,7001,localdate,"Available",ticket,customer,650.50));
	        this.bookingList.add(new  Booking(4002,7002,localdate,"Booked",ticket,customer,655.50));
	        this.bookingList.add(new  Booking(4003,7003,localdate,"Available",ticket,customer,650.50));

	        //objectMapper.registerModule(new ProblemModule());
	        //objectMapper.registerModule(new ConstraintViolationProblemModule());
	    }

	    @Test
	    void shouldFindBookingBySId() throws Exception {
	        long showId = 7001;
	        Booking booking =new Booking(4001,7001,localdate,"Available",ticket,customer,650.50);
	        given(bookingService.showBookingList(showId)).willReturn((List<Booking>) booking);

	        this.mockMvc.perform(get("/booking/getbookingbyshid/{shId}", showId))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.movieId", is(booking.getMovieId())))
	                .andExpect(jsonPath("$.bookingDate", is(booking.getBookingDate())))
	                .andExpect(jsonPath("$.status", is(booking.getStatus())))
	                .andExpect(jsonPath("$.ticket", is(booking.getTicket())))
	                .andExpect(jsonPath("$.customer", is(booking.getCustomer())))
	                .andExpect(jsonPath("$.totalAmount", is(booking.getTotalAmount())))
	               
	        ;
	    }

	    @Test
	    void shouldReturn404WhenFetchingNonExistingBooking() throws Exception {
	        long showId = 7001;
	        given(bookingService.showBookingList(showId)).willReturn(null);

	        this.mockMvc.perform(get("/booking/getbookingbyshid/{shId}", showId))
	                .andExpect(status().isNotFound());

	    }
	    
	    @Test
	    void shouldFindBookingByMId() throws Exception {
	        long movieId = 4001;
	        Booking booking =new Booking(4001,7001,localdate,"Available",ticket,customer,650.50);
	        given(bookingService.showBookingsByMovieId(movieId)).willReturn((List<Booking>) booking);

	        this.mockMvc.perform(get("/booking/getbookingbymid/{mId}", movieId))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.showId", is(booking.getShowId())))
	                .andExpect(jsonPath("$.bookingDate", is(booking.getBookingDate())))
	                .andExpect(jsonPath("$.status", is(booking.getStatus())))
	                .andExpect(jsonPath("$.ticket", is(booking.getTicket())))
	                .andExpect(jsonPath("$.customer", is(booking.getCustomer())))
	                .andExpect(jsonPath("$.totalAmount", is(booking.getTotalAmount())))
	               
	        ;
	    }

	    @Test
	    void shouldReturn404WhenFetchingNonExistingBookingByMId() throws Exception {
	        long movieId = 7001;
	        given(bookingService.showBookingsByMovieId(movieId)).willReturn(null);

	        this.mockMvc.perform(get("/booking/getbookingbyshid/{shId}", movieId))
	                .andExpect(status().isNotFound());

	    }
	    
	    @Test
	    void shouldFindBookingByDate() throws Exception {
	    	LocalDate bookingDate=localdate;
	        Booking booking =new Booking(4001,7001,localdate,"Available",ticket,customer,650.50);
	        given(bookingService.showBookingsByDate(bookingDate)).willReturn((List<Booking>) booking);

	        this.mockMvc.perform(get("/booking/getbookingbydate/{localdate}", bookingDate))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.movieId", is(booking.getMovieId())))
	                .andExpect(jsonPath("$.showId", is(booking.getShowId())))
	                .andExpect(jsonPath("$.status", is(booking.getStatus())))
	                .andExpect(jsonPath("$.ticket", is(booking.getTicket())))
	                .andExpect(jsonPath("$.customer", is(booking.getCustomer())))
	                .andExpect(jsonPath("$.totalAmount", is(booking.getTotalAmount())))
	               
	        ;
	    }

	    @Test
	    void shouldReturn404WhenFetchingNonExistingBookingByDate() throws Exception {
	    	LocalDate bookingDate=localdate;
	    	given(bookingService.showBookingsByDate(bookingDate)).willReturn(null);

	        this.mockMvc.perform(get("/booking/getbookingbydate/{localdate}", bookingDate))
	                .andExpect(status().isNotFound());

	    }

	    @Test
	    void shouldFindBookingByCustId() throws Exception {
	    	
	        Booking booking =new Booking(4001,7001,localdate,"Available",ticket,customer,650.50);
	        given(bookingService.findByCustomer(customer)).willReturn((List<Booking>) booking);

	        this.mockMvc.perform(get("/booking/getbookingbycustid/{custId}", customer))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.movieId", is(booking.getMovieId())))
	                .andExpect(jsonPath("$.showId", is(booking.getShowId())))
	                .andExpect(jsonPath("$.status", is(booking.getStatus())))
	                .andExpect(jsonPath("$.ticket", is(booking.getTicket())))
	                .andExpect(jsonPath("$.totalAmount", is(booking.getTotalAmount())))
	               
	        ;
	    }

	    @Test
	    void shouldReturn404WhenFetchingNonExistingBookingByCustId() throws Exception {
	    	
	    	given(bookingService.findByCustomer(customer)).willReturn(null);

	        this.mockMvc.perform(get("/booking/getbookingbycustid/{custId}", customer))
	                .andExpect(status().isNotFound());

	    }
	    @Test
	    void shouldCreateNewBooking() throws Exception {
	        given(bookingService.addBooking(any(Booking.class))).willAnswer((invocation) -> invocation.getArgument(0));

	        Booking booking =new Booking(4001,7001,localdate,"Available",ticket,customer,650.50);
	        this.mockMvc.perform(post("/booking/addbookingbycustid/{custId}")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(booking)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.movieId", is(booking.getMovieId())))
	                .andExpect(jsonPath("$.showId", is(booking.getShowId())))
	                .andExpect(jsonPath("$.bookingDate", is(booking.getBookingDate())))
	                .andExpect(jsonPath("$.status", is(booking.getStatus())))
	                .andExpect(jsonPath("$.ticket", is(booking.getTicket())))
	                .andExpect(jsonPath("$.customer", is(booking.getCustomer())))
	                .andExpect(jsonPath("$.totalAmount", is(booking.getTotalAmount())));

	    }

	    @Test
	    void shouldReturn400WhenCreateNewUserWithoutStatus() throws Exception {
	    	 Booking booking =new Booking(4001,7001,localdate,null,ticket,customer,650.50);

	        this.mockMvc.perform(post("/booking/addbookingbycustid/{custId}")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(booking)))
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
	    void shouldUpdateBooking() throws Exception {
	        
	        long bookingId=2001;
	   	 Booking booking =new Booking(4001,7001,localdate,null,ticket,customer,650.50);
	        given(bookingService.updateBooking(booking, ticket, bookingId)).willReturn(booking);
	        given(bookingService.updateBooking(any(Booking.class), ticket,bookingId)).willAnswer((invocation) -> invocation.getArgument(0));

	        this.mockMvc.perform(put("/booking/updatebookingbytid/{tId}/{bId}", booking.getBookingId(),booking.getTicket())
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(booking)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.movieId", is(booking.getMovieId())))
	                .andExpect(jsonPath("$.showId", is(booking.getShowId())))
	                .andExpect(jsonPath("$.bookingDate", is(booking.getBookingDate())))
	                .andExpect(jsonPath("$.status", is(booking.getStatus())))
	                .andExpect(jsonPath("$.ticket", is(booking.getTicket())))
	                .andExpect(jsonPath("$.customer", is(booking.getCustomer())))
	                .andExpect(jsonPath("$.totalAmount", is(booking.getTotalAmount())));

	    }

	    @Test
	    void shouldReturn404WhenUpdatingNonExistingBooking() throws Exception {
	    	long bookingId=2001;
	    	Booking booking =new Booking(4001,7001,localdate,null,ticket,customer,650.50);
	    	given(bookingService.updateBooking(booking, ticket, bookingId)).willReturn(null);
	        

	        this.mockMvc.perform(put("/booking/updatebookingbytid/{tId}/{bId}", booking.getBookingId(),booking.getTicket())
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(booking)))
	                .andExpect(status().isNotFound());

	    }

	    @Test
	    void shouldCancelBooking() throws Exception {
	        
	        long bookingId=2001;
	   	 Booking booking =new Booking(4001,7001,localdate,null,ticket,customer,650.50);
	        given(bookingService.cancelBooking(booking, bookingId)).willReturn(booking);
	        given(bookingService.cancelBooking(any(Booking.class),bookingId)).willAnswer((invocation) -> invocation.getArgument(0));

	        this.mockMvc.perform(put("/booking/cancelbookingbybid/{bookId}", booking.getBookingId())
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(booking)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.movieId", is(booking.getMovieId())))
	                .andExpect(jsonPath("$.showId", is(booking.getShowId())))
	                .andExpect(jsonPath("$.bookingDate", is(booking.getBookingDate())))
	                .andExpect(jsonPath("$.status", is(booking.getStatus())))
	                .andExpect(jsonPath("$.ticket", is(booking.getTicket())))
	                .andExpect(jsonPath("$.customer", is(booking.getCustomer())))
	                .andExpect(jsonPath("$.totalAmount", is(booking.getTotalAmount())));

	    }

	    @Test
	    void shouldReturn404WhenCancellingNonExistingBooking() throws Exception {
	    	long bookingId=2001;
	    	Booking booking =new Booking(4001,7001,localdate,null,ticket,customer,650.50);
	    	given(bookingService.cancelBooking(booking, bookingId)).willReturn(null);
	        

	        this.mockMvc.perform(put("/booking/cancelbookingbybid/{bookId}", booking.getBookingId())
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(booking)))
	                .andExpect(status().isNotFound());

	    }
	    

}
