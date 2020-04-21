package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Simplest {
    @RequestMapping("/simplest")
    public String particle() {
        System.out.println("simplest........");
        return "simplest";
    }
}
