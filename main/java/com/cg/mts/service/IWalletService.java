package com.cg.mts.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.mts.entity.Customer;
import com.cg.mts.entity.Wallet;

public interface IWalletService {
	/**
	 * 
	 * @param wallet
	 * @return saveWallet
	 */

	public Wallet saveWallet(Wallet wallet);
	/**
	 * 
	 * @param wId
	 * @return deleteWallet
	 */

	public ResponseEntity<Wallet> deleteWallet(long wId) ;
	/**
	 * 
	 * @param wId
	 * @return viewWalletById
	 */
	public Wallet viewWalletById(long wId) ;
	/**
	 * 
	 * @param wallet
	 * @param WalletId
	 * @return updateWallet
	 */
	public Wallet updateWallet(Wallet wallet, long WalletId);
	/**
	 * 
	 * @return viewAllWallet
	 */
	public List<Wallet> viewAllWallet();
	/**
	 * 
	 * @param wallet
	 * @return addWallet
	 */
	public Wallet addWallet(Wallet wallet);
	
	
}
