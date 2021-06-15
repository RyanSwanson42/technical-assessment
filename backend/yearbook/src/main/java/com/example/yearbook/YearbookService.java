package com.example.yearbook;

import org.springframework.stereotype.Service;

@Service
public interface YearbookService {

	public SubmitResponse submit(OrderRequest orderRequest);

	public LookupResponse lookup(LookupRequest lookupRequest);

	

}
