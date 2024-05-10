package com.danit.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Controller
public class BookController {
    @Value("${welcome.message}")
    private String message;

    private List<Book> books = Arrays.asList(
            new Book("Java 8", "Horstman"),
            new Book("Java 17", "Horstman, Kornell")
    );

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("name", message);
        model.addAttribute("books", books);

        return "welcome"; //view
    }

    // /hello?name=kotlin
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String mainWithParam(@RequestParam(name = "name", required = false, defaultValue = "")
                    String name, Model model) {

        model.addAttribute("name", name);
        if (name == null) {
            throw new RuntimeException("null");
        }

        return "welcome"; //view
    }

    @ExceptionHandler({ RuntimeException.class })
    public void handleException(Exception ex) {
        System.err.println(ex);
    }
}
