package com.cg.mts.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Screen;
import com.cg.mts.entity.Show;
import com.cg.mts.service.ShowService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ShowController.class)
@ActiveProfiles("test")
public class ShowControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShowService showService;

    @Autowired
    private ObjectMapper objectMapper;
    
    private List<Show> showList;
    static Movie movie=new Movie (); 
	static Screen screen=new Screen();
	static LocalTime localtime = LocalTime.parse("01:00");
    @BeforeEach
    void setUp() 
    {
        this.showList = new ArrayList<>();
        this.showList.add(new Show(localtime,localtime,"cda",8001,movie,screen));
        this.showList.add(new Show(localtime,localtime,"xyz",8001,movie,screen));
        this.showList.add(new Show(localtime,localtime,"ddlj",8002,movie,screen));

        //objectMapper.registerModule(new ProblemModule());
        //objectMapper.registerModule(new ConstraintViolationProblemModule());
    }
    
    @Test
    void shouldFetchAllUsers() throws Exception {
        given(showService.viewAllShows()).willReturn(this.showList);

        this.mockMvc.perform(get("/show/getallshows"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(showList.size())));
    }
    @Test
    void shouldFindShowById() throws Exception 
    {
        long showId=7001;
        Show show = new Show(localtime,localtime,"cda",8001,movie,screen);
        given(showService.viewShow(showId)).willReturn(show);

        this.mockMvc.perform(get("/show/getshowbyid/{id}", showId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.showStartTime", is(show.getShowStartTime())))
                .andExpect(jsonPath("$.showEndTime", is(show.getShowEndTime())))
                .andExpect(jsonPath("$.showName", is(show.getShowName())))
                .andExpect(jsonPath("$.movie", is(show.getMovie())))
                .andExpect(jsonPath("$.screen", is(show.getScreen())))
        		.andExpect(jsonPath("$.theatreId", is(show.getTheatreId())));
    }    
    @Test
    void shouldReturn404WhenFetchingNonExistingUser() throws Exception {
        long showId = 7001;
        given(showService.viewShow(showId)).willReturn(null);

        this.mockMvc.perform(get("/show/getshowbyid/{id}", showId))
                .andExpect(status().isNotFound());

    }
    @Test
    void shouldFindShowByTheatreId() throws Exception 
    {
        long theatreId=8001;
        Show show = new Show(localtime,localtime,"cda",8001,movie,screen);
        given(showService.viewShowList(theatreId)).willReturn((List<Show>) show);

        this.mockMvc.perform(get("/show/getshowbythid/{thId}", theatreId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.showStartTime", is(show.getShowStartTime())))
                .andExpect(jsonPath("$.showEndTime", is(show.getShowEndTime())))
                .andExpect(jsonPath("$.showName", is(show.getShowName())))
                .andExpect(jsonPath("$.movie", is(show.getMovie())))
                .andExpect(jsonPath("$.screen", is(show.getScreen())));
    }    
    @Test
    void shouldReturn404WhenFetchingNonExistingShowByTheatre() throws Exception {
        long theatreId = 8001;
        given(showService.viewShowList(theatreId)).willReturn(null);

        this.mockMvc.perform(get("/show/getshowbythid/{thId}", theatreId))
                .andExpect(status().isNotFound());

    }
    
    @Test
    void shouldFindShowByMovieId() throws Exception 
    {
        long movieId=4001;
        Show show = new Show(localtime,localtime,"cda",8001,movie,screen);
        given(showService.findByMovie(movie)).willReturn((List<Show>) show);

        this.mockMvc.perform(get("/show/getshowbymid/{mId}", movie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.showStartTime", is(show.getShowStartTime())))
                .andExpect(jsonPath("$.showEndTime", is(show.getShowEndTime())))
                .andExpect(jsonPath("$.showName", is(show.getShowName())))
                .andExpect(jsonPath("$.screen", is(show.getScreen())))
                .andExpect(jsonPath("$.theatreId", is(show.getTheatreId())));
    }    
    @Test
    void shouldReturn404WhenFetchingNonExistingShowByMovie() throws Exception {
        long movieId = 4001;
        given(showService.findByMovie(movie)).willReturn(null);

        this.mockMvc.perform(get("/show/getshowbymid/{mId}", movie))
                .andExpect(status().isNotFound());

    }
    
    @Test
    void shouldFindShowByScreenId() throws Exception 
    {
       
        Show show = new Show(localtime,localtime,"cda",8001,movie,screen);
        given(showService.findByScreen(screen)).willReturn((List<Show>) show);

        this.mockMvc.perform(get("/show/getshowbyscid/{scId}", screen))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.showStartTime", is(show.getShowStartTime())))
                .andExpect(jsonPath("$.showEndTime", is(show.getShowEndTime())))
                .andExpect(jsonPath("$.showName", is(show.getShowName())))
                .andExpect(jsonPath("$.movie", is(show.getMovie())))
                .andExpect(jsonPath("$.theatreId", is(show.getTheatreId())));
    }    
    @Test
    void shouldReturn404WhenFetchingNonExistingShowByScreen() throws Exception {
        long movieId = 4001;
        given(showService.findByScreen(screen)).willReturn(null);

        this.mockMvc.perform(get("/show/getshowbyscid/{scId}", screen))
                .andExpect(status().isNotFound());

    }
    
    
    @Test
    void shouldCreateNewShow() throws Exception {
        given(showService.saveShow(any(Show.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Show show = new Show(localtime,localtime,"cda",8001,movie,screen);
        this.mockMvc.perform(post("/show/addshowbymid/{mId}",movie)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(show)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.showStartTime", is(show.getShowStartTime())))
                .andExpect(jsonPath("$.showEndTime", is(show.getShowEndTime())))
                .andExpect(jsonPath("$.showName", is(show.getShowName())))
                .andExpect(jsonPath("$.movie", is(show.getMovie())))
                .andExpect(jsonPath("$.screen", is(show.getScreen())))
                .andExpect(jsonPath("$.theatreId", is(show.getTheatreId())))
                ;

    }

    @Test
    void shouldReturn400WhenCreateNewShowWithoutShowName() throws Exception {
        Show show = new Show(localtime,localtime,null,8001,movie,screen);

        this.mockMvc.perform(post("/show/addshowbymid/{mId}",movie)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(show)))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", is("application/problem+json")))
                .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
                .andExpect(jsonPath("$.title", is("Constraint Violation")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.violations", hasSize(1)))
                .andExpect(jsonPath("$.violations[0].field", is("email")))
                .andExpect(jsonPath("$.violations[0].message", is("Email should not be empty")))
                .andReturn()
        ;
    }

    @Test
    void shouldUpdateShow() throws Exception {
        long showId=7001;
        Show show = new Show(localtime,localtime,"cda",8001,movie,screen);
        given(showService.findByMovie(movie)).willReturn((List<Show>) show);
        given(showService.updateShow(any(Show.class),showId)).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/show/updateshowbyid/{showId}", show.getShowId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(show)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.showStartTime", is(show.getShowStartTime())))
                .andExpect(jsonPath("$.showEndTime", is(show.getShowEndTime())))
                .andExpect(jsonPath("$.showName", is(show.getShowName())))
                .andExpect(jsonPath("$.movie", is(show.getMovie())))
                .andExpect(jsonPath("$.screen", is(show.getScreen())))
        		.andExpect(jsonPath("$.theatreId", is(show.getTheatreId())));

    }

    @Test
    void shouldReturn404WhenUpdatingNonExistingShow() throws Exception {
    	long showId=7001;
    	given(showService.findByMovie(movie)).willReturn(null);
    	 Show show = new Show(localtime,localtime,"cda",8001,movie,screen);

        this.mockMvc.perform(put("/show/updateshowbyid/{showId}", showId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(show)))
                .andExpect(status().isNotFound());

    }
    
    @Test
    void shouldUpdateShowByScreen() throws Exception {
        long showId=7001;
        Show show = new Show(localtime,localtime,"cda",8001,movie,screen);
        given(showService.findByMovie(movie)).willReturn((List<Show>) show);
        given(showService.updateShowByScreen(any(Show.class), screen, showId)).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/show/updateshowbyscid/{scId}/{shId}", show.getShowId(),show.getScreen())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(show)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.showStartTime", is(show.getShowStartTime())))
                .andExpect(jsonPath("$.showEndTime", is(show.getShowEndTime())))
                .andExpect(jsonPath("$.showName", is(show.getShowName())))
                .andExpect(jsonPath("$.movie", is(show.getMovie())))
                .andExpect(jsonPath("$.screen", is(show.getScreen())))
        		.andExpect(jsonPath("$.theatreId", is(show.getTheatreId())));

    }

    @Test
    void shouldReturn404WhenUpdatingNonExistingShowByScreenId() throws Exception {
    	long showId=7001;
    	given(showService.findByMovie(movie)).willReturn(null);
    	 Show show = new Show(localtime,localtime,"cda",8001,movie,screen);

        this.mockMvc.perform(put("/show/updateshowbyscid/{scId}/{shId}", showId,screen)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(show)))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldDeleteshow() throws Exception {
    	 long showId=7001;
         Show show = new Show(localtime,localtime,"cda",8001,movie,screen);
         given(showService.findByMovie(movie)).willReturn((List<Show>) show);
        doNothing().when(showService).removeShow(show.getShowId());

        this.mockMvc.perform(delete("/show/deleteshowbyid/{showId}", show.getShowId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.showStartTime", is(show.getShowStartTime())))
                .andExpect(jsonPath("$.showEndTime", is(show.getShowEndTime())))
                .andExpect(jsonPath("$.showName", is(show.getShowName())))
                .andExpect(jsonPath("$.movie", is(show.getMovie())))
                .andExpect(jsonPath("$.screen", is(show.getScreen())))
        		.andExpect(jsonPath("$.theatreId", is(show.getTheatreId())));

    }

    @Test
    void shouldReturn404WhenDeletingNonExistingUser() throws Exception {
    	 long showId=7001;
        given(showService.findByMovie(movie)).willReturn(null);

        this.mockMvc.perform(delete("/show/deleteshowbyid/{showId}", showId))
                .andExpect(status().isNotFound());

    }
        
    
}
