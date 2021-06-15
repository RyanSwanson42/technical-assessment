package com.example.yearbook;

import java.lang.annotation.Annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YearbookServiceImpl implements YearbookService {
	
	@Autowired
	Dao dao;
	
	public SubmitResponse submit(OrderRequest orderRequest) {
		SubmitResponse response = new SubmitResponse();
		response = dao.submit(orderRequest);
		return response;
	}


	public LookupResponse lookup(LookupRequest lookupRequest) {
		LookupResponse response = new LookupResponse();
		response = dao.lookup(lookupRequest);
		return response;
	}
}
