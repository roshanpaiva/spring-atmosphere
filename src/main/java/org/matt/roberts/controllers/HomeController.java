package org.matt.roberts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A simple Spring MVC controller.
 */
@Controller
public class HomeController {

    /**
     * Redirect / requests to the comet example.
     * @return
     */
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/comet";
    }
}
