package com.ayaz.springemployees.Controller;

import com.ayaz.springemployees.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

    @Autowired
    private final EmployeeService employeeService;

    public JspController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/jsp-employees")
    public String getEmployees(Model model){
        model.addAttribute("employees",employeeService.retrieveEmployees());
        return "jsp/employee";
    }
}
