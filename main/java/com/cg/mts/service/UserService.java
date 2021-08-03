package com.cg.mts.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Admin;
import com.cg.mts.entity.Customer;
import com.cg.mts.entity.User;
import com.cg.mts.exception.UserNotFoundException;
import com.cg.mts.repository.IUserRepository;


@Service
public class UserService extends GlobalLoginService implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	

	/**
	 * save user
	 */
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return this.userRepository.save(user);
	}
	/**
	 * update user by userId
	 */

	@Override
	public User updateUser(User user,long userId) {
		// TODO Auto-generated method stub
		if(flag==1||flag==2) {
		User existingUser=this.userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Admin not found"+userId));
		existingUser.setRole(user.getRole());
		existingUser.setPassword(user.getPassword());
		return this.userRepository.save(existingUser);
		}
		return null;
	}
	/**
	 * delete user by userId
	 */

	@Override
	public ResponseEntity<User> deleteUser(long userId)  {
		// TODO Auto-generated method stub
		if(flag==1||flag==2) {
		User existingUser=this.userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Order not found with id"+userId));
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
		}
		return null;
	}
	/**
	 * view user by userId
	 */

	@Override
	public User viewUserById(long userId) {
		// TODO Auto-generated method stub
		if(flag==1||flag==2) {
		return this.userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Order not found with id"+userId));
	}
		return null;
	}
	/**
	 * view all user
	 */

	@Override
	public List<User> viewAllUsers() {
		// TODO Auto-generated method stub
		if(flag==1) {
		return this.userRepository.findAll();
		}
		return null;
	}
	/**
	 * find by role
	 */

	@Override
	public List<User> findByRole(String role) {
		// TODO Auto-generated method stub
		if(flag==1) {
		return this.userRepository.findByRole(role);
		}
		return null;
	}
	/**
	 * login user
	 */
	

	
	  @Override 
	  public Object LoginUser(long userId, String password, String role) {
		  if(role.equalsIgnoreCase("admin")) 
			 {
				  flag=1;
				  System.out.println("Admin ID : "+userId + "Logged in");
				  return this.userRepository.LoginUser(userId, password, role);
			 }
			 else  if(role.equalsIgnoreCase("customer")) 
			 {
				 flag=2;
				 System.out.println("Customer ID : "+userId + "Logged in");
				 return this.userRepository.LoginUser(password, role,userId);
			 }
		  return "Not logged in.";
		  
	  }

	  @Override
		public Object LogOutUser(Object obj, long userId) 
		{
			if(flag==1||flag==2)
			{
				flag=0;
				return obj;
			}
			return null;
		}




}
