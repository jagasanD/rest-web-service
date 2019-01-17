package com.rest.webservice.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.webservice.bean.AppUserBean;
import com.rest.webservice.service.AppUserService;



@RestController
public class UserController {
	@Autowired
	AppUserService appUserService;

	HttpHeaders headers = new HttpHeaders();
	
	@Autowired
	MessageSource messageSource;

	@GetMapping("api/get-all-users")
	public ResponseEntity<List<AppUserBean>> getUsers() {
		return new ResponseEntity(appUserService.getUsers(), HttpStatus.OK);
	}

	@GetMapping("api/get-user-by-id/{id}")
	public ResponseEntity<AppUserBean> getUserById(@PathVariable Long id) {
		AppUserBean user = appUserService.getUserById(id);
		return new ResponseEntity(user, HttpStatus.OK);

	}

	@PostMapping("api/save-user")
	public ResponseEntity<String> saveUser( @Valid @RequestBody AppUserBean bean) {
		appUserService.saveUser(bean);
		return new ResponseEntity("User Saved Successfully", HttpStatus.OK);
	}

	@DeleteMapping("api/delete-user-by-id/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
		try {
			appUserService.deleteUserById(id);
			return new ResponseEntity("Deleted User", HttpStatus.OK);
		} catch (Exception e) {
			headers.set("EXCEPTIONS", "UNAUTHORIZED_ACCESS");
			return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
		}

	}
	
	@GetMapping("api/international")
	public ResponseEntity<AppUserBean> getUserByI18() {
		return new ResponseEntity(messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale()), HttpStatus.OK);

	}
	
	@GetMapping("api/dynamic-fillter")
	public ResponseEntity<MappingJacksonValue> getDynaicFilter() {
		AppUserBean bean = new AppUserBean();
		bean.setId(10l);
		bean.setFirstName("Priya");
		bean.setLastName("Dev");
		bean.setDateOfBirth(new Date());
		bean.setPassword("12345");
		
		//Dynamic Filter of beans 
		SimpleBeanPropertyFilter fileter = SimpleBeanPropertyFilter.filterOutAllExcept(
				"firstName","id","dateOfBirth") ;
		FilterProvider provider = new SimpleFilterProvider().addFilter("filterId",fileter);
		MappingJacksonValue value = new MappingJacksonValue(bean);
		value.setFilters(provider);
		
		return new ResponseEntity(value, HttpStatus.OK);

	}

}
