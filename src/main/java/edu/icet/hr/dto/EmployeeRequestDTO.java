package edu.icet.hr.dto;

import edu.icet.hr.util.Department;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRequestDTO {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$")
    @Size(max = 100)
    private String name;

    @NotBlank
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @NotNull(message = "Department is required")
    private Department department;
}
