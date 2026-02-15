package org.ostech.springimplemetation.controller.Concurrency.SharedData.SimpleCounter;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/concurrent/simple-counter")
@RestController
public class SimpleCounterPatternController {

    @PostMapping()
    public String SequentialDependent() {

        return "Success";
    }
}
