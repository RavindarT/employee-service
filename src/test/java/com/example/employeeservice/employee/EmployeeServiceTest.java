package com.example.employeeservice.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeServiceTest {

    private EmployeeService service;

    @BeforeEach
    void setUp() {
        service = new EmployeeService();
    }

    @Test
    void createAndFindEmployee() {
        Employee created = service.create(new EmployeeRequest("Asha", "asha@example.com", "IT"));

        assertEquals(1L, created.id());
        assertEquals("Asha", service.findById(created.id()).name());
        assertEquals(1, service.findAll().size());
    }

    @Test
    void updateEmployee() {
        Employee created = service.create(new EmployeeRequest("Asha", "asha@example.com", "IT"));

        Employee updated = service.update(created.id(),
                new EmployeeRequest("Asha R", "asha.r@example.com", "Engineering"));

        assertEquals("Engineering", updated.department());
        assertEquals("asha.r@example.com", service.findById(created.id()).email());
    }

    @Test
    void deleteEmployee() {
        Employee created = service.create(new EmployeeRequest("Asha", "asha@example.com", "IT"));

        service.delete(created.id());

        assertTrue(service.findAll().isEmpty());
        assertThrows(EmployeeNotFoundException.class, () -> service.findById(created.id()));
    }

    @Test
    void missingEmployeeReturnsNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> service.findById(99L));
        assertThrows(EmployeeNotFoundException.class, () -> service.delete(99L));
        assertThrows(EmployeeNotFoundException.class, () -> service.update(99L,
                new EmployeeRequest("Asha", "asha@example.com", "IT")));
    }
}
