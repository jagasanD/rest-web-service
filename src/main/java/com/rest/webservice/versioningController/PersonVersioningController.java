package com.rest.webservice.versioningController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservice.versioningBean.Name;
import com.rest.webservice.versioningBean.PersonV1;
import com.rest.webservice.versioningBean.PersonV2;

@RestController
public class PersonVersioningController {
	
	// below example are URI versioning
	@GetMapping("v1/person")
	public PersonV1 getPersonV1() {
		return getObjP1();
	}
	@GetMapping("v2/person")
	public PersonV2 getPersonV2() {
		return getObjP2();
	}
	
	//below are Param versioning
	@GetMapping(value="/person/param",params="version=1")
	public PersonV1 getParamPersonV1() {
		return getObjP1();
	}
	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 getParamPersonV2() {
		return getObjP2();
	}
	// below are Header versioning
	//Header pass the key API-VERSION   and value are 1/2
	@GetMapping(value="/person/header",headers="API-VERSION=1")
	public PersonV1 getHeaderPersonV1() {
		return getObjP1();
	}
	@GetMapping(value="/person/header",headers="API-VERSION=2")
	public PersonV2 getHeaderPersonV2() {
		return getObjP2();
	}
	//produce is basically Media(mime) type versioning
	
	//pass the key in header  Accept and value application/vnd.company.app-v1+json/application/vnd.company.app-v2+json
	@GetMapping(value="/person/produce",produces="application/vnd.company.app-v1+json")
	public PersonV1 getProducePersonV1() {
		return getObjP1();
	}
	@GetMapping(value="/person/produce",produces="application/vnd.company.app-v2+json")
	public PersonV2 getProducePersonV2() {
		return getObjP2();
	}
	
	
	
	
	private PersonV1 getObjP1() {
		PersonV1 p1 = new PersonV1();
		p1.setName("raaz     kumar");
		return p1;
		
	}
	private PersonV2 getObjP2() {
		PersonV2 p2 = new PersonV2();
		Name name = new Name();
		name.setFirstName("jack");
		name.setLastName(" dev");
		p2.setName(name);
		return p2;
	}
}
