package gr.aueb.cf.springauthsession1.controller;

import gr.aueb.cf.springauthsession1.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final IUserService userService;

    @GetMapping("/login")
    public String login(Model model, Principal principal, HttpServletRequest request) {

        if (principal == null) return "login";

        var user = userService.getUserByUsername(principal.getName());
        String role = user.getRole().name();

        if (role.equals("TEACHER")) {
            return "redirect:/teachers/dashboard";
        }

        if (role.equals("STUDENT")) {
            return "redirect:/students/dashboard";
        }

        return "redirect:/dashboard";
        //return principal == null ? "login" : "redirect:/dashboard";
    }

    @GetMapping(path = { "/" })
    String root(Model model, Principal principal, HttpServletRequest request) throws Exception {
        //return principal == null ? "login" : "redirect:/dashboard";
        return "redirect:/login";
    }
}
