package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
//import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService EmployeeService;
	
	//displaying the list of the employees-method handler
	@GetMapping("/test")
	public List<Employee> getData() {
		List<Employee> list = new ArrayList<>();
		list.addAll(EmployeeService.getAllEmployees());
		return list;
	}	
	
	@PostMapping("/enterData") 
	public ResponseEntity<?> saveData(@RequestBody Employee employee) {
		try {
			return ResponseEntity.status(200).body(EmployeeService.saveData(employee));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return ResponseEntity.status(400).body("Some Exceptions might have occured!!!");
	}
	
	@GetMapping("/SEARCH") 
	public ResponseEntity<?> saveinfo(@RequestParam("query")String query) {
		
		return ResponseEntity.status(200).body("ANYTHING");
	}
}



