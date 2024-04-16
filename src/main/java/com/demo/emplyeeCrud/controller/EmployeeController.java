package com.demo.emplyeeCrud.controller;

import com.demo.emplyeeCrud.modal.Employee;
import com.demo.emplyeeCrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Value("${server.port}")
    private  String svrPort;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/server")
    public void getmethod(){
        System.out.println(svrPort);
    }

    @PostMapping("/employee/add")
    public String addEmployee(@RequestBody  Employee employee){
        employeeRepository.save(employee);
        return " employee record created  successfully";
    }

    @GetMapping("/employee/all")
    public ResponseEntity<List<Employee>> getAllEmployee(Employee employee){
        List<Employee> emp=new ArrayList<>();
        employeeRepository.findAll().forEach(emp::add);
        return new ResponseEntity<List<Employee>>(emp, HttpStatus.OK);
    }

    @GetMapping("/employee/{empid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid){
        Optional<Employee> emp=employeeRepository.findById(empid);
        if(emp.isPresent())
        return new ResponseEntity<Employee>(emp.get(),HttpStatus.FOUND);
        else
        return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/employee/{empid}")
    public String updateEmployee(@PathVariable long empid,@RequestBody Employee employee){
        Optional<Employee> emp=employeeRepository.findById(empid);
        if(emp.isPresent()){
            emp.get().setEmp_name(employee.getEmp_name());
            emp.get().setEmp_age(employee.getEmp_age());
            emp.get().setEmp_gender(employee.getEmp_gender());
            emp.get().setEmp_city(employee.getEmp_city());
            emp.get().setEmp_salary(employee.getEmp_salary());
            emp.get().setEmp_name(employee.getEmp_name());
            employeeRepository.save(emp.get());
            return "employee details against Id " +empid+ "updated";
        }else {
            return "employee details does not exits empid" +empid ;
        }
    }

    @DeleteMapping("employee/{empid}")
    public  String deleteEmployee(@PathVariable long empid){
        employeeRepository.deleteById(empid);
        return "delete record successfull "  +empid ;
    }

    @DeleteMapping("employee")
    public  String deleteALlEmployee(){
        employeeRepository.deleteAll();
        return "Employee record delete successfully ";
    }

}
