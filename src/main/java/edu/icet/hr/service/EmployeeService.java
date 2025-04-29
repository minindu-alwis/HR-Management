package edu.icet.hr.service;

import edu.icet.hr.dto.EmployeeRequestDTO;
import edu.icet.hr.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployees();
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeDTO);
    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO employeeDTO);
    void deleteEmployee(Long id);
}
