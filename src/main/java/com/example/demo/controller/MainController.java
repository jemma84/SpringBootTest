package com.example.demo.controller;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.example.demo.service.SectorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

        private final SectorService sectorService;

        private final PersonService personService;

        private final AuthenticationManager authenticationManager;

        @GetMapping("/userSectors")
        public String getUserSectors(Model model) {
                model.addAttribute("person", personService.getCurrentPerson());
                model.addAttribute("sectorList", sectorService.getSectorList());
                return "userSectors";
        }

        @PostMapping("/save")
        public String savePersonData(@ModelAttribute("person") @Valid PersonDTO person) {
                personService.updatePersonData(person);
                return "redirect:/";
        }

        @PostMapping("/registerUser")
        public String create(@ModelAttribute("person") @Valid PersonDTO person, HttpServletRequest request) {
                personService.savePerson(person);

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                    person.getEmail(), person.getPassword());
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authenticationManager.authenticate(authentication));

                HttpSession session = request.getSession(true);
                session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
                return "redirect:/userSectors";
        }
}
