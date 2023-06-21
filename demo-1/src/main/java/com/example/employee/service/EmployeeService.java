package com.example.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.employee.model.Employee;
import com.example.employee.repo.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired //to inject dependencies
	private EmployeeRepository EmployeeRepository;
	
	public List<Employee> getAllEmployees() {
		return EmployeeRepository.findAll();
	}
	
	public Employee saveData(Employee employee) {
		return EmployeeRepository.save(employee);
				}
    public void saveEmployee(Employee employee) {
    	this.EmployeeRepository.save(employee);
    }
    
    public Employee getEmployeeById(long id) {
    	Optional < Employee > optional = EmployeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return employee;
    }
    public void deleteEmployeeById(long id) {
    	  this.EmployeeRepository.deleteById(id);
    	 }
    public Page<Employee> findPaginated(int pageNo, int pageSize) {
    	Pageable pageable = PageRequest.of(pageNo - 1,pageSize);
    	return this.EmployeeRepository.findAll(pageable);
    }
}
