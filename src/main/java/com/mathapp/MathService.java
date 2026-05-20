package com.mathapp;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    private final ChatLanguageModel model;

    public MathService() {
        // Automatically fetches OPENAI_API_KEY from system environment variables safely
        this.model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .temperature(0.3)
                .build();
    }

    public String processProblem(String mathProblem) {
        if (mathProblem == null || mathProblem.trim().isEmpty()) {
            return "Error: Problem description cannot be blank.";
        }
        
        String structuralPrompt = """
            You are a rigorous, professional mathematics tutor. 
            Solve the following question step-by-step. 
            Clearly isolate the logical steps and state any specific mathematical principles applied.
            
            Question: %s
            """.formatted(mathProblem);

        return model.generate(structuralPrompt);
    }
}
