package com.example.yearbook;

public interface Dao {

	public SubmitResponse submit(OrderRequest orderRequest);

	public LookupResponse lookup(LookupRequest lookupRequest);

}
