package com.cg.mts.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.mts.entity.Admin;
import com.cg.mts.entity.Customer;
import com.cg.mts.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User,Long>{

	/**
	 * 
	 * @param role
	 * @return details of user by role
	 */
	public List<User> findByRole(String role);
	@Query (value="select user_id from userdetails",nativeQuery = true)

	public List<User> showAllUser();
	@Query(value="select admin_id,admin_name,admin_contact from admin where admin_id=(select type_id from userdetails  where type_id=?1 and password=?2 and role=?3)",nativeQuery = true)
	public Object LoginUser(long userId,String password,String role);
	/**
	 * 
	 * @param password
	 * @param role
	 * @param userId
	 * @return details of user by userId
	 */
	@Query(value="select cust_id,customer_name,mobile_number from customer where cust_id=(select cust_id from userdetails  where cust_id=?3 and password=?1 and role=?2)",nativeQuery = true)
	public Object LoginUser(String password,String role,long userId);
	
	


public User findByAdmin(Object obj);
}
