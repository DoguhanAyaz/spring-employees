package com.ayaz.springemployees.Controller;

import com.ayaz.springemployees.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcController {

    @Autowired
    private final EmployeeService employeeService;

    public MvcController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @RequestMapping({"","/","/index","thymeleaf/index.html"})
    public String getEmployees(Model model){
        model.addAttribute("employees",employeeService.retrieveEmployees());
        return "thymeleaf/index";
    }
}
