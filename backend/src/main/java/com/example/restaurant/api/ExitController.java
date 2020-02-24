package com.example.restaurant.api;

import com.example.restaurant.domain.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/end")
public class ExitController {

    private RestaurantService restaurantService;
    @Autowired
    private ApplicationContext applicationContext;

    public ExitController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public boolean endService(){
        return restaurantService.endService();
    }
    @GetMapping("/true")
    public void endServiceNow() throws IOException, InterruptedException {
        //TODO synchronize this
      /*  ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("sh","-c","fuser -k 4200/tcp");
        processBuilder.directory(new File(System.getProperty("user.dir")));
        Process process = processBuilder.start();
        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);*/
        SpringApplication.exit(applicationContext,() -> 0);

    }
}




