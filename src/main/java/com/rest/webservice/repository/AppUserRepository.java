package com.rest.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.webservice.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long>{
	
	

}
