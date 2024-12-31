package com.view.springweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO {



    Long id;
    @NotBlank(message="name should not be empty")
    String name;
    @Pattern(regexp = "^(WIPRO|CTS)$",message = "company is not avaliable")
    String companyName;
    @FutureOrPresent(message = "past date is not valid")
    LocalDate dateandTime;

    @AssertTrue(message = "employee should be active")
    @JsonProperty("isActive")
    boolean isActive;


}
