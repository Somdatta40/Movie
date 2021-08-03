package com.cg.mts.service;


import java.util.List;


import org.springframework.http.ResponseEntity;

import com.cg.mts.entity.Admin;
import com.cg.mts.entity.Customer;
import com.cg.mts.entity.User;



public interface IUserService {
	/**
	 * 
	 * @param user
	 * @return User saveUser
	 */

	public User saveUser(User user);
	/**
	 * 
	 * @return viewAllUsers
	 */
    public List<User> viewAllUsers();
    /**
     * 
     * @param role
     * @return findByRole
     */
	public List<User> findByRole(String role);
	/**
	 * 
	 * @param userId
	 * @return viewUserById
	 */
	public User viewUserById(long userId);
	/**
	 * 
	 * @param user
	 * @param userId
	 * @return updateUser
	 */
	public User updateUser(User user, long userId);
	/**
	 * 
	 * @param userId
	 * @return deleteUser
	 */
	public ResponseEntity<User> deleteUser(long userId);
	/**
	 * 
	 * @param userId
	 * @param password
	 * @param role
	 * @return LoginUser
	 */
	public Object LoginUser(long userId,String password,String role);
	
	
	public Object LogOutUser(Object obj, long userId) ;
}
