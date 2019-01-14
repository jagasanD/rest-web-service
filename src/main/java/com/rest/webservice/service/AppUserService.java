package com.rest.webservice.service;

import java.util.List;

import com.rest.webservice.bean.AppUserBean;

public interface AppUserService {
	
	public List<AppUserBean> getUsers();

	public AppUserBean getUserById(Long id);

	public void saveUser(AppUserBean bean);

	public void deleteUserById(Long id);
}
