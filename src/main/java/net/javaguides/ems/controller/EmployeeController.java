package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    //REST API for add employee
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto addEmployee = employeeService.addEmployee(employeeDto);
        return  new ResponseEntity<>(addEmployee, HttpStatus.CREATED);
    }

    //REST API for get employee
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id){
        EmployeeDto employeeDto = employeeService.getEmployee(id);
        return  new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    //REST API for all employee
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
      List<EmployeeDto>  allEmployee = employeeService.getAllEmployee();
      return new ResponseEntity<>(allEmployee, HttpStatus.OK);
    }

    //REST API for update employee
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
        EmployeeDto employee = employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    //REST API for delete employee
    @DeleteMapping("{id}")
    public ResponseEntity<String>  deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("delete successfully !");
    }
}
