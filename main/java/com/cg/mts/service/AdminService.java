package com.cg.mts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Admin;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.repository.IAdminRepository;

@Service
public class AdminService extends GlobalLoginService implements IAdminService {

	@Autowired
	private IAdminRepository adminRepository;


	/**
	 * add admin
	 */
	@Override
	public Admin saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return this.adminRepository.save(admin);
	}

	/**
	 * update admin
	 */
	@Override
	public Admin updateAdmin(Admin admin, long adminId) {
		// TODO Auto-generated method stub
		if(flag==1) {
		Admin existingAdmin = this.adminRepository.findById(adminId)
				.orElseThrow(() -> new AdminNotFoundException("Admin not found" + adminId));
		existingAdmin.setAdminContact(admin.getAdminContact());
		existingAdmin.setAdminName(admin.getAdminName());
		return this.adminRepository.save(existingAdmin);
		}
		return null;
	}

	/**
	 * delete admin
	 */
	@Override
	public ResponseEntity<Admin> deleteAdmin(long adminId) {
		// TODO Auto-generated method stub
		if(flag==1) {
		Admin existingAdmin = this.adminRepository.findById(adminId)
				.orElseThrow(() -> new AdminNotFoundException("Admin not found with id" + adminId));
		this.adminRepository.delete(existingAdmin);
		return ResponseEntity.ok().build();
		}
		return null;
	}
	
	/**
	 * view admin by adminId
	 */
	@Override
	public Admin viewAdminById(long adminId) {
		// TODO Auto-generated method stub
		if(flag==1) {
		return this.adminRepository.findById(adminId)
				.orElseThrow(() -> new AdminNotFoundException("Admin not found with id" + adminId));
	}
		return null;
	}

	/**
	 * view all admins
	 */
	@Override
	public List<Admin> viewAllAdmins() {
		// TODO Auto-generated method stub
		return this.adminRepository.findAll();
	}

	/**
	 * view admin by adminName
	 */
	@Override
	public List<Admin> findByAdminName(String adminName) {
		// TODO Auto-generated method stub
		if(flag==1) {
		return this.adminRepository.findByAdminName(adminName);
	}
		return null;
	}

}
