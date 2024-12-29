package com.view.springweb.controllers;

import com.view.springweb.dto.EmployeeDTO;
import com.view.springweb.entities.EmployeeEntity;
import com.view.springweb.services.EmployeeService;
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
    public EmployeeDTO getEmployeeById(@PathVariable Long id)
    {

        return employeeService.getEmployeeId(id);
    }
    @GetMapping("")
    public List<EmployeeDTO> getAllEmployee()
    {

        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputName)
    {

        return employeeService.createNewEmployee(inputName);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO inputName,@PathVariable Long id )
    {
         return employeeService.updateEmployeeById(inputName,id);
    }
   @DeleteMapping("/{id}")
    public boolean deleteEmployeeById(@PathVariable Long id)
   {
       return employeeService.deleteEmployeeById(id);
   }

   @PatchMapping("/{id}")
    public EmployeeDTO updatePartialById(@RequestBody Map<String, Object>updates , @PathVariable Long id)
   {
       return employeeService.updatePartialById(updates,id);
   }

}
