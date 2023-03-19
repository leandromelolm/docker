package com.company.project.controllers;

import com.company.project.entity.Person;
import com.company.project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PersonRepository repository;

    @GetMapping("/")
    public String showHome(String name, Model model) {
        //"😕"
        List<Person> person = repository.findAll();
        model = model.addAttribute("name", person);
        return "home";
    }

}
