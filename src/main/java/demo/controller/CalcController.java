package demo.controller;

import demo.model.CalcParam;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/calc-service")
public class CalcController {
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public int addGet(@RequestParam("a") int a, @RequestParam("b") int b) {
        return a + b;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public int addPost(@RequestBody CalcParam calcParam) {
        return calcParam.getA() + calcParam.getB();
    }

//    @RequestMapping(value = "/none", method = RequestMethod.POST)
//    public int PostNone() {
//        return 0;
//    }
//
//    @RequestMapping(value = "/addP", method = RequestMethod.POST)
//    public int addPostParam(@RequestParam("a") int a, @RequestParam("b") int b) {
//        return a + b;
//    }
}
