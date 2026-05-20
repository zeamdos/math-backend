package com.mathapp;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/math")
public class MathController {

    private final MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @PostMapping("/solve")
    public Map<String, String> solve(@RequestBody Map<String, String> requestPayload) {
        String problemText = requestPayload.get("problem");
        String stepByStepSolution = mathService.processProblem(problemText);
        
        // Return structured JSON response back to the client web browser
        return Map.of("solution", stepByStepSolution);
    }
}