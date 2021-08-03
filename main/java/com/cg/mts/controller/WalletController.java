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

import com.cg.mts.entity.Wallet;
import com.cg.mts.repository.ICustomerRepository;
import com.cg.mts.repository.IWalletRepository;
import com.cg.mts.service.GlobalLoginService;
import com.cg.mts.service.ICustomerService;
import com.cg.mts.service.IWalletService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/wallet")
@Api(value = "Online Movie Ticket Booking System", description = "Operations pertaining to Wallet in Online Movie Ticket Booking System")
public class WalletController extends GlobalLoginService
/**
 * Wallet Controller
 */
{
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IWalletService walletService;
	
	@Autowired 
	private ICustomerRepository customerRepository;
	
	@Autowired
	private IWalletRepository walletRepository;
	/**
	 * 
	 * @return get all wallet
	 */
	
	@ApiOperation(value = "View list of wallet", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/getallwallet")
	public List<Wallet> getAllWallet()
	{
		if(flag==1) {
		return this.walletService.viewAllWallet();
		}
		return null;
	}
	/**
	 * 
	 * @param walletId
	 * @return get wallet by walletId
	 */

	@ApiOperation(value = "Get an wallet by Id")
	@GetMapping("/getwalletbyid/{wId}")
	public Wallet getwalletById(
			@ApiParam(value = "Wallet id from which wallet object will retrieve", required = true)@PathVariable("wId") @Positive long walletId)
	{
		if(flag==1||flag==2) {
		return this.walletService.viewWalletById(walletId);
		}
		return null;
	}
	/**
	 * 
	 * @param wallet
	 * @param customerId
	 * @return add wallet
	 */
	@ApiOperation(value = "Add Wallet")
	@PostMapping("/addwalletbycustid/{custId}")
	public Optional<Object> saveWallet(
			 @ApiParam(value = "wallet object stored in database table", required = true)@RequestBody Wallet wallet,
			 @ApiParam(value = "customer Id to add wallet object", required = true)@PathVariable("custId") Long customerId)
	{
		if(flag==2) {
		return this.customerRepository.findById(customerId).map(customer->{wallet.setCustomer(customer);
		return walletRepository.save(wallet);
		});
		}
		return null;
	}
	/**
	 * 
	 * @param wallet
	 * @param wId
	 * @return update wallet
	 */
	
	@PutMapping("/updateWalletbyid/{wId}")
	public Wallet updateWallet( 
			@ApiParam(value = "Update wallet object", required = true)@RequestBody Wallet wallet,
			@ApiParam(value = "wallet Id to update wallet object", required = true) @PathVariable("wId")@Positive long wId)
	{
		if(flag==2) {
		Wallet existingUser = this.walletService.updateWallet(wallet, wId);
		return existingUser;
		}
		return null;
	}
	/**
	 * 
	 * @param wId
	 * @return delete wallet
	 */

	@ApiOperation(value = "Delete wallet")
	@DeleteMapping("/deletewalletbyid/{wId}")
	public ResponseEntity<Wallet> deleteWallet(
			@ApiParam(value = "wallet Id from which wallet object will delete from database table", required = true)@PathVariable("wId")long wId){
		if(flag==2) {
		return this.walletService.deleteWallet(wId);
		}
		return null;
	}
}
