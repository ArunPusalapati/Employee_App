package com.view.springweb.controllers;

import com.view.springweb.dto.EmployeeDTO;
import com.view.springweb.entities.EmployeeEntity;
import com.view.springweb.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequestMapping("/employee")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    EmployeeController(EmployeeService employeeService)
    {
        this.employeeService=employeeService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable @Valid Long id)
    {
        Optional<EmployeeDTO> empDTO= employeeService.getEmployeeId(id);
        return empDTO
                .map(empDTO1->ResponseEntity.ok(empDTO1))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee()
    {

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputName)
    {

        EmployeeDTO empDTO=employeeService.createNewEmployee(inputName);
        return new ResponseEntity<>(empDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO inputName,@PathVariable @Valid Long id )
    {
         EmployeeDTO empDTO=employeeService.updateEmployeeById(inputName,id);
         return ResponseEntity.ok(empDTO);
    }
   @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable  Long id)
   {

       if(!employeeService.deleteEmployeeById(id)) return ResponseEntity.notFound().build();
       return ResponseEntity.ok(true);


   }

   @PatchMapping("/{id}")
    public EmployeeDTO updatePartialById(@RequestBody Map<String, Object>updates , @PathVariable Long id)
   {
       return employeeService.updatePartialById(updates,id);
   }

}
