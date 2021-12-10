package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TehController {

  @GetMapping(value = "/hello/{name}/")
  public String greetByName(@PathVariable String name) {
    return "Hello, " + name + "!";
  }

  @GetMapping(value = "/hello/")
  public String justGreet() {
    return "Hello!";
  }
}
