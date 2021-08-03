package com.cg.mts.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entity.Admin;
import com.cg.mts.entity.User;
import com.cg.mts.repository.IAdminRepository;
import com.cg.mts.repository.ICustomerRepository;
import com.cg.mts.repository.IUserRepository;
import com.cg.mts.service.GlobalLoginService;
import com.cg.mts.service.IAdminService;
import com.cg.mts.service.ICustomerService;
import com.cg.mts.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/user")
@Api(value = "Online Movie Ticket Booking System", description = "Operations pertaining to User in Online Movie Ticket Booking System")
@Validated
public class UserController extends GlobalLoginService
/**
 * User Controller
 */

{
	
	@Autowired
	private IUserService userService;

	@Autowired
	private IAdminService adminService;

	@Autowired
	private IAdminRepository adminRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private ICustomerRepository customerRepository;
	/**
	 * 
	 * @return get all user
	 */

	@ApiOperation(value = "View list of users", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/getallusers")
	public List<User> getAllUser() {
		return this.userService.viewAllUsers();
		
	}
	
	/**
	 * 
	 * @param userId
	 * @param password
	 * @param role
	 * @return get user by Id
	 */
	@ApiOperation(value = "Get an user by Id,password and role")
	  @GetMapping("/loginuserbyid/{userId}/{password}/{role}")
	  public Object getUserById(
			  @ApiParam(value = "User id from which products user will retrieve", required = true) @PathVariable("userId") @Positive long userId,
			  @ApiParam(value = "Password from which products user will retrieve", required = true) @PathVariable("password")String password,
			  @ApiParam(value = "Role from which products user will retrieve", required = true) @PathVariable("role")String role) {
		
		 return this.userService.LoginUser(userId, password, role);
	  }
	  
	
	@GetMapping("/LogoutUser/{userId}")
	  public Object logoutUser(
			  @ApiParam(value = "User id from which products user will retrieve", required = true) @PathVariable("userId") @Positive long userId,
			  @ApiParam(value = "Admin, who will log out")Admin admin) {
		 return this.userService.LogOutUser(admin, userId);
	}
	/**
	 * 
	 * @param adminId
	 * @param user
	 * @return admin 
	 */
	@ApiOperation(value = "Add Admin in user")
	@PostMapping("/adduserbyadid/{adId}")
	public Optional<Object> saveUser(
			@ApiParam(value = "admin Id to update user object", required = true)@PathVariable("adId") Long adminId, 
			 @ApiParam(value = "Update user object", required = true)@Valid @RequestBody User user) {
		return this.adminRepository.findById(adminId).map(admin -> {
			user.setAdmin(admin);
			return userRepository.save(user);
		});
	}
	/**
	 * 
	 * @param customerId
	 * @param user
	 * @return customer
	 */

	@ApiOperation(value = "Add Customer in user")
	@PostMapping("/adduserbycustid/{custId}")
	public Optional<Object> saveUserByCustomer(
			@ApiParam(value = "customer Id to update customer object", required = true)@PathVariable("custId") Long customerId, 
			@ApiParam(value = "Update user object", required = true)@Valid @RequestBody User user) {
		return this.customerRepository.findById(customerId).map(customer -> {
			user.setCustomer(customer);
			return userRepository.save(user);
		});
	}
	/**
	 * 
	 * @param user
	 * @param userId
	 * @return update user by userId
	 */
	 @ApiOperation(value = "Update user")
	@PutMapping("/updateuserbyid/{userId}")
	public User updateUser(
	        @ApiParam(value = "Update user object", required = true)@Valid @RequestBody User user,
			@ApiParam(value = "user Id to update user object", required = true)@PathVariable("userId") @Positive long userId) {
		User existingUser = this.userService.updateUser(user, userId);
		return existingUser;
	}
	/**
	 * 
	 * @param userId
	 * @return delete user by user Id
	 */
	    @ApiOperation(value = "Delete user")
	@DeleteMapping("/deleteuserbyid/{userId}")
	public ResponseEntity<User> deleteUser(
			@ApiParam(value = "user Id from which user object will be deleted from database table", required = true)@PathVariable("userId") long userId) {
		return this.userService.deleteUser(userId);
	}
}
