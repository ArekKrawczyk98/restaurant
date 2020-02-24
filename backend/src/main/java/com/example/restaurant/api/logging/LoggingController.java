package com.example.restaurant.api.logging;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("start")
@RestController
public class LoggingController {

    @PostMapping
    public Response canPass(@RequestBody Logging logging){

        if (logging.canPass()){
            return new Response(true);
        }
        else {
            return new Response(false);
        }

    }
}
