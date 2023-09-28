package pl.zajavka.online_medical_clinic.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class HomeController {

    static final String HOME = "/";

//    @GetMapping(HOME)
    @RequestMapping(value = HOME, method = RequestMethod.GET)
    public String homePage(){
        return "home";
    }
}
