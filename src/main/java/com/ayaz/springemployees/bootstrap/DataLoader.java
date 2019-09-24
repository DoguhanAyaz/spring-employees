package com.ayaz.springemployees.bootstrap;

import com.ayaz.springemployees.Model.Employee;
import com.ayaz.springemployees.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final EmployeeService employeeService;

    @Autowired
    public DataLoader(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    public void initData() {
        Employee employee = new Employee();

        employee.setName("Serkan Cahyir");
        employee.setDepartment("Global");
        employee.setSalary(8000);

        employeeService.saveEmployee(employee);

        Employee employee1 = new Employee();
        employee1.setName("Doguhan Ayaz");
        employee1.setDepartment("Local");
        employee1.setSalary(6000);

        employeeService.saveEmployee(employee1);


        Employee employee2 = new Employee();
        employee2.setName("Nihat Onder");
        employee2.setDepartment("Local");
        employee2.setSalary(9000);

        employeeService.saveEmployee(employee2);

    }

}
