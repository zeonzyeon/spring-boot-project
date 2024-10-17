package com.estsoft.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PageController { // 구조: MVC
    // Person   /thymeleaf/example
    @GetMapping("/thymeleaf/example") // controller
    public String show(Model model) { // model
        Person person = new Person();
        person.setId(1L);
        person.setName("김자바");
        person.setAge(20);
        person.setHobbies(List.of("달리기", "줄넘기", "복싱", "운동"));

        model.addAttribute("person", person);
        model.addAttribute("today", LocalDateTime.now());

        return "examplePage"; // view
    }
}
