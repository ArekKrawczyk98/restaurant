package com.example.easy.api.logging;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("start")
@RestController
public class LoggingController {

    @PostMapping
    public String canPass(@RequestBody Logging logging){

        if (logging.canPass()){
            return "pass";
        }
        else {
            return "cant pass";
        }

    }
}
