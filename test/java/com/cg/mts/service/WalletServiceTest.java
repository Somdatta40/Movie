package com.cg.mts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Wallet;

import com.cg.mts.exception.WalletNotFoundException;
import com.cg.mts.repository.IWalletRepository;
import com.cg.mts.service.WalletService;

public class WalletServiceTest 
{
	private WalletService walletService;
    private IWalletRepository walletRepository;
   
   
    @BeforeEach
    void setUp() {
    	walletRepository = mock(IWalletRepository.class);
    	walletService = new WalletService();
    }

    @Test
    void shouldSavedUserSuccessfully() throws WalletNotFoundException {
    	Wallet wallet = new Wallet(1301,10000);
        given(walletService.saveWallet(wallet)).willReturn(null);
        given(walletRepository.save(wallet)).willAnswer(invocation -> invocation.getArgument(0));

       Wallet savedWallet = walletService.saveWallet(wallet);

        assertThat(savedWallet).isNotNull();

        verify(walletRepository).save(any(Wallet.class));
    }

    @Test
    void shouldThrowErrorWhenSaveWalletWithExisting() {
    	Wallet wallet = new Wallet(1301,10000);
        given(walletService.addWallet(wallet)).willReturn(null);

        assertThrows(WalletNotFoundException.class, () -> {
        	walletService.addWallet(wallet);
        });

        verify(walletRepository, never()).save(any(Wallet.class));
    }
}
