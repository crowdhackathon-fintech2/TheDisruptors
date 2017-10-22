package com.gio.service;

import com.gio.service.vo.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/user/{name}")
    public User greeting(@PathVariable("name") String name) {
        return new User(counter.incrementAndGet(),
                String.format(template, name));
    }


}
