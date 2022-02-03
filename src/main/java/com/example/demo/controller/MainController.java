package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.example.demo.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {
        @Autowired
        private SectorService sectorService;

        @Autowired
        private PersonService personService;

        @GetMapping("/")
        public String getMainPage(Model model) {
                model.addAttribute("person", personService.getPerson());
                model.addAttribute("sectorList", sectorService.getSectorList());
                return "mainPage";
        }

        @PostMapping("/save")
        public String savePersonData(@ModelAttribute("person") Person person) {
                personService.savePerson(person);
                return "redirect:/";
        }
}
