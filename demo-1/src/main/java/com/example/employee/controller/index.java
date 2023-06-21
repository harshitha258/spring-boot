package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

@Controller
public class index {
	
	@Autowired
	private EmployeeService EmployeeService;
	
	@GetMapping("/path")
    public String viewHomePage(Model model) {
		return findPaginated(1, model);
//		 List<Employee> listEmployees= EmployeeService.getAllEmployees();
//		    for(Employee e : listEmployees)
//		    {
//		    	System.out.println(e.getFirstName());
//		    }
//		    model.addAttribute("listEmployees",listEmployees);
//		    return "index";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		EmployeeService.saveEmployee(employee);
		return"redirect:/path";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		 Employee employee = EmployeeService.getEmployeeById(id);
		 
		 model.addAttribute("employee", employee);
		 return "update_employee";	
	}

	@GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        this.EmployeeService.deleteEmployeeById(id);
        return "redirect:/path";

   }
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
	    int pageSize = 5;

	    Page < Employee > page = EmployeeService.findPaginated(pageNo, pageSize);
	    List < Employee > listEmployees = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("listEmployees", listEmployees);
	    return "index";
	}
}
