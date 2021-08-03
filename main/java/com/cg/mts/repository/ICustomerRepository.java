package com.cg.mts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long>
{
	
}
