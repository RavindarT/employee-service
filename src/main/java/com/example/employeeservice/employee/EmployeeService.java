package com.example.employeeservice.employee;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    // Demo-only storage: data is lost when the application restarts.
    private final Map<Long, Employee> employees = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<Employee> findAll() {
        return employees.values().stream()
                .sorted((left, right) -> left.id().compareTo(right.id()))
                .toList();
    }

    public Employee findById(Long id) {
        Employee employee = employees.get(id);
        if (employee == null) {
            throw new EmployeeNotFoundException(id);
        }
        return employee;
    }

    public Employee create(EmployeeRequest request) {
        Long id = nextId.getAndIncrement();
        Employee employee = new Employee(id, request.name(), request.email(), request.department());
        employees.put(id, employee);
        return employee;
    }

    public Employee update(Long id, EmployeeRequest request) {
        Employee updated = employees.computeIfPresent(id, (key, original) ->
                new Employee(key, request.name(), request.email(), request.department()));
        if (updated == null) {
            throw new EmployeeNotFoundException(id);
        }
        return updated;
    }

    public void delete(Long id) {
        if (employees.remove(id) == null) {
            throw new EmployeeNotFoundException(id);
        }
    }
}
