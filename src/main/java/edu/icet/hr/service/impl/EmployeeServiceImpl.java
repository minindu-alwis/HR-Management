package edu.icet.hr.service.impl;

import edu.icet.hr.dto.EmployeeRequestDTO;
import edu.icet.hr.dto.EmployeeResponseDTO;
import edu.icet.hr.entity.Employee;
import edu.icet.hr.exception.EmailAlreadyExistsException;
import edu.icet.hr.exception.ResourceNotFoundException;
import edu.icet.hr.repository.EmployeeRepository;
import edu.icet.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    final ModelMapper modelMapper;


    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeDTO) {
        if (employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Employee employee = convertToEntity(employeeDTO);
        return convertToDTO(employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO employeeDTO) {
        return employeeRepository.findById(id).map(employee -> {
            if (employeeRepository.existsByEmailAndIdNot(employeeDTO.getEmail(), id)) {
                throw new EmailAlreadyExistsException("Email already exists");
            }

            employee.setName(employeeDTO.getName());
            employee.setEmail(employeeDTO.getEmail());
            employee.setDepartment(employeeDTO.getDepartment());
            return convertToDTO(employeeRepository.save(employee));
        }).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeResponseDTO> searchByName(String name) {
        List<EmployeeResponseDTO> customerArrayList = new ArrayList<>();
        List<Employee> all = employeeRepository.findByName(name);
        all.forEach(customerEntity -> {
            customerArrayList.add(modelMapper.map(customerEntity, EmployeeResponseDTO.class));
        });
        return customerArrayList;
    }

    private Employee convertToEntity(EmployeeRequestDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        return employee;
    }

    private EmployeeResponseDTO convertToDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment().name());
        dto.setCreatedAt(employee.getCreatedAt());
        dto.setUpdatedAt(employee.getUpdatedAt());
        return dto;
    }
}
