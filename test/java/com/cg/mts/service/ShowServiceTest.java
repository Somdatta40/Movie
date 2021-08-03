package com.cg.mts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Show;
import com.cg.mts.exception.ShowNotFoundException;
import com.cg.mts.repository.IShowRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class ShowServiceTest {
	private ShowService showService;
    private IShowRepository IShowRepository;

    @BeforeEach
    void setUp() {
    	IShowRepository = mock(IShowRepository.class);
        showService = new ShowService();
    }

    @Test
    void shouldSavedUserSuccessfully() throws ShowNotFoundException {
    	Show show = new Show();
    	given(showService.addShow(show)).willReturn(show);
        given(IShowRepository.save(show)).willAnswer(invocation -> invocation.getArgument(0));

        Show savedShow = showService.addShow(show);

        assertThat(savedShow).isNotNull();

        verify(IShowRepository).save(any(Show.class));
    }

    @Test
    void shouldThrowErrorWhenSaveUserWithExistingMobileNumber() {
    	Show show = new Show();
    	
    	given(showService.addShow(show)).willReturn(show);

        assertThrows(ShowNotFoundException.class, () -> {
            showService.addShow(show);
        });

        verify(IShowRepository, never()).save(any(Show.class));
    }
}
