package com.digis01.DGarciProgramacionNCapasNoviembre2024.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Demo")
public class DemoController {
    
    @GetMapping
    public String HolaMundo(Model model){
        model.addAttribute("mensaje", "Hola mundo desde el controlador, soy alejandro");
        return "HolaMundo"; // archivo HTML
    }
    
    @GetMapping("/Saludo")
    public String HolaMundo2(Model model){
        model.addAttribute("mensaje", "Hola mundo ... :)");
        return "HolaMundo";
    }
    
    /*MVC
    Modelo
    Vista
    Controlador - Atrapa interacciones del usuario 
    */
    
    
    /*
    Java SE y Java EE
    Anotaciones en JAVA 
    Estereotipos
    Bootstrap (responsividad, Breackpoint)
    Thymeleaf 
        - Expression Language
    
    
    */
    
}
