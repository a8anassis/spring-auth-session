package gr.aueb.cf.springauthsession1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "dashboard";
    }
}


