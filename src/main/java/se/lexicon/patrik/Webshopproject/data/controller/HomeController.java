package se.lexicon.patrik.Webshopproject.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @RequestMapping(value="/home")
    public static String test() {
        return "index";
    }
}
