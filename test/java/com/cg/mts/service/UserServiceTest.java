package com.cg.mts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.User;
import com.cg.mts.exception.UserNotFoundException;
import com.cg.mts.repository.IUserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class UserServiceTest {
	private UserService userService;
    private IUserRepository IUserRepository;

    @BeforeEach
    void setUp() {
    	IUserRepository = mock(IUserRepository.class);
        userService = new UserService();
    }

    @Test
    void shouldSavedUserSuccessfully() throws UserNotFoundException {
    	User user = new User();
        given(userService.viewUserById(user.getUserId())).willReturn(null);
        given(IUserRepository.save(user)).willAnswer(invocation -> invocation.getArgument(0));

        User savedUser = userService.saveUser(user);

        assertThat(savedUser).isNotNull();

        verify(IUserRepository).save(any(User.class));
    }

    @Test
    void shouldThrowErrorWhenSaveUserWithExistingMobileNumber() {
    	User user = new User();
    	
        given(userService.viewUserById(user.getUserId())).willReturn(user);

        assertThrows(UserNotFoundException.class, () -> {
            userService.saveUser(user);
        });

        verify(IUserRepository, never()).save(any(User.class));
    }
}
