package web.study.lr.dockerapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("docker")
public class HelloWorldController {
    @Value("${spring.test-world}")
    private String value;
    @GetMapping(value = "hello/{name}")
    public String hello(@PathVariable String name){
        return value + " " + name;
    }
}
