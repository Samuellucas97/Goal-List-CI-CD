package br.ufrn.goallist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class RedirectController {
    @GetMapping(value = "")
    public ModelAndView redirectToDocPage() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }

    @GetMapping(value = "apidocs")
    public ModelAndView redirectToApiPage() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }
}
