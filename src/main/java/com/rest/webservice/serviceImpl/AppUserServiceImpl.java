package com.rest.webservice.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rest.webservice.bean.AppUserBean;
import com.rest.webservice.model.AppUser;
import com.rest.webservice.repository.AppUserRepository;
import com.rest.webservice.service.AppUserService;

@Component
public class AppUserServiceImpl implements AppUserService{

	@Autowired
	AppUserRepository appUserRepo;
	
	@Override
	public List<AppUserBean> getUsers() {
		List<AppUserBean> beans = new ArrayList<>();
		List<AppUser> users = appUserRepo.findAll();
		users.forEach((user)->{
			AppUserBean bean = new AppUserBean();
			bean.setId(user.getId());
			bean.setFirstName(user.getFirstName());
			bean.setLastName(user.getLastName());
			bean.setDateOfBirth(user.getDateOfBirth());
			beans.add(bean);
		});
		return beans;
	}

	@Override
	public AppUserBean getUserById(Long id) {
		Optional<AppUser> userOp = appUserRepo.findById(id);
		AppUser user = userOp.get();
		AppUserBean bean = new AppUserBean();
		bean.setId(user.getId());
		bean.setFirstName(user.getFirstName());
		bean.setLastName(user.getLastName());
		bean.setDateOfBirth(user.getDateOfBirth());
		return bean;
	}

	@Override
	public void saveUser(AppUserBean bean) {
		AppUser user = new AppUser();
		user.setId(bean.getId());
		user.setFirstName(bean.getFirstName());
		user.setLastName(bean.getLastName());
		user.setDateOfBirth(bean.getDateOfBirth());
		appUserRepo.save(user);
	}

	@Override
	public void deleteUserById(Long id) {
		Optional<AppUser> userOp = appUserRepo.findById(id);
		AppUser user = userOp.get();
		appUserRepo.delete(user);
		
	}
}
