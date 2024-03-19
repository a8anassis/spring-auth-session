package gr.aueb.cf.springauthsession1.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = { RuntimeException.class })
    public String handleBadRequest(Model model, RuntimeException ex) {
        model.addAttribute("err", ex);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        model.addAttribute("err", e);
        return "error";
    }
}


