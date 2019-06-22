package com.scylla.demo.service;

import com.scylla.demo.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    ReactiveEmployeeRepository employeeRepository;

    public void initializeEmployees(List<Employee> employees) {
        Flux<Employee> savedEmployees = employeeRepository.saveAll(employees);
        savedEmployees.subscribe();
    }

    public Flux<Employee> getAllEmployees() {
        Flux<Employee> employees =  employeeRepository.findAll();
        return employees;
    }

    public Flux<Employee> getEmployeesFilterByAge(int age) {
        return employeeRepository.findByAgeGreaterThan(age);
    }

    public Mono<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }
}
