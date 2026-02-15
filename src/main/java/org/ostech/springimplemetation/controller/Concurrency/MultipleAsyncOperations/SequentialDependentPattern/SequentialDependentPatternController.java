package org.ostech.springimplemetation.controller.Concurrency.MultipleAsyncOperations.SequentialDependentPattern;

import org.ostech.springimplemetation.service.JavaCore.ConcurrencyAPI.ConcurrencyPatterns.SequentialDependentPattern.SequentialDependentPattern;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/concurrent/sequential-dependent-pattern")
@RestController
public class SequentialDependentPatternController {

    SequentialDependentPattern sequentialDependentPattern;

    SequentialDependentPatternController(SequentialDependentPattern sequentialDependentPattern) {
        this.sequentialDependentPattern = sequentialDependentPattern;
    }

    @PostMapping()
    public String SequentialDependent() {//newSingleThreadExecutor
        sequentialDependentPattern.sendEmailSequentialDependent();
        return "Success";
    }
}
