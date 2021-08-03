package com.cg.mts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Theatre;
import com.cg.mts.exception.TheatreNotFoundException;
import com.cg.mts.repository.ITheatreRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class TheatreServiceTest {
	private TheatreService theatreService;
    private ITheatreRepository ITheatreRepository;

    @BeforeEach
    void setUp() {
    	ITheatreRepository = mock(ITheatreRepository.class);
        theatreService = new TheatreService();
    }

    @Test
    void shouldSavedUserSuccessfully() throws TheatreNotFoundException {
    	Theatre theatre = new Theatre();
        given(theatreService.viewTheatreById(theatre.getTheatreId())).willReturn(null);
        given(ITheatreRepository.save(theatre)).willAnswer(invocation -> invocation.getArgument(0));

        Theatre savedTheatre = theatreService.saveTheatre(theatre);

        assertThat(savedTheatre).isNotNull();

        verify(ITheatreRepository).save(any(Theatre.class));
    }

    @Test
    void shouldThrowErrorWhenSaveUserWithExistingMobileNumber() {
    	Theatre theatre = new Theatre();
    	
        given(theatreService.viewTheatreById(theatre.getTheatreId())).willReturn(theatre);

        assertThrows(TheatreNotFoundException.class, () -> {
            theatreService.saveTheatre(theatre);
        });

        verify(ITheatreRepository, never()).save(any(Theatre.class));
    }
}
