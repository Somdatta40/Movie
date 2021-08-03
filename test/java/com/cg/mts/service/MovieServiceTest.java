package com.cg.mts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Admin;
import com.cg.mts.entity.Movie;
import com.cg.mts.exception.MovieNotFoundException;
import com.cg.mts.repository.IMovieRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class MovieServiceTest {

	private MovieService movieService;
    private IMovieRepository IMovieRepository;

    @BeforeEach
    void setUp() {
    	IMovieRepository = mock(IMovieRepository.class);
        movieService = new MovieService();
    }

    @Test
    void shouldSavedUserSuccessfully() throws MovieNotFoundException {
    	Movie movie = new Movie();
        given(movieService.viewMovieById(movie.getMovieId())).willReturn(null);
        given(IMovieRepository.save(movie)).willAnswer(invocation -> invocation.getArgument(0));

        Movie savedMovie = movieService.addMovie(movie);

        assertThat(savedMovie).isNotNull();

        verify(IMovieRepository).save(any(Movie.class));
    }

    @Test
    void shouldThrowErrorWhenSaveUserWithExistingMobileNumber() {
    	Movie movie = new Movie();
    	
        given(movieService.viewMovieById(movie.getMovieId())).willReturn(movie);

        assertThrows(MovieNotFoundException.class, () -> {
            movieService.addMovie(movie);
        });

        verify(IMovieRepository, never()).save(any(Movie.class));
    }
}
