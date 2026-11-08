package com.example.employeeservice;
import jakarta.persistence.*;
@Entity
@Table(name = "employee")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "location", nullable = false, length = 255)
    private String location;
    public Long getEmpId() { return empId; }
    public void setEmpId(Long value) { empId = value; }
    public String getName() { return name; }
    public void setName(String value) { name = value; }
    public Integer getAge() { return age; }
    public void setAge(Integer value) { age = value; }
    public String getLocation() { return location; }
    public void setLocation(String value) { location = value; }
}
