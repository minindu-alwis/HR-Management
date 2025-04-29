package edu.icet.hr.controller;

import edu.icet.hr.dto.EmployeeRequestDTO;
import edu.icet.hr.dto.EmployeeResponseDTO;
import edu.icet.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

}
