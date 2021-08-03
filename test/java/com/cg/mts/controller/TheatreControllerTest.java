package com.cg.mts.controller;


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

import com.cg.mts.controller.TheatreController;
import com.cg.mts.entity.Theatre;
import com.cg.mts.service.TheatreService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TheatreController.class)
@ActiveProfiles("test")
public class TheatreControllerTest 
{
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private TheatreService theatreService;

    @Autowired
    private ObjectMapper objectMapper;
    
    private List<Theatre> theatreList;
   
	
    @BeforeEach
    void setUp() 
    {
        this.theatreList = new ArrayList<>();
        this.theatreList.add(new Theatre("INOX","07890011788"));
        this.theatreList.add(new Theatre("PVR","07980446198"));
        this.theatreList.add(new Theatre("MAX","07894456789"));
    }
    
    @Test
    void shouldFetchAllTheatre() throws Exception {
        given(theatreService.viewAllTheatre()).willReturn(this.theatreList);

        this.mockMvc.perform(get("/theatre/getalltheatre"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(theatreList.size())));
    }
    
    @Test
    void shouldFindTheatreById() throws Exception {
        long theatreId=8001;
        Theatre theatre = new Theatre("INOX","7890011788");
        given(theatreService.viewTheatreById(theatreId)).willReturn(theatre);

        this.mockMvc.perform(get("/theatre/gettheatrebyid/{thId}", theatreId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.theatreName", is(theatre.getTheatreName())))
                .andExpect(jsonPath("$.theatreContact", is(theatre.getTheatreContact())));
              
    }
    
    @Test
    void shouldReturn404WhenFetchingNonExistingTheatre() throws Exception {
        long theatreId = 8001;
        given(theatreService.viewTheatreById(theatreId)).willReturn(null);

        this.mockMvc.perform(get("/theatre/gettheatrebyid/{trhId}", theatreId))
                .andExpect(status().isNotFound());

    }
    
    @Test
    void shouldAddNewTheatre() throws Exception {
    
        given(theatreService.saveTheatre(any(Theatre.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Theatre theatre = new Theatre("Iniox","7890011788");
		this.mockMvc.perform(post("/theatre/addtheatre/{thId}")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(theatre)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.theatreName", is(theatre.getTheatreName())))
                .andExpect(jsonPath("$.theatreContact", is(theatre.getTheatreContact())));
              
    }

    
    
    @Test
    void shouldUpdateTheatre() throws Exception {
    	long theatreId = 8001;
    	Theatre theatre = new Theatre("Iniox","7890011788");
    	given(theatreService.viewTheatreById(theatreId)).willReturn(theatre);
    	given(theatreService.updateTheatre(any(Theatre.class),theatreId)).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/theatre/updatetheatrebyid/{thid}", theatre.getTheatreId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(theatre)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.theatreName", is(theatre.getTheatreName())))
                .andExpect(jsonPath("$.theatreContact", is(theatre.getTheatreContact())));

    }

    @Test
    void shouldReturn404WhenUpdatingNonExistingTheatre() throws Exception {
        long theatreId = 8001;
        given(theatreService.viewTheatreById(theatreId)).willReturn(null);
        Theatre theatre = new Theatre("Iniox","7890011788");
        this.mockMvc.perform(put("/api/customers/{id}", theatreId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(theatre)))
                .andExpect(status().isNotFound());

    }

    
    @Test
    void shouldDeleteTheatre() throws Exception {
    	long theatreId = 8001;
    	Theatre theatre = new Theatre("Iniox","7890011788");
    	given(theatreService.viewTheatreById(theatreId)).willReturn(theatre);
        doNothing().when(theatreService).deleteTheatre(theatre.getTheatreId());

        this.mockMvc.perform(delete("/theatre/deletetheatrebyid/{thid}", theatre.getTheatreId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.theatreName", is(theatre.getTheatreName())))
                .andExpect(jsonPath("$.theatreContact", is(theatre.getTheatreContact())));

    }

    @Test
    void shouldReturn404WhenDeletingNonExistingTheatre() throws Exception {
    	long theatreId = 8001;
    	given(theatreService.viewTheatreById(theatreId)).willReturn(null);

        this.mockMvc.perform(delete("/theatre/deletetheatrebyid/{thid}", theatreId))
                .andExpect(status().isNotFound());

    }
    
    @Test
    void shouldFindTheatreByName() throws Exception {
        String Name="Inox";
        String theatreName="Inox";
        theatreList=new ArrayList<Theatre>();
        Theatre theatre = new Theatre("Inox","0908765432");
        given(theatreService.findByTheatreName(Name)).willReturn(theatreList);

		this.mockMvc.perform(get("/theatre/gettheatrebyname/{thname}",theatreName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.theatreName", is(theatre.getTheatreName())))
                .andExpect(jsonPath("$.theatreContact", is(theatre.getTheatreContact())));

    }
    
    @Test
    void shouldReturn404WhenFetchingNonExistingTheatreByName() throws Exception {
        long Id=1301;
        String Name="Inox";
        String theatreName="Inox";
        given(theatreService.findByTheatreName(Name)).willReturn(null);

        this.mockMvc.perform(get("/theatre/gettheatrebyname/{thname}",theatreName))
                .andExpect(status().isNotFound());

    }
}
