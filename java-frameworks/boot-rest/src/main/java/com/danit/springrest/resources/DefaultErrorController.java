package com.danit.springrest.resources;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        //do logging
        return "forward:/index.html";
    }
}