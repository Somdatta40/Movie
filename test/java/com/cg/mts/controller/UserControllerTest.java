package com.cg.mts.controller;

import com.cg.mts.entity.Admin;
import com.cg.mts.entity.User;
import com.cg.mts.service.AdminService;
import com.cg.mts.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {
	
	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private UserService userService;

	    @Autowired
	    private ObjectMapper objectMapper;

	    private List<User> userList;

	    @BeforeEach
	    void setUp() {
	        this.userList = new ArrayList<>();
	        this.userList.add(new  User("abcAB123","admin"));
	        this.userList.add(new  User("abdAB123","admin"));
	        this.userList.add(new  User("abcAB1234","customer"));

	        //objectMapper.registerModule(new ProblemModule());
	        //objectMapper.registerModule(new ConstraintViolationProblemModule());
	    }

	    @Test
	    void shouldFetchAllUsers() throws Exception {
	        given(userService.viewAllUsers()).willReturn(this.userList);

	        this.mockMvc.perform(get("/user/getallusers"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.size()", is(userList.size())));
	    }

	    @Test
	    void shouldFindUserById() throws Exception {
	        long userId = 1201;
	        String password="abcAD123";
	        String role="admin";
	        String role1="customer";
	        User user = new User("abcAB123","admin");
	        given(userService.viewUserById(userId)).willReturn(user);
	        given(userService.LoginUser(userId, password, role)).willReturn(user);
	        

	        this.mockMvc.perform(get("/user/loginuserbyid/{userId}/{password}/{role}", userId,password,role))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.password", is(user.getPassword())))
	                .andExpect(jsonPath("$.role", is(user.getRole())))
	        ;
	    }

	    @Test
	    void shouldReturn404WhenFetchingNonExistingUser() throws Exception {
	        long userId = 1201;
	        String password="abcAD123";
	        String role="admin";
	        String role1="customer";
	        given(userService.LoginUser(userId, password, role)).willReturn(null);
	        

	        this.mockMvc.perform(get("/user/loginuserbyid/{userId}/{password}/{role}", userId,password,role))
	                .andExpect(status().isNotFound());

	    }

	    @Test
	    void shouldCreateNewUserByAdmin() throws Exception {
	        given(userService.saveUser(any(User.class))).willAnswer((invocation) -> invocation.getArgument(0));

	        User user = new User("abcAB123","admin");
	        this.mockMvc.perform(post("/user/adduserbyadid/{adId}")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(user)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.password", is(user.getPassword())))
	                .andExpect(jsonPath("$.role", is(user.getRole())))
	                ;

	    }

	    @Test
	    void shouldReturn400WhenCreateNewUserWithoutRole() throws Exception {
	    	 User user = new User("abcAB123",null);

	        this.mockMvc.perform(post("/user/adduserbyadid/{adId}")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(user)))
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
	    void shouldCreateNewUserByCustomer() throws Exception {
	        given(userService.saveUser(any(User.class))).willAnswer((invocation) -> invocation.getArgument(0));

	        User user = new User("abcAB123","customer");
	        this.mockMvc.perform(post("/user/adduserbycustid/{custId}")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(user)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.password", is(user.getPassword())))
	                .andExpect(jsonPath("$.role", is(user.getRole())))
	                ;

	    }

	    @Test
	    void shouldReturn400WhenCreateNewUserWithoutCustRole() throws Exception {
	    	 User user = new User("abcAB123",null);

	        this.mockMvc.perform(post("/user/adduserbycustid/{custId}")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(user)))
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
	    void shouldUpdateUser() throws Exception {
	        long userId = 1201;
	        User user = new User("abcAB123","admin");
	        given(userService.viewUserById(userId)).willReturn(user);
	        given(userService.updateUser(any(User.class), userId)).willAnswer((invocation) -> invocation.getArgument(0));

	        this.mockMvc.perform(put("/user/updateuserbyid/{userId}", user.getUserId())
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(user)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.password", is(user.getPassword())))
	                .andExpect(jsonPath("$.role", is(user.getRole())));

	    }

	    @Test
	    void shouldReturn404WhenUpdatingNonExistingUser() throws Exception {
	    	 long userId = 1201;
		        User user = new User("abcAB123","admin");
		        given(userService.updateUser(user, userId)).willReturn(null);

	        this.mockMvc.perform(put("/user/updateuserbyid/{userId}", userId)
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(user)))
	                .andExpect(status().isNotFound());

	    }

	    @Test
	    void shouldDeleteUser() throws Exception {
	    	  long userId = 1201;
		        User user = new User("abcAB123","admin");
		        given(userService.viewUserById(userId)).willReturn(user);
	        doNothing().when(userService).deleteUser(user.getUserId());

	        this.mockMvc.perform(delete("/user/deleteuserbyid/{userId}", user.getUserId()))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.password", is(user.getPassword())))
	                .andExpect(jsonPath("$.role", is(user.getRole())));

	    }

	    @Test
	    void shouldReturn404WhenDeletingNonExistingUser() throws Exception {
	    	 long userId = 1201;
	    	 given(userService.viewUserById(userId)).willReturn(null);

	        this.mockMvc.perform(delete("/user/deleteuserbyid/{userId}", userId))
	                .andExpect(status().isNotFound());

	    }


}

