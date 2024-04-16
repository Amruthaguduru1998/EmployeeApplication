package com.demo.emplyeeCrud.modal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private  Long emp_id;

    @Column(name = "emp_name")
    private  String emp_name;

    @Column(name = "emp_age")
    private  int emp_age;

    @Column(name = "emp_gender")
    private String emp_gender;

    @Column(name = "emp_salary")
    private  double emp_salary;

    @Column(name = "emp_city")
    private  String emp_city;

}
