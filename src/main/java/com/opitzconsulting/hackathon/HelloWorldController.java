package com.opitzconsulting.hackathon;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.time.LocalDateTime;

@Controller("/hello")
public class HelloWorldController {

    @Get
    public HttpResponse<String> helloWorld() {
        return HttpResponse.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN)
                .body("Hello World. Time now: " + LocalDateTime.now());
    }
}
