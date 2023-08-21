package pl.zajavka.online_medical_clinic.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    static final String HOME = "/";

    @GetMapping(HOME)
    public String homePage(){
        return "home";
    }
}
