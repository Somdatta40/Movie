package com.cg.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mts.entity.Customer;
import com.cg.mts.entity.Wallet;
public interface IWalletRepository extends JpaRepository<Wallet,Long> {
	

}
