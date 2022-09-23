package org.kgonia.jetty.loom.showcase.controller;


import org.kgonia.jetty.loom.showcase.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private final Random random = new Random();

    @GetMapping("/blocking/greeting")
    public Greeting blockingGreeting(@RequestParam(value = "name", defaultValue = "World") String name) throws InterruptedException {
        var duration = random.nextLong(1000, 5000);
        Thread.sleep(duration);
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/non-blocking/greeting")
    public Greeting nonblockingGreeting(@RequestParam(value = "name", defaultValue = "World") String name) throws InterruptedException {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/non-blocking/numbers")
    public Long nonblockingAddition() throws InterruptedException {
        return (long) random.nextInt() + random.nextInt();
    }
}
