package com.cg.mts.controller;

import com.cg.mts.entity.Admin;
import com.cg.mts.service.AdminService;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AdminController.class)
@ActiveProfiles("test")
public class AdminControllerTest {
	
	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private AdminService adminService;

	    @Autowired
	    private ObjectMapper objectMapper;

	    private List<Admin> adminList;

	    @BeforeEach
	    void setUp() {
	        this.adminList = new ArrayList<>();
	        this.adminList.add(new  Admin("sharmistha","0903234157"));
	        this.adminList.add(new  Admin("somdatta","09330792809"));
	        this.adminList.add(new  Admin("shreya","0903234166"));

	        //objectMapper.registerModule(new ProblemModule());
	        //objectMapper.registerModule(new ConstraintViolationProblemModule());
	    }

	    @Test
	    void shouldFetchAllAdmin() throws Exception {
	        given(adminService.viewAllAdmins()).willReturn(this.adminList);

	        this.mockMvc.perform(get("/admin/getalladmins"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.size()", is(adminList.size())));
	    }

	    @Test
	    void shouldFindAdminById() throws Exception {
	        long adminId = 1001;
	        Admin admin =new  Admin("shreya","0903234166");
	        given(adminService.viewAdminById(adminId)).willReturn(admin);

	        this.mockMvc.perform(get("/admin/getadminbyid/{id}", adminId))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.adminName", is(admin.getAdminName())))
	                .andExpect(jsonPath("$.adminContact", is(admin.getAdminContact())))
	               
	        ;
	    }

	    @Test
	    void shouldReturn404WhenFetchingNonExistingAdmin() throws Exception {
	        long adminId = 1001;
	        given(adminService.viewAdminById(adminId)).willReturn(null);

	        this.mockMvc.perform(get("/admin/getadminbyid/{adId}", adminId))
	                .andExpect(status().isNotFound());

	    }
	    
	    @Test
	    void shouldFindAdminByName() throws Exception {
	        String adminName="shreya";
	        Admin admin =new  Admin("shreya","0903234166");
	        given(adminService.findByAdminName(adminName)).willReturn(adminList);

	        this.mockMvc.perform(get("/admin/getadminbyname/{name}", adminName))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.adminContact", is(admin.getAdminContact())))
	               
	        ;
	    }

	    @Test
	    void shouldReturn404WhenFetchingNonExistingAdminName() throws Exception {
	    	 String adminName="shreya";
	    	  given(adminService.findByAdminName(adminName)).willReturn(null);

	        this.mockMvc.perform(get("/admin/getadminbyname/{adname}", adminName))
	                .andExpect(status().isNotFound());

	    }

	    @Test
	    void shouldCreateNewAdmin() throws Exception {
	        given(adminService.saveAdmin(any(Admin.class))).willAnswer((invocation) -> invocation.getArgument(0));

	        Admin admin = new Admin("shreya","0903234166");
	        this.mockMvc.perform(post("/admin/addadmin")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(admin)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.adminName", is(admin.getAdminName())))
	                .andExpect(jsonPath("$.adminContact", is(admin.getAdminContact())))
	                ;

	    }

	    @Test
	    void shouldReturn400WhenCreateNewUserWithoutName() throws Exception {
	       Admin admin = new Admin(null,"0903234166");

	        this.mockMvc.perform(post("/admin/addadmin")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(admin)))
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
	    void shouldUpdateAdmin() throws Exception {
	        long adminId = 1001;
	        Admin admin = new Admin("shreya","0903234166");
	        given(adminService.viewAdminById(adminId)).willReturn(admin);
	        given(adminService.updateAdmin(any(Admin.class), adminId)).willAnswer((invocation) -> invocation.getArgument(0));

	        this.mockMvc.perform(put("/admin/updateadminbyid/{adId}", admin.getAdminId())
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(admin)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.adminName", is(admin.getAdminName())))
	                .andExpect(jsonPath("$.adminContact", is(admin.getAdminContact())));

	    }

	    @Test
	    void shouldReturn404WhenUpdatingNonExistingUser() throws Exception {
	        long adminId = 1001;
	        given(adminService.viewAdminById(adminId)).willReturn(null);
	        Admin admin = new Admin("shreya","0903234166");

	        this.mockMvc.perform(put("/admin/updateadminbyid/{adId}", adminId)
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(admin)))
	                .andExpect(status().isNotFound());

	    }

	    @Test
	    void shouldDeleteAdmin() throws Exception {
	        long adminId = 1001;
	        Admin admin = new Admin("shreya","0903234166");
	        given(adminService.viewAdminById(adminId)).willReturn(admin);
	        doNothing().when(adminService).deleteAdmin(admin.getAdminId());

	        this.mockMvc.perform(delete("/admin/deleteadminbyid/{adId}", admin.getAdminId()))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.adminName", is(admin.getAdminName())))
	                .andExpect(jsonPath("$.adminContact", is(admin.getAdminContact())));

	    }

	    @Test
	    void shouldReturn404WhenDeletingNonExistingAdmin() throws Exception {
	        long adminId = 1001;
	        given(adminService.viewAdminById(adminId)).willReturn(null);

	        this.mockMvc.perform(delete("/admin/deleteadminbyid/{adId}", adminId))
	                .andExpect(status().isNotFound());

	    }

}
