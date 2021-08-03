package com.cg.mts.controller;

import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Theatre;
import com.cg.mts.service.MovieService;
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
@WebMvcTest(controllers = MovieController.class)
@ActiveProfiles("test")
public class MovieControllerTest {
	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private MovieService movieService;

	    @Autowired
	    private ObjectMapper objectMapper;

	    private List<Movie> movieList;
	    static Theatre theatre=new Theatre();
	    
	    @BeforeEach
	    void setUp() {
	        this.movieList = new ArrayList<>();
	        this.movieList.add(new Movie("Conjuring", "Horror", "02:30", "English",theatre));
	        this.movieList.add(new Movie("Spider-Man", "Action", "02:40", "English",theatre));
	        this.movieList.add(new Movie("Gravity", "Sci-Fi", "01:31", "English",theatre));
	        
	    }
	    @Test
	    void shouldFetchAllMovie() throws Exception {
	        given(movieService.viewAllMovie()).willReturn(this.movieList);

	        this.mockMvc.perform(get("/movie/getallmovies"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.size()", is(movieList.size())));
	    }
	    @Test
	    void shouldFindMovieById() throws Exception {
	        long movieId = 4001;
	        Movie movie = new Movie("Conjuring", "Horror", "02:30", "English",theatre);
	        given(movieService.viewMovieById(movieId)).willReturn(movie);

	        this.mockMvc.perform(get("/movie/getmoviebyid/{movieId}", movieId))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.movieName", is(movie.getMovieName())))
	                .andExpect(jsonPath("$.movieGenre", is(movie.getMovieGenre())))
	                .andExpect(jsonPath("$.movieHours", is(movie.getMovieHours())))
	                .andExpect(jsonPath("$,language", is(movie.getLanguage())))
	                
	        ;
	    }
	    @Test
	    void shouldReturn404WhenFetchingNonExistingMovie() throws Exception {
	        long movieId = 4001;
	        given(movieService.viewMovieById(movieId)).willReturn(null);

	        this.mockMvc.perform(get("/movie/getmoviebyid/{movieId}", movieId))
	                .andExpect(status().isNotFound());

	    }
	    @Test
	    void shouldCreateNewMovie() throws Exception {
	        given(movieService.addMovie(any(Movie.class))).willAnswer((invocation) -> invocation.getArgument(0));

	        Movie movie = new Movie("Conjuring", "Horror", "02:30", "English",theatre);
	        this.mockMvc.perform(post("/movie/addmoviebythid/{thId}")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(movie)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.movieName", is(movie.getMovieName())))
	                .andExpect(jsonPath("$.movieGenre", is(movie.getMovieGenre())))
	                .andExpect(jsonPath("$.movieHours", is(movie.getMovieHours())))
	                .andExpect(jsonPath("$,language", is(movie.getLanguage())))
	                ;

	    }
	    @Test
	    void shouldReturn400WhenCreateNewUserWithoutName() throws Exception {
	        Movie movie = new Movie(null, "Horror", "02:30", "English",theatre);

	        this.mockMvc.perform(post("/movie/addmoviebythid/{thId}")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(movie)))
	                .andExpect(status().isBadRequest())
	                .andExpect(header().string("Content-Type", is("application/problem+json")))
	                .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
	                .andExpect(jsonPath("$.title", is("Constraint Violation")))
	                .andExpect(jsonPath("$.status", is(400)))
	                .andExpect(jsonPath("$.violations", hasSize(1)))
	                .andExpect(jsonPath("$.violations[0].field", is("movieName")))
	                .andExpect(jsonPath("$.violations[0].message", is("Movie name should not be empty")))
	                .andReturn()
	        ;
	    }
	    @Test
	    void shouldUpdateMovie() throws Exception {
	        long movieId = 4001;
	        Movie movie = new Movie("Conjuring", "Horror", "02:30", "English",theatre);
	        given(movieService.viewMovieById(movieId)).willReturn(movie);
	        given(movieService.updateMovie(any(Movie.class), movieId)).willAnswer((invocation) -> invocation.getArgument(0));

	        this.mockMvc.perform(put("/movie/updatemoviebyid/{movieId}", movie.getMovieId())
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(movie)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.movieName", is(movie.getMovieName())))
	                .andExpect(jsonPath("$.movieGenre", is(movie.getMovieGenre())))
	                .andExpect(jsonPath("$.movieHours", is(movie.getMovieHours())))
	                .andExpect(jsonPath("$,language", is(movie.getLanguage())))
	                ;

	    }
	    @Test
	    void shouldReturn404WhenUpdatingNonExistingMovie() throws Exception {
	        long movieId = 4001;
	        given(movieService.viewMovieById(movieId)).willReturn(null);
	        Movie movie = new Movie("Conjuring", "Horror", "02:30", "English",theatre);

	        this.mockMvc.perform(put("/movie/updatemoviebyid/{movieId}", movieId)
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(movie)))
	                .andExpect(status().isNotFound());

	    }

	    void shouldDeleteMovie() throws Exception {
	        long movieId = 1L;
	        Movie movie = new Movie();
	        given(movieService.viewMovieById(movieId)).willReturn(movie);
	        doNothing().when(movieService).deleteMovie(movie.getMovieId());

	        this.mockMvc.perform(delete("/movie/deletemoviebyid/{mId}", movie.getMovieId()))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.movieName", is(movie.getMovieName())))
	                .andExpect(jsonPath("$.movieGenre", is(movie.getMovieGenre())))
	                .andExpect(jsonPath("$.movieHours", is(movie.getMovieHours())))
	                .andExpect(jsonPath("$,language", is(movie.getLanguage())))
	                ;
	    }

	    @Test
	    void shouldReturn404WhenDeletingNonExistingMovie() throws Exception {
	        long movieId = 4001;
	        given(movieService.viewMovieById(movieId)).willReturn(null);

	        this.mockMvc.perform(delete("/movie/deletemoviebyid/{mId}", movieId))
	                .andExpect(status().isNotFound());

	    }

	    @Test
	    void shouldFindMovieByTheatreId() throws Exception {
	        long movieId = 4001;
	        Movie movie = new Movie("Conjuring", "Horror", "02:30", "English",theatre);
	        given(movieService.viewMovieById(movieId)).willReturn(movie);

	        this.mockMvc.perform(get("/movie/getmoviebythid/{thId}", movieId))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.movieName", is(movie.getMovieName())))
	                .andExpect(jsonPath("$.movieGenre", is(movie.getMovieGenre())))
	                .andExpect(jsonPath("$.movieHours", is(movie.getMovieHours())))
	                .andExpect(jsonPath("$,language", is(movie.getLanguage())))
	                
	        ;
	    }
	    @Test
	    void shouldReturn404WhenFetchingNonExistingMovieByTheatreId() throws Exception {
	        long movieId = 4001;
	        given(movieService.viewMovieById(movieId)).willReturn(null);

	        this.mockMvc.perform(get("/movie/getmoviebyid/{movieId}", movieId))
	                .andExpect(status().isNotFound());

	    }

}
