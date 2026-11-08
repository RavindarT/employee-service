package com.example.employeeservice;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    public EmployeeService(EmployeeRepository repository) { this.repository = repository; }
    @Transactional(readOnly = true)
    public List<Employee> list() { return repository.findAll(); }
    @Transactional(readOnly = true)
    public Employee get(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found")); }
    @Transactional
    public Employee create(EmployeeRequest request) {

        Employee record = new Employee();
        record.setName(request.name());
        record.setAge(request.age());
        record.setLocation(request.location());
        return repository.save(record);
    }
    @Transactional
    public Employee update(Long id, EmployeeRequest request) {
        Employee record = get(id);
        record.setName(request.name());
        record.setAge(request.age());
        record.setLocation(request.location());
        return repository.save(record);
    }
    @Transactional
    public void delete(Long id) { repository.delete(get(id)); }
}
