package com.tpi.gestion;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestionController {

    @GetMapping("/saludo") 
    public String holaMundo() {
        return "hola mundo";
    }

   
}