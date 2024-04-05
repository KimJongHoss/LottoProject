package hg.jh.luko6.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class index2htmlController {

    @GetMapping("/hello")
    public String helloController(){

        return "/index2";
    }


}
