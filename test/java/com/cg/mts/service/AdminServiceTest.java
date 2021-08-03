package com.cg.mts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Admin;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.repository.IAdminRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class AdminServiceTest {
	private AdminService adminService;
    private IAdminRepository IAdminRepository;

    @BeforeEach
    void setUp() {
    	IAdminRepository = mock(IAdminRepository.class);
        adminService = new AdminService();
    }

    @Test
    void shouldSavedUserSuccessfully() throws AdminNotFoundException {
    	Admin admin = new Admin("abc","09903234157");
        given(adminService.viewAdminById(admin.getAdminId())).willReturn(null);
        given(IAdminRepository.save(admin)).willAnswer(invocation -> invocation.getArgument(0));

        Admin savedAdmin = adminService.saveAdmin(admin);

        assertThat(savedAdmin).isNotNull();

        verify(IAdminRepository).save(any(Admin.class));
    }

    @Test
    void shouldThrowErrorWhenSaveUserWithExistingMobileNumber() {
    	Admin admin = new Admin("bcd","09903234158");
    	
        given(adminService.viewAdminById(admin.getAdminId())).willReturn(admin);

        assertThrows(AdminNotFoundException.class, () -> {
            adminService.saveAdmin(admin);
        });

        verify(IAdminRepository, never()).save(any(Admin.class));
    }
}
