package com.kgfsl.springbootconfig.controller;

import com.kgfsl.springbootconfig.model.Employee;
import com.kgfsl.springbootconfig.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = { "/employee1" })
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = { "all" })
    public String index(Model model) {
        System.out.println(employeeRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee/index";
    }

    @RequestMapping(value = "create")
    public String create() {
        return "redirect:all";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:all";   
    }

   @RequestMapping(value = "edit")
    public String edit(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("employee", employeeRepository.findOne(id));
          model.addAttribute("employees", employeeRepository.findAll());
        return "employee/index";
    }


    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:all";
    }

    @RequestMapping(value = "{id}")
    public String Delete(@PathVariable("id") Integer id) {
        employeeRepository.delete(id);
        return "redirect:all";
    }
    
}