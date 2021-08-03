package com.cg.mts.controller;

import java.util.List;

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
import com.cg.mts.entity.Customer;
import com.cg.mts.entity.User;
import com.cg.mts.service.IAdminService;
import com.cg.mts.service.ICustomerService;
import com.cg.mts.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/admin")
@Api(value = "Online Movie Ticket Booking System", description = "Operations pertaining to Admin in Online Movie Ticket Booking System")
@Validated
public class AdminController {

	/**
	 * Admin Controller
	 */
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private ICustomerService customerService;

	@ApiOperation(value = "View list of all admins", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

	/**
	 * 
	 * @return allAdmins
	 */
	@GetMapping("/getalladmins")
	public List<Admin> getAllAdmins() {
		return this.adminService.viewAllAdmins();
	}
	
	/**
	 * 
	 * @return all customers
	 */

	@GetMapping("/getallcustomers")
	public List<Customer> getAllCustomers() {
		return this.customerService.viewAllCustomers();
	}
	

	/**
	 * 
	 * @param adminId
	 * @return admin by adminId
	 */
	@ApiOperation(value = "Get admin by Id")
	@GetMapping("/getadminbyid/{adId}")
	public Admin getAdminById(
			@ApiParam(value = "Admin id from which admin object will retrieve", required = true) @PathVariable("adId") @Positive long adminId) {
		return this.adminService.viewAdminById(adminId);
	}

	/**
	 * 
	 * @param adminName
	 * @return admin by adminName
	 */
	@ApiOperation(value = "Get admin by adminName")
	@GetMapping("/getadminbyname/{adname}")
	public List<Admin> getAdminByName(
			@ApiParam(value = "Admin id from which admin object will retrieve", required = true) @PathVariable("adname") String adminName) {
		return this.adminService.findByAdminName(adminName);
	}

	/**
	 * 
	 * @param admin
	 * @return saveAdmin taking input
	 */
	@ApiOperation(value = "Add Admin")
	@PostMapping("/addadmin")
	public Admin saveAdmin(
			@ApiParam(value = " admin stored in database table", required = true) @Valid @RequestBody Admin admin) {
		return this.adminService.saveAdmin(admin);
	}

	/**
	 * 
	 * @param admin
	 * @param adminId
	 * @return updateAdmin by adminId
	 */
	@ApiOperation(value = "Update Admin")
	@PutMapping("/updateadminbyid/{adId}")
	public Admin updateAdmin(@ApiParam(value = "Update admin object", required = true) @Valid @RequestBody Admin admin,
			@ApiParam(value = "Admin Id to update admin object", required = true) @PathVariable("adId") long adminId) {
		return this.adminService.updateAdmin(admin, adminId);
	}

	/**
	 * 
	 * @param adminId
	 * @return deleteAdmin by adminId
	 */
	@ApiOperation(value = "Delete an Admin")
	@DeleteMapping("/deleteadminbyid/{adId}")
	public ResponseEntity<Admin> deleteAdmin(
			@ApiParam(value = "Admin Id  from which admin object will be deleted from database table", required = true) @PathVariable("adId") long adminId) {
		return this.adminService.deleteAdmin(adminId);
	}
}
