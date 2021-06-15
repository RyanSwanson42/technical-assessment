package com.example.yearbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restController")
public class ControllerLayer {
	
	@Autowired
	YearbookService service;
	
	@GetMapping(value = "/test")
	public ResponseEntity<TestModel> testConnection(){
		HttpStatus status = HttpStatus.OK;
		TestModel model = new TestModel();
		model.setMessage("Success");
		return new ResponseEntity<>(model, status);
	}
	
	@PostMapping(value="/submit")
	public ResponseEntity<SubmitResponse> submit(@RequestBody OrderRequest orderRequest){
		HttpStatus status = HttpStatus.OK;
		SubmitResponse response = new SubmitResponse();
		response = this.service.submit(orderRequest);
		return new ResponseEntity<>(response, status);
	}
	
	@GetMapping(value="/lookup/{orderId}/{lastName}")
	public ResponseEntity<LookupResponse> lookup(@PathVariable(name = "orderId") String orderId, 
			@PathVariable(name = "lastName") String lastName){
		LookupRequest lookupRequest = new LookupRequest();
		lookupRequest.setLastName(lastName);
		lookupRequest.setOrderId(orderId);
		HttpStatus status = HttpStatus.OK;
		LookupResponse response = new LookupResponse();
		response = this.service.lookup(lookupRequest);
		return new ResponseEntity<>(response, status);
	}
}
