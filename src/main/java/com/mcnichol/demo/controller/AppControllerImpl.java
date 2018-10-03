package com.mcnichol.demo.controller;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Timed
class AppControllerImpl implements AppController {

    private MeterRegistry meterRegistry;

    AppControllerImpl(MeterRegistry meterRegistry){
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("World", HttpStatus.OK);
    }

    @GetMapping("/password/hello")
    public ResponseEntity<String> passwordHello(){
        return new ResponseEntity<>("Protected World", HttpStatus.OK);
    }

    @Timed(value = "my.delay")
    @GetMapping("/delay/{time}")
    public ResponseEntity<String> takeTime(@PathVariable(value = "time") Integer time) throws InterruptedException {
        meterRegistry.gauge("micrometer.gauge", time);

        return new ResponseEntity<>(String.format("You waited %d seconds",time), HttpStatus.OK);
    }
}
