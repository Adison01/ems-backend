package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
     Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
    Employee savedEmployee =  employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
      Employee employee =   employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id not found : " + id));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee(){
        return employeeRepository.findAll().stream().map((employee -> EmployeeMapper.mapToEmployeeDto(employee))).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee getEmployee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id not available : " + id));
        getEmployee.setFirstName(employeeDto.getFirstName());
        getEmployee.setLastName(employeeDto.getLastName());
        getEmployee.setEmail(employeeDto.getEmail());
        Employee updatedEmployee = employeeRepository.save(getEmployee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id){
       Employee employee =  employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("id not found : " + id));
        employeeRepository.delete(employee);
    }
}
