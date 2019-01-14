package com.rest.webservice.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<String> saveUser(@RequestBody AppUserBean bean) {
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

}
