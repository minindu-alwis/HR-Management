package edu.icet.hr.service.impl;

import edu.icet.hr.dto.EmployeeRequestDTO;
import edu.icet.hr.dto.EmployeeResponseDTO;
import edu.icet.hr.entity.Employee;
import edu.icet.hr.exception.EmailAlreadyExistsException;
import edu.icet.hr.exception.ResourceNotFoundException;
import edu.icet.hr.repository.EmployeeRepository;
import edu.icet.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

}
