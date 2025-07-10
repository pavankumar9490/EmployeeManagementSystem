package com.cdgn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdgn.model.Employee;
import com.cdgn.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller

public class EmployeeController {
	@Autowired
	private EmployeeService service;

	@RequestMapping("/save")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		Employee emp = service.saveEmployee(employee);
		ModelAndView mav = new ModelAndView("register.jsp");
		if (emp != null) {
			mav.addObject("status", "data added successfully");
		} else {
			mav.addObject("status", "data not added successfully");
		}
		return mav;

	}

	@RequestMapping("/emplogin")
	public ModelAndView checkCredentials(@RequestParam String email, @RequestParam String password,
			HttpSession session) {
		boolean validate = service.existsByEmailAndPassword(email, password);
		ModelAndView mav;
		if (email.equals("admin@codegnan.com") && password.equals("admin")) {
			mav = new ModelAndView("admin.jsp");
			session.setAttribute("email", email);

		} else {
			if (validate) {
				mav = new ModelAndView("employee.jsp");
				session.setAttribute("email", email);
			} else {
				mav = new ModelAndView("login.jsp");
				mav.addObject("validate", "Invalid Credentials");
			}
		}
		return mav;

	}
	@RequestMapping("/findAll")
	public ModelAndView ViewAllEmployees() {
	List<Employee>	empList =service.viewAllEmployees();
	ModelAndView mav = new ModelAndView("viewemps.jsp");
	mav.addObject("empList",empList);
	return mav;
	}
	@RequestMapping("/search")
	public ModelAndView getByEmail(@RequestParam String email) {
		Employee employee = service.getByEmail(email);
		ModelAndView mav;
		if(employee != null) {
			mav = new ModelAndView("viewemp.jsp");
			mav.addObject("employee",employee);
		}
		else {
			mav = new ModelAndView("search.jsp");
			mav.addObject("employee","Invalid Email");
		}
		return mav;
	}
	@RequestMapping("/emplogout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView("login.jsp");
		return mav;
		}
	@RequestMapping("/delete")
	public ModelAndView deleteById(@RequestParam int id) {
		service.deleteById(id);
		ModelAndView mav=new ModelAndView("findAll");
		return mav;
		}
	@RequestMapping("/edituser")
	public ModelAndView updateEmployee(@ModelAttribute Employee employee) {
		service.saveEmployee(employee);
		ModelAndView mav=new ModelAndView("findAll");
		return mav;
	}
	@RequestMapping("/view")
	public ModelAndView findById(@RequestParam String email ) {
		Employee employee=service.getByEmail(email);
		ModelAndView mav=new ModelAndView("viewprofile.jsp");
		mav.addObject("employee",employee);
		return mav;
		
	}
	
}