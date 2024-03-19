package gr.aueb.cf.springauthsession1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @GetMapping("/students/dashboard")
    public String dashboard() {
        return "/students/dashboard";
    }
}
