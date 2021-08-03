package com.cg.mts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Customer;
import com.cg.mts.entity.Wallet;
import com.cg.mts.exception.WalletNotFoundException;
import com.cg.mts.repository.IWalletRepository;

@Service
public class WalletService implements IWalletService{

	@Autowired
	private IWalletRepository WalletRepository;
	/**
	 * save wallet
	 */
	
	@Override
	public Wallet saveWallet(Wallet wallet) {
		// TODO Auto-generated method stub
		
		return this.WalletRepository.save(wallet);
	}
	/**
	 * update wallet by walletId
	 */

	@Override
	public Wallet updateWallet(Wallet wallet,long wId) {
		// TODO Auto-generated method stub
		Wallet existingWallet=this.WalletRepository.findById(wId).orElseThrow(()-> new WalletNotFoundException("Wallet not found"+wId));
		existingWallet.setWalletId(wallet.getWalletId());
		existingWallet.setBalance(wallet.getBalance());
		return this.WalletRepository.save(existingWallet);
	}
	/**
	 * view wallet by wallet Id
	 */


	@Override

	public Wallet viewWalletById(long wId) {
		// TODO Auto-generated method stub
		return this.WalletRepository.findById(wId).orElseThrow(()-> new WalletNotFoundException("Wallet not found with id"+wId));
	}
	/**
	 * delete wallet by wallet Id
	 */

	@Override
	public ResponseEntity<Wallet> deleteWallet(long wId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * view all wallet
	 */

	@Override
	public List<Wallet> viewAllWallet() {
		// TODO Auto-generated method stub
		return this.WalletRepository.findAll();
	}
	/**
	 * add wallet
	 */

	@Override
	public Wallet addWallet(Wallet wallet) {
		// TODO Auto-generated method stub
		return this.WalletRepository.save(wallet);
	}





}