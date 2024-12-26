package com.digis01.DGarciProgramacionNCapasNoviembre2024.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demoapi")
public class DemoRestController {
    
    
    //Retorna un index 
    
    @GetMapping    
    public String HolaMundo(@RequestParam String nombre){
        return "Hola mundo, soy " + nombre;
    }
    
}
