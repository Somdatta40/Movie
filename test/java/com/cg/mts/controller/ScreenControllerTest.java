package com.cg.mts.controller;
import com.cg.mts.entity.Screen;
import com.cg.mts.entity.Theatre;
import com.cg.mts.service.ScreenService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ScreenController.class)
@ActiveProfiles("test")
public class ScreenControllerTest {
	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private ScreenService screenService;

	    @Autowired
	    private ObjectMapper objectMapper;

	    private List<Screen> screenList;
	    static Theatre theatre=new Theatre();
	    
	    @BeforeEach
	    void setUp() {
	        this.screenList = new ArrayList<>();
	        this.screenList.add(new Screen("ss1",10, 10,theatre));
	        this.screenList.add(new Screen("ss2",20, 20,theatre));
	        this.screenList.add(new Screen("ss3",30, 30,theatre));

	    }

	    @Test
	    void shouldFetchAllScreen() throws Exception {
	        given(screenService.viewAllScreenDetails()).willReturn(this.screenList);

	        this.mockMvc.perform(get("/screen/getallscreensdetails"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.size()", is(screenList.size())));
	    }
	    
	   
	    @Test
	    void shouldFindScreenById() throws Exception {
	       long screenId=7001;
	        Screen screen = new Screen("ss1",10, 10,theatre);
	        given(screenService.viewScreenDetailsById(screenId)).willReturn(screen);

	        this.mockMvc.perform(get("/screen/getscreenbythid/{thId}", screenId))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.screenName",is(screen.getScreenName()))) 
	                .andExpect(jsonPath("$.rows",is(screen.getRows()))) 
	                .andExpect(jsonPath("$.columns",is(screen.getColumns()))) ; 

	        ;
	    }

	    @Test
	    void shouldReturn404WhenFetchingNonExistingScreen() throws Exception {
	        long screenId = 7001;
	        given(screenService.viewScreenDetailsById(screenId)).willReturn(null);

	        this.mockMvc.perform(get("screen/getscreenbythid/{thId}", screenId))
	                .andExpect(status().isNotFound());

	    }

	    
	    
	    @Test
	    void shouldCreateNewScreen() throws Exception {
	        given(screenService.addScreenDetails(any(Screen.class))).willAnswer((invocation) -> invocation.getArgument(0));

	        Screen screen = new Screen("ss1",10, 10,theatre);
	        this.mockMvc.perform(post("/screen/addScreenbythid/{thId}")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(screen)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.screenName",is(screen.getScreenName()))) 
	                .andExpect(jsonPath("$.rows",is(screen.getRows()))) 
	                .andExpect(jsonPath("$.columns",is(screen.getColumns()))) ; 

	    }
	    @Test
	    void shouldReturn400WhenCreateNewUserWithoutName() throws Exception {
	        Screen screen = new Screen(null,10, 10,theatre);

	        this.mockMvc.perform(post("/screen/addScreenbythid/{thId}")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(screen)))
	                .andExpect(status().isBadRequest())
	                .andExpect(header().string("Content-Type", is("application/problem+json")))
	                .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
	                .andExpect(jsonPath("$.title", is("Constraint Violation")))
	                .andExpect(jsonPath("$.status", is(400)))
	                .andExpect(jsonPath("$.violations", hasSize(1)))
	                .andExpect(jsonPath("$.violations[0].field", is("screenName")))
	                .andExpect(jsonPath("$.violations[0].message", is("screen name should not be empty")))
	                .andReturn()
	        ;
	    }

	    @Test
	    void shouldUpdateScreen() throws Exception {
	        long screenId = 7001;
	        Screen screen = new Screen("ss1",10, 10,theatre);
	        given(screenService.viewScreenDetailsById(screenId)).willReturn(screen);
	        given(screenService.updateScreenDetails(any(Screen.class),screenId)).willAnswer((invocation) -> invocation.getArgument(0));

	        this.mockMvc.perform(put("/screen/updatescreensdetailsbyid/{scrId", screen.getScreenId())
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(screen)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.screenName",is(screen.getScreenName()))) 
	                .andExpect(jsonPath("$.rows",is(screen.getRows()))) 
	                .andExpect(jsonPath("$.columns",is(screen.getColumns()))) ; 


	    }

	    @Test
	    void shouldReturn404WhenUpdatingNonExistingScreen() throws Exception {
	        long screenId = 7001;
	        given(screenService.viewScreenDetailsById(screenId)).willReturn(null);
	        Screen screen = new Screen("ss1",10, 10,theatre);

	        this.mockMvc.perform(put("/screen/updatescreensdetailsbyid/{scrId", screenId)
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(screen)))
	                .andExpect(status().isNotFound());

	    }

	    @Test
	    void shouldDeleteScreen() throws Exception {
	        long screenId = 7001;
	        Screen screen = new Screen("ss1",10, 10,theatre);
	        given(screenService.viewScreenDetailsById(screenId)).willReturn(screen);
	        doNothing().when(screenService).deleteScreenDetails(screen.getScreenId());

	        this.mockMvc.perform(delete("/screen/deletescreensdetailsbyid/{scrId}", screen.getScreenId()))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.screenName",is(screen.getScreenName()))) 
	                .andExpect(jsonPath("$.rows",is(screen.getRows()))) 
	                .andExpect(jsonPath("$.columns",is(screen.getColumns()))) ; 

	              
	    }

	    @Test
	    void shouldReturn404WhenDeletingNonExistingScreen() throws Exception {
	        long screenId = 7001;
	        given(screenService.viewScreenDetailsById(screenId)).willReturn(null);

	        this.mockMvc.perform(delete("/screen/deletescreensdetailsbyid/{scrId}", screenId))
	                .andExpect(status().isNotFound());

	    }
	    

}
