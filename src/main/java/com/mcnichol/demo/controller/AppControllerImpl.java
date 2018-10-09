package com.mcnichol.demo.controller;

import brave.Span;
import brave.Tracer;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Timed
class AppControllerImpl implements AppController {


    Logger log = LoggerFactory.getLogger(AppControllerImpl.class);

    private MeterRegistry meterRegistry;
    private Tracer tracer;

    @Autowired
    AppControllerImpl(MeterRegistry meterRegistry, Tracer tracer) {
        this.meterRegistry = meterRegistry;
        this.tracer = tracer;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        log.info("My Log Message");
        return new ResponseEntity<>("World", HttpStatus.OK);
    }

    @GetMapping("/password/hello")
    public ResponseEntity<String> passwordHello() {
        return new ResponseEntity<>("Protected World", HttpStatus.OK);
    }

    @Timed(value = "my.delay")
    @GetMapping("/delay/{time}")
    public ResponseEntity<String> takeTime(@PathVariable(value = "time") Integer time) throws InterruptedException {
        meterRegistry.gauge("micrometer.gauge", time);

        return new ResponseEntity<>(String.format("You waited %d seconds", time), HttpStatus.OK);
    }
}
