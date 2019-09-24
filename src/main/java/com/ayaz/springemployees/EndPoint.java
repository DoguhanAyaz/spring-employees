package com.ayaz.springemployees;

import com.ayaz.WebServices.*;
import com.ayaz.springemployees.Model.Employee;
import com.ayaz.springemployees.Services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class EndPoint {
    private static final String NAMESPACE_URI = "http://www.ayaz.com/springemployees/WebServices";

    private final EmployeeService employeeService;

    public EndPoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest getEmployeeRequest) {
        GetEmployeeResponse getEmployeeResponse = new GetEmployeeResponse();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(employeeService.getEmployee(getEmployeeRequest.getEmployeeInfoId()), employeeInfo);
        getEmployeeResponse.setEmployeeInfo(employeeInfo);
        return getEmployeeResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEmployeeRequest")
    @ResponsePayload
    public GetAllEmployeeResponse getAllEmployeeResponse() {
        GetAllEmployeeResponse getAllEmployeeResponse = new GetAllEmployeeResponse();
        List<EmployeeInfo> employeeInfos = new ArrayList<>();
        List<Employee> employees = employeeService.retrieveEmployees();
        for (Employee entity : employees) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(entity, employee);
        }

        getAllEmployeeResponse.getEmployeeInfo().addAll(employeeInfos);
        return getAllEmployeeResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployeeResponse(@RequestPayload AddEmployeeRequest request) {
        AddEmployeeResponse response = new AddEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setSalary(request.getSalary());
        employee.setDepartment(request.getDepartment());
        if (!employeeService.retrieveEmployees().contains(employee)) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Content Already Available");
            response.setServiceStatus(serviceStatus);
        } else {
            EmployeeInfo employeeInfo = new EmployeeInfo();
            BeanUtils.copyProperties(employee, employeeInfo);
            serviceStatus.setStatusCode("SUCCES");
            serviceStatus.setMessage("Employee Added succesfully");
            response.setServiceStatus(serviceStatus);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployeeResponse(@RequestPayload DeleteEmployeeRequest request) {
        DeleteEmployeeResponse response = new DeleteEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        if (request.getId() == 0) {
            serviceStatus.setStatusCode("Fail");
            serviceStatus.setMessage("Employee not available");
        } else {
            employeeService.deleteEmployee(request.getId());
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Employee has been deleted");
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse updateEmployeeResponse(@RequestPayload UpdateEmployeeRequest request) {
        ServiceStatus serviceStatus = new ServiceStatus();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        Employee employee = new Employee();
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());
        employee.setName(request.getName());
        employee.setId(request.getId());
        BeanUtils.copyProperties(employee, employeeInfo);
        serviceStatus.setStatusCode("SUCCES");
        serviceStatus.setMessage("Updated succesfully");
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }


}
