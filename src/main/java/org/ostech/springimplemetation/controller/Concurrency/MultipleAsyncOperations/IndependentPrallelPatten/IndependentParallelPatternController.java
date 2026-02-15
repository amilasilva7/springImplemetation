package org.ostech.springimplemetation.controller.Concurrency.MultipleAsyncOperations.IndependentPrallelPatten;

import org.ostech.springimplemetation.service.JavaCore.ConcurrencyAPI.ConcurrencyPatterns.IndependentParallelPattern.IndependentParallelPatternService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/concurrent/independent-dependent-pattern")
@RestController
public class IndependentParallelPatternController {

    IndependentParallelPatternService independentParallelPatternService;

    IndependentParallelPatternController(IndependentParallelPatternService independentParallelPatternService) {
        this.independentParallelPatternService = independentParallelPatternService;
    }

    @PostMapping()
    public String independentParallelPattern() {//newSingleThreadExecutor
        independentParallelPatternService.sendNotifications();
        return "Success";
    }
}
