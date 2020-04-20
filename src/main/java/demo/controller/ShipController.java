package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShipController {
    @RequestMapping("/ship")
    public String particle() {
        System.out.println("ship........");
        return "ship";
    }
}
