package com.view.springweb.services;


import com.view.springweb.configs.EmployeeConfig;
import com.view.springweb.dto.EmployeeDTO;
import com.view.springweb.entities.EmployeeEntity;
import com.view.springweb.repositories.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelmapper;


    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelmapper) {
        this.employeeRepository = employeeRepository;
        this.modelmapper = modelmapper;
    }

    public Optional<EmployeeDTO> getEmployeeId(Long id) {

        Optional<EmployeeEntity> empEntity=employeeRepository.findById(id);
        return empEntity.map(empEntity1-> modelmapper.map(empEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {

        List<EmployeeEntity> empEntities=employeeRepository.findAll();
        return empEntities
                 .stream()
                 .map(employeeEntity -> modelmapper.map(employeeEntity,EmployeeDTO.class))
                 .collect(Collectors.toList());
    }
   public boolean existsById(Long id)
   {
       if(employeeRepository.existsById(id))
           return true;
       return false;
   }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputName) {

        EmployeeEntity empEntity=modelmapper.map(inputName,EmployeeEntity.class);
        EmployeeEntity toSaveEntity=employeeRepository.save(empEntity);
        return modelmapper.map(toSaveEntity,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO inputName, Long id) {
        if(!existsById(id))
            return null;
        EmployeeEntity employeeEntity=modelmapper.map(inputName,EmployeeEntity.class);
        employeeEntity.setId(id);
        return modelmapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);

    }

    public Boolean deleteEmployeeById(Long id) {
        if(!existsById(id))
            return false;
        employeeRepository.deleteById(id);
        return true;

    }

    public EmployeeDTO updatePartialById(Map<String, Object> updates, Long id) {
        EmployeeEntity employeeEntity=employeeRepository.findById(id).orElse(null);
        updates.forEach((field,value)->{
            Field fieldToUpdate= ReflectionUtils.findField(EmployeeEntity.class,field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate,employeeEntity,value);
        });
        return modelmapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);

    }
}
