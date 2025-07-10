package com.cdgn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdgn.model.Employee;
import com.cdgn.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
     private EmployeeRepository repository;

	public Employee saveEmployee(Employee employee) {
		
		return repository.save(employee);
	}
	

	public boolean existsByEmailAndPassword(String email, String password) {
		return repository.existsByEmailAndPassword(email,password);
	}


	public List<Employee> viewAllEmployees() {
		return repository.findAll();
	}


	public Employee getByEmail(String email) {
		
		return repository.findByEmail(email);
	}


	public void deleteById(int id) {
		 repository.deleteById(id);
	}


	public Employee findById(int id) {
		
		return repository.findById(id).get();
	}



	
}
