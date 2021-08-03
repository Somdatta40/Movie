package com.cg.mts.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.mts.controller.WalletController;
import com.cg.mts.entity.Wallet;
import com.cg.mts.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = WalletController.class)
@ActiveProfiles("test")
public class WalletControllerTest 
{
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletService walletService;

    @Autowired
    private ObjectMapper objectMapper;
    
    private List<Wallet> walletList;
   
	
    @BeforeEach
    void setUp() 
    {
        this.walletList = new ArrayList<>();
        this.walletList.add(new Wallet(1301,10000));
        this.walletList.add(new Wallet(1302,70000));
        this.walletList.add(new Wallet(1303,70000));
    }
    
    @Test
    void shouldFetchAllWallet() throws Exception {
        given(walletService.viewAllWallet()).willReturn(this.walletList);

        this.mockMvc.perform(get("/wallet/getallwallet"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(walletList.size())));
    }
    
    @Test
    void shouldFindwalletById() throws Exception {
        long walletId=1301;
        Wallet wallet = new Wallet(1303,70000);
        given(walletService.viewWalletById(walletId)).willReturn(wallet);

        this.mockMvc.perform(get("/wallet/getwalletbyid/{wId}", walletId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(wallet.getBalance())));
                
              
    }
    
    @Test
    void shouldReturn404WhenFetchingNonExistingWallet() throws Exception {
    	 long walletId=1301;
    	 given(walletService.viewWalletById(walletId)).willReturn(null);

    	 this.mockMvc.perform(get("/wallet/getwalletbyid/{wId}", walletId))
                .andExpect(status().isNotFound());

    }
    
    @Test
    void shouldAddNewWallet() throws Exception {
    	long custId=3001;
        given(walletService.saveWallet(any(Wallet.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Wallet wallet = new Wallet(1303,70000);
        this.mockMvc.perform(post("/wallet/getwalletbycustid/{custId}",custId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(wallet)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.balance", is(wallet.getBalance())));
    }

    
    
    @Test
    void shouldUpdateWallet() throws Exception {
    	 long walletId=1301;
         Wallet wallet = new Wallet(1303,70000);
         given(walletService.viewWalletById(walletId)).willReturn(wallet);
         given(walletService.updateWallet(any(Wallet.class),walletId)).willAnswer((invocation) -> invocation.getArgument(0));

         this.mockMvc.perform(get("/wallet/getwalletbyid/{wId}", wallet.getWalletId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(wallet)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(wallet.getBalance())));
               

    }

    @Test
    void shouldReturn404WhenUpdatingNonExistingWallet() throws Exception {
    	 long walletId=1301;
    	 given(walletService.viewWalletById(walletId)).willReturn(null);
    	 Wallet wallet = new Wallet(1303,70000);
        this.mockMvc.perform(put("/api/customers/{id}", walletId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(wallet)))
                .andExpect(status().isNotFound());

    }

    
    @Test
    void shouldDeleteWallet() throws Exception {
    	long walletId=1301;
        Wallet wallet = new Wallet(1303,70000);
        given(walletService.viewWalletById(walletId)).willReturn(wallet);
        doNothing().when(walletService).deleteWallet(wallet.getWalletId());

        this.mockMvc.perform(delete("/wallet/deletewalletbyid/{wId}", wallet.getWalletId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(wallet.getBalance())));
                
    }

    @Test
    void shouldReturn404WhenDeletingNonExistingWallet() throws Exception {
    	long walletId=1301;
   	 given(walletService.viewWalletById(walletId)).willReturn(null);
        this.mockMvc.perform(delete("/wallet/deletewalletbyid/{wId}", walletId))
                .andExpect(status().isNotFound());

    }
    
    
}
