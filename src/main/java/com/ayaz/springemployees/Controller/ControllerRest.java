package com.ayaz.springemployees.Controller;

import com.ayaz.springemployees.Model.Employee;
import com.ayaz.springemployees.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerRest {

    @Autowired
    public   EmployeeService employeeService;

    public ControllerRest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/api/employees")
    public List<Employee> getEmployees(){
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    @GetMapping("/api/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name = "employeeId") Long employeeId){
        return employeeService.getEmployee(employeeId);
    }
    @PostMapping("/api/employees")
    public void saveEmployee(Employee employee){
        employeeService.saveEmployee(employee);
    }
    @DeleteMapping("/api/employees/{employeeId}")
    public void deleteEmployee (@PathVariable(name = "employeeId") Long employeeId){
        employeeService.deleteEmployee(employeeId);
    }

    //Sor
    @PutMapping("/api/employees/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable(name = "employeeId") Long employeeId){
       Employee employee1 = employeeService.getEmployee(employeeId);
       if (employee1 != null){
           employeeService.updateEmployee(employee);
       }
    }
}
